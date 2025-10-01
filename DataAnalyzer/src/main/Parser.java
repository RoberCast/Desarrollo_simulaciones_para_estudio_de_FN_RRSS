package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Represents a parser, which analyzes and extracts information from files, facilitating 
 * the subsequent processing of that extracted information.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class Parser 
{
	private int dataSetBelievers;			// A counter for dataset believers.
	private boolean containsIdInDataset;	// Saves whether the id appears in the dataset or not.
	private int paramBeliever;				// The Believer parameter that is read from the Repast Simphony batch parameter file.
	private int paramSusceptible;			// The Susceptible parameter that is read from the Repast Simphony batch parameter file.
	private int paramFactChecker;			// The FactChecker parameter that is read from the Repast Simphony batch parameter file.
	private double spreadingRate;			// The spreading rate parameter that is read from the Repast Simphony batch parameter file.
	private double verifyingProb;			// The verify probability parameter that is read from the Repast Simphony batch parameter file.
	private double credHoax;				// The credibility hoax parameter that is read from the Repast Simphony batch parameter file.
	private double forgettingRate;			// The forgetting rate parameter that is read from the Repast Simphony batch parameter file.
	private int nTicks;						// The tick count parameter that is read from the Repast Simphony batch parameter file.
	private int runs;						// The number of runs that simulation  executes that is read from the Repast Simphony batch parameter file.	
	private int totalSusceptible;			// The total number of susceptibles after n simulation runs.
	private int totalBeliever;				// The total number of believers after n simulation runs.
	private int totalFactChecker;			// The total number of factCheckers after n simulation runs.
	
	/**
	 * Default constructor for Parser.
	 */
	public Parser()
	{
		dataSetBelievers = 0;
		containsIdInDataset = false;	
		paramBeliever = 0;
		paramSusceptible = 0;
		paramFactChecker = 0;
		spreadingRate = 0;
		verifyingProb = 0;
		credHoax = 0;
		forgettingRate = 0;
		nTicks = 0;
		runs = 0;	
		totalSusceptible = 0;
		totalBeliever = 0;
		totalFactChecker = 0;
	}
	
	/**
	 * Gets the number of believers read from the dataset.
	 * 
	 * @return		The number of believers read from the dataset.
	 */
	public int getDataSetBelievers()
	{
		return dataSetBelievers;
	}
	
	/**
	 * Gets if the id appears in the dataset or not.
	 * 
	 * @return		True if the id appears in the dataset, false otherwise.
	 */
	public boolean isIdInDataset()
	{
		return containsIdInDataset;
	}
	
	/**
	 * Gets the believer parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The believer parameter that is read from the Repast Simphony batch parameter file.
	 */
	public int getPBeliever()
	{
		return paramBeliever;
	}
	
	/**
	 * Gets the susceptible parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The susceptible parameter that is read from the Repast Simphony batch parameter file.
	 */
	public int getPSusceptible()
	{
		return paramSusceptible;
	}
	
	/**
	 * Gets the factChecker parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The factChecker parameter that is read from the Repast Simphony batch parameter file.
	 */
	public int getPFactChecker()
	{
		return paramFactChecker;
	}
	
	/**
	 * Gets the spreading rate parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The spreading rate parameter that is read from the Repast Simphony batch parameter file.
	 */
	public double getSpreadingRate()
	{
		return spreadingRate;
	}
	
	/**
	 * Gets the verify probability parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The verify probability parameter that is read from the Repast Simphony batch parameter file.
	 */
	public double getVerifyingProb()
	{
		return verifyingProb;
	}
	
	/**
	 * Gets the credibility hoax parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The credibility hoax parameter that is read from the Repast Simphony batch parameter file.
	 */
	public double getCredHoax()
	{
		return credHoax;
	}
	
	/**
	 * Gets the forgetting rate parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The forgetting rate parameter that is read from the Repast Simphony batch parameter file.
	 */
	public double getForgettingRate()
	{
		return forgettingRate;
	}
	
	/**
	 * Gets the tick count parameter that is read from the Repast Simphony batch parameter file.
	 * 
	 * @return		The tick count parameter that is read from the Repast Simphony batch parameter file.
	 */
	public int getNTicks()
	{
		return nTicks;
	}
	
	/**
	 * Gets the number of runs that simulation  executes that is read from the Repast Simphony batch parameter file.	
	 * 
	 * @return		The number of runs that simulation  executes that is read from the Repast Simphony batch parameter file.	
	 */
	public int getRuns()
	{
		return runs;
	}
	
	
	/**
	 * Gets the total number of susceptibles after n simulation runs.
	 * 
	 * @return		The total number of susceptibles after n simulation runs.
	 */
	public int getTotalSusceptible()
	{
		return totalSusceptible;
	}
	
	/**
	 * Gets the total number of believers after n simulation runs.
	 * 
	 * @return		The total number of believers after n simulation runs.
	 */
	public int getTotalBeliever()
	{
		return totalBeliever;
	}
	
	/**
	 * Gets the total number of factCheckers after n simulation runs.
	 * 
	 * @return		The total number of factCheckers after n simulation runs.
	 */
	public int getTotalFactChecker()
	{
		return totalFactChecker;
	}
	
	/**
	 * Sets whether the id appears in the dataset or not.
	 * 
	 * @param value		True if the news ID appears in the dataset, false otherwise.
	 */
	public void setIdInDataset(boolean value)
	{
		containsIdInDataset = value;
	}

	/**
	 * Reads a file. The file can be a dataset, a batch parameter file, or a batch execution file.
	 * 
	 * @param file			The path of the file.
	 * @param id			An id if exist, null otherwise.
	 * @param type			The type of file.
	 */
	public void readFile(String file, String id, FileType type)
	{
		BufferedReader bReader = null;
		
		try {
			InputStream is = getClass().getResourceAsStream(file);
			if(is == null) {  // The file path is outside the src.
				bReader = new BufferedReader(new FileReader(file));
			}
			else {
				bReader = new BufferedReader(new InputStreamReader(is));
			}
			
			switch(type) {
				case DATASET:
					readDataset(bReader, id);
					break;
				case BATCH_PARAMS:
					readRSOutputBatchParams(bReader);
					break;
				case BATCH_RUN:
					readRSOutputBatch(bReader);
					break;
				default:
					throw new IllegalArgumentException("[ERROR] Unsupported file type.");
			}
		}
		catch(IOException e) {
			System.err.println("[ERROR] There was a problem reading the file, its name is incorrect or the path is incorrect: " + file);
		}
		finally {
			//Close the file.
			try {
				if(bReader != null) {
					bReader.close();
				}
			}
			catch(IOException e) {
				System.err.println("[ERROR] There was a problem closing the file: " + file);
			}
		}
	}
	
	/**
	 * Reads a dataset.
	 * 
	 * @param bReader			A BufferedReader object.
	 * @param id				An id.
	 * @throws IOException		Throws an IOException if an I/O error occurs.
	 */
	private void readDataset(BufferedReader bReader, String id) throws IOException
	{
		dataSetBelievers = 0;		// Resets the variable if a previous reading has been performed
		String[] comp = null;
		String[] t_ids = null;
		
		String linea = bReader.readLine();
		
		// Read the file
		while(linea != null) {
			//Filter for id
			if(linea.contains(id)) {	
				comp = linea.split(",");		
				if(comp[0].contentEquals(id)) {
					containsIdInDataset = true;
					t_ids = comp[comp.length - 1].split("\\s+");
					for(int i = 0; i < t_ids.length; i++) {
						t_ids[i] = t_ids[i].trim();
						try {
							Long iaux = Long.valueOf(t_ids[i]);  // Discard what is not a number.
							dataSetBelievers++;
						}
						catch(NumberFormatException e) {
							// Here tweets_id = 0.
						}
					}
				}
			}
			// Update the line in the file.
			linea = bReader.readLine();
		}
		
		// Throws an error message if the id does not exist.
		if(!containsIdInDataset) {
			System.out.println("[INFO] The id: " + id + " does not exist in the dataset.");
		}
	}
	
	/**
	 * Reads a Repast Simphony output batch parameter file.
	 * 
	 * @param bReader			A BufferedReader object.
	 * @throws IOException		Throws an IOException if an I/O error occurs.
	 */
	private void readRSOutputBatchParams(BufferedReader bReader) throws IOException
	{
		String[] comp = null;
		
		String linea = bReader.readLine();
		int count = 0;  // Counter of the lines that contain the simulation data.
		
		while(linea != null) {
			if(count == 1) {
				comp = linea.split(",");
				try {
					paramBeliever = Integer.valueOf(comp[2]);
					paramSusceptible = Integer.valueOf(comp[7]);
					paramFactChecker = Integer.valueOf(comp[5]);
					spreadingRate = Double.valueOf(comp[3]);
					verifyingProb = Double.valueOf(comp[6]);
					credHoax = Double.valueOf(comp[8]);
					forgettingRate = Double.valueOf(comp[9]);
					nTicks = Integer.valueOf(comp[4]);
				}
				catch(NumberFormatException e) {
					// Nothing is done here.

				}		
			}
			// Update the line in the file.
			linea = bReader.readLine();
			if(linea != null) {
				count++;
			}
		}
		runs = count;	// Update the number of simulation runs.
	}
	
	/**
	 * Reads a Repast Simphony output batch execution.
	 * 
	 * @param bReader			A BufferedReader object.
	 * @throws IOException		Throws an IOException if an I/O error occurs.
	 */
	private void readRSOutputBatch(BufferedReader bReader) throws IOException
	{
		String[] comp = null;
		
		String linea = bReader.readLine();
		while(linea != null) {
			comp = linea.split(",");
			try {
				int numOfTick = (int) Double.parseDouble(comp[1]);	// Check the tick number.
				if(numOfTick == nTicks) {		// The last tick of the run.
					totalSusceptible += (int) Double.parseDouble(comp[2]);
					totalBeliever += (int) Double.parseDouble(comp[3]);
					totalFactChecker += (int) Double.parseDouble(comp[4]);
				}
			}
			catch(NumberFormatException e) {
				// Nothing is done here.
			}
			// Update the line in the file.
			linea = bReader.readLine();
		}
	}
}
