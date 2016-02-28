package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Note;
import net.sf.memoranda.NoteImpl;
import nu.xom.Element;
/**
 * Tests tag functionality 
 */
public class 
TagsNoteTest 
{	
	/**
	 * Tests the default tag
	 */
	@Test
	public void
	testDefault () 
	{
		assertTrue (n.getTags () == "");
	}
	
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
	 * Tests multiple tags
	 */
	@Test
	public void
	testMultipleTags () 
	{
		n.addTag ("Test 1, Test 2");
		assertTrue (n.getTags ().equals ("Test 1, Test 2")); 
	}
	
	/**
	 * Tests one tag
	 */
	@Test
	public void
	testOneTag () 
	{
		n.addTag ("Test 1");
		assertTrue (n.getTags () == "Test 1");
	}

	Element element;
	Note n;
}
