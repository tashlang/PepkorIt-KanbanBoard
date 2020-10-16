
package com.pepkorit.assesment.kanban;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import com.pepkorit.assesment.kanban.dto.Status;
import com.pepkorit.assesment.kanban.dto.User;
import com.pepkorit.assesment.kanban.dto.WorkItem;

/**
 * @author Natasha
 *
 */
public class KanbanBoard
{
	private int               maxInProgressCapacity = 5;
	HashMap<String, WorkItem> workItems             = new HashMap<>();
	HashSet<User>             users                 = new HashSet<>();
	
	/**
	 * Retrieves a sub-set of the work items, assigned to a specific
	 * {@link Status}
	 * 
	 * @param staus
	 *        The {@link Status} to filter on
	 * @return
	 */
	public HashMap<String, WorkItem> getWorkItemsByStatus(Status staus)
	{
		HashMap<String, WorkItem> subWorkItems = new HashMap<String, WorkItem>();
		
		for (String wiID : workItems.keySet())
		{
			WorkItem wi = workItems.get(wiID);
			if (wi.getStatus() == staus)
			{
				subWorkItems.put(wiID, wi);
			}
		}
		return subWorkItems;
	}
	
	/**
	 * Adds a new work item to the cache, and returns the work item's unique id.
	 * 
	 * @param newWi
	 *        The new {@link WorkItem} to be added
	 * @return the {@link WorkItem} unique id.
	 * @throws XIllegalAction
	 */
	public String addWorkItem(WorkItem newWi) throws XIllegalAction
	{
		validateWorkItemStatus(newWi);
		String wiID = "";
		// this should only happen once,
		// but in the case of a freak incident, we will be safe
		do
		{
			wiID = UUID.randomUUID().toString();
		}
		while (workItems.containsKey(wiID));
		
		return saveWorkItem(wiID, newWi);
	}
	
	/**
	 * Removes a {@link WorkItem} from the cache, using the work item ID
	 * 
	 * @param wiID
	 *        The unique id of the work item to be removed
	 * @throws XIllegalAction
	 */
	public void removeWorkItem(String wiID) throws XIllegalAction
	{
		validateWorkItemID(wiID);
		workItems.remove(wiID);
	}
	
	/**
	 * Saves a work item, and returns the work item's unique id.
	 * 
	 * @param newWi
	 *        The {@link WorkItem} to be updated
	 * @return
	 * @throws XIllegalAction
	 */
	public String saveWorkItem(String wiID, WorkItem newWi) throws XIllegalAction
	{
		validateWorkItemStatus(newWi);
		addUserIfMissing(newWi.getUser());
		workItems.put(wiID, newWi);
		return wiID;
	}
	
	/**
	 * Assigns a {@link User} to the linked {@link WorkItem}.
	 * 
	 * @param wiID
	 *        The {@link WorkItem} identifier.
	 * @param user
	 *        The {@link User} to be assigned.
	 * @throws XIllegalAction
	 */
	public void assignUser(String wiID, User user) throws XIllegalAction
	{
		validateWorkItemID(wiID);
		WorkItem wi = workItems.get(wiID);
		wi.assignUser(user);
		saveWorkItem(wiID, wi);
	}
	
	/**
	 * Updates the {@link Status} of the linked {@link WorkItem}.
	 * 
	 * @param wiID
	 *        The {@link WorkItem} identifier.
	 * @param status
	 *        The new {@link Status} of the item.
	 * @throws XIllegalAction
	 */
	public void updateStatus(String wiID, Status status) throws XIllegalAction
	{
		validateWorkItemID(wiID);
		// no validation here, as it will be done in updateWorkItem
		WorkItem wi = workItems.get(wiID);
		wi.updateStatus(status);
		saveWorkItem(wiID, wi);
	}
	
	/**
	 * Adds a user to the cache if not present
	 * 
	 * @param user
	 *        The {@link User} to be verified.
	 */
	public void addUserIfMissing(User user)
	{
		if (users.contains(user))
		{
			return;
		}
		users.add(user);
	}
	
	//TODO
	public HashSet<User> getAllRegisteredUsers()
	{
		return users;
	}
	
	/**
	 * Validates the number of in progress work items if this {@link WorkItem}
	 * has a status of {@link Status#IN_PROGRESS}.
	 * 
	 * @param wItem
	 *        The {@link WorkItem} to be validated.
	 * @return
	 * @throws XIllegalAction
	 *         When the count is at capacity.
	 */
	private void validateWorkItemStatus(WorkItem wItem) throws XIllegalAction
	{
		if (wItem.getStatus() != Status.IN_PROGRESS)
		{
			return;
		}
		HashMap<String, WorkItem> inProgress = getWorkItemsByStatus(Status.IN_PROGRESS);
		if (inProgress.size() == maxInProgressCapacity)
		{
			throw new XIllegalAction(
					"Cannot have more than " + maxInProgressCapacity +
							" work items with status 'In-Progress'.");
		}
	}
	
	/**
	 * Validates the work item ID against the existing cache.
	 * 
	 * @param wItem
	 *        The {@link WorkItem} to be validated.
	 * @return
	 * @throws XIllegalAction
	 *         When the ID is null or does not have an entry in the cache.
	 */
	private void validateWorkItemID(String wItemID) throws XIllegalAction
	{
		if (wItemID == null)
		{
			throw new XIllegalAction("NULL work item ID is not acceptable. Please use a valid ID.");
		}
		
		if (!workItems.containsKey(wItemID))
		{
			throw new XIllegalAction("Unrecognised work item ID. Please use a valid ID.");
		}
	}
}
