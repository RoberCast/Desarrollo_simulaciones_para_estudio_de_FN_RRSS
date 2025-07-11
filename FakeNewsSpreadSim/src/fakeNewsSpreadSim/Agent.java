package fakeNewsSpreadSim;

import java.util.Random;
import java.util.UUID;

/**
 * Represents an agent in the social network.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class Agent 
{
	private final UUID id = UUID.randomUUID();		// Agent ID 
	private AgentState currentState;				// The current state of the agent 
	private double pSusceptible;					// Probability of being in a Susceptible state - ps element of [0,1]
	private double pBeliever;						// Probability of being in a Believer state - pb element of [0,1]
	private double pFactChecker;					// Probability of being in a Fact-Checker state - pfc element of [0,1]
	private double fBeliever;						// The spreading function f_b
	private double fFactChecker;					// The spreading function f_fc	
	private AgentState nextState; 					// The state of the agent on the next tick (t + 1)
	
	/**
	 * Default constructor for Agent.
	 * 
	 * @param initialState		The initial state of the agent.
	 */
	public Agent(AgentState initialState) 
	{
		currentState = initialState;
		pSusceptible = generateRandomValue(true);
		pBeliever = generateRandomValue(true);
		pFactChecker = generateRandomValue(true);
		fBeliever = 0;
		fFactChecker = 0;			
		nextState = null;   
	}
	
	/**
	 * Constructor that builds an Agent Object by copy.
	 * 
	 * @param agent 	An Agent object.
	 */
	public Agent(Agent agent)
	{
		currentState = agent.getCurrentState();
		pSusceptible = agent.getPSusceptible();
		pBeliever = agent.getPBeliever();
		pFactChecker = agent.getPFactChecker();
		fBeliever = agent.getFBeliever();
		fFactChecker = agent.getFFactChecker();	
		nextState = agent.getNextState();
	}
	
	/**
	 * Gets the ID of the Agent.
	 * 
	 * @return		The ID of the Agent.
	 */
	public UUID getId()
	{
		return id;
	}
	
	/**
	 * Gets the current state of the agent.
	 * 
	 * @return		The current state of the agent.
	 */
	public AgentState getCurrentState()
	{
		return currentState;
	}
	
	/**
	 * Gets whether the agent is in the susceptible state.
	 * 
	 * @return		True if the agent is in the susceptible state, false otherwise.
	 */
	public boolean isSusceptible()
	{
		return currentState == AgentState.SUSCEPTIBLE;
	}
	
	/**
	 * Gets whether the agent is in the believer state.
	 * 
	 * @return		True if the agent is in the believer state, false otherwise.
	 */
	public boolean isBeliever()
	{
		return currentState == AgentState.BELIEVER;
	}
	
	/**
	 * Gets whether the agent is in the fact checker state.
	 * 
	 * @return		True if the agent is in the fact checker state, false otherwise.
	 */
	public boolean isFactChecker()
	{
		return currentState == AgentState.FACT_CHECKER;
	}
	
	/**
	 * Gets the probability of being in a Susceptible state with value [0,1].
	 * 
	 * @return	The probability of being in a Susceptible state with value [0,1].
	 */
	public double getPSusceptible()
	{
		return pSusceptible;
	}
	
	/**
	 * Gets the probability of being in a Believer state with value [0,1].
	 * 
	 * @return	The probability of being in a Believer state with value [0,1].
	 */
	public double getPBeliever()
	{
		return pBeliever;
	}
	
	/**
	 * Gets the probability of being in a FactChecker state with value [0,1].
	 * 
	 * @return	The probability of being in a FactChecker state with value [0,1].
	 */
	public double getPFactChecker()
	{
		return pFactChecker;
	}
	
	/**
	 * Gets the spreading function f_b.
	 * 
	 * @return		The spreading function f_b.
	 */
	public double getFBeliever()
	{
		return fBeliever;
	}
	
	/**
	 * Gets the spreading function f_fc.
	 * 
	 * @return		The spreading function f_fc.
	 */
	public double getFFactChecker()
	{
		return fFactChecker;
	}
	
	/**
	 * Gets the state of agent on the next tick of the simulation, t + 1.
	 * 
	 * @return		The state of agent on the next tick of the simulation, t + 1.
	 */
	public AgentState getNextState()
	{
		return nextState;
	}
	
	/**
	 * Sets the current state of the agent.
	 * 
	 * @param state		A state for the agent.
	 */
	public void setCurrentState(AgentState state)
	{
		currentState = state;
	}
		
	/**
	 * Sets the probability of being in a Susceptible state, with value [0,1], indicated in the parameter.
	 * 
	 * @param rValue	A value [0,1].
	 */
	public void setPSusceptible(double rValue)
	{
		pSusceptible = clampFunction(rValue);
	}
	
	/**
	 * Sets the probability of being in a Believer state, with value [0,1], indicated in the parameter.
	 * 
	 * @param rValue	A value [0,1].
	 */
	public void setPBeliever(double rValue)
	{
		pBeliever = clampFunction(rValue);
	}
	
	/**
	 * Sets the probability of being in a FactChecker state, with value [0,1], indicated in the parameter.
	 * 
	 * @param rValue	A value [0,1].
	 */
	public void setPFactChecker(double rValue)
	{
		pFactChecker = clampFunction(rValue);
	}
	
	/**
	 * Sets the value of the spreading function f_b from other value passed by parameter.
	 * 
	 * @param fBeliever		A new value for the spreading function f_b.
	 */
	public void setFBeliever(double fBeliever)
	{
		this.fBeliever = clampFunction(fBeliever);
	}
	
	/**
	 * Sets the value of the spreading function f_fc from other value passed by parameter.
	 * 
	 * @param fFactChecker		A new value for the spreading function f_fc.
	 */
	public void setFFactChecker(double fFactChecker)
	{
		this.fFactChecker = clampFunction(fFactChecker);
	}
	
	/**
	 * Sets the state of agent on the next tick of the simulation, t + 1.
	 * 
	 * @param nextState		A new state for the agent.
	 */
	public void setNextState(AgentState nextState)
	{
		this.nextState = nextState;
	}
	
	/**
	 * Update the state probabilities of the agent for the next tick of the simulation (t + 1).
	 * 
	 * @param forgettingRate			A probability of the forgetting -  pf element of [0,1].
	 * @param verifyingProbability		A probability of the verifying -  pv element of [0,1].
	 */
	public void updateStateProbabilities(double forgettingRate, double verifyingProbability)
	{
		double auxSusceptible = clampFunction((1 - fBeliever- fFactChecker) * pSusceptible + forgettingRate * (pBeliever + pFactChecker));
		double auxBeliever = clampFunction(fBeliever * pSusceptible + (1 - forgettingRate) * (1 - verifyingProbability) * pBeliever);
		double auxFactChecker = clampFunction(fFactChecker * pSusceptible + verifyingProbability * pBeliever + (1 - forgettingRate) * pFactChecker);
		
		pSusceptible = auxSusceptible;
		pBeliever =	auxBeliever;
		pFactChecker = auxFactChecker;
	}
	
	/**
	 * Limits a decimal value within a range [0,1].
	 * 
	 * @param value		A decimal value.
	 * @return			A decimal value within a range [0,1].
	 */
	private double clampFunction(double value)
	{
		if(Double.isNaN(value)) {
			return 0;
		}

		if(Double.isInfinite(value)){
			return 0;
		}
		
		if(value < 0) {
			return 0;
		}
		
		if(value > 1) {
			return 1;
		}
		
		return value;
	}
	
	/**
	 * Generates a random value in the range [0,1) or [0,1], depending of the value of the parameter.
	 * 
	 * @param oneIsIncluded		True for the range [0,1], false for the range [0,1).
	 * @return					A random value in the range [0,1) or [0,1].
	 */
	public double generateRandomValue(boolean oneIsIncluded) 
	{
		Random ran = new Random();
		
		if(oneIsIncluded) {
			double result = ran.nextDouble(2);
			if(result > 1) {
				return 1;
			}
			else {
				return result;
			}
		}
		else {
			return ran.nextDouble();
		}
	}
	
	/**
	 * Gets a hashCode value for an Agent object.
	 * 
	 * @return	A hashCode value for an Agent object.
	 */
	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
	
	/**
	 * Gets if two Agent objects are equals.
	 * 
	 * @param o			An Agent object.
	 * @return 			True if two Agent objects are equal, false in otherwise.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(this == o) {
			return true;
		}
		
		if(o == null) {
			return false;
		}
		if(!(o instanceof Agent)) {
			return false;
		}
		else {
			Agent ag = (Agent) o;
			return id.equals(ag.id);
		}
	}
	
	/**
	 * Gets a String representation of an Agent object.
	 * 
	 * @return 	A String representation of an Agent object.
	 */
	@Override
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		
		buff.append("[Id: " + id + "]");
		buff.append("[Current state: " + currentState.getState() + "]");
		buff.append("[State Probabilities: " + pSusceptible);
		buff.append(", " + pBeliever);
		buff.append(", " + pFactChecker + "]");
		buff.append("[Spreading function: " + fBeliever);
		buff.append(", " + fFactChecker + "]");
		
		return buff.toString();
	}
}
