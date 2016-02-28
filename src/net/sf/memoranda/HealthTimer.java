package net.sf.memoranda;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HealthTimer extends Thread {

	public HealthTimer(int minutes) {
		this.waitTime = minutes * 60 * 1000;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1);
			createPopUp();
			// show pop up panel with reminder
			// wait for 10 seconds or until 'OK' is clicked

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createPopUp()
	{
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Time to take your eyes of the screen and stand up for 2 minutes.");
	}

	private long waitTime;
}
