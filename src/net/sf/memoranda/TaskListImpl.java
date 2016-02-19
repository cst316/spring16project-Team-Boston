/**
 * TaskListImpl.java
 * Created on 21.02.2003, 12:29:54 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Util;

public class TaskListImpl implements TaskList
{
	private static final long serialVersionUID = 7504630657790091439L;
	
	/**
	 * Constructor for TaskListImpl.
	 */

	public
	TaskListImpl (Project prj)
	{
		// _project = prj;
		taskList = new Hashtable<String, Task> ();
		rootTaskList = new ArrayList<Task> ();
	}
	
	/**
	 * Looks through the entire sub task tree and calculates progress on all
	 * parent task nodes
	 * 
	 * @param t
	 * @return long[] of size 2. First long is expended effort in
	 *         milliseconds, 2nd long is total effort in milliseconds
	 */
	public long[]
	calculateCompletionFromSubTasks (Task t)
	{
		return t.recursivelyModifyCompletionFromSubTasks ();
	}

	/**
	 * Recursively calculate total effort based on subtasks for every node
	 * in the task tree The values are saved as they are calculated as well
	 * 
	 * @param t
	 * @return
	 */
	public long calculateTotalEffortFromSubTasks (Task t)
	{
		return t.recursivelyModifyEffortFromSubTasks ();
	}

	public Collection<Task> getTopLevelTasks ()
	{
		return getAllRootTasks ();
	}

	/**
	 * All methods to obtain list of tasks are consolidated under
	 * getAllSubTasks and getActiveSubTasks. If a root task is required,
	 * just send a null taskId
	 */
	public Collection<Task>
	getActiveSubTasks (String taskId, CalendarDate date)
	{
		Collection<Task> allTasks = getAllSubTasks (taskId);
		return filterActiveTasks (allTasks, date);
	}

	/**
	 * All methods to obtain list of tasks are consolidated under
	 * getAllSubTasks and getActiveSubTasks. If a root task is required,
	 * just send a null taskId
	 */
	public Collection<Task>
	getAllSubTasks (String taskId)
	{
		if ( (taskId == null) || (taskId.length () == 0))
		{
			return getAllRootTasks ();
		}
		else
		{
			Task task = getTaskElement (taskId);
			return task.getSubTasks ();
		}
	}

	/**
	 * Looks through the entire sub task tree and corrects any
	 * inconsistencies in start dates
	 * 
	 * @param t
	 * @return
	 */
	public CalendarDate
	getEarliestStartDateFromSubTasks (Task t)
	{
		return t.recursivelyModifyStartDateFromSubTasks ();
	}

	/**
	 * Looks through the entire sub task tree and corrects any
	 * inconsistencies in start dates
	 * 
	 * @param t
	 * @return
	 */
	public CalendarDate
	getLatestEndDateFromSubTasks (Task t)
	{
		return t.recursivelyModifyEndDateFromSubTasks ();
	}

	public boolean
	hasParentTask (String id)
	{
		Task t = getTaskElement (id);
	
		if (t.getParentTask () != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean
	hasSubTasks (String id)
	{
		Task task = getTaskElement (id);
		if (task == null)
		{
			return false;
		}
		if (task.getSubTasks ().size () > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Task
	createTask (Task task)
	{
		Task newTask = task.deepCopy();
		newTask.setID(Util.generateId());
		recursivleyUpdateID(newTask.getSubTasks());
		rootTaskList.add(newTask);
		taskList.put(newTask.getID(), newTask);
		return newTask;
	}

	public Task
	createTask (CalendarDate startDate, CalendarDate endDate, String text, int priority, long effort, long effortActual, String description, String parentTaskId, boolean updateFromChildren)
	{
		Task newTask = new TaskImpl (new ArrayList<Task> ());
		if (parentTaskId == null)
		{
			newTask.setParentTask (null);
		}
		else
		{
			newTask.setParentTask (taskList.get (parentTaskId));
		}
		newTask.setStartDate (startDate);
		newTask.setEndDate (endDate);
		newTask.setID (Util.generateId ());
		newTask.setProgress (0);
		newTask.setEffort (effort);
		newTask.setEffortActual (effortActual);
		newTask.setPriority (priority);
		newTask.setUpdateSubTasks (updateFromChildren);
		newTask.setText (text);
		newTask.setDescription (description);
	
		if (parentTaskId == null)
		{
			rootTaskList.add (newTask);
		}
		else
		{
			Task parent = getTaskElement (parentTaskId);
			parent.addSubTask (newTask);
		}
	
		taskList.put (newTask.getID (), newTask);
	
		Util.debug ("Created task with parent " + parentTaskId);
	
		return (TaskImpl) newTask;
	}

	public Task
	getTask (String id)
	{
		Util.debug ("Getting task " + id);
		return getTaskElement (id);
	}

	/**
	 * @see net.sf.memoranda.TaskList#removeTask(import
	 *      net.sf.memoranda.Task)
	 */

	public void
	removeTask (Task task)
	{
		String parentTaskId = task.getParentID ();
		if (parentTaskId != null)
		{
			Task parentNode = getTaskElement (parentTaskId);
			parentNode.removeSubTask (task);
		}
		taskList.remove (task.getID ());
		rootTaskList.remove (task);
	}

	/*
	 * private methods below this line
	 */
	private Task
	getTaskElement (String id)
	{
		Task el = (Task) taskList.get (id);
		if (el == null)
		{
			Util.debug ("Task " + id + " cannot be found in project ");
		}
		return el;
	}

	private Collection<Task>
	getAllRootTasks ()
	{
		Vector<Task> temp = new Vector<Task> ();
		for (Task task : rootTaskList)
		{
			temp.add (task);
		}
		return temp;
	}

	private Collection<Task>
	filterActiveTasks (Collection<Task> tasks, CalendarDate date)
	{
		Vector<Task> v = new Vector<Task> ();
		for (Iterator<Task> iter = tasks.iterator (); iter.hasNext ();)
		{
			Task t = iter.next ();
			if (isActive (t, date))
			{
				v.add (t);
			}
		}
		return v;
	}

	private boolean
	isActive (Task t, CalendarDate date)
	{
		if ( (t.getStatus (date) == Task.ACTIVE) || (t.getStatus (date) == Task.DEADLINE) || (t.getStatus (date) == Task.FAILED))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void
	recursivleyUpdateID(Collection<Task> subTasks)
	{
		for(Task task : subTasks)
		{
			if(task.getSubTasks().size() > 0)
			{
				recursivleyUpdateID(task.getSubTasks());
			}
			else
			{
				task.setID(Util.generateId());
				taskList.put(task.getID(), task);
			}
		}
	}

	private Hashtable<String, Task> taskList;
	private ArrayList<Task> rootTaskList;
}
