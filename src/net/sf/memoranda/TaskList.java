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

/**
 * 
 */
/* $Id: TaskList.java,v 1.8 2005/12/01 08:12:26 alexeya Exp $ */
public interface
TaskList extends Serializable
{
	Collection<Task>
	getActiveSubTasks (String taskId, CalendarDate date);
	
	Collection<Task>
	getAllSubTasks (String taskId);

	CalendarDate
	getEarliestStartDateFromSubTasks (Task t);
	
	CalendarDate
	getLatestEndDateFromSubTasks (Task t);
	
	// Project getProject();
	Task
	getTask(String id);
	
	Collection<Task>
	getTopLevelTasks ();
	
	long[]
	calculateCompletionFromSubTasks (Task t);
	
	long
	calculateTotalEffortFromSubTasks (Task t);
	
	Task
	createTask (Task task);
	
	// Add effortActual
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
