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
		task.setDescription (null);
		assertEquals ("", task.getDescription ());
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
		Task child = new TaskImpl (new ArrayList<Task> ());
		task.addSubTask (child);
		child.setEndDate (null);
		assertEquals (param, child.getEndDate ());
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
		CalendarDate start = new CalendarDate ();
		CalendarDate end = new CalendarDate ();
		task.setStartDate (start);
		task.setEndDate (end);
		assertTrue (task.getRate () == -100);
		task.setEndDate (CalendarDate.yesterday ());
		assertTrue (task.getRate () == -1);
	}
	
	@Test
	public void
	getStartDateTest ()
	{
		CalendarDate param;
		param = new CalendarDate ();
		task.setStartDate (param);
		assertEquals (param, task.getStartDate ());
		Task child = new TaskImpl (new ArrayList<Task> ());
		task.addSubTask (child);
		child.setStartDate (null);
		assertEquals (param, child.getStartDate ());
	}
	
	@Test
	public void
	getStatusTest ()
	{
		CalendarDate date = new CalendarDate (1, 1, 1);
		task.setStartDate (new CalendarDate (2, 2, 2));
		task.setEndDate (new CalendarDate (4, 4, 4));
		assertEquals (Task.FAILED, task.getStatus (date));
		date = new CalendarDate (5, 5, 5);
		assertEquals (Task.SCHEDULED, task.getStatus (date));
		date = new CalendarDate (3, 3, 3);
		assertEquals (Task.ACTIVE, task.getStatus (date));
		date = new CalendarDate (4, 4, 4);
		assertEquals (Task.DEADLINE, task.getStatus (date));
		date = new CalendarDate (1, 1, 1);
		task.setProgress (100);
		assertEquals (Task.COMPLETED, task.getStatus (date));
		setFrozenTest ();
	}
	
	@Test
	public void
	getSubTaskTest ()
	{
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		Task child;
		child = new TaskImpl (new ArrayList<Task> ());
		child.setID ("child");
		collection.add (child);
		task.setSubTasks (collection);
		assertEquals (child, task.getSubTask ("child"));
	}
	
	@Test
	public void
	getSubTasksTest ()
	{
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (new TaskImpl (new ArrayList<Task> ()));
		param.add (new TaskImpl (new ArrayList<Task> ()));
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
		task.setText (null);
		assertEquals ("", task.getText ());
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
		task.setText (null);
		assertEquals ("", task.getText ());
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
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		task.setSubTasks (collection);
		Task child;
		child = new TaskImpl (new ArrayList<Task> ());
		child.setID ("child");
		task.addSubTask (child);
		assertEquals (child, task.getSubTask ("child"));
	}
	
	@Test
	public void
	addSubTasksTest ()
	{
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		task.setSubTasks (collection);
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task> ());
		child0.setID ("child0");
		param.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task> ());
		child1.setID ("child1");
		param.add (child1);
		task.addSubTasks (param);
		assertEquals (child0, task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
	}
	
	@Test
	public void
	compareToTest ()
	{
		Task other = new TaskImpl (new ArrayList<Task> ());
		other.setPriority (2);
		task.setPriority (1);
		assertTrue (task.compareTo (other) > 0);
		other.setPriority (1);
		assertTrue (task.compareTo (other) == 0);
		other.setPriority (0);
		assertTrue (task.compareTo (other) < 0);
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
		TaskImpl rootTask = new TaskImpl (new ArrayList<Task> ());
		rootTask.setUpdateSubTasks (true);
		TaskImpl rootSubTask1 = new TaskImpl (new ArrayList<Task> ());
		rootSubTask1.setUpdateSubTasks (true);
		TaskImpl rootSubTask2 = new TaskImpl (new ArrayList<Task> ());
		TaskImpl subTask1Child1 = new TaskImpl (new ArrayList<Task> ());
		TaskImpl subTask1Child2 = new TaskImpl (new ArrayList<Task> ());
		rootTask.addSubTask (rootSubTask1);
		rootTask.addSubTask (rootSubTask2);
		rootSubTask1.addSubTask (subTask1Child1);
		rootSubTask1.addSubTask (subTask1Child2);
		
		subTask1Child1.setProgress (25);
		subTask1Child2.setProgress (100);
		rootSubTask2.setProgress (50);
		
		rootTask.recursivelyModifyCompletionFromSubTasks ();
		assertTrue (rootTask.getProgress() == 56);

		
	}
	
	@Test
	public void
	recursivelyModifyEffortFromSubTasksTest ()
	{
		ArrayList<Task> childCollection;
		childCollection = new ArrayList<Task> ();
		task.setSubTasks (childCollection);
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.get (0).setEffort (1);
		childCollection.get (1).setEffort (2);
		childCollection.get (2).setEffort (3);
		ArrayList<Task> grandChildCollection;
		grandChildCollection = new ArrayList<Task> ();
		childCollection.get (0).setSubTasks (grandChildCollection);
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.get (0).setEffort (3);
		grandChildCollection.get (1).setEffort (4);
		grandChildCollection.get (2).setEffort (5);
		task.recursivelyModifyEffortFromSubTasks ();
		assertEquals (17, task.getEffort ());
		assertEquals (12, childCollection.get (0).getEffort ());
		assertEquals (2, childCollection.get (1).getEffort ());
		assertEquals (3, childCollection.get (2).getEffort ());
		assertEquals (3, grandChildCollection.get (0).getEffort ());
		assertEquals (4, grandChildCollection.get (1).getEffort ());
		assertEquals (5, grandChildCollection.get (2).getEffort ());
	}

	@Test
	public void
	recursivelyModifyEndDateFromSubTasksTest ()
	{
		CalendarDate a =  new CalendarDate (2, 3, 2014);
		CalendarDate b = new CalendarDate (2, 2, 2016);
		CalendarDate c = new CalendarDate (1, 1, 2010);
		CalendarDate d = new CalendarDate (3, 2, 2011);
		CalendarDate e = new CalendarDate (11, 11, 2015);
		CalendarDate f = new CalendarDate (9, 7, 2014);
		ArrayList<Task> childCollection;
		childCollection = new ArrayList<Task> ();
		task.setSubTasks (childCollection);
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.get (0).setEndDate (a);
		childCollection.get (1).setEndDate (b);
		childCollection.get (2).setEndDate (c);
		ArrayList<Task> grandChildCollection;
		grandChildCollection = new ArrayList<Task> ();
		childCollection.get (0).setSubTasks (grandChildCollection);
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.get (0).setEndDate (d);
		grandChildCollection.get (1).setEndDate (e);
		grandChildCollection.get (2).setEndDate (f);
		task.recursivelyModifyEndDateFromSubTasks ();
		assertEquals (b, task.getEndDate ());
		assertEquals (e, childCollection.get (0).getEndDate ());
	}
	
	@Test
	public void
	recursivelyModifyStartDateFromSubTasksTest ()
	{
		CalendarDate a = new CalendarDate (2, 3, 2014);
		CalendarDate b = new CalendarDate (2, 2, 2016);
		CalendarDate c = new CalendarDate (1, 1, 2010);
		CalendarDate d =new CalendarDate (3, 2, 2011); 
		CalendarDate e = new CalendarDate (11, 11, 2015);
		CalendarDate f = new CalendarDate (9, 7, 2014);
		ArrayList<Task> childCollection;
		childCollection = new ArrayList<Task> ();
		task.setSubTasks (childCollection);
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.add (new TaskImpl (new ArrayList<Task> ()));
		childCollection.get (0).setStartDate (a);
		childCollection.get (1).setStartDate (b);
		childCollection.get (2).setStartDate (c);
		ArrayList<Task> grandChildCollection;
		grandChildCollection = new ArrayList<Task> ();
		childCollection.get (0).setSubTasks (grandChildCollection);
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.add (new TaskImpl (new ArrayList<Task> ()));
		grandChildCollection.get (0).setStartDate (d);
		grandChildCollection.get (1).setStartDate (e);
		grandChildCollection.get (2).setStartDate (f);
		task.recursivelyModifyStartDateFromSubTasks ();
		assertEquals (c, task.getStartDate ());
		assertEquals (d, childCollection.get (0).getStartDate ());
	}
	
	@Test
	public void
	removeAllSubTasksTest ()
	{
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task> ());
		collection.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task> ());
		collection.add (child1);
		task.setSubTasks (collection);
		task.removeAllSubTasks ();
		assertEquals (0, task.getSubTasks ().size ());
	}
	
	@Test
	public void
	removeSubTaskTest ()
	{
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task> ());
		child0.setID ("child0");
		collection.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task> ());
		child1.setID ("child1");
		collection.add (child1);
		task.setSubTasks (collection);
		task.removeSubTask (child0);
		assertEquals (1, task.getSubTasks ().size ());
		assertNull (task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
	}
	
	@Test
	public void
	removeSubTasksTest ()
	{
		ArrayList<Task> collection;
		collection = new ArrayList<Task> ();
		Task child0;
		child0 = new TaskImpl (new ArrayList<Task> ());
		child0.setID ("child0");
		collection.add (child0);
		Task child1;
		child1 = new TaskImpl (new ArrayList<Task> ());
		child1.setID ("child1");
		collection.add (child1);
		Task child2;
		child2 = new TaskImpl (new ArrayList<Task> ());
		child2.setID ("child2");
		collection.add (child2);
		task.setSubTasks (collection);
		Task child0prime;
		child0prime = new TaskImpl (new ArrayList<Task> ());
		child0prime.setID ("child0");
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (child0prime);
		Task child2prime;
		child2prime = new TaskImpl (new ArrayList<Task> ());
		child2prime.setID ("child2");
		param.add (child2prime);
		task.removeSubTasks (param);
		assertEquals (1, task.getSubTasks ().size ());
		assertNull (task.getSubTask ("child0"));
		assertEquals (child1, task.getSubTask ("child1"));
		assertNull (task.getSubTask ("child2"));
	}
	
	private Task task = null;
}