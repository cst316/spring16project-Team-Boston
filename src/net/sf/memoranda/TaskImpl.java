package net.sf.memoranda;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;

public class
TaskImpl implements Task, Comparable
{
	
	public
	TaskImpl (Task parent)
	{
		this.parent = parent;
		SubTasks = new Vector<Task>();
	}
	
	public boolean
	getActive()

	{
		return active;
	}

	public String
	getDescription ()
	{
		return description;
	}

	public long
	getEffort ()
	{
		return effort;
	}
	
	public long
	getEffortActual ()
	{
		return effortActual;
	}

	public CalendarDate
	getEndDate (Project project)
	{
		if (endDate != null) 
		{
			return endDate;
		}
		if (parent != null)
		{
			return parent.getEndDate ();
		}
		if (project.getEndDate () != null)
		{
			return project.getEndDate ();
		}
		return getStartDate ();
	}

	public
	String getID ()
	{
		return id;
	}

	public
	String getParentId ()
	{
		if (parent != null)
		{ 
			return parent.getID();
		}
		return null;
	}

	public Task
	getParentTask ()
	{
		return parent;
	}

	public int
	getPriority ()
	{
		return priority;
	}

	public int
	getProgress ()
	{
		return progress;
	}

	public long
	getRate ()
	{
		CalendarDate d = CurrentDate.get ();
		Calendar endDateCal = getEndDate ().getCalendar ();
		Calendar dateCal = d.getCalendar ();
		int numOfDays = (endDateCal.get (Calendar.YEAR) * 365 + endDateCal.get (Calendar.DAY_OF_YEAR)) - (dateCal.get (Calendar.YEAR) * 365 + dateCal.get (Calendar.DAY_OF_YEAR));
		if (numOfDays < 0) return -1; // Something wrong ?
		return -1 * (100 - getProgress ()) / (numOfDays + 1) * (getPriority () + 1);
	}

	public CalendarDate
	getStartDate (Project project)
	{
		if (startDate != null) 
		{
			return startDate;
		}
		if (parent != null) 
		{
			return parent.getStartDate ();
		}
		if (project.getStartDate () != null) 
		{
			return project.getStartDate ();
		}
		return new CalendarDate ();
	}

	public int
	getStatus (CalendarDate date)
	{
		if (frozen){
			return Task.FROZEN;
		}
		if (progress == 100){
			return Task.COMPLETED;
		}

		if (date.inPeriod (startDate, endDate))
		{
			if (date.equals (endDate))
				return Task.DEADLINE;
			else
				return Task.ACTIVE;
		}
		else
			if (date.before (startDate))
			{
				return Task.SCHEDULED;
			}
		if (startDate.after (endDate))
		{
			return Task.ACTIVE;
		}
		return Task.FAILED;
	}

	public Task
	getSubTask (String id)
	{
		Iterator<Task> iter = SubTasks.iterator ();
		while (iter.hasNext ())
		{
			Task t = iter.next ();
			if (t.getID() == id) return t;
		}
		return null;
	}

	public Collection<Task>
	getSubTasks ()
	{
		if(SubTasks.size() > 0)
		{
			return SubTasks;
		}
		else
		{
			return new Vector<Task>();
		}
	}

	public boolean
	getUpdateSubTasks ()
	{
		return updateSubTasks;
	}

	public String
	getText ()
	{
		return text;
	}
	
	public CalendarDate
	getEndDate() {
		return endDate;
	}
	
	public long
	getEffortActual() 
	{
		return effortActual;
	}

	public CalendarDate
	getStartDate() {
		return startDate;
	}

	public void
	setActive (boolean active)
	{
		this.active = active;
	}

	public void
	setDescription (String description)
	{
		if (description == null)
			this.description = "";
		else
			this.description = description;
	}
	
	public void
	setID (String id) 
	{
		this.id = id;
	}

	public void
	setEffort (long effort)
	{
		this.effort = effort;
	}
	
	public void
	setEffortActual (long effortActual)
	{
		this.effortActual = effortActual;
	}

	public void
	setEndDate (CalendarDate date)
	{
		if (date == null)
		{
			//Don't think anything should be done if date is null
		}
		else
			endDate = date;
	}

	public void
	setFrozen (boolean frozen)
	{
		this.frozen = frozen;
	}

	public void
	setParentTask (Task task)
	{
		parent = task;
	}

	public void
	setPriority (int p)
	{
		priority = p;
	}

	public void
	setProgress (int p)
	{
		if ( (p >= 0) && (p <= 100))
		{
			progress = p;
		}
		if (parent != null)
		{
			parent.recursivelyModifyCompletionFromSubTasks();
		}
	}

	public void
	setStartDate (CalendarDate date)
	{
		if (date == null)
		{
			
		}
		else
		{
			startDate = date;
		}
	}

	public void
	setSubTasks (Collection<Task> SubTasks)
	{
		this.SubTasks = SubTasks;
	}

	public void
	setText (String text)
	{
		if (text == null)
			this.text = "";
		else
			this.text = text;
	}

	public void
	setUpdateSubTasks (boolean updateSubTasks)
	{
		this.updateSubTasks = updateSubTasks;
	}
	

	public void
	addSubTask (Task task)
	{
		SubTasks.add (task);
	}

	public void
	addSubTasks (Collection<Task> tasks)
	{
		SubTasks.addAll (tasks);
	}
	
	public Task
	deepCopy()
	{
		Task newTask = null;
		try {
			// Serialize the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this);
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
	
	public long[]
	recursivelyModifyCompletionFromSubTasks ()
	{
		long[] res = new long[2];
		long expendedEffort = 0;
		long totalEffort = 0;
		Collection subTasks = getSubTasks ();
		if (subTasks == null || subTasks.size() == 0)
		{
			long eff = getEffort ();
			if (eff == 0)
				eff = 1;
			res[0] = Math.round((double)(getProgress()*eff)/100d);
			res[1] = eff;
			return res;
		}
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			long[] subTaskCompletion = element.recursivelyModifyCompletionFromSubTasks ();
			expendedEffort = expendedEffort + subTaskCompletion[0];
			totalEffort = totalEffort + subTaskCompletion[1];
		}
		int thisProgress = (int) Math.round((((double)expendedEffort / (double)totalEffort)*100));
		setProgress (thisProgress);
		res[0] = expendedEffort;
		res[1] = totalEffort;
		return res;
	}
	
	public CalendarDate
	recursivelyModifyEarliestEndDateFromSubTasks ()
	{
		CalendarDate d = getEndDate ();
		Collection subTasks = getSubTasks ();
		if (subTasks == null)
			return startDate;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			CalendarDate dd = element.recursivelyModifyEarliestEndDateFromSubTasks ();
			if (dd.after (d))
			{
				d = dd;
			}
		}
		setEndDate (d);
		return d;
	}

	public CalendarDate
	recursivelyModifyLatestStartDateFromSubTasks ()
	{
		CalendarDate d = getStartDate ();
		Collection subTasks = getSubTasks ();
		if (subTasks == null)
			return startDate;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			CalendarDate dd = element.recursivelyModifyLatestStartDateFromSubTasks ();
			if (dd.before (d))
			{
				d = dd;
			}
		}
		setStartDate (d);
		return d;
	}

	public long
	recursivelyModifyEffortFromSubTasks ()
	{
		long totalEffort = 0;
		Collection subTasks = getSubTasks ();
		if (subTasks == null)
			return effort;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			totalEffort = totalEffort + element.recursivelyModifyEffortFromSubTasks ();
		}
		setEffort (totalEffort);
		return totalEffort;
	}
	
	public void
	removeAllSubTasks ()
	{
		SubTasks.clear ();
	}

	public void
	removeSubTask (Task task)
	{
		SubTasks.remove (task);
	}

	public void
	removeSubTasks (Collection<Task> tasks)
	{
		SubTasks.removeAll (tasks);
	}

	public int
	compareTo (Object o)
	{
		Task task = (Task) o;
		if (getRate () > task.getRate ())
			return 1;
		else
			if (getRate () < task.getRate ())
				return -1;
			else
				return 0;
	}

	public boolean
	equals (Object o)
	{
		boolean a = o instanceof Task;
		Task t = (Task) o;
		boolean b = t.getID ().equals (this.getID ());
		return a && b;
	}
	
	public long
	recursivelyModifyTotalEffortFromSubTasks ()
	{
		//Copied the method from recursivelyModifyEffortFromSubTasks not sure what the difference was
		
		long totalEffort = 0;
		Collection subTasks = getSubTasks ();
		if (subTasks == null)
			return effort;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			totalEffort = totalEffort + element.recursivelyModifyEffortFromSubTasks ();
		}
		setEffort (totalEffort);
		return totalEffort;
	}
	
	private boolean active;
	private Collection<Task> SubTasks;
	private String description;
	private long effort, effortActual;
	private CalendarDate endDate;
	private String id;
	private Task parent;
	private int priority;
	private int progress;
	private CalendarDate startDate;
	private int status;
	private String text;
	private boolean updateSubTasks, frozen, updateChildren;
	
}