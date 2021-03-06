/**
 * TaskList.java
 * Created on 21.02.2003, 12:25:16 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.io.Serializable;
import java.util.Collection;

import net.sf.memoranda.date.CalendarDate;


public interface
TaskList extends Serializable
{
	/**
	 * Returns tasks that fulfill two criteria:
	 * child to the task with the given taskId and active with respect to the given date.
	 * 
	 * @param taskId the parent's identifier
	 * @param date used to determine whether a task is considered active or expired
	 * @return returns a collection of tasks that meet the criteria
	 */
	Collection<Task>
	getActiveSubTasks (String taskId, CalendarDate date);
	
	/**
	 * Returns subtasks with respect to the task with the given taskId.
	 * 
	 * @param taskId the parent's identifier
	 * @return returns a collection that contains subtasks of the task with the given id
	 */
	Collection<Task>
	getAllSubTasks (String taskId);

	/**
	 * Returns the task with the given id.
	 * 
	 * @param taskId the task's identifier
	 * @return the first task found with that id.
	 */
	Task
	getTask(String id);
	
	/**
	 * Returns all tasks which do not have parent tasks.
	 * 
	 * @return the collection of all tasks with null parents.
	 */
	Collection<Task>
	getTopLevelTasks ();
	
	/**
	 * Returns a deep copy of the given task and its children, but each with a different id.
	 * The duplicated task is stored internally as a child of a specific parent and a reference to it is returned.
	 * 
	 * @param task the task to duplicate
	 * @param parentTaskID the id of the internal parent task that will contain the new task reference
	 * @return a reference to the exact copy (different identifications) of the task hierarchy
	 */
	Task
	cloneTask (Task task, String parentTaskID);

	/**
	 * Returns true if the task that has the given id has a parent.
	 * 
	 * @param id the identifier for the task in question
	 * @return true if the task in question has a parent, false otherwise
	 */
	boolean
	hasParentTask (String id);
	
	/**
	 * Returns true if the task that has the given id has sub tasks.
	 * 
	 * @param id the identifier for the task in question
	 * @return true if the task in question has at least one child, false otherwise
	 */
	boolean
	hasSubTasks (String id);
	
	/**
	 * Inspects the given task for its id, and removes the first task found with that id.
	 * 
	 * @param task a task which contains the id shared by a task that will be removed
	 */
	void
	removeTask (Task task);
}
