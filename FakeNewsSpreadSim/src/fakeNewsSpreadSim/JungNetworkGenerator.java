package fakeNewsSpreadSim;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections15.Factory;
import edu.uci.ics.jung.algorithms.generators.random.BarabasiAlbertGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * Represents a network generator based in the JUNG library.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class JungNetworkGenerator 
{
	private BarabasiAlbertGenerator<Agent, Integer> generator;		// A Barabasi-Albert network generator
	private Set<Agent> initialSeeds;								// A set with the initial vertices of the Barabasi-Albert network
	
	/**
	 * Default constructor for JungNetworkGenerator.
	 * 
	 * @param anParams	An AgentNetworkParams object.
	 */
	public JungNetworkGenerator(AgentNetworkParams anParams)
	{
		initialSeeds = initialSeedVertex(anParams);
		generator = new BarabasiAlbertGenerator<Agent, Integer>(createGraphFactory(), createVertexFactory(anParams), createEdgeFactory(), initialSeeds.size(), 3, new HashSet<Agent>(initialSeeds));
	}
	
	/**
	 * Gets a Barabasi-Albert network generator.
	 * 
	 * @return	A Barabasi-Albert network generator.
	 */
	public BarabasiAlbertGenerator<Agent, Integer> getGenerator()
	{
		return generator;
	}
	
	/**
	 * Gets a Set with the initial vertices of the Barabasi-Albert network.
	 * 
	 * @return		A Set with the initial vertices of the Barabasi-Albert network.
	 */
	public Set<Agent> getInitialSeedVertex()
	{
		return initialSeeds;
	}
	
	/**
	 * Sets a Barabasi-Albert network generator passed as a parameter.
	 * 
	 * @param generator		A Barabasi-Albert network generator.
	 */
	public void setGenerator(BarabasiAlbertGenerator<Agent, Integer> generator)
	{
		this.generator = generator;
	}
	
	/**
	 * Sets a Set with the initial vertices of the Barabasi-Albert network.
	 * 
	 * @param initialSeeds		A Set with the initial vertices of the Barabasi-Albert network.
	 */
	public void setInitialSeedVertex(Set<Agent> initialSeeds)
	{
		this.initialSeeds = initialSeeds;
	}

	/**
	 * Gets a Graph factory for building the network.
	 * 
	 * @return	A Graph factory for building the network.
	 */
	private Factory<Graph<Agent, Integer>> createGraphFactory()
	{
		return new Factory<Graph<Agent, Integer>>() {
            public Graph<Agent, Integer> create() {
                return new SparseMultigraph<Agent, Integer>();
            }
		};
	}
	
	/**
	 * Gets a vertices factory for building the network.
	 * 
	 * @param anParams		An AgentNetworkParams object.
	 * @return				A vertices factory for building the network.
	 */
	private Factory<Agent> createVertexFactory(AgentNetworkParams anParams)
	{
		return new Factory<Agent>() {
          
			int sCount = 0;		// Counts the number of Susceptible agents to be created
			int bCount = 0;		// Counts the number of Believer agents to be created
			int fcCount = 0;	// Counts the number of FactChecker agents to be created			
            
            public Agent create() {
            	if(sCount < anParams.getSCount()) {
            		sCount++;
            		Agent agent = new Agent(AgentState.SUSCEPTIBLE);
            		return agent;
            	}
            	else if(bCount < anParams.getBCount()) {
            		bCount++;
            		Agent agent = new Agent(AgentState.BELIEVER);
            		return agent;
            	}
            	else if(fcCount < anParams.getFcCount()) {
            		fcCount++;
            		Agent agent = new Agent(AgentState.FACT_CHECKER);
            		return agent;
            	}
            	else {
            		System.out.println("\u001B[31m [FACTORY LIMIT REACHED] \u001B[34m  Returning null or throwing exception. \u001B[30m");
            		throw new RuntimeException("\u001B[31m [CONFIGURATION NOT ALLOWED] \u001B[34m The number of agents does not correspond to the total requested or fewer than 3 agents have been requested. \u001B[30m");
            	}
            }      
		};
	}
	
	/**
	 * Gets an edges factory for building the network.
	 * 
	 * @return		An edges factory for building the network.
	 */
	private Factory<Integer> createEdgeFactory()
	{
		return new Factory<Integer>() {
            int i = 0;
            public Integer create() {
                return i++;
            }
		};
	}
	
	/**
	 * Creates an initial set of vertices to building the network.
	 * 
	 * @param anParams		An AgentNetworkParams object.
	 * @return				An initial set of vertices to building the network.
	 */
	private Set<Agent> initialSeedVertex(AgentNetworkParams anParams)
	{
		Set<Agent> seedVertex = new HashSet<Agent>();
		
		// Adds the susceptible seeds
		for(int i = 0; i < anParams.getSSeed(); i++) {
			Agent agent = new Agent(AgentState.SUSCEPTIBLE);
			seedVertex.add(agent);
		}
		// Adds the believer seeds
		for(int i = 0; i < anParams.getBSeed(); i++) {
			Agent agent = new Agent(AgentState.BELIEVER);
			seedVertex.add(agent);
		}
		// Adds the fact checker seeds
		for(int i = 0; i < anParams.getFcSeed(); i++) {
			Agent agent = new Agent(AgentState.FACT_CHECKER);
			seedVertex.add(agent);
		}

		return  seedVertex;
	}
}