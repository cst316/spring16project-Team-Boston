package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import net.sf.memoranda.ui.TaskTableModel;
import nu.xom.Element;

/**
 * Test predicted effort hours
 *
 */
public class PredictedEffortTest 
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
		task = new TaskImpl (element, null);
		taskTable = new TaskTableModel ();
	}
	/**
	 * Test null value
	 */
	@Test
	public void test () 
	{
		assertTrue (0 == task.getPredictedEffort());
		
	}
	/**
	 * Test setter and getter
	 */
	@Test
	public void test1 () 
	{
		task.setPredictedEffort ( (long) 4.0);
		assertTrue( (long) 4.0 == task.getPredictedEffort ());
	}
	/**
	 * Test data type 
	 */
	@Test
	public void test2 () 
	{
		task.setPredictedEffort( (long) 5.5);
		assertFalse (5.5 == task.getPredictedEffort ());
		assertTrue ( (long) 5.5 == task.getPredictedEffort ());
	}
	/**
	 * Test value in milliseconds for TaskTable
	 */
	@Test
	public void test3 () 
	{
		task.setPredictedEffort ( (long) 3600000);
		assertTrue ((double) 1 == (double) taskTable.getValueAt (task, 7));
		
	}
	/**
	 * Test correct String for TaskTable
	 */
	@Test
	public void test4 () 
	{
		assertTrue(taskTable.getColumnName(7).equals("Predicted Effort(hrs)"));
	}
}