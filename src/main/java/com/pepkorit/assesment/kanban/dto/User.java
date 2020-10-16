
package com.pepkorit.assesment.kanban.dto;

/**
 * @author Natasha
 *
 */
public class User
{
	String name, email;
	
	/**
	 * Creates a Blank User
	 */
	public User()
	{
		this.name = "N/A";
		this.email = "N/A";
	}
	
	/**
	 * Creates a Blank User
	 */
	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
	
	/**
	 * @param name
	 *        the new name to be updated
	 */
	public void updateName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @param email
	 *        the new email to be updated
	 */
	public void updateEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return name + " <" + email + ">";
	}
	
}
