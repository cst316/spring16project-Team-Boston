package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
//import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;

/*$Id: TaskDialog.java,v 1.25 2005/12/01 08:12:26 alexeya Exp $*/
public class TaskDialog extends JDialog
{
	JPanel mPanel = new JPanel (new BorderLayout ());
	JPanel areaPanel = new JPanel (new BorderLayout ());
	JPanel buttonsPanel = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	JButton cancelB = new JButton ();
	JButton okB = new JButton ();
	Border border1;
	Border border2;
	JPanel dialogTitlePanel = new JPanel (new FlowLayout (FlowLayout.LEFT));
	JLabel header = new JLabel ();
	public boolean CANCELLED = true;
	public boolean updateChildren = false;
	JPanel jPanel8 = new JPanel (new GridBagLayout ());
	Border border3;
	Border border4;
	// Border border5;
	// Border border6;
	// changed to (4,2) from (3,2)
	JPanel jPanel2 = new JPanel (new GridLayout (4, 2));
	JTextField todoField = new JTextField ();

	// added by rawsushi
	JTextField effortField = new JTextField ();
	// effect predicted field
	JTextField effortPredictedField = new JTextField ();
	JTextArea descriptionField = new JTextArea ();
	JScrollPane descriptionScrollPane = new JScrollPane (descriptionField);

	// Border border7;
	Border border8;
	CalendarFrame startCalFrame = new CalendarFrame ();
	CalendarFrame endCalFrame = new CalendarFrame ();
	String[] priority =
	{
			Local.getString ("Lowest"),
			Local.getString ("Low"),
			Local.getString ("Normal"),
			Local.getString ("High"),
			Local.getString ("Highest")
	};
	boolean ignoreStartChanged = false;
	boolean ignoreEndChanged = false;
	JPanel jPanel4 = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	JPanel jPanel6 = new JPanel (new FlowLayout (FlowLayout.LEFT));
	JLabel startDateLabel = new JLabel ();
	JButton setStartDateB = new JButton ();
	JPanel jPanel1 = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	JLabel endDateLabel = new JLabel ();
	JSpinner startDate;
	JSpinner endDate;
	// JSpinner endDate = new JSpinner(new SpinnerDateModel());
	JButton setEndDateB = new JButton ();
	// JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel jPanel3 = new JPanel (new FlowLayout (FlowLayout.LEFT));
	JPanel jPanelEffort = new JPanel (new FlowLayout (FlowLayout.LEFT));
	// new jpanel for predicted effort
	JPanel jPanelEffortPredicted = new JPanel (new FlowLayout (FlowLayout.LEFT));
	// JPanel jPanelNotes = new JPanel(new FlowLayout(FlowLayout.LEFT));

	JButton setNotifB = new JButton ();
	JComboBox priorityCB = new JComboBox (priority);
	JLabel jLabel7 = new JLabel ();
	// added by rawsushi
	JLabel jLabelEffort = new JLabel ();
	// new label for predicted effort
	JLabel jLabelEffortPredicted = new JLabel ();
	JLabel jLabelDescription = new JLabel ();
	JCheckBox chkEndDate = new JCheckBox ();
	JCheckBox chkStartDate = new JCheckBox ();

	JPanel jPanelProgress = new JPanel (new FlowLayout (FlowLayout.RIGHT));
	JLabel jLabelProgress = new JLabel ();
	JCheckBox jCheckBoxProgress = new JCheckBox ("Update from children.");
	JSpinner progress = new JSpinner (new SpinnerNumberModel (0, 0, 100, 5));

	// Forbid to set dates outside the bounds
	CalendarDate startDateMin = CurrentProject.get ().getStartDate ();
	CalendarDate startDateMax = CurrentProject.get ().getEndDate ();
	CalendarDate endDateMin = startDateMin;
	CalendarDate endDateMax = startDateMax;

	public TaskDialog (Frame frame, String title)
	{
		super (frame, title, true);
		try
		{
			jbInit ();
			pack ();
		}
		catch (Exception ex)
		{
			new ExceptionDialog (ex);
		}
	}

