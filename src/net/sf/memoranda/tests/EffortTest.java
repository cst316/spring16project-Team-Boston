package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import net.sf.memoranda.ui.TaskTableModel;
import nu.xom.Element;
/**
 * Test actual effort hours
 *
 */
public class EffortTest 
{
	Element element;
	Task task;
	TaskTableModel taskTable;
	
	/**
	 * Set up test cases by creating an new element and task
	 * @throws Exception
	 */
	@Before
	public void 
	setUp () 
	throws Exception
	{
		element = new Element ("task");
		task = new TaskImpl (element, null);
		taskTable = new TaskTableModel ();
	}
	/**
	 * Test null value
	 */
	@Test
	public void 
	nullTest () 
	{
		assertTrue (0 == task.getEffort());
		
	}
	/**
	 * Test setter and getter
	 */
	@Test
	public void 
	setTest () 
	{
		task.setEffort ( (long) 4.0);
		assertTrue( (long) 4.0 == task.getEffort ());
	}
	/**
	 * Test data type 
	 */
	@Test
	public void 
	dataTypeTest () 
	{
		task.setEffort( (long) 5.5);
		assertFalse (5.5 == task.getEffort ());
		assertTrue ( (long) 5.5 == task.getEffort ());
	}
	/**
	 * Test value in milliseconds for TaskTable
	 */
	@Test
	public void 
	taskTableValueTest () 
	{
		task.setEffort ( (long) 3600000);
		assertTrue ((double) 1 == (double) taskTable.getValueAt (task, 8));
	}
	/**
	 * Test correct String for TaskTable
	 */
	@Test
	public void 
	taskTableStringTest () 
	{
		assertTrue(taskTable.getColumnName (8).equals ("Actual Effort(hrs)"));
	}
}