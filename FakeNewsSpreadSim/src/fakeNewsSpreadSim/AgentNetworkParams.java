package fakeNewsSpreadSim;

/**
 * This class acts as a Data Transfer Object that keeps the parameters to build the network.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class AgentNetworkParams 
{
	private int sCount;		// The number of Susceptible type agents
	private int bCount;		// The number of Believer type agents
	private int fcCount; 	// The number of FactChecker type agents
	private int sSeed;		// The number of Susceptible type seed agents
	private int bSeed;		// The number of Believer type seed agents
	private int fcSeed;		// The number of FactChecker type seed agents
	
	/**
	 * Default constructor that receives agents and seed agents.
	 * 
	 * @param sCount		The number of Susceptible type agents.
	 * @param bCount		The number of Believer type agents.
	 * @param fcCount		The number of FactChecker type agents.
	 */
	public AgentNetworkParams(int sCount, int bCount, int fcCount)
	{
		this.sCount = sCount;
		this.bCount = bCount;
		this.fcCount = fcCount; 
		this.sSeed = 0;
		this.bSeed = 0;
		this.fcSeed = 0;
	}
	
	/**
	 * Gets the number of Susceptible type agents.
	 * 
	 * @return		The number of Susceptible type agents.
	 */
	public int getSCount() 
	{
		return sCount;
	}
	
	/**
	 * Gets the number of Believer type agents.
	 * 
	 * @return		The number of Believer type agents.
	 */
	public int getBCount() 
	{
		return bCount;
	}
	
	/**
	 * Gets the number of FactChecker type agents.
	 * 
	 * @return		The number of FactChecker type agents.
	 */
	public int getFcCount() 
	{
		return fcCount;
	}
	
	/**
	 * Gets the number of Susceptible type seed agents.
	 * 
	 * @return		The number of Susceptible type seed agents.
	 */
	public int getSSeed() 
	{
		return sSeed;
	}
	
	/**
	 * Gets the number of Believer type seed agents.
	 * 
	 * @return		The number of Believer type seed agents.
	 */
	public int getBSeed() 
	{
		return bSeed;
	}
	
	/**
	 * Gets the number of FactChecker type seed agents.
	 * 
	 * @return		The number of FactChecker type seed agents.
	 */
	public int getFcSeed() 
	{
		return fcSeed;
	}
	
	/**
	 * Sets the number of Susceptible type agents.
	 * 
	 * @param sCount	An integer value.
	 */
	public void setSCount(int sCount)
	{
		this.sCount = sCount;
	}
	
	/**
	 * Sets the number of Believer type agents.
	 * 
	 * @param bCount	An integer value.
	 */
	public void setBCount(int bCount)
	{
		this.bCount = bCount;
	}
	
	/**
	 * Sets the number of FactChecker type agents.
	 * 
	 * @param fcCount	An integer value.
	 */
	public void setFcCount(int fcCount)
	{
		this.fcCount = fcCount;
	}
	
	/**
	 * Sets the number of Susceptible type seed agents.
	 * 
	 * @param sSeed		An integer value.
	 */
	public void setSSeed(int sSeed)
	{
		this.sSeed = sSeed;
	}
	
	/**
	 * Sets the number of Believer type seed agents.
	 * 
	 * @param bSeed		An integer value.
	 */
	public void setBSeed(int bSeed)
	{
		this.bSeed = bSeed;	
	}
	
	/**
	 * Sets the number of FactChecker type seed agents.
	 * 
	 * @param fcSeed	An integer value.
	 */
	public void setFcSeed(int fcSeed)
	{
		this.fcSeed = fcSeed;
	}
}
