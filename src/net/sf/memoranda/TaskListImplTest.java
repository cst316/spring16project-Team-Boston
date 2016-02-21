package net.sf.memoranda;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.date.CalendarDate;

public class TaskListImplTest {

	TaskListImpl testList;
	String[] taskIDs;
	@Before
	public void setUp() throws Exception {
		testList = new TaskListImpl();
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask1", 0, 0, 0, "", null, true);
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask2", 0, 0, 0, "", null, true);
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask3", 0, 0, 0, "", null, true);
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask4", 0, 0, 0, "", null, true);
		
		taskIDs = new String[4];
		Task[] tasks = testList.getTopLevelTasks().toArray(new Task[0]);
		for (int i = 0; i < taskIDs.length; i++)
		{
			taskIDs[i] = tasks[i].getID();
		}
		

		testList.getTask(taskIDs[0]).addSubTask(new TaskImpl(new ArrayList<Task>()));
		testList.getTask(taskIDs[0]).addSubTask(new TaskImpl(new ArrayList<Task>()));
		Task[] rootTask1SubTasks = testList.getTask(taskIDs[0]).getSubTasks().toArray(new Task[0]);
		rootTask1SubTasks[1].setFrozen(true);
	}

	@Test
	public void testGetTopLevelTasks() {
		assertTrue(testList.getTopLevelTasks().size() == 4);
	}

	@Test
	public void testGetActiveSubTasks() {
		assertTrue(testList.getActiveSubTasks(taskIDs[0], new CalendarDate()).size() == 1);
	}

	@Test
	public void testGetAllSubTasks() {
		assertTrue(testList.getAllSubTasks(taskIDs[0]).size() == 2);
	}

	@Test
	public void testGetTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDuplicateTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreateTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testHasParentTask() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testHasSubTasks() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveTask() {
		fail("Not yet implemented"); // TODO
	}

}
