package net.sf.memoranda;

import java.util.Collection;
import java.util.Iterator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;

public class
TaskImpl implements Task
{
	private static final long serialVersionUID = 1295565190499990928L;

	public
	TaskImpl (Collection<Task> taskStorage)
	{
		subTasks = taskStorage;
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
	getPredictedEffort ()
	{
		return effortPredicted;
	}

	public CalendarDate
	getEndDate ()
	{
		if (endDate != null)
		{
			return endDate;
		}
		if (parent != null)
		{
			return parent.getEndDate ();
		}
		return getStartDate ();
	}

	public String
	getID ()
	{
		return id;
	}

	public String
	getParentID ()
	{
		if (parent != null)
		{
			return parent.getID ();
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
	getStartDate ()
	{
		if (startDate != null)
		{
			return startDate;
		}
		if (parent != null)
		{
			return parent.getStartDate ();
		}
		return new CalendarDate ();
	}

	public int
	getStatus (CalendarDate date)
	{
		if (frozen)
		{
			return Task.FROZEN;
		}
		if (progress == 100)
		{
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
			if (startDate.before (date))
			{
				return Task.SCHEDULED;
			}
		return Task.FAILED;
	}

	public Task
	getSubTask (String id)
	{
		Iterator<Task> iter = subTasks.iterator ();
		while (iter.hasNext ())
		{
			Task t = iter.next ();
			if (t.getID ().equals (id)) return t;
		}
		return null;
	}

	public Collection<Task>
	getSubTasks ()
	{
		return subTasks;
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
	setPredictedEffort (long effortPredicted)
	{
		this.effortPredicted = effortPredicted;
	}

	public void
	setEndDate (CalendarDate date)
	{
		if (date == null)
		{
			// Don't think anything should be done if date is null
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
			if(parent.getUpdateSubTasks ())
			{
				parent.recursivelyModifyCompletionFromSubTasks ();	
			}
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
		this.subTasks = SubTasks;
	}

	public void setText (String text)
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
		task.setParentTask (this);
		subTasks.add (task);
	}

	public void
	addSubTasks (Collection<Task> tasks)
	{
		Iterator<Task> iter = tasks.iterator ();
		while (iter.hasNext ())
			iter.next ().setParentTask (this);
		subTasks.addAll (tasks);
	}

	public int
	compareTo (Task task)
	{
		if (getRate () > task.getRate ())
			return 1;
		else
			if (getRate () < task.getRate ())
				return -1;
			else
				return 0;
	}

	public Task
	deepCopy ()
	{
		Task newTask = null;
		try
		{
			// Serialize the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream ();
			ObjectOutputStream out = new ObjectOutputStream (bos);
			out.writeObject (this);
			out.flush ();
			out.close ();

			// Deserialize object back in
			ObjectInputStream in = new ObjectInputStream (new ByteArrayInputStream (bos.toByteArray ()));
			newTask = (Task) in.readObject ();
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace ();
		}
		return newTask;
	}

	public boolean
	equals (Object o)
	{
		boolean a = o instanceof Task;
		Task t = (Task) o;
		boolean b = t.getID ().equals (this.getID ());
		return a && b;
	}
	
	public int
	hashCode ()
	{
		return this.id.hashCode ();
	}

	public long[]
	recursivelyModifyCompletionFromSubTasks ()
	{
		int totalProgress = 0;
		if (subTasks.size () > 0) 
		{
			for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();) 
			{
				Task childTask = iter.next ();
				totalProgress += childTask.getProgress ();
			}
			this.setProgress (totalProgress / subTasks.size ());
		}
		/*
		long[] res = new long[2];
		long expendedEffort = 0;
		long totalEffort = 0;
		Collection<Task> subTasks = getSubTasks ();
		if (subTasks == null || subTasks.size () == 0)
		{
			long eff = getEffort ();
			if (eff == 0)
			{
				eff = 1;
			}
			res[0] = Math.round ((double) (getProgress () * eff) / 100d);
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
		int thisProgress = (int) Math.round ( ( ((double) expendedEffort / (double) totalEffort) * 100));
		setProgress (thisProgress);
		res[0] = expendedEffort;
		res[1] = totalEffort;
		return res;*/
		return null;
	}

	public long
	recursivelyModifyEffortFromSubTasks ()
	{
		long totalEffort = 0;
		Collection<Task> subTasks = getSubTasks ();
		if (subTasks.size () == 0) return effort;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			totalEffort = totalEffort + element.recursivelyModifyEffortFromSubTasks ();
		}
		setEffort (totalEffort);
		return totalEffort;
	}

	public CalendarDate
	recursivelyModifyEndDateFromSubTasks ()
	{
		CalendarDate d = getEndDate ();
		Collection<Task> subTasks = getSubTasks ();
		if (subTasks == null) return startDate;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			CalendarDate dd = element.recursivelyModifyEndDateFromSubTasks ();
			if (dd.after (d))
			{
				d = dd;
			}
		}
		setEndDate (d);
		return d;
	}

	public CalendarDate
	recursivelyModifyStartDateFromSubTasks ()
	{
		CalendarDate d = getStartDate ();
		Collection<Task> subTasks = getSubTasks ();
		if (subTasks == null) return startDate;
		for (Iterator<Task> iter = subTasks.iterator (); iter.hasNext ();)
		{
			Task element = iter.next ();
			CalendarDate dd = element.recursivelyModifyStartDateFromSubTasks ();
			if (dd.before (d))
			{
				d = dd;
			}
		}
		setStartDate (d);
		return d;
	}

	public void
	removeAllSubTasks ()
	{
		subTasks.clear ();
	}

	public void
	removeSubTask (Task task)
	{
		subTasks.remove (task);
	}

	public void
	removeSubTasks (Collection<Task> tasks)
	{
		subTasks.removeAll (tasks);
	}

	private Collection<Task> subTasks;
	private String description;
	private long effort = 0;
	private long effortPredicted;
	private CalendarDate endDate;
	private boolean frozen = false;
	private String id;
	private Task parent = null;
	private int priority;
	private int progress;
	private CalendarDate startDate;
	private String text;
	private boolean updateSubTasks;
}