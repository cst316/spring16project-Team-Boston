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
		Task child;
		child = new Task (task);
		child.setID ("child");
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

	}
	
	public void
	addSubTasksTest (Collection<Task> tasks)
	{
		
	}
	
	public void
	deepCopyTest ()
	{
		
	}
	
	public void
	recursivelyModifyCompletionFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyEarliestEndDateFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyLatestStartDateFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyTotalEffortFromSubTasksTest ()
	{
		
	}
	
	public void
	recursivelyModifyEffortFromSubTasksTest ()
	{
		
	}
	
	public void
	removeAllSubTasksTest ()
	{
		
	}
	
	public void
	removeSubTaskTest (Task task)
	{
		
	}
	
	public void
	removeSubTasksTest (Collection<Task> tasks)
	{
		
	}
}