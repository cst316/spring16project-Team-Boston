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
	
	Task
	getTask(String id);
	
	Collection<Task>
	getTopLevelTasks ();
	
	Task
	createTask (Task task);
	
	Task
	createTask
	(	CalendarDate startDate,
		CalendarDate endDate,
		String text,
		int priority,
		long effort,
		long effortActual,
		String description,
		String parentTaskId,
		boolean updateFromChildren
	);

	boolean
	hasParentTask (String id);
	
	boolean
	hasSubTasks (String id);
	
	void
	removeTask (Task task);
}
