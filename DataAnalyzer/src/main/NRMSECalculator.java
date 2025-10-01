package main;

import java.lang.Math;

/**
 * Represents a normalized root mean square error (NRMSE) calculator.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class NRMSECalculator 
{
	/**
	 * Default constructor for NRMSECalculator.
	 */
	public NRMSECalculator()
	{
		// Empty constructor, does not perform any action
	}
	
	/**
	 * Calculates the normalized root mean square error (NRMSE).
	 *
	 * @param totalBeliever			The total number of Believer agents.
	 * @param totalSusceptible		The total number of Susceptible agents.
	 * @param totalFactChecker		The total number of FactChecker agents.
	 * @param bDataset				The number of believers in a news ID in the dataset.
	 * @param runs					The number of runs in the Repast Simphony simulation.
	 * @param type					The type of calculation.
	 * @return						The NRMSE value.
	 */
	public double calculateNRMSE(int totalBeliever, int totalSusceptible, int totalFactChecker, double bDataset, int runs, TypeOfCalculation type)
	{
		double bAverage = 0;
		double sAverage = 0;
		double fcAverage = 0;
		double pMaxModel = 748;
		double pMinModel = 73;
		double rmse = 0;
		double nrmse = 0;
		
		switch (type) {
			case VALIDATION:
				bAverage = (double)totalBeliever/(double)runs;
				sAverage = (double)totalSusceptible/(double)runs;
				fcAverage = (double)totalFactChecker/(double)runs;
				rmse = calculateRMSEValidation(bAverage, sAverage, fcAverage);
				nrmse = rmse / (pMaxModel - pMinModel);
				break;
			case DATASET:
				bAverage = (double)totalBeliever/(double)runs;
				rmse = calculateRMSEDataset(bAverage, bDataset);
				nrmse = rmse / bDataset;
				break;
			default:
				throw new IllegalArgumentException("[ERROR] Unsupported file type.");
		}
		
		return nrmse;
	}
	
	/**
	 * Calculates the root mean square error (RMSE) for validation.
	 * 
	 * @param bAverage		The average Believer agent. 
	 * @param sAverage		The average Susceptible agent. 
	 * @param fcAverage		The average FactChecker agent. 
	 * @return				The RMSE value.
	 */
	private double calculateRMSEValidation(double bAverage, double sAverage, double fcAverage)
	{
		double believersInModel = 73;
		double susceptiblesInModel = 179;
		double factCheckersInModel = 748;
		int n = 3;
		
		double sumValues = Math.pow((believersInModel - bAverage), 2) + Math.pow((susceptiblesInModel - sAverage), 2) + Math.pow((factCheckersInModel - fcAverage), 2);
		
		return Math.sqrt(sumValues / n);
	}
	
	/**
	 * Calculates the root mean square error (RMSE) for validation with real data in the dataset.
	 * 
	 * @param bAverage		The average Believer agent.
	 * @param bDataset		The number of believers in a news ID in the dataset.
	 * @return				The RMSE value.
	 */
	private double calculateRMSEDataset(double bAverage, double bDataset)
	{
		return Math.abs((bDataset - bAverage));
	}
}
