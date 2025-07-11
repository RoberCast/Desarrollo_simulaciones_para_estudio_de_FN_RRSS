package fakeNewsSpreadSim;

/**
 * Representation of all valid states of an agent in the simulation.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public enum AgentState 
{
	SUSCEPTIBLE("Susceptible"),		 // The agent ignores the fake news
	BELIEVER("Believer"), 			 // The agent believes in fake news
	FACT_CHECKER("FactChecker")		 // The agent verifies the facts in the fake news or knows that the news is fake
	;
	
	private String state;	// The state of the agent as a String
	
	/**
	 * Initializes with the corresponding state string.
	 * 
	 * @param state		The state as a string.
	 */
	AgentState(String state)
	{
		this.state = state;
	}
	
	/**
	 * Gets the current state string.
	 * 
	 * @return		The current state string.
	 */
	public String getState()
	{
		return state;
	}
}