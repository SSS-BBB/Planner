package pkg;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

public class MainWindow extends JFrame {

	public static final String APP_NAME = "Planner";

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField seachTextField;
	private JLabel titleLabel;
	private JButton searchButton;
	private JPanel filterPanel;
	private JComboBox someFilterComboBox;
	private JLabel someFilterLabel;
	private JTabbedPane tabSelectPane;
	private JPanel calendarPanel;
	private JPanel activitiesPanel;
	private JLabel activitiesLabel;
	private JPanel monthYearPanel;
	private JLabel monthLabel;
	private JButton prevMonthButton;
	private JButton nextMonthButton;
	private JTextField yearTextField;
	private JPanel monthPanel;
	private JPanel dayOfWeekPanel;
	private JLabel sunLabel;
	private JLabel monLabel;
	private JLabel tueLabel;
	private JLabel wedLabel;
	private JLabel thuLabel;
	private JLabel friLabel;
	private JLabel satLabel;
	private JPanel datePanel;
	private JSeparator separator;
	
	String[] monthName = new String[] { "None", "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY", "AUG", "SEP",
			"OCT", "NOV", "DEC", "None" };
	private int currentYear, currentMonthNum;
	final int CALENDAR_SCALE = 2; // make everything in calendar bigger, so that the calendar takes more space

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setTitle(APP_NAME);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		createHeader();
		createCalendarTab();

		// Activities
		activitiesPanel = new JPanel();

		activitiesLabel = new JLabel("This is activities page");
		activitiesPanel.add(activitiesLabel);

