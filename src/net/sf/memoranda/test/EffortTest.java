package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import nu.xom.Element;
/**
 * Test actual effort hours
 *
 */
public class EffortTest 
{
	Element element;
	Task task;
	
	/**
	 * Set up test cases by creating an new element and task
	 * @throws Exception
	 */
	@Before
	public void setUp () throws Exception
	{
		element = new Element ("task");
		task = new TaskImpl (element, null);
	}
	/**
	 * Test setter and getter
	 */
	@Test
	public void test1 () 
	{
		task.setEffort ( (long) 4.0);
		assertTrue( (long) 4.0 == task.getEffort ());
	}
	/**
	 * Test data type 
	 */
	@Test
	public void test2 () 
	{
		task.setEffort( (long) 5.5);
		assertFalse (5.5 == task.getEffort ());
		assertTrue ( (long) 5.5 == task.getEffort ());
	}
}