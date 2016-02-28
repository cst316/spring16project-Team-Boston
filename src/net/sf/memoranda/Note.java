/**
 * Note.java
 * Created on 11.02.2003, 17:05:27 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import net.sf.memoranda.date.CalendarDate;
/**
 * 
 */
/*$Id: Note.java,v 1.4 2004/09/30 17:19:52 ivanrise Exp $*/
public interface 
Note 
{
    // priority for notes
    public static final int PRIORITY_LOWEST = 0;
    
    public static final int PRIORITY_LOW = 1;
    
    public static final int PRIORITY_NORMAL = 2;
    
    public static final int PRIORITY_HIGH = 3;
    
    public static final int PRIORITY_HIGHEST = 4;
    
    CalendarDate getDate ();
    
    // priority for notes
    int getPriority ();
    void setPriority (int p);
    String getPriorityString ();
    
    String getTitle ();
    void setTitle (String s);
    
	String getId ();
	void setId (String s);
	
    boolean isMarked ();
    void setMark (boolean mark);
    
    // add tags
    void addTag (String s);
    String getTags ();
    void setTags (String s);
        
    Project getProject ();
}

