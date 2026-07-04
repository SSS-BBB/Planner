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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

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
	private JLabel calendarLabel;
	private JPanel activitiesPanel;
	private JLabel activitiesLabel;

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
		
		// Title
		JPanel titlePanel = new JPanel();
		contentPane.add(titlePanel);
		
		titleLabel = new JLabel(APP_NAME);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 28));
		titlePanel.add(titleLabel);
		
		// Search
		JPanel searchPanel = new JPanel();
		contentPane.add(searchPanel);
		
		seachTextField = new JTextField();
		searchPanel.add(seachTextField);
		seachTextField.setColumns(50);
		
		searchButton = new JButton("Search");
		searchPanel.add(searchButton);
		
		// Filter
		filterPanel = new JPanel();
		contentPane.add(filterPanel);
		
		someFilterLabel = new JLabel("Some Filter");
		filterPanel.add(someFilterLabel);
		
		someFilterComboBox = new JComboBox();
		filterPanel.add(someFilterComboBox);
		
		// Tab Select
		tabSelectPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabSelectPane);
		
		// Calendar
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		calendarLabel = new JLabel("This is calendar page");
		calendarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		calendarPanel.add(calendarLabel);
		calendarPanel.add(Box.createRigidArea(new Dimension(1280, 450)));
		
		// Activities
		activitiesPanel = new JPanel();
		
		activitiesLabel = new JLabel("This is activities page");
		activitiesPanel.add(activitiesLabel);
		
		// Tab
		tabSelectPane.addTab("Calendar", null, calendarPanel, "Go go calendar page");
		tabSelectPane.addTab("Activities", null, activitiesPanel, "Go go activities page");
		
		System.out.println("Commit Test");
		
	}

}
