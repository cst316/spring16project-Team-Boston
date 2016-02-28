package net.sf.memoranda;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HealthTimer extends Thread {

	HealthTimer(int minutes) {
		this.waitTime = minutes * 60 * 1000;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(waitTime);
			// show pop up panel with reminder
			// wait for 10 seconds or until 'OK' is clicked

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createPopUp()
	{
		final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Click me to show dialog!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent,
                        "What is your name?", null);
            }
        });
	}

	private long waitTime;
}
