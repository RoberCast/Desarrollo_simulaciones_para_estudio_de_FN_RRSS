package fakeNewsSpreadSim;

import java.awt.Color;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

/**
 * Represents a modification of the DefaultStyleOGL2D class of Repast Simphony.
 * 
 * @author  Roberto Castillejo Embid.
 * @version PFG II - SEPTEMBER 2025.
 */
public class CustomStyleAgentOGL2D extends DefaultStyleOGL2D
{
	/**
	 * Default constructor of CustomStyleAgentOGL2D.
	 */
	public CustomStyleAgentOGL2D() 
	{
		super();
	}
	
	/**
	 * Gets the color for the specified object.
	 * 
	 * @param agent		An agent object.
	 */
	@Override
	public Color getColor(Object agent)
	{
		Agent ag = (Agent) agent;

		if(ag.getCurrentState() == null) {
			System.err.println("\u001B[31m [NULL STATE OF AGENT] \u001B[34m Agent ID: " + ag.getId() + ". \u001B[30m");
			return Color.DARK_GRAY;
		}
		
		switch (ag.getCurrentState()) {
			case SUSCEPTIBLE :
				return Color.DARK_GRAY;
			case BELIEVER :
				return Color.BLUE;
			case FACT_CHECKER :
				return Color.RED;
			default :
				return Color.DARK_GRAY;
		}
	}
	
	/**
	 * Gets the scale for the specified object.
	 * 
	 * @param agent		An agent object.
	 */
	@Override
	public float getScale(Object agent)
	{
		return 3f;
	}
}
