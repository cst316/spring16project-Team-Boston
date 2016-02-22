package net.sf.memoranda;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.date.CalendarDate;

public class 
TaskListImplTest 
{ 

	@Before
	public void 
	setUp() 
	throws Exception 
	{
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
		
		
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask1Sub1", 0, 0, 0, "", taskIDs[0], false);
		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask1Sub2", 0, 0, 0, "", taskIDs[0], false);
		Task[] rootTask1SubTasks = testList.getTask(taskIDs[0]).getSubTasks().toArray(new Task[0]);
		rootTask1SubTasks[1].setFrozen(true);
		testSubTask = rootTask1SubTasks[0];
		testList.createTask(new CalendarDate(), new CalendarDate(), "testSubTaskChild", 0, 0, 0, "", testSubTask.getID(), false);
	}

	@Test
	public void 
	testGetTopLevelTasks() 
	{
		assertTrue(testList.getTopLevelTasks().size() == 4);
	}

	@Test
	public void 
	testGetActiveSubTasks() 
	{
		assertTrue(testList.getActiveSubTasks(taskIDs[0], new CalendarDate()).size() == 1);
	}

	@Test
	public void 
	testGetAllSubTasks() 
	{
		assertTrue(testList.getAllSubTasks(null).size() == 4);
		assertTrue(testList.getAllSubTasks(taskIDs[0]).size() == 2);
	}

	@Test
	public void 
	testGetTask() 
	{
		assertTrue(testList.getTask(taskIDs[1]) != null);
		assertTrue(testList.getTask("") == null);
	}

	@Test
	public void 
	testDuplicateTask() 
	{
		Task rootTask1Copy = testList.duplicateTask(testList.getTask(taskIDs[0]));
		Task[] rootTask1SubTasks = testList.getAllSubTasks(taskIDs[0]).toArray(new Task[0]);
		Task[] rootTask1CopySubTasks = testList.getAllSubTasks(rootTask1Copy.getID()).toArray(new Task[0]);
		for(int i = 0; i < rootTask1SubTasks.length; i++)
		{
			assertTrue(rootTask1SubTasks[i].getID() != rootTask1CopySubTasks[i].getID() && rootTask1SubTasks[i].getText().equals(rootTask1CopySubTasks[i].getText()));
		}
		Task rootTask2 = testList.getTask(taskIDs[1]);
		Task rootTask2Copy = testList.duplicateTask(rootTask2);
		assertTrue(rootTask2Copy.getID() != testList.getTask(taskIDs[1]).getID() && rootTask2Copy.getText().equals(rootTask2.getText()));
	}

	@Test
	public void 
	testCreateTask() 
	{

		testList.createTask(new CalendarDate(), new CalendarDate(), "rootTask5", 0, 0, 0, "", null, true);
		assertTrue(testList.getTopLevelTasks().size() == 5);
	}

	@Test
	public void 
	testHasParentTask() 
	{
		assertTrue(!testList.hasParentTask(taskIDs[1]));
		assertTrue(testList.hasParentTask(testSubTask.getID()));
	}

	@Test
	public void 
	testHasSubTasks() 
	{
		assertTrue(testList.hasSubTasks(taskIDs[0]));
		assertTrue(!testList.hasSubTasks(taskIDs[3]));
		assertTrue(!testList.hasSubTasks("117"));
	}

	@Test
	public void 
	testRemoveTask() 
	{
		testList.removeTask(testSubTask);
		assertTrue(testList.getTopLevelTasks().size() == 4 && testList.getAllSubTasks(taskIDs[0]).size() == 1);
	}
	
	private TaskListImpl testList;
	private String[] taskIDs;
	private Task testSubTask;

}