		tabSelectPane.addTab("Activities", null, activitiesPanel, "Go go activities page");

	}

	private void createHeader() {
		// Title
		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel);

		titleLabel = new JLabel(APP_NAME);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 42));
		titlePanel.add(titleLabel);

		// Search
		JPanel searchPanel = new JPanel();
		contentPane.add(searchPanel);

		seachTextField = new JTextField();
		searchPanel.add(seachTextField);
		seachTextField.setColumns(50);

		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
		searchPanel.add(searchButton);

		// Filter
		filterPanel = new JPanel();
		contentPane.add(filterPanel);

		someFilterLabel = new JLabel("Some Filter");
		someFilterLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		filterPanel.add(someFilterLabel);

		someFilterComboBox = new JComboBox();
		filterPanel.add(someFilterComboBox);

		// Tab Select
		tabSelectPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabSelectPane);
	}

	private void createCalendarTab() {
		
		// Current Year and Month
		currentYear = Year.now().getValue();
		currentMonthNum = LocalDate.now().getMonthValue();
		String currentMonth = monthName[currentMonthNum];
		
		// Calendar
		calendarPanel = new JPanel();

		// Tab
		tabSelectPane.setFont(new Font("Arial", Font.PLAIN, 18));
		tabSelectPane.addTab("Calendar", null, calendarPanel, "Go go calendar page");
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));

		// Month Year
		monthYearPanel = new JPanel();
		calendarPanel.add(monthYearPanel);
		monthYearPanel.setLayout(new GridBagLayout());

		JLabel emptyLabel = new JLabel("0000");
		emptyLabel.setFont(new Font("Arial", Font.BOLD, 16 * CALENDAR_SCALE)); // make it as big as the year, so that we
																				// can center the month
		emptyLabel.setForeground(new Color(0, 0, 0, 0));
		GridBagConstraints gbc_space = new GridBagConstraints();
		gbc_space.gridx = 0;
		gbc_space.weightx = 1;
		gbc_space.fill = GridBagConstraints.HORIZONTAL;
		monthYearPanel.add(emptyLabel, gbc_space);

		monthPanel = new JPanel();

		prevMonthButton = new JButton("<");
		prevMonthButton.setVerticalAlignment(SwingConstants.TOP);
		prevMonthButton.setFont(new Font("Arial", Font.BOLD, 10 * CALENDAR_SCALE));
		prevMonthButton.addActionListener(e -> {
			goToPrevMonth();
		});
		monthPanel.add(prevMonthButton);

		monthLabel = new JLabel(currentMonth);
		monthLabel.setVerticalAlignment(SwingConstants.TOP);
		monthLabel.setFont(new Font("Arial", Font.BOLD, 18 * CALENDAR_SCALE));
		monthPanel.add(monthLabel);

		nextMonthButton = new JButton(">");
		nextMonthButton.setVerticalAlignment(SwingConstants.TOP);
		nextMonthButton.setFont(new Font("Arial", Font.BOLD, 10 * CALENDAR_SCALE));
		nextMonthButton.addActionListener(e -> {
			goToNextMonth();
		});
		monthPanel.add(nextMonthButton);

		GridBagConstraints gbc_monthPanel = new GridBagConstraints();
		gbc_monthPanel.gridx = 1;
		gbc_monthPanel.weightx = 0;
		gbc_monthPanel.fill = GridBagConstraints.NONE;
		monthYearPanel.add(monthPanel, gbc_monthPanel);

		yearTextField = new JTextField();
		yearTextField.setHorizontalAlignment(SwingConstants.TRAILING);
		yearTextField.setFont(new Font("Arial", Font.BOLD, 16 * CALENDAR_SCALE));
		yearTextField.setText(String.valueOf(currentYear));
		yearTextField.setOpaque(false);
		yearTextField.setBorder(null);
		yearTextField.setColumns(5);

		GridBagConstraints gbc_yearTextField = new GridBagConstraints();
		gbc_yearTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearTextField.anchor = GridBagConstraints.EAST;
		gbc_yearTextField.gridx = 2;
		gbc_yearTextField.weightx = 1;
		monthYearPanel.add(yearTextField, gbc_yearTextField);

		// Day of Week
		int dateHGap = 10;
		int dateVGap = 50;

		dayOfWeekPanel = new JPanel(new GridLayout(1, 7, dateHGap, dateVGap));
		calendarPanel.add(dayOfWeekPanel);

		sunLabel = new JLabel("SUN");
		sunLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sunLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(sunLabel);

		monLabel = new JLabel("MON");
		monLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(monLabel);

		tueLabel = new JLabel("TUE");
		tueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tueLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(tueLabel);

		wedLabel = new JLabel("WED");
		wedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wedLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(wedLabel);

		thuLabel = new JLabel("THU");
		thuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		thuLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(thuLabel);

		friLabel = new JLabel("FRI");
		friLabel.setHorizontalAlignment(SwingConstants.CENTER);
		friLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(friLabel);

		satLabel = new JLabel("SAT");
		satLabel.setHorizontalAlignment(SwingConstants.CENTER);
		satLabel.setFont(new Font("Arial", Font.BOLD, 12 * CALENDAR_SCALE));
		dayOfWeekPanel.add(satLabel);

		separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		calendarPanel.add(separator);
		
		datePanel = new JPanel();
		calendarPanel.add(datePanel);
		datePanel.setLayout(new GridLayout(5, 7));
		
		createDateOfMonthYear(currentYear, currentMonthNum);
	}
	
	private void goToPrevMonth()
	{
		// update month
		currentMonthNum -= 1;
		
		if (currentMonthNum == 0)
		{
			currentMonthNum = 12;
			currentYear -= 1;
		}
		
		// set month label
		String currentMonthName = monthName[currentMonthNum];
		monthLabel.setText(currentMonthName);
		
		// set year label
		yearTextField.setText(String.valueOf(currentYear));
		
		// set date
		createDateOfMonthYear(currentYear, currentMonthNum);
	}
	
	private void goToNextMonth()
	{
		// update month
		currentMonthNum += 1;
		
		if (currentMonthNum == 13)
		{
			currentMonthNum = 1;
			currentYear += 1;
		}
		
		// set month label
		String currentMonthName = monthName[currentMonthNum];
		monthLabel.setText(currentMonthName);
		
		// set year label
		yearTextField.setText(String.valueOf(currentYear));
		
		// set date
		createDateOfMonthYear(currentYear, currentMonthNum);
	}
	
	private void createDateOfMonthYear(int year, int month) {
		// create date (e.g. 1st - 31th) on the window
		
		datePanel.removeAll();
		
		// Date
		int firstDayOfWeek = getDayOfWeek(year, month, 1); // day of week of 1st of current month and
																			// year
		int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
		int lastDayOfWeek = getDayOfWeek(year, month, daysInMonth);
		
		// determine whether date needs 5 or 6 rows
		int rows = 5;
		int totalCells = (firstDayOfWeek - 1) + daysInMonth;
		if (totalCells > 35)
			rows = 6;
		datePanel.setLayout(new GridLayout(rows, 7));
		
		
		// get last month info
		int lastMonth = month - 1;
		int lastMonthYear = year;
		
		if (lastMonth == 0)
		{
			lastMonth = 12;
			lastMonthYear -= 1;
		}
		
		int lastMonthDaysLength = YearMonth.of(lastMonthYear, lastMonth).lengthOfMonth();
		

		// current month date
		int currentLastDateNum = lastMonthDaysLength - (firstDayOfWeek - 2);
		int currentDayNum = 1;
		int currentNextDateNum = 1;
		
		totalCells = 7 * rows;
		for (int cell = 0; cell < totalCells; cell++) {
			int i = cell / 7 + 1;
			int j = cell % 7 + 1;
			
			if (currentLastDateNum <= lastMonthDaysLength)
			{
				// last month date
				createDateBox(currentLastDateNum, CALENDAR_SCALE, rows, i, j, Color.GRAY);
				currentLastDateNum++;
			}
			
			else if (currentDayNum <= daysInMonth) {
				// current month date
				createDateBox(currentDayNum, CALENDAR_SCALE, rows, i, j, Color.BLACK);
				currentDayNum++;
			}
			
			else {
				// next month date
				createDateBox(currentNextDateNum, CALENDAR_SCALE, rows, i, j, Color.GRAY);
				currentNextDateNum++;
			}
		}
		
	}

	private void createDateBox(int currentDayNum, int CALENDAR_SCALE, int rows, int i, int j, Color color) {
		int padding = 45;

		JPanel dateBox = new JPanel();
		dateBox.setLayout(new BoxLayout(dateBox, BoxLayout.Y_AXIS));

		// date label
		JLabel dateLabel = new JLabel(String.valueOf(currentDayNum));
		dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		dateLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 10 * CALENDAR_SCALE));
		dateLabel.setForeground(color);
		dateBox.add(dateLabel);

		// bottom padding
		dateBox.add(Box.createVerticalStrut(padding));

		int bottomBorder = 0;
		int rightBorder = 0;

		if (i < rows) {
			// not last row
			bottomBorder = 1;
		}

		if (j < 7) {
			// not last column
			rightBorder = 1;
		}

		// add border
		dateBox.setBorder(BorderFactory.createMatteBorder(0, 0, bottomBorder, rightBorder, Color.BLACK));

		datePanel.add(dateBox);
	}

	private int convertDayOfWeek(int dayOfWeek) {
		if (dayOfWeek <= 0 || dayOfWeek > 7)
			System.err.println("WARNING: Invalid day of week number. It should be from 1 to 7.");

		// convert from MON - SUN to SUN - SAT
		return (dayOfWeek % 7) + 1;
	}

	private int getDayOfWeek(int year, int month, int dateNum) {
		LocalDate date = LocalDate.of(year, month, dateNum);
		int dayOfWeek = date.getDayOfWeek().getValue(); // day of week of 1st of current month and year
		dayOfWeek = convertDayOfWeek(dayOfWeek);
		return dayOfWeek;
	}

}
