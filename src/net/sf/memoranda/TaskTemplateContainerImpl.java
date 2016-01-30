/**
 * 
 */
package net.sf.memoranda;

/**
 * @author Brian Bradley
 *
 */
public class TaskTemplateContainerImpl
{
	TreeMap<String, TaskTemplate> templates;
	
	public TaskTemplateListImpl ()
	{
	}

	public void put (String name, TaskTemplate taskTemplate)
	{
		templates.put (name, taskTemplate);
	}
	
	public TaskTemplate get (String name)
	{
		return templates.get (name);
	}
	
	public void remove (String name)
	{
		templates.remove (name);
	}
	
	public Iteratator<TaskTemplate> iterator ()
	{
		return templates.values.iterator ();
	}
}
