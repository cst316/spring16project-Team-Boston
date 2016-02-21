package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Note;
import net.sf.memoranda.NoteImpl;
import nu.xom.Element;
/**
 * Tests for priority settings on notes
 *
 */
public class 
PriorityNoteTest 
{
	Element element;
	Note n;
	
	/**
	 * Set up new note to be tested
	 * @throws Exception
	 */
	@Before
	public void 
	setUp () 
	throws Exception 
	{
		element = new Element ("note");
		n = new NoteImpl (element, null);
	}
	
	/**
	 * Tests the default priority
	 */
	@Test
	public void
	testDefault () 
	{
		assertTrue (n.getPriority () == 2);
		assertTrue (n.getPriorityString ().equals ("Normal"));
	}
	
	/**
	 * Test the high priority
	 */
	@Test
	public void 
	testHigh () 
	{
		n.setPriority (3);
		assertTrue (n.getPriority () == 3);
		assertTrue (n.getPriorityString ().equals ("High"));
	}
	/**
	 * Test the highest priority
	 */
	@Test
	public void 
	testHighest () 
	{
		n.setPriority (4);
		assertTrue (n.getPriority () == 4);
		assertTrue (n.getPriorityString ().equals ("Highest"));
	}

	/**
	 * Test the low priority
	 */
	@Test 
	public void 
	testLow () 
	{
		n.setPriority (1);
		assertTrue (n.getPriority () == 1);
		assertTrue (n.getPriorityString ().equals ("Low"));
	}
	
	/**
	 * Test the lowest priority
	 */
	@Test 
	public void 
	testLowest () 
	{
		n.setPriority (0);
		assertTrue (n.getPriority () == 0);
		assertTrue (n.getPriorityString ().equals ("Lowest"));
	}
	
	/**
	 * Test the normal priority
	 */
	@Test
	public void 
	testNormal () 
	{
		n.setPriority (2);
		assertTrue (n.getPriority () == 2);
		assertTrue (n.getPriorityString ().equals ("Normal"));
	}
}
