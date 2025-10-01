package main;

/**
 * Representation of all valid types of file.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public enum FileType 
{
	DATASET("dataset"),					// A type of dataset file.
	BATCH_PARAMS("batch_params"),		// A type of batch parameters file.
	BATCH_RUN("batch_run")				// A type of batch execution file.
	;
	
	private String type;				// The type of the file as a String
	
	/**
	 * Initializes with the corresponding type of file string.
	 * 
	 * @param type		The type of file as a string.
	 */
	FileType(String type)
	{
		this.type = type;
	}
	
	/**
	 * Gets the current type of file as a string.
	 * 
	 * @return		The current type of file as a string.
	 */
	public String getType()
	{
		return type;
	}
}
