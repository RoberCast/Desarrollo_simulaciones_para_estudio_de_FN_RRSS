package fakeNewsSpreadSim;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.graph.Network;

/**
 * Represents an agent scheduler that is responsible for performing the simulation logic.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class AgentScheduler 
{
	private Context<Object> context;			// The Repast Simphony context
	private Network<Object> network;			// The Repast Simphony network
	private ProbabilityParams pParams;			// The fixed probabilities of the Fake News	
	private Random rand;						// A Random object
	
	/**
	 * Default constructor for AgentScheduler.
	 * 
	 * @param context	The Repast Simphony context.
	 * @param network	The Repast Simphony network.
	 * @param pParams	The fixed probabilities of the Fake News.
	 */
	public AgentScheduler(Context<Object> context, Network<Object> network, ProbabilityParams pParams)
	{
		this.context = context;
		this.network = network;
		this.pParams = pParams;
		rand = new Random();
	}
	
	/**
	 * This method is called at each iteration of the simulation and performs all the steps the simulation should perform in one tick (timestep).
	 */
	@ScheduledMethod(start = 1, interval = 1)
	public void step()
	{
		/* 
		 * @ScheduledMetod annotation calls this method every iteration of the simulation. The parameters are used to specify when and how often the method will be called,
		 * the number of object instances to invoke the method on, and so on. In this case, the parameters will schedule this method to be called on all agents starting
		 * at tick (timestep) 1 and every tick thereafter
		 */
		Set<Agent> agents = new HashSet<Agent>(getSetOfAgents());
		updateSpreadingFunction(agents, pParams);		
		processing(agents, pParams);
		updateAgentStates(agents);
	}
	
	/**
	 * Updates the spreading functions, f_b and f_fc, to all agents of the simulation.
	 * 
	 * @param agents		A set of agents.
	 * @param pParams		An ProbabilityParams object.
	 */
	private void updateSpreadingFunction(Set<Agent> agents, ProbabilityParams pParams)
	{
		for(Agent agent : agents) {
			int nBelievers = 0;
			int nFactCheckers = 0;
			for(Object neighbor : network.getAdjacent(agent)) {
				if(((Agent)neighbor).getCurrentState() == AgentState.BELIEVER) {
					nBelievers++;
				}
				if(((Agent)neighbor).getCurrentState() == AgentState.FACT_CHECKER) {
					nFactCheckers++;
				}
			}
			agent.setFBeliever(calculateSpredingFunction("believer", nBelievers, nFactCheckers, pParams));
			agent.setFFactChecker(calculateSpredingFunction("factChecker", nBelievers, nFactCheckers, pParams));
		}
	}
	
	/**
	 * Executes the main processes of the simulation: spreading, forgetting, verifying and update the probabilities of being 
	 * susceptible, believer or fact Checker in the next tick.
	 * 
	 * @param agents		A set of agents.
	 * @param pParams		An ProbabilityParams object.
	 */
	private void processing(Set<Agent> agents, ProbabilityParams pParams)
	{
		for(Agent agent : agents) {
			spreading(agent, pParams);
			forgetting(agent, pParams);
			verifying(agent, pParams);	
			agent.updateStateProbabilities(pParams.getForgettingRate(), pParams.getVerifyingProbability());
		}
	}
	
	/**
	 * Updates the agents' status for the next tick.
	 * 
	 * @param agents	A set of agents.
	 */
	private void updateAgentStates(Set<Agent> agents)
	{
		for(Agent agent : agents) {
			if (agent.getNextState() != null) {
		        agent.setCurrentState(agent.getNextState());
		        agent.setNextState(null);  // Reset for the next tick
		    }
		}
	}
	
	/**
	 * Calculates the spreading functions, f_b and f_fc.
	 * 
	 * @param state				The agent state as String: "believer" or "factChecker".
	 * @param bNeighbors		The number of neighbors as believer state.
	 * @param fcNeighbors		The number of neighbors as factChecker state.
	 * @param pParams			An ProbabilityParams object.
	 * @return					A value for the spreading function.
	 */
	private double calculateSpredingFunction(String state, int bNeighbors, int fcNeighbors, ProbabilityParams pParams)
	{
		double aux1 = 0;
		double aux2 = 0;
		
		if(state.equals("believer")) {
			aux1 = bNeighbors * (1 + pParams.getCredibilityHoax());
			aux2 = aux1 + fcNeighbors * (1 - pParams.getCredibilityHoax());
		}
		
		if(state.equals("factChecker")) {
			aux1 = fcNeighbors * (1 - pParams.getCredibilityHoax());
			aux2 = bNeighbors * (1 + pParams.getCredibilityHoax()) + aux1;
		}
		
		return pParams.getSpreadingRate() * (aux1 / aux2);
	}
	
	/**
	 * Simulates the action of spreading fake news.
	 * 
	 * @param agent			An agent object.
	 * @param pParams		An ProbabilityParams object.
	 */
	private void spreading(Agent agent, ProbabilityParams pParams)
	{
		if(agent.getCurrentState() == AgentState.SUSCEPTIBLE) {
			double result = rand.nextDouble();

			if(agent.getNextState() == null) {
				if(result < agent.getFBeliever()) {
					agent.setNextState(AgentState.BELIEVER);
				}
				else if(result < (agent.getFBeliever() + agent.getFFactChecker())) {
					agent.setNextState(AgentState.FACT_CHECKER);
				}
			}
			// If no if-else condition is satisfied, means that result is >= F_b + F_fc and the agent don't change its state
		}
	}
	
	/**
	 * Simulates the action of forgetting the fake news.
	 * 
	 * @param agent			An agent object.
	 * @param pParams		An ProbabilityParams object.
	 */
	private void forgetting(Agent agent, ProbabilityParams pParams)
	{
		if(agent.getNextState() == null) {
			if(agent.getCurrentState() == AgentState.BELIEVER || agent.getCurrentState() == AgentState.FACT_CHECKER) {
				double randomValue = agent.generateRandomValue(true);
				if(randomValue < pParams.getForgettingRate()) {
					agent.setNextState(AgentState.SUSCEPTIBLE);
				}
			}
		}
	}
	
	/**
	 * Simulates the action of verifying facts or when an agent knows that the news is false.
	 * 
	 * @param agent			An agent object.
	 * @param pParams		An ProbabilityParams object.
	 */
	private void verifying(Agent agent, ProbabilityParams pParams)
	{
		if(agent.getNextState() == null) {
			if(agent.getCurrentState() == AgentState.BELIEVER) {
				double randomValue = agent.generateRandomValue(true);
				if(randomValue < pParams.getVerifyingProbability()) {
					agent.setNextState(AgentState.FACT_CHECKER);
				}
			}
		}
	}
	
	/**
	 * Gets a set with all agents in the network.
	 * 
	 * @return		A set with all agents in the network.
	 */
	private Set<Agent> getSetOfAgents()
	{
		Set<Agent> agentSet = new HashSet<Agent>();
		for(Object o : context) {
			if(o instanceof Agent) {
				agentSet.add((Agent)o);
			}
		}
		
		return agentSet;
	}  
}