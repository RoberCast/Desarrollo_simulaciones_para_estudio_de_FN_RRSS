package fakeNewsSpreadSim;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

/**
 * Represents the Fake News Spread Simulation builder.
 * 
 * @author  Roberto Castillejo Embid
 * @version PFG II - SEPTEMBER 2025.
 */
public class FNSpreadSimBuilder implements ContextBuilder<Object> 
{
	private int numNodes;		// A counter for vertices in the network
	private int numSeeds;		// A counter for seeds in the network
	private int numEdges;		// A counter for edges in the network
	
	/**
	 * Default constructor for FNSpreadSimBuilder.
	 */
	public FNSpreadSimBuilder()
	{
		numNodes = 0;
		numSeeds = 0;
		numEdges = 0;
	}
	
	/**
	 * Builds the context of the simulation.
	 * 
	 * @param context	A Context object.
	 */
	@Override
	public Context<Object> build(Context<Object> context) 
	{
		context.setId("FakeNewsSpreadSim");
		
		// Parameterizes and stores the number of agents of each type and the fixed statistics chosen in runtime
		Parameters params = RunEnvironment.getInstance().getParameters();
		AgentNetworkParams anParams = new AgentNetworkParams(
				params.getInteger("susceptible_count"),
				params.getInteger("believer_count"),
				params.getInteger("factChecker_count")
		);
		
		ProbabilityParams pParams = new ProbabilityParams(
				params.getDouble("cred_hoax"),
				params.getDouble("spreading_rate"),
				params.getDouble("verifying_prob"),
				params.getDouble("forgetting_rate")
		);

		int initialVertices = 4;	// The number of initial vertices or seeds to generate the network
		
		checkParameters(anParams, pParams);

		// Asigns the state of the initial vertices and build the JUNG network
		assignSeedTypes(anParams, initialVertices);
		JungNetworkGenerator generator = new JungNetworkGenerator(anParams);		
		generator.getGenerator().evolveGraph(anParams.getSCount() + anParams.getBCount() + anParams.getFcCount() - initialVertices);
		Graph<Agent, Integer> graph = generator.getGenerator().create();
		
		// Creates the space and grid of the simulation
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace("space", context, new RandomCartesianAdder<Object>(), new repast.simphony.space.continuous.WrapAroundBorders(), 100, 100);
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>(new WrapAroundBorders(), new SimpleGridAdder<Object>(), true, 100, 100));
		
		// Adds the seeds and the agents to the context
		Set<Agent> seedAgents = generator.getInitialSeedVertex();	
		addSeedsToContext(seedAgents, context);
		connectSeeds(seedAgents, graph);
		addAgentsToContext(graph, context);
		
		// Creates a network builder to build a network in the Repast Simphony context, in other words, the network is registered in the simulation engine to its manipulation and visualization
		NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>("Social network", context, false);
		Network<Object> network = netBuilder.buildNetwork();
		
		addJungEdgesToRepastNetwork(graph, context, network);
		moveAgentsToGrid(context, space, grid);

	    AgentScheduler aScheduler = new AgentScheduler(context, network, pParams);
	    context.add(aScheduler); 
	    
	    printNetworkInformation(context, numNodes, numSeeds, numEdges);
	    resetCounters();
	    
