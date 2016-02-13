package net.sf.memoranda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class
TaskImplTest
{
	private Task task = null;
	
	@Before
	public void
	setUp ()
	throws Exception
	{
		task = new TaskImpl ();
		assertTrue (task != null);
	}

	public void
	getActiveTest ()
	{
		boolean param;
		param = true;
		task.setActive (param);
		assertEquals (task.getActive (),  param);
	}
	
	public void
	getDescriptionTest ()
	{
		String param;
		param = "";
		task.setDescription (param);
		assertEquals (task.getDescription (), param);
	}
	
	public void
	getEffortTest ()
	{
		long param;
		param = 0.0f;
		task.setEffort (param);
		assertEquals (task.getEffort (), param);
	}
	
	public void
	getEffortActualTest ()
	{
		long param;
		param = 0.0f;
		task.setEffortActual (param);
		assertEquals (task.getEffortActual (), param);
	}
	
	public void
	getEndDateTest ()
	{
		CalenderDate param;
		param = new CalenderDate ();
		task.setEndDate (param);
		assertEquals (task.getEndDate (), param);
	}
	
	public void
	getIDTest ()
	{
		String param;
		param = "";
		task.setID (param);
		assertEquals (task.getID (), param);
	}
	
	public void
	getParentIdTest ()
	{
		String param;
		param = "";
		task.setParentID (param);
		assertEquals (task.getParentID (), param);
	}
	
	public void
	getParentTaskTest ()
	{
		Task parent;
		parent = new TaskImpl (null);
		Task child = new TaskImpl (parent);
		assertEquals (child.getParentTask (), parent);
	}
	
	public void
	getPriorityTest ()
	{
		int param;
		param = 0;
		task.setPriority (param);
		assertEquals (task.getPriority (), param);
	}
	
	public void
	getProgressTest ()
	{
		int param
		param = 0;
		task.setProgress (param);
		assertEquals (task.getProgress (), param);
	}
	
	public void
	getRateTest ()
	{
		long param;
		param = 0.0f;
		task.setRate (param);
		assertEquals (task.getRate (), param);
	}
	
	public void
	getStartDateTest ()
	{
		CalenderDate param;
		param = new CalenderDate ();
		task.setStartDate (param);
		assertEquals (task.getStartDate (), param);
	}
	
	public void
	getStatusTest ()
	{
		int param;
		param = Task.ACTIVE;
		task.setStatus (param);
		assertEquals (task.getStatus (), param);
	}
	
	public void
	getSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child;
		child = new Task (task);
		child.setID ("child");
		container.add (child);
		task.setSubTasks (container);
		assertEquals (task.getSubTask ("child"), child);
	}
	
	public void
	getSubTasksTest ()
	{
		ArrayList<Task> param;
		param = new ArrayList<Task> ();
		param.add (new Task (task));
		param.add (new Task (task));
		task.setSubTasks (param);
		assertEquals (task.getSubTasks (), param);
	}
	
	public void
	getTextTest ()
	{
		String param;
		param = "";
		task.setText (param);
		assertEquals (task.getText (), param);
	}
	
	public void
	getUpdateSubTasksTest ()
	{
		boolean param;
		param = true;
		task.setUpdateSubTasks (param);
		assertEquals (task.getUpdateSubTasks (), param);
	}
	
	
	public void
	setActiveTest ()
	{
		getActiveTest ();
	}
	
	public void
	setDescriptionTest ()
	{
		getDescriptionTest ();
	}
	
	public void
	setEffortTest ()
	{
		getEffortTest ();
	}
	
	public void
	setEffortActualTest ()
	{
		getEffortActualTest ();
	}
	
	public void
	setEndDateTest ()
	{
		getEndDateTest ();
	}
	
	public void
	setIDTest (String id)
	{
		getIDTest (id);
	}
	
	public void
	setFrozenTest ()
	{
		getFrozenTest ();
	}
	
	public void
	setParentTaskTest ()
	{
		getParentTaskTest ();
	}
	
	public void
	setPriorityTest ()
	{
		getPriorityTest ();
	}
	
	public void
	setProgressTest ()
	{
		getProgressTest ();
	}
	
	public void
	setStartDateTest ()
	{
		getStartDateTest ();
	}
	
	public void
	setSubTasksTest ()
	{
		getSubTasksTest ();
	}
	
	public void
	setTextTest (String s)
	{
		getTextTest ();
	}
	
	public void
	setUpdateSubTasksTest ()
	{
		getUpdateSubTasksTest ();
	}
	
	
	public void
	addSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		task.setSubTasks (container);
		Task child;
		child = new Task (task);
		child.setID ("child");
		task.addSubTask (child);
		assertEquals (task.getSubTask ("child"), child);
	}
	
	public void
	addSubTasksTest (Collection<Task> tasks)
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		task.setSubTasks (container);
		ArrayList<Task> param;
		Task child0;
		child0 = new Task (task);
		child0.setID ("child0");
		param.add (child0);
		Task child1;
		child1 = new Task (task);
		child1.setID ("child1");
		param.add (child1);
		task.addSubTasks (param);
		assertEquals (task.getSubTask ("child0"), child0);
		assertEquals (task.getSubTask ("child1"), child1);
	}
	
	public void
	deepCopyTest ()
	{
		Task param;
		param = task.deepCopy ();
		assertEquals (task, param);
	}
	
	public void
	recursivelyModifyCompletionFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyEndDateFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyStartDateFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyTotalEffortFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		container = new ArrayList<Task> ();
		taskContainer.add (new Task (task));
		taskContainer.add (new Task (task));
		taskContainer.add (new Task (task));
		taskContainer.get (0).setTotalEffort (1);
		taskContainer.get (1).setTotalEffort (2);
		taskContainer.get (2).setTotalEffort (3);
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new Task (container.get (0)));
		childContainer.add (new Task (container.get (0)));
		childContainer.add (new Task (container.get (0)));
		childContainer.get (0).setTotalEffort (3);
		childContainer.get (1).setTotalEffort (4);
		childContainer.get (2).setTotalEffort (5);
		task.recursivelyModifyTotalEffortFromSubtasks ();
		assertEquals (task.getEffort (), 17);
		assertEquals (taskContainer.get (0).getTotalEffort (), 12);
		assertEquals (taskContainer.get (1).getTotalEffort (), 2);
		assertEquals (taskContainer.get (2).getTotalEffort (), 3);
		assertEquals (childContainer.get (0).getTotalEffort (), 3);
		assertEquals (childContainer.get (1).getTotalEffort (), 4);
		assertEquals (childContainer.get (2).getTotalEffort (), 5);
	}
	
	public void
	recursivelyModifyEffortFromSubTasksTest ()
	{
		ArrayList<Task> taskContainer;
		container = new ArrayList<Task> ();
		taskContainer.add (new Task (task));
		taskContainer.add (new Task (task));
		taskContainer.add (new Task (task));
		taskContainer.get (0).setEffort (1);
		taskContainer.get (1).setEffort (2);
		taskContainer.get (2).setEffort (3);
		ArrayList<Task> childContainer;
		childContainer = new ArrayList<Task> ();
		childContainer.add (new Task (container.get (0)));
		childContainer.add (new Task (container.get (0)));
		childContainer.add (new Task (container.get (0)));
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
	
	public void
	removeAllSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new Task (task);
		container.add (child0);
		Task child1;
		child1 = new Task (task);
		container.add (child1);
		task.setSubTasks (container);
		task.removeAllSubTasks ();
		assertEquals (task.getSubTasks ().size (), 0);
	}
	
	public void
	removeSubTaskTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new Task (task);
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new Task (task);
		child1.setID ("child1");
		container.add (child1);
		task.setSubTasks (container);
		task.removeSubTask (child0);
		assertEquals (task.getSubTasks ().size (), 1);
		assertNull (task.getSubTask ("child0"));
		assertEquals (task.getSubTask ("child1"), child1);
	}
	
	public void
	removeSubTasksTest ()
	{
		ArrayList<Task> container;
		container = new ArrayList<Task> ();
		Task child0;
		child0 = new Task (task);
		child0.setID ("child0");
		container.add (child0);
		Task child1;
		child1 = new Task (task);
		child1.setID ("child1");
		container.add (child1);
		Task child2;
		child2 = new Task (task);
		child2.setID ("child2");
		container.add (child2);
		task.setSubTasks (container);
		ArrayList<Task> param;
		child0prime = new Task (task);
		child0prime.setID ("child0");
		param.add (child0prime);
		child2prime = new Task (task);
		child2prime.setID ("child2");
		param.add (child2prime);
		task.removeSubTasks (param);
		assertEquals (task.getSubTasks ().size (), 1);
		assertNull (task.getSubTask ("child0"));
		assertEquals (task.getSubTask ("child1"), child1);
		assertNull (task.getSubTask ("child2"));
	}
}