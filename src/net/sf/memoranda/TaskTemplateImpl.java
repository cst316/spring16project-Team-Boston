/**
 * 
 */
package net.sf.memoranda;

/**
 * @author Brian Bradley
 *
 */
public class TaskTemplateImpl implements TaskTemplate
{
	private Task task;
	
	public TaskTemplateImpl (Task task)
	{
		this.task = task;
	}
	
	public Task makeTask ()
	{
		return task;
	}
}