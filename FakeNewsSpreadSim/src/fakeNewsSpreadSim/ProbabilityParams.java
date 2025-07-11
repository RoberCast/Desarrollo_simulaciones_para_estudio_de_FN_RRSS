package fakeNewsSpreadSim;

/**
 * This class acts as a Data Transfer Object that keeps the parameters of the fixed probabilities of the fake news.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class ProbabilityParams 
{
	private double credibilityHoax;			// Probability that an agent trust on the hoax -  alpha element of [0,1)
	private double spreadingRate;			// Probability of the spreading -  beta element of [0,1]
	private double verifyingProbability;	// Probability of the verifying -  pv element of [0,1]
	private double forgettingRate;			// Probability of the forgetting -  pf element of [0,1]	
	
	/**
	 * Default constructor that receives the probabilities relative to the fake news.
	 * 
	 * @param credibilityHoax			A probability that an agent trust on the hoax -  alpha element of [0,1).
	 * @param spreadingRate				A probability of the spreading -  beta element of [0,1].
	 * @param verifyingProbability		A probability of the verifying -  pv element of [0,1].
	 * @param forgettingRate			A probability of the forgetting -  pf element of [0,1].
	 */
	public ProbabilityParams(double credibilityHoax, double spreadingRate, double verifyingProbability, double forgettingRate)
	{
		this.credibilityHoax = credibilityHoax;
		this.spreadingRate = spreadingRate;
		this.verifyingProbability = verifyingProbability;
		this.forgettingRate = forgettingRate;
	}
	
	/**
	 * Gets the credibility of the fake news as a statistic in the range of [0,1).
	 * 
	 * @return		The credibility of the fake news as a statistic in the range of [0,1).
	 */
	public double getCredibilityHoax()
	{
		return credibilityHoax;
	}
	
	/**
	 * Gets the spreading rate of the fake news as a statistic in the range of [0,1].
	 * 
	 * @return		The spreading rate of the fake news as a statistic in the range of [0,1].
	 */
	public double getSpreadingRate()
	{
		return spreadingRate;
	}
	
	/**
	 * Gets the verifying probability of the fake news in the range of [0,1].
	 * 
	 * @return		The verifying probability of the fake news in the range of [0,1].
	 */
	public double getVerifyingProbability()
	{
		return verifyingProbability;
	}
	
	/**
	 * Gets the forgetting rate of the fake news as a statistic in the range of [0,1].
	 * 
	 * @return		The forgetting rate of the fake news as a statistic in the range of [0,1].
	 */
	public double getForgettingRate()
	{
		return forgettingRate;
	}
	
	/**
	 * Sets the credibility of the fake news as a statistic in the range of [0,1).
	 * 
	 * @param credibilityHoax		A decimal value in the range of [0,1).
	 */
	public void setCredibilityHoax(double credibilityHoax)
	{
		this.credibilityHoax = credibilityHoax;
	}
	
	/**
	 * Sets the spreading rate of the fake news as a statistic in the range of [0,1].
	 * 
	 * @param spreadingRate			A decimal value in the range of [0,1].
	 */
	public void setSpreadingRate(double spreadingRate)
	{
		this.spreadingRate = spreadingRate;
	}
	
	/**
	 * Sets the verifying probability of the fake news in the range of [0,1].
	 * 
	 * @param verifyingProbability	A decimal value in the range of [0,1].
	 */
	public void setVerifyingProbability(double verifyingProbability)
	{
		this.verifyingProbability = verifyingProbability;
	}
	
	/**
	 * Sets the forgetting rate of the fake news as a statistic in the range of [0,1].
	 * 
	 * @param forgettingRate		A decimal value in the range of [0,1].
	 */
	public void setForgettingRate(double forgettingRate)
	{
		this.forgettingRate = forgettingRate;
	}
}
