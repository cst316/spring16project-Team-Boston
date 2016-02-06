package net.sf.memoranda;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Calendar;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;

public class TaskImpl implements Task, Comparable
{
	private boolean active;
	private Collection<Task> children;
	private String description;
	private long effort;
	private CalenderDate endDate;
	private String id;
	private Task parent;
	private int priority;
	private int progress;
	private CalenderDate startDate;
	private int status;
	private String text;
	private boolean updateChildren;

	public TaskImpl (Task parent)
	{
		this.parent = parent;
	}
	
	public boolean getActive

	{
		return active;
	}

	public String getDescription ()
	{
		return description;
	}

	public long getEffort ()
	{
		return effort;
	}

	public CalendarDate getEndDate (Project project)
	{
		if (endDate != "") return new CalendarDate (endDate);
		if (parent != null) return parent.getEndDate ();
		if (project.getEndDate () != null) return project.getEndDate ();
		return getStartDate ();
	}

	public String getId ();
	{
		return id;
	}

	public String getParentId ()
	{
		if (parent != null) return parent.getID ();
		return null;
	}

	public Task getParentTask ()
	{
		return parent;
	}

	public int getPriority ()
	{
		return priority;
	}

	public int getProgress ()
	{
		return progress;
	}

	public long getRate ()
	{
		CalenderDate d = CurrentDate.get ();
		Calendar endDateCal = getEndDate ().getCalendar ();
		Calendar dateCal = d.getCalendar ();
		int numOfDays = (endDateCal.get (Calendar.YEAR) * 365 + endDateCal.get (Calendar.DAY_OF_YEAR)) - (dateCal.get (Calendar.YEAR) * 365 + dateCal.get (Calendar.DAY_OF_YEAR));
		if (numOfDays < 0) return -1; // Something wrong ?
		return -1 * (100 - getProgress ()) / (numOfDays + 1) * (getPriority () + 1);
	}

	public CalendarDate getStartDate (Project project)
	{
		if (startDate != "") return new CalendarDate (startDate);
		if (parent != null) return parent.getStartDate ();
		if (project.getStartDate () != null) return project.getStartDate ();
		return new CalendarDate ();
	}

	public int getStatus (CalendarDate date)
	{
		if (isFrozen ()) return Task.FROZEN;
		if (isCompleted ()) return Task.COMPLETED;

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

	public Task getSubTask (String id)
	{
		Iterator<Task> iter = children.iterator ();
		while (iter.hasNext ())
		{
			Task t = iter.next ();
			if (t.getID == id) return t;
		}
		return null;
	}

	public Collection<Task> getSubTasks ()
	{
		return children;
	}

	public boolean getUpdateChildren ()
	{
		return updateChildren;
	}

	public String getText ()
	{
		return text;
	}

	public void setActive (boolean active)
	{
		this.active = active;
	}

	public void setDescription (String description)
	{
		if (description == null)
			this.description = "";
		else
			this.description = description;
	}

	public void setEffort (long effort)
	{
		this.effort = effort;
	}

	public void setEndDate (CalendarDate date)
	{
		if (date == null)
			endDate = "";
		else
			endDate = date.toString ();
	}

	public void setFrozen (boolean frozen)
	{
		this.frozen = frozen;
	}

	public void setParentTask (Task task)
	{
		parent = task;
	}

	public void setPriority (int p)
	{
		priority = p;
	}

	public void setProgress (int p)
	{
		if ( (p >= 0) && (p <= 100)) progress = p;
		if (parent != null)
		{
			updateParentProgress (parent);
		}
	}

	public void setStartDate (CalendarDate date)
	{
		if (date == null)
			startDate = "";
		else
			startDate = date.toString ();
	}

	public void setChildren (Collection<Task> children)
	{
		this.children = children;
	}

	public void setText (String text)
	{
		if (text == null)
			this.text = "";
		else
			this.text = text;
	}

	public void setUpdateChildren (boolean updateChildren)
	{
		this.updateChildren = updateChildren;
	}

	public void addChild (Task task)
	{
		children.add (task);
	}

	public void addChildren (Collection<Task> tasks)
	{
		children.addAll (tasks);
	}

	public void removeAllChildren ()
	{
		children.clear ();
	}

	public void removeChild (Task task)
	{
		children.remove (task);
	}

	public void removeChildren (Collection<Task> tasks)
	{
		children.removeAll (tasks);
	}

	public int compareTo (Object o)
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

	public boolean equals(Object o)
	{
		boolean a = o instanceof Task;
		Task t = (Task) o;
		boolean b = t.getId ().equals (this.getId ());
		return a && b;
	}
}