	    return context;
	}
	
	/**
	 * Checks if the parameters were introduced properly.
	 * 
	 * @param anParams		An AgentNetworkParams object.
	 * @param pParams		A ProbabilityParams object.
	 */
	private void checkParameters(AgentNetworkParams anParams, ProbabilityParams pParams)
	{
		checkAgentCounts(anParams.getSCount(), anParams.getBCount(), anParams.getFcCount());
		checkStatistics(pParams.getCredibilityHoax(), "Hoax Credibility", false);
		checkStatistics(pParams.getSpreadingRate(), "Spreading Rate", true);
		checkStatistics(pParams.getVerifyingProbability(), "pVerify", true);
		checkStatistics(pParams.getForgettingRate(), "pForget", true);
	}
	
	/**
	 * Adds the seeds to the Repast Simphony context.
	 * 
	 * @param seedAgents	A set of seeds.
	 * @param context		A context object.
	 */
	private void addSeedsToContext(Set<Agent> seedAgents, Context<Object> context)
	{
		for(Agent seed : seedAgents) {
			if(!context.contains(seed)) {
				context.add(seed);
				numSeeds++;	// Adds the seed to the seed counter
			}
		}
	}
	
	/**
	 * Connects the seeds to each other.
	 * 
	 * @param seedAgents	A set of seeds.
	 * @param graph			A graph object.
	 */
	private void connectSeeds(Set<Agent> seedAgents, Graph<Agent, Integer> graph)
	{
		List<Agent> seeds = new ArrayList<>(seedAgents);
		int edgeID = graph.getEdgeCount();  // Gets the current number of edges in the graph, edgeID stores the ID for the edge to add

		for (int i = 0; i < seeds.size(); i++) {
		   for (int j = i + 1; j < seeds.size(); j++) {
		        graph.addEdge(edgeID++, seeds.get(i), seeds.get(j));
		    }
		}
	}
	
	/**
	 * Adds agents to the Repast Simphony context.
	 * 
	 * @param graph		A graph object.
	 * @param context	A context object.
	 */
	private void addAgentsToContext(Graph<Agent, Integer> graph, Context<Object> context)
	{
		for (Agent agent : graph.getVertices()) {
			if(!context.contains(agent)) {
				context.add(agent);
				numNodes++;	
			}
		}
	}
	
	/**
	 * Adds JUNG edges to the Repast Simphony Network.
	 * 
	 * @param graph		A graph object.
	 * @param context	A context object.
	 * @param network	A network object.
	 */
	private void addJungEdgesToRepastNetwork(Graph<Agent, Integer> graph, Context<Object> context, Network<Object> network)
	{
		for(Integer edge : graph.getEdges()) {
			Pair<Agent> endpoints = graph.getEndpoints(edge);
			Agent source = endpoints.getFirst();
			Agent target = endpoints.getSecond();
			
			// Ensures that the source vertex and destination vertex are in the context
			if(context.contains(source) && context.contains(target)) {
				network.addEdge(source, target);
				numEdges++;	
			}
		}
	}
	
	/**
	 * Move agents to the Repast Simphony Grid.
	 * 
	 * @param context		A context object.
	 * @param space			A space object.
	 * @param grid			A grid object.
	 */
	private void moveAgentsToGrid(Context<Object> context, ContinuousSpace<Object> space, Grid<Object> grid)
	{
		for(Object o : context) {
			NdPoint pt = space.getLocation(o);
			grid.moveTo(o, (int)pt.getX(), (int)pt.getY());
		}
	}
	
	/**
	 * Checks if the fixed statistics introduced by parameter are correct.
	 * 
	 * @param value				A decimal value for statistics.
	 * @param statistics		The type of statistics as a String.
	 * @param oneIsIncluded		True if the one number is included, false otherwise.
	 */
	private void checkStatistics(Double value, String statistics, boolean oneIsIncluded)
	{
		if(oneIsIncluded) {
			if(value < 0 || value > 1) {
				resetConfig("The value in " + statistics + " must be in the range [0,1].");
			}
		}
		else {
			if(value < 0 || value >= 1) {
				resetConfig("The value in " + statistics + " must be in the range [0,1).");
			}
		}
	}
	
	/**
	 * Checks if the number of agents introduced by parameter are correct.
	 * 
	 * @param sCount	The number of Susceptible agent introduced by parameter.
	 * @param bCount	The number of Believer agent introduced by parameter.
	 * @param fcCount	The number of FactChecker agent introduced by parameter.
	 */
	private void checkAgentCounts(int sCount, int bCount, int fcCount)
	{
		if(sCount < 0 || bCount < 0 || fcCount < 0) {
			resetConfig("The number of agents cannot be a negative number.");
		}
		
		if((sCount + bCount + fcCount) < 100) {
			resetConfig("The total number of agents must be at least 100.");
		}
		
		if((sCount > 0 && bCount == 0 && fcCount == 0) ||
				(sCount == 0 && bCount > 0 && fcCount == 0) ||
				(sCount == 0 && bCount == 0 && fcCount > 0)) {
			resetConfig("At least two agent types must be greater than 0.");
		}
	}
	
	/**
	 * Assigns agent types to seeds and subtracts those seeds from the total number of agents to be created in the network.
	 * 
	 * @param anParams				An AgentNetworkParams object.
	 * @param initialVertices		The initial number of vertices of the network (seeds).
	 */
	private void assignSeedTypes(AgentNetworkParams anParams, int initialVertices)
	{
		for(int i = 0; i < initialVertices; i++) {
			if(anParams.getSCount() >= anParams.getBCount() && anParams.getSCount() >= anParams.getFcCount()) {
				anParams.setSSeed(anParams.getSSeed() + 1);
				anParams.setSCount(anParams.getSCount() - 1);
			}
			else if(anParams.getBCount() >= anParams.getSCount() && anParams.getBCount() >= anParams.getFcCount()) {
				anParams.setBSeed(anParams.getBSeed() + 1);
				anParams.setBCount(anParams.getBCount() - 1);
			}
			else if(anParams.getFcCount() >= anParams.getSCount() && anParams.getFcCount() >= anParams.getBCount()) {
				anParams.setFcSeed(anParams.getFcSeed() + 1);
				anParams.setFcCount(anParams.getFcCount() - 1);
			}
		}
	}
	
	/**
	 * Throws an exception if the configuration is not allowed and informs the user how to enter the parameters.
	 * 
	 * @param text		Text information for the user.
	 */
	private void resetConfig(String text)
	{
		JOptionPane.showMessageDialog(null, text + ". Please, try again", "CONFIGURATION NOT ALLOWED", JOptionPane.ERROR_MESSAGE);
		throw new IllegalArgumentException("\u001B[31m [CONFIGURATION NOT ALLOWED] \u001B[34m" + text + " \u001B[30m");
	}
	
	/**
	 * Prints network information: nodes, seeds, edges, and total agents in context.
	 * 
	 * @param context		A context.
	 * @param numNodes		The number of vertices.
	 * @param numSeeds		The number of seeds.
	 * @param numEdges		The number of edges.
	 */
	private void printNetworkInformation(Context<Object> context, int numNodes, int numSeeds, int numEdges)
	{
		int totalAgentsInContext = 0;
		
		for(Object o : context) {
			if(o instanceof Agent) {
				totalAgentsInContext++;
			}
		}
		
		System.out.println("Nodes, seeds and edges added to the context: [" + numNodes + ", " + numSeeds + ", " + numEdges + "]");
		System.out.println("Total agents in context: " + totalAgentsInContext);
	}
	
	/**
	 * Resets the vertices, seeds and edges counters.
	 */
	private void resetCounters()
	{
		numNodes = 0;
		numSeeds = 0;
		numEdges = 0;
	}
}
