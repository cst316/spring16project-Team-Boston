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
public class EffortActualTest 
{
	Element element;
	Task task;
	TaskTableModel taskTable;
	
	/**
	 * Set up test cases by creating an new element and task
	 * @throws Exception
	 */
	@Before
	public void setUp () throws Exception
	{
		element = new Element ("task");
		task = new TaskImpl (null);
		taskTable = new TaskTableModel ();
	}
	/**
	 * Test null value
	 */
	@Test
	public void nullTest () 
	{
		assertTrue (0 == task.getEffortActual());
		
	}
	/**
	 * Test setter and getter
	 */
	@Test
	public void setTest () 
	{
		task.setEffortActual ( (long) 4.0);
		assertTrue( (long) 4.0 == task.getEffortActual ());
	}
	/**
	 * Test data type 
	 */
	@Test
	public void dataTypeTest () 
	{
		task.setEffortActual( (long) 5.5);
		assertFalse (5.5 == task.getEffortActual ());
		assertTrue ( (long) 5.5 == task.getEffortActual ());
	}
	/**
	 * Test value in milliseconds for TaskTable
	 */
	@Test
	public void taskTableValueTest () 
	{
		task.setEffortActual ( (long) 3600000);
		assertTrue ((double) 1 == (double) taskTable.getValueAt (task, 8));
	}
	/**
	 * Test correct String for TaskTable
	 */
	@Test
	public void taskTableStringTest () 
	{
		assertTrue(taskTable.getColumnName(8).equals("Actual EffortActual(hrs)"));
	}
}