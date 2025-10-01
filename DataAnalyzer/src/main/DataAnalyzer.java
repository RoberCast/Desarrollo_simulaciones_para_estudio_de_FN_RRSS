package main;

import java.io.File;
import java.util.Scanner;

/**
 * Represents a data analyzer, which receives data from the Repast Simphony simulator 
 * and performs analysis on the received data.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class DataAnalyzer 
{
	/**
	 * Default constructor for DataAnalyzer.
	 */
	public DataAnalyzer()
	{
		// Empty constructor, does not perform any action.
	}
	
	/**
	 * Main method for DataAnalyzer.
	 * 
	 * @param args		The execution arguments.
	 */
	public static void main(String[] args)
	{
		try {
			if(args.length < 1 || args.length > 3) { 	// Unaccepted combinations.
				errParametersCombination();
			}
			else {
				if(args.length == 1) {
					runOneParameterCombination(args[0]);
				}
				else {
					String path = new File(DataAnalyzer.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + File.separator + "Resources";
					String batchParamsPath = path + File.separator + "Repast Simphony Output" + File.separator + args[0];
					String batchRunPath = path + File.separator + "Repast Simphony Output" + File.separator + args[1];
					File batchParams = new File(batchParamsPath);
					File batchRun = new File(batchRunPath);
					
					if(batchParams.exists() && batchRun.exists()) {
						// Verify that they are the correct files with the name given to them by Repast Simphony.
						if(args[0].contains("batch_param_map") && args[1].contains("Model_Output_Data")) {  
							Parser p = new Parser();
							NRMSECalculator nrmsec = new NRMSECalculator();
							double result = 0;
							boolean isValid = false;
							double limit = 0.2;		// The model validation is correct below this limit.
							p.readFile(batchParamsPath, null, FileType.BATCH_PARAMS);
							p.readFile(batchRunPath, null, FileType.BATCH_RUN);
				
							if(args.length == 2) {	// Arguments for the validation of the Repast Simphony model.
								runTwoParametersCombination(result, isValid, limit, p, nrmsec);
							}
							else if(args.length == 3) {		// Arguments for validating real data.
								String dataSetPath = path + File.separator + "Dataset" + File.separator + args[2];
								File dataSet = new File(dataSetPath);
								
								if(dataSet.exists()) {
									runThreeParametersCombination(dataSetPath, p, result, limit, isValid, nrmsec);
								}
								else {
									System.out.println("[ERROR] The file containing the dataset does not exist, the folder does not have the correct name, or the combination of parameters is incorrect.\n");
									printHelp();
								}
							}
						}
						else {
							System.out.println("[ERROR] The position of the files in the parameters is not correct or they do not have the name given to them by Repast Simphony.\n");
							printHelp();
						}
					}
					else {
						System.out.println("[ERROR] The batch run parameter file or Repast Simphony batch run file does not exist, the folder name is incorrect, or the combination of parameters was incorrect.\n");
						printHelp();
					}	
				}
			}
		}
		catch(Exception e) {
			System.err.println("[ERROR] Could not get directory: " + e.getMessage());
		}
	}
	
	/**
	 * Displays help for running the program.
	 * 
	 * @param arg		An execution argument.
	 */
	private static void runOneParameterCombination(String arg)
	{
		if(arg.toLowerCase().equals("-h")) {
			printHelp();
		}
		else {
			errParametersCombination();
		}
	}
	
	/**
	 * Performs validation of the model executed in Repast Simphony.
	 * 
	 * @param result		The result of the validation.
	 * @param isValid		True if it is a valid model, false otherwise.
	 * @param limit			The limit for model validation.
	 * @param p				A Parser object.
	 * @param nrmsec		A NRMSECalculator object.
	 */
	private static void runTwoParametersCombination(double result, boolean isValid, double limit, Parser p, NRMSECalculator nrmsec)
	{
		result = nrmsec.calculateNRMSE(p.getTotalBeliever(), p.getTotalSusceptible(), p.getTotalFactChecker(), 0, p.getRuns(), TypeOfCalculation.VALIDATION);
		isValid = isAValidModel(result, limit);
		printInformation(p, null, TypeOfCalculation.VALIDATION, result, limit, isValid);
	}
	
	/**
	 * Performs validation for real data by comparing it with the Repast Simphony model.
	 * 
	 * @param dataSetPath		The path of the dataset.
	 * @param p					A Parser object.
	 * @param result			The result of the validation.
	 * @param limit				The limit for model validation.
	 * @param isValid			True if it is a valid model, false otherwise.
	 * @param nrmsec			A NRMSECalculator object.
	 */
	private static void runThreeParametersCombination(String dataSetPath, Parser p, double result, double limit, boolean isValid, NRMSECalculator nrmsec)
	{
		boolean isExit = false;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.print("Please enter the news ID (type 'quit' to exit): ");
			String id = sc.nextLine();
			System.out.println();
			
			if(id.toLowerCase().equals("quit")) {
				isExit = true;
			}
			else {
				p.readFile(dataSetPath, id, FileType.DATASET);	
			}
			
			if(p.isIdInDataset()) {
				int totalAgents = p.getPBeliever() + p.getPSusceptible() + p.getPFactChecker();		
				if(totalAgents > p.getDataSetBelievers() && p.getDataSetBelievers() > 0) {
					result = nrmsec.calculateNRMSE(p.getTotalBeliever(), 0, 0, p.getDataSetBelievers(), p.getRuns(), TypeOfCalculation.DATASET);
					isValid = isAValidModel(result, limit);
					printInformation(p, id, TypeOfCalculation.DATASET, result, limit, isValid);
				}
				else {
					System.out.println("[INFO] The number of news believers cannot be equal to zero or greater than the total number of agents in the simulation, please select another news ID.");
					p.setIdInDataset(false);
				}
			}
		}
		while(!p.isIdInDataset() && isExit == false);
	}
	
	/**
	 * Displays an error and help for running the program if the parameters are not valid.
	 */
	private static void errParametersCombination()
	{
		System.out.println("[ERROR] Unrecognized parameter or combination of parameters.\n");
		printHelp();
	}
	
	/**
	 * Returns whether the model is valid or not by comparing it with the threshold for model validation.
	 * 
	 * @param result	The result of the validation.
	 * @param limit		The limit for model validation.
	 * @return			True if it is a valid model, false otherwise.
	 */
	private static boolean isAValidModel(double result, double limit)
	{
		return (result < limit) ? true : false;
	}
	
	/**
	 * Prints the validation result to standard output.
	 * 
	 * @param p				A Parser object.
	 * @param id			A news identifier in the dataset.
	 * @param type			A TypeOfCalculation object.
	 * @param result		The result of the validation.
	 * @param limit			The limit for model validation.
	 * @param isValid		True if it is a valid model, false otherwise.
	 */
	private static void printInformation(Parser p, String id, TypeOfCalculation type, double result, double limit, boolean isValid)
	{
		printParameters(p);
		printAverageAgentsNRuns(p);
		if(type.equals(TypeOfCalculation.DATASET)) {
			printNewsBelievers(p, id);
		}
		printResult(result, limit, isValid);
	}
	
	/**
	 * Prints help to standard output.
	 */
	private static void printHelp()
	{
		System.out.println("/**************************************************");
		System.out.println("*                  DATA ANALYZER                  *");
		System.out.println("*                                                 *");
		System.out.println("*        Version: PFG II - september 2025         *");
		System.out.println("*        Author:  Roberto Castillejo Embid        *");
		System.out.println("***************************************************/");
		System.out.println();
		System.out.println("CORRECT SYNTAX");
		System.out.println("==============");
		System.out.println();
		System.out.println("java -jar dataAnalyzer.jar [-h] batch_param.txt batch_run.txt [dataset.csv]");
		System.out.println();
		System.out.println("Accepted options:");
		System.out.println("     java -jar dataAnalyzer.jar -h                                           Print this help.");
		System.out.println("     java -jar dataAnalyzer.jar batch_param.txt batch_run.txt                Validate the model.");
		System.out.println("     java -jar dataAnalyzer.jar batch_param.txt batch_run.txt dataset.csv    Validate/calibrate the model with real data.");
		System.out.println();
		System.out.println("PARAMETERS");
		System.out.println("==========");
		System.out.println();
		System.out.println("     -h                  Show this help.");
		System.out.println("     batch_param.txt     The file with the Repast Simphony batch parameters.");
		System.out.println("     batch_run.txt     	 The file with the results of the Repast Simphony batch after n simulations.");
		System.out.println("     dataset.csv     	 The file with the dataset, which contains the real data.");
		System.out.println();
		System.out.println("DATA ANALYZER FILE TREE");
		System.out.println("=======================");
		System.out.println();
		System.out.println("     Root_Folder/");
		System.out.println("         ├── DataAnalyzer.jar");
		System.out.println("         └── Resources/");
		System.out.println("               ├── Dataset/");
		System.out.println("               │     └── dataset.csv");
		System.out.println("               └── Repast Simphony Output/");
		System.out.println("                     ├── batch_param.txt");
		System.out.println("                     └── batch_run.txt");
	}
	
	/**
	 * Prints simulation parameters in Repast Simphony to standard output.
	 * 
	 * @param p		A Parser object.
	 */
	private static void printParameters(Parser p)
	{
		System.out.println("[REPAST SIMPHONY PARAMETERS IN SIMULATION]");
		System.out.println("Total agents: " + (p.getPBeliever() + p.getPSusceptible() + p.getPFactChecker()));
		System.out.println("Believers: " + p.getPBeliever());
		System.out.println("Susceptibles: " + p.getPSusceptible());
		System.out.println("Fact-Checkers: " + p.getPFactChecker());
		System.out.println("Spreading rate: " + p.getSpreadingRate());
		System.out.println("Verifying Probability: " + p.getVerifyingProb());
		System.out.println("Credibility hoax: " + p.getCredHoax());
		System.out.println("Forgetting rate: " + p.getForgettingRate());
		System.out.println("Number of ticks: " + p.getNTicks());
		System.out.println("Number of runs: " + p.getRuns() + "\n");
		
	}
	
	/**
	 * Prints the average of each agent after n runs in Repast Simphony to standard output.
	 * 
	 * @param p		A Parser object.
	 */
	private static void printAverageAgentsNRuns(Parser p)
	{		
		System.out.println("[AVERAGE NUMBER OF EACH AGENT AFTER " + p.getRuns() + " EXECUTIONS]");
		System.out.println("Believers: " + ((double)p.getTotalBeliever() / (double)p.getRuns()));
		System.out.println("Susceptibles: " + ((double)p.getTotalSusceptible() / (double)p.getRuns()));
		System.out.println("Fact-Checkers: " + ((double)p.getTotalFactChecker() / (double)p.getRuns()) + "\n");
	}
	
	/**
	 * Prints the validation result to standard output.
	 * 
	 * @param result	The result of the validation.
	 * @param limit		The limit for model validation.
	 * @param isValid	True if it is a valid model, false otherwise.
	 */
	private static void printResult(double result, double limit, boolean isValid)
	{
		System.out.println("[RESULTS]");
		if(isValid) {
			System.out.println("NRMSE = " + result + " - The model is considered valid.");
		}
		else {
			System.out.println("NRMSE = " + result + " - The model is considered invalid.");
		}
	}
	
	/**
	 * Prints the number of actual Believers that have been read for a given news id in the dataset.
	 * 
	 * @param p			A Parser object.
	 * @param id		A news identifier in the dataset.
	 */
	private static void printNewsBelievers(Parser p, String id)
	{
		System.out.println("[NUMBER OF BELIEVERS IN THE NEWS WITH ID " + id + "]");
		System.out.println("Believers: " + p.getDataSetBelievers() + "\n");
	}
}