	void jbInit () throws Exception
	{
		this.setResizable (false);
		this.setSize (new Dimension (430, 300));
		border1 = BorderFactory.createEmptyBorder (5, 5, 5, 5);
		border2 = BorderFactory.createEtchedBorder (Color.white, new Color (142, 142, 142));
		border3 = new TitledBorder (BorderFactory.createEmptyBorder (0, 0, 10, 0), Local.getString ("To Do"), TitledBorder.LEFT, TitledBorder.BELOW_TOP);
		border4 = BorderFactory.createEmptyBorder (0, 5, 0, 5);
		// border5 = BorderFactory.createEmptyBorder();
		// border6 =
		// BorderFactory.createBevelBorder(BevelBorder.LOWERED,
		// Color.white, Color.white, new Color(178, 178, 178),
		// new Color(124, 124, 124));
		// border7 = BorderFactory.createLineBorder(Color.white, 2);
		border8 = BorderFactory.createEtchedBorder (Color.white, new Color (178, 178, 178));
		cancelB.setMaximumSize (new Dimension (100, 26));
		cancelB.setMinimumSize (new Dimension (100, 26));
		cancelB.setPreferredSize (new Dimension (100, 26));
		cancelB.setText (Local.getString ("Cancel"));
		cancelB.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				cancelB_actionPerformed (e);
			}
		});

		startDate = new JSpinner (new SpinnerDateModel (new Date (), null, null, Calendar.DAY_OF_WEEK));
		endDate = new JSpinner (new SpinnerDateModel (new Date (), null, null, Calendar.DAY_OF_WEEK));

		chkEndDate.setSelected (false);
		chkEndDate_actionPerformed (null);
		chkEndDate.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				chkEndDate_actionPerformed (e);
			}
		});
		chkStartDate.setSelected (false);
		chkStartDate_actionPerformed (null);
		chkStartDate.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				chkStartDate_actionPerformed (e);
			}
		});
		okB.setMaximumSize (new Dimension (100, 26));
		okB.setMinimumSize (new Dimension (100, 26));
		okB.setPreferredSize (new Dimension (100, 26));
		okB.setText (Local.getString ("Ok"));
		okB.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				okB_actionPerformed (e);
			}
		});

		this.getRootPane ().setDefaultButton (okB);
		mPanel.setBorder (border1);
		areaPanel.setBorder (border2);
		dialogTitlePanel.setBackground (Color.WHITE);
		dialogTitlePanel.setBorder (border4);
		// dialogTitlePanel.setMinimumSize(new Dimension(159, 52));
		// dialogTitlePanel.setPreferredSize(new Dimension(159, 52));
		header.setFont (new java.awt.Font ("Dialog", 0, 20));
		header.setForeground (new Color (0, 0, 124));
		header.setText (Local.getString ("To do"));
		header.setIcon (new ImageIcon (net.sf.memoranda.ui.TaskDialog.class.getResource ("resources/icons/task48.png")));

		GridBagLayout gbLayout = (GridBagLayout) jPanel8.getLayout ();
		jPanel8.setBorder (border3);

		todoField.setBorder (border8);
		todoField.setPreferredSize (new Dimension (375, 24));
		GridBagConstraints gbCon = new GridBagConstraints ();
		gbCon.gridwidth = GridBagConstraints.REMAINDER;
		gbCon.weighty = 1;
		gbLayout.setConstraints (todoField, gbCon);

		jLabelDescription.setMaximumSize (new Dimension (100, 16));
		jLabelDescription.setMinimumSize (new Dimension (60, 16));
		jLabelDescription.setText (Local.getString ("Description"));
		gbCon = new GridBagConstraints ();
		gbCon.gridwidth = GridBagConstraints.REMAINDER;
		gbCon.weighty = 1;
		gbCon.anchor = GridBagConstraints.WEST;
		gbLayout.setConstraints (jLabelDescription, gbCon);

		descriptionField.setBorder (border8);
		descriptionField.setPreferredSize (new Dimension (375, 387));
		// 3 additional pixesl from 384 so that the last line is not cut
		// offl
		descriptionField.setLineWrap (true);
		descriptionField.setWrapStyleWord (true);
		gbCon = new GridBagConstraints ();
		gbCon.gridwidth = GridBagConstraints.REMAINDER;
		gbCon.weighty = 3;
		descriptionScrollPane.setPreferredSize (new Dimension (375, 96));
		gbLayout.setConstraints (descriptionScrollPane, gbCon);

		jLabelEffort.setMaximumSize (new Dimension (100, 16));
		jLabelEffort.setMinimumSize (new Dimension (60, 16));
		jLabelEffort.setText (Local.getString ("Actual Effort(hrs)"));
		effortField.setBorder (border8);
		effortField.setPreferredSize (new Dimension (30, 24));

		// new entry for predicted effort hours
		jLabelEffortPredicted.setMaximumSize (new Dimension (100, 16));
		jLabelEffortPredicted.setMinimumSize (new Dimension (60, 16));
		jLabelEffortPredicted.setText (Local.getString ("Predicted Effort(hrs)"));
		effortPredictedField.setBorder (border8);
		effortPredictedField.setPreferredSize (new Dimension (30, 24));
		
		startDate.setBorder (border8);
		startDate.setPreferredSize (new Dimension (80, 24));
		SimpleDateFormat sdf = new SimpleDateFormat ();
		sdf = (SimpleDateFormat) DateFormat.getDateInstance (DateFormat.SHORT);
		// //Added by (jcscoobyrs) on 14-Nov-2003 at 10:45:16 PM
		startDate.setEditor (new JSpinner.DateEditor (startDate, sdf.toPattern ()));

		/*startDate.addChangeListener (new ChangeListener ()
		{
			public void stateChanged (ChangeEvent e)
			{
				// it's an ugly hack so that the spinner can
				// increase day by day
				SpinnerDateModel sdm = new SpinnerDateModel ((Date) startDate.getModel ().getValue (), null, null, Calendar.DAY_OF_WEEK);
				startDate.setModel (sdm);

				if (ignoreStartChanged) return;
				ignoreStartChanged = true;
				Date sd = (Date) startDate.getModel ().getValue ();
				Date ed = (Date) endDate.getModel ().getValue ();
				if (!chkStartDate.isSelected ())
					return;
				if (sd.after (ed) && chkEndDate.isSelected ())
				{
					startDate.getModel ().setValue (ed);
					sd = ed;
				}
				if ( (startDateMax != null) && sd.after (startDateMax.getDate ()))
				{
					startDate.getModel ().setValue (startDateMax.getDate ());
					sd = startDateMax.getDate ();
				}
				if ( (startDateMin != null) && sd.before (startDateMin.getDate ()))
				{
					startDate.getModel ().setValue (startDateMin.getDate ());
					sd = startDateMin.getDate ();
				}
				startCalFrame.cal.set (new CalendarDate (sd));
				ignoreStartChanged = false;
			}
		});*/

		startDateLabel.setText (Local.getString ("Start date"));
		// startDateLabel.setPreferredSize(new Dimension(60, 16));
		startDateLabel.setMinimumSize (new Dimension (60, 16));
		startDateLabel.setMaximumSize (new Dimension (100, 16));
		setStartDateB.setMinimumSize (new Dimension (24, 24));
		setStartDateB.setPreferredSize (new Dimension (24, 24));
		setStartDateB.setText ("");
		setStartDateB.setIcon (new ImageIcon (net.sf.memoranda.ui.AppFrame.class.getResource ("resources/icons/calendar.png")));
		setStartDateB.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				setStartDateB_actionPerformed (e);
			}
		});
		endDateLabel.setMaximumSize (new Dimension (270, 16));
		// endDateLabel.setPreferredSize(new Dimension(60, 16));
		endDateLabel.setHorizontalAlignment (SwingConstants.RIGHT);
		endDateLabel.setText (Local.getString ("End date"));
		endDate.setBorder (border8);
		endDate.setPreferredSize (new Dimension (80, 24));

		endDate.setEditor (new JSpinner.DateEditor (endDate, sdf.toPattern ())); // Added
		// by
		// (jcscoobyrs)
		// on
		// 14-Nov-2003 at 10:45:16PM

		endDate.addChangeListener (new ChangeListener ()
		{
			public void stateChanged (ChangeEvent e)
			{
				// it's an ugly hack so that the spinner can
				// increase day by day
				SpinnerDateModel sdm = new SpinnerDateModel ((Date) endDate.getModel ().getValue (), null, null, Calendar.DAY_OF_WEEK);
				endDate.setModel (sdm);

				if (ignoreEndChanged) return;
				ignoreEndChanged = true;
				Date sd = (Date) startDate.getModel ().getValue ();
				Date ed = (Date) endDate.getModel ().getValue ();
				if (ed.before (sd))
				{
					endDate.getModel ().setValue (ed);
					ed = sd;
				}
				if ( (endDateMax != null) && ed.after (endDateMax.getDate ()))
				{
					endDate.getModel ().setValue (endDateMax.getDate ());
					ed = endDateMax.getDate ();
				}
				if ( (endDateMin != null) && ed.before (endDateMin.getDate ()))
				{
					endDate.getModel ().setValue (endDateMin.getDate ());
					ed = endDateMin.getDate ();
				}
				endCalFrame.cal.set (new CalendarDate (ed));
				ignoreEndChanged = false;
			}
		});
		setEndDateB.setMinimumSize (new Dimension (24, 24));
		setEndDateB.setPreferredSize (new Dimension (24, 24));
		setEndDateB.setText ("");
		setEndDateB.setIcon (new ImageIcon (net.sf.memoranda.ui.AppFrame.class.getResource ("resources/icons/calendar.png")));
		setEndDateB.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				setEndDateB_actionPerformed (e);
			}
		});

		setNotifB.setText (Local.getString ("Set notification"));
		setNotifB.setIcon (new ImageIcon (net.sf.memoranda.ui.AppFrame.class.getResource ("resources/icons/notify.png")));
		setNotifB.addActionListener (new java.awt.event.ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				setNotifB_actionPerformed (e);
			}
		});
		jLabel7.setMaximumSize (new Dimension (100, 16));
		jLabel7.setMinimumSize (new Dimension (60, 16));
		// jLabel7.setPreferredSize(new Dimension(60, 16));
		jLabel7.setText (Local.getString ("Priority"));

		priorityCB.setFont (new java.awt.Font ("Dialog", 0, 11));
		jPanel4.add (jLabel7, null);
		getContentPane ().add (mPanel);
		mPanel.add (areaPanel, BorderLayout.CENTER);
		mPanel.add (buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.add (okB, null);
		buttonsPanel.add (cancelB, null);
		this.getContentPane ().add (dialogTitlePanel, BorderLayout.NORTH);
		dialogTitlePanel.add (header, null);
		areaPanel.add (jPanel8, BorderLayout.NORTH);
		jPanel8.add (todoField, null);
		jPanel8.add (jLabelDescription);
		jPanel8.add (descriptionScrollPane, null);
		areaPanel.add (jPanel2, BorderLayout.CENTER);
		jPanel2.add (jPanel6, null);
		jPanel6.add (chkStartDate, null);
		jPanel6.add (startDateLabel, null);
		jPanel6.add (startDate, null);
		jPanel6.add (setStartDateB, null);
		jPanel2.add (jPanel1, null);
		jPanel1.add (chkEndDate, null);
		jPanel1.add (endDateLabel, null);
		jPanel1.add (endDate, null);
		jPanel1.add (setEndDateB, null);
		// added by rawsushi
		jPanel2.add (jPanelEffort, null);
		jPanelEffort.add (jLabelEffort, null);
		jPanelEffort.add (effortField, null);

		jPanel2.add (jPanel4, null);
		jPanel4.add (priorityCB, null);
		
		// add predicted effort panel
		jPanel2.add (jPanelEffortPredicted, null);
		jPanelEffortPredicted.add (jLabelEffortPredicted, null);
		jPanelEffortPredicted.add (effortPredictedField, null);
		
		// moved notification button
		jLabelProgress.setText (Local.getString ("Progress"));
		jPanelProgress.add (jLabelProgress, null);
		jPanelProgress.add (progress, null);
		jPanel2.add (jPanel3, null);

		jPanel3.add (setNotifB, null);

		

		jCheckBoxProgress.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent actionEvent)
			{
				if (!jCheckBoxProgress.isSelected ())
				{
					updateChildren = false;
					((DefaultEditor) progress.getEditor ()).getTextField ().setEditable (true);
				}
				else
				{
					updateChildren = true;
					((DefaultEditor) progress.getEditor ()).getTextField ().setEditable (false);
				}
			}
		});
		jPanelProgress.add (jCheckBoxProgress, null);
		jPanel2.add (jPanelProgress);

		priorityCB.setSelectedItem (Local.getString ("Normal"));
		startCalFrame.cal.addSelectionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				if (ignoreStartChanged) return;
				startDate.getModel ().setValue (startCalFrame.cal.get ().getCalendar ().getTime ());
			}
		});

		endCalFrame.cal.addSelectionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent e)
			{
				if (ignoreEndChanged) return;
				endDate.getModel ().setValue (endCalFrame.cal.get ().getCalendar ().getTime ());
			}
		});
	}

	public void setStartDate (CalendarDate d)
	{
		this.startDate.getModel ().setValue (d.getDate ());
	}

	public void setEndDate (CalendarDate d)
	{
		if (d != null) this.endDate.getModel ().setValue (d.getDate ());
	}

	public void setStartDateLimit (CalendarDate min, CalendarDate max)
	{
		this.startDateMin = min;
		this.startDateMax = max;
	}

	public void setEndDateLimit (CalendarDate min, CalendarDate max)
	{
		this.endDateMin = min;
		this.endDateMax = max;
	}

	void okB_actionPerformed (ActionEvent e)
	{
		CANCELLED = false;
		this.dispose ();
	}

	void cancelB_actionPerformed (ActionEvent e)
	{
		this.dispose ();
	}

	void chkEndDate_actionPerformed (ActionEvent e)
	{
		endDate.setEnabled (chkEndDate.isSelected ());
		setEndDateB.setEnabled (chkEndDate.isSelected ());
		endDateLabel.setEnabled (chkEndDate.isSelected ());
		if (chkEndDate.isSelected ())
		{
			Date currentEndDate = (Date) endDate.getModel ().getValue ();
			Date currentStartDate = (Date) startDate.getModel ().getValue ();
			if (currentEndDate.getTime () < currentStartDate.getTime ())
			{
				endDate.getModel ().setValue (currentStartDate);
			}
		}
	}
	
	void chkStartDate_actionPerformed (ActionEvent e)
	{
		startDate.setEnabled (chkStartDate.isSelected ());
		setStartDateB.setEnabled (chkStartDate.isSelected ());
		startDateLabel.setEnabled (chkStartDate.isSelected ());
		if (chkStartDate.isSelected ())
		{
			Date currentEndDate  = (Date) endDate.getModel ().getValue ();
			Date currentStartDate = (Date) startDate.getModel ().getValue ();
			if (currentEndDate.getTime () < currentStartDate.getTime ())
			{
				startDate.getModel ().setValue (currentEndDate);
			}
		}
	}

	void setStartDateB_actionPerformed (ActionEvent e)
	{
		startCalFrame.setLocation (setStartDateB.getLocation ());
		startCalFrame.setSize (200, 200);
		this.getLayeredPane ().add (startCalFrame);
		startCalFrame.show ();

	}

	void setEndDateB_actionPerformed (ActionEvent e)
	{
		endCalFrame.setLocation (setEndDateB.getLocation ());
		endCalFrame.setSize (200, 200);
		this.getLayeredPane ().add (endCalFrame);
		endCalFrame.show ();
	}

	void setNotifB_actionPerformed (ActionEvent e)
	{
		((AppFrame) App.getFrame ()).workPanel.dailyItemsPanel.eventsPanel.newEventB_actionPerformed (e, this.todoField.getText (), (Date) startDate.getModel ().getValue (), (Date) endDate.getModel ().getValue ());
	}

}
