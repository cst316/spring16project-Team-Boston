package net.sf.memoranda;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HealthTimer extends Thread {

	public 
	HealthTimer (int minutes) 
	{
		this.waitTime = minutes * 60 * 1000;
		frame  = new JFrame();
		frame.setAlwaysOnTop(true);
	}
	
	public boolean 
	isRunning ()
	{
		return isRunning;
	}

	@Override
	public void 
	run() 
	{
		try 
		{
			System.out.println("[DEBUG] HealthTimer started. With an interval of " + waitTime + " milliseconds.");
			isRunning = true;
			while (keepRunning) {
				Thread.sleep (waitTime);
				if (keepRunning)
				{
					createPopUp ();
				}
			}
			System.out.println("[DEBUG] HeathTimer has stopped.");
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
	}
	
	public void
	terminate ()
	{
		keepRunning = false;
	}

	private void 
	createPopUp ()
	{
		JOptionPane.showMessageDialog(frame, "Time to take your eyes of the screen and stand up for 2 minutes.");
	}
	
	private JFrame frame;
	private long waitTime;
	private boolean isRunning = false, keepRunning = true;
}