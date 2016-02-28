package net.sf.memoranda;

import java.io.Serializable;
import java.util.Collection;

import net.sf.memoranda.date.CalendarDate;

public interface
Task extends Serializable, Comparable<Task>
{
	public static final int SCHEDULED = 0;
	public static final int ACTIVE = 1;
	public static final int COMPLETED = 2;
	public static final int FROZEN = 4;
	public static final int FAILED = 5;
	public static final int LOCKED = 6;
	public static final int DEADLINE = 7;
	public static final int PRIORITY_LOWEST = 0;
	public static final int PRIORITY_LOW = 1;
	public static final int PRIORITY_NORMAL = 2;
	public static final int PRIORITY_HIGH = 3;
	public static final int PRIORITY_HIGHEST = 4;
	
	/**
	 * Returns the description of the task.
	 * 
	 * @return the description
	 */
	String
	getDescription ();
	
	/**
	 * Returns the effort of the task.
	 * 
	 * @return the effort
	 */
	long
	getEffort ();
	
	/**
	 * Returns the actual effort of the task.
	 * 
	 * @return the actual effort
	 */
	long
	getEffortActual ();
	
	/**
	 * Returns the end date of the task.
	 * 
	 * @return the end date
	 */
	CalendarDate
	getEndDate ();
	
	/**
	 * Returns the ID of the task.
	 * 
	 * @return the ID
	 */
	String
	getID ();
	
	/**
	 * If the task has a parent then this function returns the parent's id, otherwise null.
	 * 
	 * @return the id of the parent of this task, or null if this task has no parent
	 */
	String
	getParentID ();
	
	/**
	 * If this task has a parent then this function returns the parent task, otherwise null.
	 * 
	 * @return the parent of this task, or null if this task has no parent
	 */
	Task
	getParentTask ();
	
	/**
	 * Returns the priority of this task.
	 * 
	 * @return the priority
	 */
	int
	getPriority ();
	
	/**
	 * Returns the progress of this task.
	 * 
	 * @return the progress
	 */
	int
	getProgress ();
	
	/**
	 * Returns the rate of this task.
	 * 
	 * @return the rate
	 */
	long
	getRate ();
	
	/**
	 * Returns the start date of this task.
	 * 
	 * @return the start date
	 */
	CalendarDate
	getStartDate ();
	
	/**
	 * Returns the status of this task with respect to the given date.
	 * If the task is frozen, then return Task.FROZEN
	 * Otherwise, if the task's progress is 100, then return Task.COMPLETED
	 * Otherwise, if the date is equal to the task's end date, then returns Task.DEADLINE
	 * Otherwise, if the date is within the task's start and end date range, return Task.ACTIVE
	 * Otherwise, if the date is before the task's start date, then return Task.SCHEDULED
	 * Otherwise, returns Task.FAILED
	 * 
	 * @param date the date against which to check the status of the given task
	 * @return the status
	 */
	int
	getStatus (CalendarDate date);
	
	/**
	 * Returns the first sub task found with the given id.
	 * 
	 * @param id the id to search for
	 * @return the sub task with the given id
	 */
	Task
	getSubTask (String id);
	
	/**
	 * Returns all sub tasks.
	 * 
	 * @return all sub tasks
	 */
	Collection<Task>
	getSubTasks ();
	
	/**
	 * Returns the text of the task.
	 * 
	 * @return the text
	 */
	String
	getText ();
	
	/**
	 * Returns true if this task should update itself based on subtasks.
	 * 
	 * @return true if this task should update itself based on subtasks
	 */
	boolean
	getUpdateSubTasks ();
	
	/**
	 * Sets the description of the task.
	 * 
	 * @param description the description
	 */
	void
	setDescription (String description);
	
	/**
	 * Sets the effort of the task.
	 * 
	 * @param effort the effort
	 */
	void
	setEffort (long effort);
	
	/**
	 * Sets the actual effort of the task.
	 * 
	 * @param millisFromHours the actual effort
	 */
	void
	setEffortActual (long millisFromHours);
	
	/**
	 * Sets the end date of the task.
	 * 
	 * @param date the end date
	 */
	void
	setEndDate (CalendarDate date);
	
	/**
	 * Sets the id of the task.
	 * 
	 * @param id the id
	 */
	void
	setID (String id);
	
	/**
	 * Sets whether or not the task is frozen.
	 * 
	 * @param frozen whether or not the task is frozen
	 */
	void
	setFrozen (boolean frozen);
	
	/**
	 * Sets the reference to the parent task.
	 * 
	 * @param task the parent task reference
	 */
	void
	setParentTask (Task task);
	
	/**
	 * Sets the priority of the task.
	 * 
	 * @param p the priority
	 */
	void
	setPriority (int p);
	
	/**
	 * Sets the progress of the task.
	 * 
	 * @param p the progress
	 */
	void
	setProgress (int p);
	
	/**
	 * Sets the start date of the task.
	 * 
	 * @param date the start date
	 */
	void
	setStartDate (CalendarDate date);
	
	/**
	 * Sets the sub task collection of the task.
	 * 
	 * @param tasks the sub task collection
	 */
	void
	setSubTasks (Collection<Task> tasks);
	
	/**
	 * Sets the text of the task.
	 * 
	 * @param s the text
	 */
	void
	setText (String s);
	
	/**
	 * Sets whether or not the task should update based on sub tasks.
	 * 
	 * @param updateChildren whether or not to update based on sub tasks
	 */
	void
	setUpdateSubTasks (boolean updateChildren);
	
	/**
	 * Adds the given sub task to this task.
	 * 
	 * @param task the sub task
	 */
	void
	addSubTask (Task task);
	
	/**
	 * Adds all of the sub tasks in the collection to this task.
	 * 
	 * @param tasks the collection of sub tasks.
	 */
	void
	addSubTasks (Collection<Task> tasks);
	
	/**
	 * Compares the priority of this task and another task.
	 * 
	 * @param tasks the task against which to compare priority
	 */
	public int
	compareTo (Task task);
	
	/**
	 * Returns a copy of this task hierarchy, but each with different ID.
	 * 
	 * @return a deep copy of this task hierarchy, with different ID
	 */
	Task
	deepCopy ();
	
	/**
	 * Looks through the entire sub task tree and calculates progress on all
	 * parent task nodes
	 * 
	 * @param t
	 * @return long[] of size 2. First long is expended effort in
	 *         milliseconds, 2nd long is total effort in milliseconds
	 */
	long[]
	recursivelyModifyCompletionFromSubTasks ();
	
	/**
	 * After this operation:
	 * No task in the task hiearchy will have an end date that occurs after its parent.
	 * The end dates of tasks in the task hiearchy that are childless are unmodified.
	 * 
	 * @return the new end date of this task
	 */
	CalendarDate
	recursivelyModifyEndDateFromSubTasks ();
	
	/**
	 * After this operation:
	 * No task in the task hiearchy will have an start date that occurs before its parent.
	 * The start dates of tasks in the task hiearchy that are childless are unmodified.
	 * 
	 * @return the new start date of this task
	 */
	CalendarDate
	recursivelyModifyStartDateFromSubTasks ();
	
	/**
	 * After this operation the effort of any parent is the sum of effort of its children.
	 * 
	 * @return the new effort of this task
	 */
	long
	recursivelyModifyEffortFromSubTasks ();
	
	/**
	 * Removes all sub tasks from this task.
	 */
	void
	removeAllSubTasks ();
	
	/**
	 * Removes a specific reference to a sub task from this task.
	 * 
	 * @param task the reference to the sub task that should be removed
	 */
	void
	removeSubTask (Task task);
	
	/**
	 * Removes all the sub tasks in the given collection from this task.
	 * 
	 * @param tasks the collection of sub tasks to remove
	 */
	void
	removeSubTasks (Collection<Task> tasks);
}