package net.sf.memoranda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;

public class
TaskImplTest
{
	private Task task = null;
	
	@Before
	public void
	setUp ()
	throws Exception
	{
		task = new TaskImpl (new ArrayList<Task> ());
		assertTrue (task != null);
	}
	
	@Test
	public void
	getDescriptionTest ()
	{
		String param;
		param = "";
		task.setDescription (param);
		assertEquals (param, task.getDescription ());
	}
	
	@Test
	public void
	getEffortTest ()
	{
		long param;
		param = 0l;
		task.setEffort (param);
		assertEquals (param, task.getEffort ());
	}
	
	@Test
	public void
	getEffortActualTest ()
	{
		long param;
		param = 0l;
		task.setEffortActual (param);
		assertEquals (param, task.getEffortActual ());
	}
	
	@Test
	public void
	getEndDateTest ()
	{
		CalendarDate param;
		param = new CalendarDate ();
		task.setEndDate (param);
		assertEquals (param, task.getEndDate ());
	}
	
	@Test
	public void
	getIDTest ()
	{
		String param;
		param = "";
		task.setID (param);
		assertEquals (param, task.getID ());
	}
	
	@Test
	public void
	getParentIDTest ()
	{
		assertEquals (task.getParentID (), null);
		String param;
		param = "";
		task.setID (param);
		Task child;
		child = new TaskImpl (new ArrayList<Task> ());
		task.addSubTask (child);
		assertEquals ("", child.getParentID ());
	}
	
	@Test
	public void
	getParentTaskTest ()
	{
		task.setID ("sameID");
		Task child = new TaskImpl (new ArrayList<Task> ());
		child.setID ("sameID");
		task.addSubTask (child);
		assertEquals (task, child.getParentTask ());
	}
	
	@Test
	public void
	getPriorityTest ()
	{
		int param;
		param = 0;
		task.setPriority (param);
		assertEquals (param, task.getPriority ());
	}
	
	@Test
	public void
	getProgressTest ()
	{
		int param;
		param = 0;
		task.setProgress (param);
		assertEquals (param, task.getProgress ());
	}
	
	@Test
	public void
	getRateTest ()
	{
		assertTrue (false);
	}
	
	@Test
	public void
	getStartDateTest ()
	{
		CalendarDate param;
		param = new CalendarDate ();
		task.setStartDate (param);
		assertEquals (param, task.getStartDate ());
	}
	
	@Test
	public void
	getStatusTest ()
	{
		setFrozenTest ();
	}
	
	@Test
	public void
	getSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child;
		child = new TaskImpl (new ArrayList<Task>());
		child.setID ("child");
		container.add (child);
		task.setSubTasks (container);
		assertEquals (child, task.getSubTask ("child"));
	}
	
	@Test
	public void
	getSubTasksTest ()
	{
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (new TaskImpl (new ArrayList<Task>()));
		param.add (new TaskImpl (new ArrayList<Task>()));
		task.setSubTasks (param);
		assertEquals (param, task.getSubTasks ());
	}
	
	@Test
	public void
	getTextTest ()
	{
		String param;
		param = "";
		task.setText (param);
		assertEquals (task.getText (), param);
	}
	
	@Test
	public void
	getUpdateSubTasksTest ()
	{
		boolean param;
		param = true;
		task.setUpdateSubTasks (param);
		assertEquals (param, task.getUpdateSubTasks ());
	}
	
	@Test
	public void
	setDescriptionTest ()
	{
		getDescriptionTest ();
	}
	
	@Test
	public void
	setEffortTest ()
	{
		getEffortTest ();
	}
	
	@Test
	public void
	setEffortActualTest ()
	{
		getEffortActualTest ();
	}
	
	@Test
	public void
	setEndDateTest ()
	{
		getEndDateTest ();
	}
	
	@Test
	public void
	setIDTest ()
	{
		getIDTest ();
	}
	
	@Test
	public void
	setFrozenTest ()
	{
		task.setFrozen (true);
		assertEquals ( Task.FROZEN, task.getStatus (new CalendarDate (3, 5, 2016)));
	}
	
	@Test
	public void
	setParentTaskTest ()
	{
		getParentTaskTest ();
	}
	
	@Test
	public void
	setPriorityTest ()
	{
		getPriorityTest ();
	}
	
	@Test
	public void
	setProgressTest ()
	{
		getProgressTest ();
	}
	
	@Test
	public void
	setStartDateTest ()
	{
		getStartDateTest ();
	}
	
	@Test
	public void
	setSubTasksTest ()
	{
		getSubTasksTest ();
	}
	
	@Test
	public void
	setTextTest ()
	{
		getTextTest ();
	}
	
	@Test
	public void
	setUpdateSubTasksTest ()
	{
		getUpdateSubTasksTest ();
	}
	
	@Test
	public void
	addSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		task.setSubTasks (container);
		Task child;
		child = new TaskImpl (new ArrayList<Task>());
		child.setID ("child");
		task.addSubTask (child);
		assertEquals (child, task.getSubTask ("child"));
	}
	
	@Test
	public void
	addSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		task.setSubTasks (container);
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task>());
		child0.setID ("child0");
		param.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task>());
		child1.setID ("child1");
		param.add (child1);
		task.addSubTasks (param);
		assertEquals (child0, task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
	}
	
	@Test
	public void
	deepCopyTest ()
	{
		task.setID ("sameID");
		Task param;
		param = task.deepCopy ();
		assertEquals (task, param);
	}
	
	@Test
	public void
	recursivelyModifyCompletionFromSubTasksTest ()
	{
		assertTrue (false);
	}
	
	@Test
	public void
	recursivelyModifyEndDateFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		taskContainer = new ArrayList<Task> ();
		task.setSubTasks (taskContainer);
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.get (0).setEndDate (new CalendarDate (1, 1, 2014));
		taskContainer.get (1).setEndDate (new CalendarDate (3, 4, 2016));
		taskContainer.get (2).setEndDate (new CalendarDate (12, 11, 2016));
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		taskContainer.get (0).setSubTasks (childContainer);
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.get (0).setEndDate (new CalendarDate (5, 8, 2015));
		childContainer.get (1).setEndDate (new CalendarDate (9, 7, 2015));
		childContainer.get (2).setEndDate (new CalendarDate (10, 10, 2016));
		task.recursivelyModifyEndDateFromSubTasks ();
		assertEquals (new CalendarDate (12, 11, 2016), task.getEndDate ());
		assertEquals (new CalendarDate (10, 10, 2016), taskContainer.get (0).getEndDate ());
	}
	
	@Test
	public void
	recursivelyModifyStartDateFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		taskContainer = new ArrayList<Task> ();
		task.setSubTasks (taskContainer);
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.get (0).setStartDate (new CalendarDate (1, 1, 2014));
		taskContainer.get (1).setStartDate (new CalendarDate (3, 4, 2016));
		taskContainer.get (2).setStartDate (new CalendarDate (12, 11, 2016));
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		taskContainer.get (0).setSubTasks (childContainer);
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.get (0).setStartDate (new CalendarDate (5, 8, 2015));
		childContainer.get (1).setStartDate (new CalendarDate (9, 7, 2015));
		childContainer.get (2).setStartDate (new CalendarDate (10, 10, 2016));
		task.recursivelyModifyStartDateFromSubTasks ();
		assertEquals (new CalendarDate (1, 1, 2014), task.getStartDate ());
		assertEquals (new CalendarDate (5, 8, 2015), taskContainer.get (0).getStartDate ());
	}
	
	@Test
	public void
	recursivelyModifyEffortFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		taskContainer = new ArrayList<Task> ();
		task.setSubTasks (taskContainer);
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.add (new TaskImpl (new ArrayList<Task>()));
		taskContainer.get (0).setEffort (1);
		taskContainer.get (1).setEffort (2);
		taskContainer.get (2).setEffort (3);
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.add (new TaskImpl (new ArrayList<Task>()));
		childContainer.get (0).setEffort (3);
		childContainer.get (1).setEffort (4);
		childContainer.get (2).setEffort (5);
		task.recursivelyModifyEffortFromSubTasks ();
		assertEquals (17,task.getEffort ());
		assertEquals (12, taskContainer.get (0).getEffort ());
		assertEquals (2, taskContainer.get (1).getEffort ());
		assertEquals (3, taskContainer.get (2).getEffort ());
		assertEquals (3, childContainer.get (0).getEffort ());
		assertEquals (4, childContainer.get (1).getEffort ());
		assertEquals (5, childContainer.get (2).getEffort ());
	}
	
	@Test
	public void
	removeAllSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task>());
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task>());
		container.add (child1);
		task.setSubTasks (container);
		task.removeAllSubTasks ();
		assertEquals (0, task.getSubTasks ().size ());
	}
	
	@Test
	public void
	removeSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task>());
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task>());
		child1.setID ("child1");
		container.add (child1);
		task.setSubTasks (container);
		task.removeSubTask (child0);
		assertEquals (1, task.getSubTasks ().size ());
		assertNull (task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
	}
	
	@Test
	public void
	removeSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task>());
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task>());
		child1.setID ("child1");
		container.add (child1);
		Task child2;
		child2 = new TaskImpl (new ArrayList<Task>());
		child2.setID ("child2");
		container.add (child2);
		task.setSubTasks (container);
		Task child0prime;
		child0prime = new TaskImpl (new ArrayList<Task>());
		child0prime.setID ("child0");
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (child0prime);
		Task child2prime;
		child2prime = new TaskImpl (new ArrayList<Task>());
		child2prime.setID ("child2");
		param.add (child2prime);
		task.removeSubTasks (param);
		assertEquals (1, task.getSubTasks ().size ());
		assertNull (task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
		assertNull (task.getSubTask ("child2"));
	}
}