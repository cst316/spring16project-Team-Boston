/**
 * NoteImpl.java
 * Created on 13.02.2003, 15:36:55 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.util.ArrayList;
import java.util.Arrays;

import net.sf.memoranda.date.CalendarDate;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * 
 */
/*$Id: NoteImpl.java,v 1.6 2004/10/06 19:15:44 ivanrise Exp $*/
public class 
NoteImpl implements Note, Comparable
{
    
    private Element _el = null; 
    private Project _project;
    
    /**
     * Constructor for NoteImpl.
     */
    public NoteImpl (Element el, Project project) 
    {
        _el = el;
        _project = project;
    }

    /**
     * @see net.sf.memoranda.Note#getDate()
     */
    public 
    CalendarDate getDate () 
    {
		Element day = (Element)_el.getParent ();
		Element month = (Element)day.getParent ();
		Element year = (Element)month.getParent ();

     //   return new CalendarDate(day.getAttribute("date").getValue());
		
		return new CalendarDate (new Integer (day.getAttribute ("day").getValue ()).intValue (), 
								new Integer (month.getAttribute ("month").getValue ()).intValue (),
								new Integer (year.getAttribute ("year").getValue ()).intValue ());

    }
    
    /**
     * get priority for notes
     */
	public int 
	getPriority () 
	{
		Attribute pa = _el.getAttribute ("priority");
		if (pa == null)
		{
			return Task.PRIORITY_NORMAL;
		}
		return new Integer (pa.getValue ()).intValue ();
	}
	
	/**
	 * set priority for notes
	 */
	public void 
	setPriority (int p) 
	{
		setAttr ("priority", String.valueOf (p));
	}
	
	/**
	 * set element attribute for notes
	 * @param a
	 * @param value
	 */
	private void 
	setAttr (String a, String value) 
	{
		Attribute attr = _el.getAttribute(a);
		if (attr == null)
		{
			_el.addAttribute (new Attribute (a, value));
		}
		else
		{
			attr.setValue (value);
		}
	}
	
	public 
	String getPriorityString ()
	{
		switch (getPriority ())
		{
			case PRIORITY_LOWEST:
				return "Lowest";
			case PRIORITY_LOW:
				return "Low";
			case PRIORITY_NORMAL:
				return "Normal";
			case PRIORITY_HIGH:
				return "High";
			case PRIORITY_HIGHEST:
				return "Highest";
			default:
				return "Normal";
		}
	}
    
    public 
    Project getProject () 
    {
        return _project;
    }
    
    /**
     * @see net.sf.memoranda.Note#getTitle()
     */
    public 
    String getTitle () 
    {
        Attribute ta = _el.getAttribute ("title");
        if (ta == null)
        {
        	return "";
        }
        return _el.getAttribute ("title").getValue ();
    }
    
    /**
     * @see net.sf.memoranda.Note#setTitle(java.lang.String)
     */
    public void 
    setTitle (String s) 
    {
        Attribute ta = _el.getAttribute ("title");
        if (ta == null) 
        {
        	_el.addAttribute (new Attribute ("title", s));
        }
        else 
        {
            ta.setValue (s);
        }
    }
    
    /**
     * Add tags to notes
     */
    public void 
    addTag (String s) 
    {
        Attribute tag = _el.getAttribute ("tag");
        if (tag == null)
        {
        	_el.addAttribute (new Attribute ("tag", s));
        }
        else 
        {
            ArrayList<String> tags = new ArrayList<String> 
            	(Arrays.asList (tag.getValue ().split (",")));
            tags.add(s);
            StringBuilder sb = new StringBuilder ();
            for (String c : tags)
            {
            	sb.append (c);
            	sb.append (",");
            }
            tag.setValue (sb.toString ());
        }
    }
    
    /**
     * String for tags
     */
    public 
    String getTags ()
    {
    	Attribute tag = _el.getAttribute ("tag");
    	if (tag == null)
    	{
    		return "";
    	}
    	else
    	{
    		return tag.getValue ();
    	}
    }
    
    /**
     * Set tags
     */
	public void 
	setTags (String s)
	{
		Attribute tag = _el.getAttribute ("tag") ;
	    if (tag == null)
	    {
	        _el.addAttribute (new Attribute ("tag", s));
	    }
	    else
	    {
	        tag.setValue (s);
	    }
	}
	
	/**
     * @see net.sf.memoranda.Note#getId
     */
	public 
	String getId () 
	{
		Attribute id = _el.getAttribute ("refid");
		if (id==null)
		{
			return "";
		}
		return _el.getAttribute ("refid").getValue();
	}
	
	/**
     * @see net.sf.memoranda.Note#setId(java.lang.String)
     */ 
	public void 
	setId (String s) 
	{
		Attribute id = _el.getAttribute ("refid");
		if (id==null)
		{
			_el.addAttribute (new Attribute ("refid", s));
		}
	}
	
    /**
     * @see net.sf.memoranda.Note#isMarked()
     */
    public boolean 
    isMarked () 
    {
        return _el.getAttribute ("bookmark") != null;        
    }
    
    /**
     * @see net.sf.memoranda.Note#setMark(boolean)
     */
    public void 
    setMark (boolean mark) 
    {
        Attribute ma = _el.getAttribute ("bookmark");        
        if (ma == null) 
        {
            if (mark)
            {
                _el.addAttribute (new Attribute ("bookmark", "yes"));
            }
            return;
        }
        else if (!mark)
        {
            _el.removeAttribute (ma);
        }
    }
	
	/*
	 * Comparable interface
	 */
	public int 
	compareTo (Object o)
	{
		Note note = (Note) o;
		if (getDate ().getDate ().getTime () > note.getDate ().getDate ().getTime ())
		{
			return 1;
		}
		else if (getDate ().getDate ().getTime () < note.getDate ().getDate ().getTime ())
		{
			return -1;
		}
		else 
		{
			return 0;
		}
	}  
}