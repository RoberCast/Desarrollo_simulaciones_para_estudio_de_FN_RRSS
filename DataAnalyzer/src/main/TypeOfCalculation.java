package main;

/**
 * Representation a type of calculation.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public enum TypeOfCalculation 
{
	VALIDATION("validation"),	// Corresponds to the validation of the model.
	DATASET("dataset")			// Corresponds to the comparison with real data.
	;
	
	private String type;		// The type of calculation.
	
	/**
	 * Initializes with the corresponding type of calculation.
	 * 
	 * @param type		The type of calculation as a string.
	 */
	TypeOfCalculation(String type)
	{
		this.type = type;
	}
	
	/**
	 * Gets the current type of calculation as a string.
	 * 
	 * @return		The current type of calculation as a string.
	 */
	public String getType()
	{
		return type;
	}
}
