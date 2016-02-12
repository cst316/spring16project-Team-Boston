package net.sf.memoranda;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import net.sf.memoranda.date.CalendarDate;

public interface Task extends Serializable
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
	
	boolean getActive ();
	String getDescription ();
	long getEffort ();
	long getEffortActual();
	CalendarDate getEndDate ();
	String getID ();
	String getParentId ();
	Task getParentTask ();
	int getPriority ();
	int getProgress ();
	long getRate ();
	CalendarDate getStartDate ();
	int getStatus (CalendarDate date);
	Task getSubTask (String id);
	Collection<Task>  getSubTasks ();
	String getText ();
	boolean getUpdateSubTasks ();
	
	void setActive (boolean active);
	void setDescription (String description);
	void setEffort (long effort);
	void setEffortActual(long millisFromHours);
	void setEndDate (CalendarDate date);
	void setID (String id);
	void setFrozen (boolean frozen);
	void setParentTask (Task task);
	void setPriority (int p);
	void setProgress (int p);
	void setStartDate (CalendarDate date);
	void setSubTasks (Collection<Task> tasks);
	void setText (String s);
	void setUpdateSubTasks (boolean updateChildren);
	
	void addSubTask (Task task);
	void addSubTasks (Collection<Task> tasks);
	Task deepCopy ();
	long[] recursivelyModifyCompletionFromSubTasks ();
	CalendarDate recursivelyModifyEarliestEndDateFromSubTasks ();
	CalendarDate recursivelyModifyLatestStartDateFromSubTasks ();
	long recursivelyModifyTotalEffortFromSubTasks ();
	long recursivelyModifyEffortFromSubTasks ();
	void removeAllSubTasks ();
	void removeSubTask (Task task);
	void removeSubTasks (Collection<Task> tasks);
}