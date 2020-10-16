
package com.pepkorit.assesment.kanban.dto;

/**
 * @author Natasha
 *
 */
public class WorkItem
{
	String title, description;
	User   user;
	Status status;
	
	/**
	 * 
	 */
	public WorkItem(String title, String description)
	{
		this(title, description, NULL_USER, Status.TODO);
	}
	
	/**
	 * @param title
	 * @param description
	 * @param user
	 * @param status
	 */
	public WorkItem(String title, String description, User user, Status status)
	{
		this.title = title;
		this.description = description;
		this.user = user;
		this.status = status;
	}
	
	/**
	 * @param title
	 *        the title to be updated
	 */
	public void updateTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * @param description
	 *        the description to be updated
	 */
	public void updateDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * @param user
	 *        the user to assign
	 */
	public void assignUser(User user)
	{
		this.user = user;
	}
	
	/**
	 * @param status
	 *        the status to set
	 */
	public void updateStatus(Status status)
	{
		this.status = status;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * @return the status
	 */
	public Status getStatus()
	{
		return status;
	}
	
	static final User NULL_USER = new User();
}
