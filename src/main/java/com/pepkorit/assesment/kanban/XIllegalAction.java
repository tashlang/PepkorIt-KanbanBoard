
package com.pepkorit.assesment.kanban;

/**
 * @author Natasha
 *
 */
public class XIllegalAction extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7301240703164480588L;
	
	String errorMessage;
	
	/**
	 * 
	 */
	public XIllegalAction(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}
}
