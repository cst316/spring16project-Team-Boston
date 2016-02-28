package net.sf.memoranda;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class
HealthTimer extends Thread 
{

	public 
	HealthTimer (int minutes) 
	{
		this.waitTime = minutes * 60 * 1000;
		frame  = new JFrame ();
		frame.setAlwaysOnTop (true);
	}
	
	/**
	 * Returns whether or not the thread is running.
	 * 
	 * @return boolean
	 */
	public boolean 
	isRunning ()
	{
		return isRunning;
	}

	/**
	 * The thread that handles displaying the pop up at the correct interval
	 * 
	 * @return void
	 */
	@Override
	public void 
	run () 
	{
		try 
		{
			System.out.println ("[DEBUG] HealthTimer started. With an interval of " + waitTime + " milliseconds.");
			isRunning = true;
			while (keepRunning) 
			{
				Thread.sleep (waitTime);
				if (keepRunning)
				{
					createPopUp ();
				}
			}
			System.out.println ("[DEBUG] HeathTimer has stopped.");
		}
		catch (InterruptedException e) 
		{

			e.printStackTrace ();
		}
	}
	
	/**
	 * Terminates an already running health timer.
	 * 
	 * @return void
	 */
	public void
	terminate ()
	{
		keepRunning = false;
	}

	/**
	 * Creates and shows the pop up reminder for the coder to take a break
	 * 
	 * @return void
	 */
	private void 
	createPopUp ()
	{
		JOptionPane.showMessageDialog (frame, "Time to take your eyes of the screen and stand up for 2 minutes.");
	}
	
	private JFrame frame;
	private boolean isRunning = false, keepRunning = true;
	private long waitTime;
}
