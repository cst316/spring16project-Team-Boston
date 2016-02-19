package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Note;
import net.sf.memoranda.NoteImpl;
import nu.xom.Element;

public class PriorityNoteTest 
{
	Note n;
	Element element;

	@Before
	public void setUp() throws Exception 
	{
		element = new Element("note");
		n = new NoteImpl(element, null);
	}

	@Test
	public void testDefault() 
	{
		assertTrue(n.getPriority() == 2);
		assertTrue(n.getPriorityString().equals("Normal"));
	}
	
	@Test
	public void testLowest() 
	{
		assertTrue(n.getPriority() == 2);
	}

}
