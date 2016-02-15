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
		task = new TaskImpl (null);
		assertTrue (task != null);
	}
	
	@Test
	public void
	getDescriptionTest ()
	{
		String param;
		param = "";
		task.setDescription (param);
		assertEquals (task.getDescription (), param);
	}
	
	@Test
	public void
	getEffortTest ()
	{
		long param;
		param = 0l;
		task.setEffort (param);
		assertEquals (task.getEffort (), param);
	}
	
	@Test
	public void
	getEffortActualTest ()
	{
		long param;
		param = 0l;
		task.setEffortActual (param);
		assertEquals (task.getEffortActual (), param);
	}
	
	@Test
	public void
	getEndDateTest ()
	{
		CalendarDate param;
		param = new CalendarDate ();
		task.setEndDate (param);
		assertEquals (task.getEndDate (), param);
	}
	
	@Test
	public void
	getIDTest ()
	{
		String param;
		param = "";
		task.setID (param);
		assertEquals (task.getID (), param);
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
		child = new TaskImpl (task);
		task.addSubTask (child);
		assertEquals (child.getParentID (), "");
	}
	
	@Test
	public void
	getParentTaskTest ()
	{
		Task parent;
		parent = new TaskImpl (null);
		parent.setID ("sameID");
		Task child = new TaskImpl (parent);
		child.setID ("sameID");
		assertEquals (child.getParentTask (), parent);
	}
	
	@Test
	public void
	getPriorityTest ()
	{
		int param;
		param = 0;
		task.setPriority (param);
		assertEquals (task.getPriority (), param);
	}
	
	@Test
	public void
	getProgressTest ()
	{
		int param;
		param = 0;
		task.setProgress (param);
		assertEquals (task.getProgress (), param);
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
		assertEquals (task.getStartDate (), param);
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
		child = new TaskImpl (task);
		child.setID ("child");
		container.add (child);
		task.setSubTasks (container);
		assertEquals (task.getSubTask ("child"), child);
	}
	
	@Test
	public void
	getSubTasksTest ()
	{
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (new TaskImpl (task));
		param.add (new TaskImpl (task));
		task.setSubTasks (param);
		assertEquals (task.getSubTasks (), param);
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
		assertEquals (task.getUpdateSubTasks (), param);
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
		assertEquals (task.getStatus (new CalendarDate (3, 5, 2016)), Task.FROZEN);
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
		child = new TaskImpl (task);
		child.setID ("child");
		task.addSubTask (child);
		assertEquals (task.getSubTask ("child"), child);
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
		child0 = new TaskImpl (task);
		child0.setID ("child0");
		param.add (child0);
		Task child1;
		child1 = new TaskImpl (task);
		child1.setID ("child1");
		param.add (child1);
		task.addSubTasks (param);
		assertEquals (task.getSubTask ("child0"), child0);
		assertEquals (task.getSubTask ("child1"), child1);
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
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.get (0).setEndDate (new CalendarDate (1, 1, 2014));
		taskContainer.get (1).setEndDate (new CalendarDate (3, 4, 2016));
		taskContainer.get (2).setEndDate (new CalendarDate (12, 12, 2016));
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.get (0).setEndDate (new CalendarDate (5, 8, 2015));
		childContainer.get (1).setEndDate (new CalendarDate (9, 7, 2015));
		childContainer.get (2).setEndDate (new CalendarDate (10, 10, 2016));
		task.recursivelyModifyEndDateFromSubTasks ();
		assertEquals (task.getEndDate (), new CalendarDate (12, 12, 2016));
		assertEquals (taskContainer.get (0).getEndDate (), new CalendarDate (10, 10, 2016));
	}
	
	@Test
	public void
	recursivelyModifyStartDateFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		taskContainer = new ArrayList<Task> ();
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.get (0).setStartDate (new CalendarDate (1, 1, 2014));
		taskContainer.get (1).setStartDate (new CalendarDate (3, 4, 2016));
		taskContainer.get (2).setStartDate (new CalendarDate (12, 12, 2016));
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.get (0).setStartDate (new CalendarDate (5, 8, 2015));
		childContainer.get (1).setStartDate (new CalendarDate (9, 7, 2015));
		childContainer.get (2).setStartDate (new CalendarDate (10, 10, 2016));
		task.recursivelyModifyStartDateFromSubTasks ();
		assertEquals (task.getStartDate (), new CalendarDate (1, 1, 2014));
		assertEquals (taskContainer.get (0).getStartDate (), new CalendarDate (5, 8, 2015));
	}
	
	@Test
	public void
	recursivelyModifyEffortFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		taskContainer = new ArrayList<Task> ();
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.add (new TaskImpl (task));
		taskContainer.get (0).setEffort (1);
		taskContainer.get (1).setEffort (2);
		taskContainer.get (2).setEffort (3);
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.add (new TaskImpl (taskContainer.get (0)));
		childContainer.get (0).setEffort (3);
		childContainer.get (1).setEffort (4);
		childContainer.get (2).setEffort (5);
		task.recursivelyModifyEffortFromSubTasks ();
		assertEquals (task.getEffort (), 17);
		assertEquals (taskContainer.get (0).getEffort (), 12);
		assertEquals (taskContainer.get (1).getEffort (), 2);
		assertEquals (taskContainer.get (2).getEffort (), 3);
		assertEquals (childContainer.get (0).getEffort (), 3);
		assertEquals (childContainer.get (1).getEffort (), 4);
		assertEquals (childContainer.get (2).getEffort (), 5);
	}
	
	@Test
	public void
	removeAllSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (task);
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (task);
		container.add (child1);
		task.setSubTasks (container);
		task.removeAllSubTasks ();
		assertEquals (task.getSubTasks ().size (), 0);
	}
	
	@Test
	public void
	removeSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (task);
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (task);
		child1.setID ("child1");
		container.add (child1);
		task.setSubTasks (container);
		task.removeSubTask (child0);
		assertEquals (task.getSubTasks ().size (), 1);
		assertNull (task.getSubTask ("child0"));
		assertEquals (task.getSubTask ("child1"), child1);
	}
	
	@Test
	public void
	removeSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (task);
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new TaskImpl (task);
		child1.setID ("child1");
		container.add (child1);
		Task child2;
		child2 = new TaskImpl (task);
		child2.setID ("child2");
		container.add (child2);
		task.setSubTasks (container);
		Task child0prime;
		child0prime = new TaskImpl (task);
		child0prime.setID ("child0");
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (child0prime);
		Task child2prime;
		child2prime = new TaskImpl (task);
		child2prime.setID ("child2");
		param.add (child2prime);
		task.removeSubTasks (param);
		assertEquals (task.getSubTasks ().size (), 1);
		assertNull (task.getSubTask ("child0"));
		assertEquals (task.getSubTask ("child1"), child1);
		assertNull (task.getSubTask ("child2"));
	}
}