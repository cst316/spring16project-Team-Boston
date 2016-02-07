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
	boolean getUpdateChildren ();
	
	void setActive (boolean active);
	void setDescription (String description);
	void setEffort (long effort);
	void setEndDate (CalendarDate date);
	void setFrozen ();
	void setParentTask (Task task);
	void setPriority (int p);
	void setProgress (int p);
	void setStartDate (CalendarDate date);
	void setChildren (Collection<Task> tasks);
	void setText (String s);
	void setUpdateChildren (boolean updateChildren);
	
	void addChild (Task task);
	void addChildren (Collection<Task> tasks);
	void removeAllChildren ();
	void removeChild (Task task);
	void removeChildren (Collection<Task> tasks);
	
	/**
	  Method: copyTask
	  Inputs: Task
	  Returns: Task

	  Description: Copies Task input and all children and creates a completely new Task tree.
	*/
	public static Task copyTask(Task taskToCopy) {
		Task newTask = null;
		try {
			// Serialize the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(taskToCopy);
			out.flush();
			out.close();

			// Deserialize object back in
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			newTask = (Task) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return newTask;
	}
}