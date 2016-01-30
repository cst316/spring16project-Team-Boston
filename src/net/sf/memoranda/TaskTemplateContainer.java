/**
 * 
 */
package net.sf.memoranda;

/**
 * @author Brian Bradley
 *
 */
public interface TaskTemplateContainer
{
	public void put (TaskTemplate t);
	
	public TaskTemplate get (String name);
	
	public void remove (String name);
	
	public Iteratator<TaskTemplate> iterator ();
}
