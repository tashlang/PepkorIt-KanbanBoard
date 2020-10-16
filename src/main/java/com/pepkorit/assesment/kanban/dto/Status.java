
package com.pepkorit.assesment.kanban.dto;

/**
 * @author Natasha
 *
 */
public enum Status
{
	TODO("To-Do"), IN_PROGRESS("In-Progress"), DONE("Done");
	
	public final String description;
	
	/**
	 * Basic constructor
	 */
	private Status(String description)
	{
		this.description = description;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	@Override
	public String toString()
	{
		return description;
	}
	
}
