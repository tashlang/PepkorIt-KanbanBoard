
package com.pepkorit.assessment.kanban.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pepkorit.assesment.kanban.KanbanBoard;
import com.pepkorit.assesment.kanban.XIllegalAction;
import com.pepkorit.assesment.kanban.dto.Status;
import com.pepkorit.assesment.kanban.dto.User;
import com.pepkorit.assesment.kanban.dto.WorkItem;

/**
 * Should test the validation for not exceeding the max 5 In-Progress tickets.
 * 
 * @author Natasha
 */
public class InProgressTest
{
	static KanbanBoard board;
	
	@Before
	public void initBoard() throws XIllegalAction
	{
		board = new KanbanBoard();
		User a = new User("UserA", "uA@sample.com");
		User b = new User("UserB", "uB@sample.com");
		User c = new User("UserC", "uC@sample.com");
		board.addWorkItem(new WorkItem("Task 1", "Setup-Users", c, Status.IN_PROGRESS));
		board.addWorkItem(new WorkItem("Task 2", "Remove-Users", c, Status.IN_PROGRESS));
		board.addWorkItem(new WorkItem(
				"Make Up Tasks",
				"Set up some dummy tasks",
				a,
				Status.IN_PROGRESS));
		
		board.addWorkItem(new WorkItem(
				"Complete GUI",
				"Have some gui layout",
				b,
				Status.IN_PROGRESS));
		
		board.addWorkItem(new WorkItem("Complete Board", "Complete backend", b, Status.IN_PROGRESS));
		
		board.addWorkItem(new WorkItem(
				"Have fun",
				"Spend some time learning a new lesssson",
				a,
				Status.DONE));
	}
	
	/**
	 * Test method for
	 * {@link com.pepkorit.assesment.kanban.KanbanBoard#addWorkItem(com.pepkorit.assesment.kanban.dto.WorkItem)}.
	 * <br>
	 * Adding a new In-Progress Ticket
	 */
	@Test
	public void testAddWorkItem()
	{
		// ensure 5 items in progress
		int count = board.getWorkItemsByStatus(Status.IN_PROGRESS).size();
		assertEquals(5, count);
		User newU = new User("UserNew", "new@sample.com");
		try
		{
			board.addWorkItem(new WorkItem(
					"Fail Task",
					"This task should fail bc too many in progress tasks",
					newU,
					Status.IN_PROGRESS));
			// This should throw and exception
			fail();
		}
		catch (Exception e)
		{
			assertEquals(XIllegalAction.class, e.getClass());
		}
	}
	
	/**
	 * Test method for
	 * {@link com.pepkorit.assesment.kanban.KanbanBoard#saveWorkItem(java.lang.String, com.pepkorit.assesment.kanban.dto.WorkItem)}.
	 * <br>
	 * Adding a new Done Ticket, and then updating it to In-Progress
	 */
	@Test
	public void testSaveWorkItem()
	{
		// ensure 5 items in progress
		int count = board.getWorkItemsByStatus(Status.IN_PROGRESS).size();
		assertEquals(5, count);
		User newU = new User("UserNew", "new@sample.com");
		try
		{
			WorkItem testWI =
					new WorkItem(
							"Fail Task",
							"This task should fail bc too many in progress tasks",
							newU,
							Status.DONE);
			String id = board.addWorkItem(testWI);
			testWI =
					new WorkItem(
							"Fail Task",
							"This task should fail bc too many in progress tasks",
							newU,
							Status.IN_PROGRESS);
			board.saveWorkItem(id, testWI);
			// This should throw and exception
			fail();
		}
		catch (Exception e)
		{
			assertEquals(XIllegalAction.class, e.getClass());
		}
	}
	
}
