package src.softwareSpecs;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;
import javax.swing.JList;

import java.awt.Button;

public class NewGui extends JFrame {

	private JPanel contentPane;
	private File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGui frame = new NewGui();
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
	public NewGui() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewGui.class.getResource("/Images/JFrame Icon.png")));
		setForeground(UIManager.getColor("textHighlight"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// code found here http://www.eclipse.org/forums/index.php/t/236548/
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final double x = screen.width / 1366;
		final double y = (screen.height) / 768;
		final double w = screen.width / 1366;
		final double h = screen.height / 768;
		setBounds(0, 0, screen.width, screen.height - 40);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#014D33"));
		contentPane.setForeground(UIManager.getColor("textHighlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnContactUs = new JButton("Contact us");
		btnContactUs.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnContactUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// To contact us a new JFrame will appear and display an email
				// to which the user can send messages
				JFrame alert = new JFrame();
				alert.setTitle("How to Contact us");
				alert.setResizable(false);
				alert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				alert.setBounds((int)(100 * x),(int)( 100 * y),(int) (450 * w),(int)( 300 * h));
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setBackground(Color.decode("#014D33"));
				alert.setContentPane(contentPane);
				contentPane.setLayout(null);

				JPanel panel = new JPanel();
				panel.setBounds((int)(21 * x),(int)( 23 * y), (int)(402 * w),(int)( 224 * h));
				panel.setBackground(Color.decode("#163259"));
				contentPane.add(panel);
				panel.setLayout(null);

				JLabel lblNewLabel = new JLabel(
						"Please Email jamesmpalmisano@gmail.com");
				lblNewLabel.setForeground(new Color(255, 255, 255));
				lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				lblNewLabel.setBounds((int)(59 * x),(int)( 59 * y), (int)(302 * w),(int)( 89 * h));
				panel.add(lblNewLabel);

				alert.setVisible(true);
				alert.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
		});
		btnContactUs.setBounds((int)(987 * x), (int)(622 * y),(int) (139 * w),(int)( 23 * h));
		contentPane.add(btnContactUs);

		JButton btnDoOurSurvey = new JButton("Do our survey");
		btnDoOurSurvey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// open default webbrowsser
				// this code was found here
				// http://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						desktop.browse(new URI("http://goo.gl/forms/m8OqtUwd8l"));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnDoOurSurvey.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDoOurSurvey.setBounds(1136, 622, 178, 23);
		contentPane.add(btnDoOurSurvey);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setForeground(UIManager.getColor("textHighlight"));
		tabbedPane.setBackground(UIManager.getColor("textHighlight"));
		tabbedPane.setBounds((int)(37*x),(int)( 45*y),(int)( 1277*w),(int)( 566*h));
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.decode("#163259"));
		tabbedPane.addTab("<html><H1 color=\"#163259\">Open File</H3></html>",
				null, panel, null);
		panel.setLayout(null);

		JLabel lblNewProject = new JLabel("New Project: ");
		lblNewProject.setBounds((int)(122*x),(int)( 11*y),(int)( 310*w),(int)( 54*h));
		lblNewProject.setForeground(Color.WHITE);
		lblNewProject.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		panel.add(lblNewProject);

		JLabel label = new JLabel("");
		label.setBounds((int)(793*x), (int)(32*y), 0, 0);
		panel.add(label);

		JLabel lblPreviousProject = new JLabel("Previous Project: ");
		lblPreviousProject.setForeground(Color.WHITE);
		lblPreviousProject
				.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		lblPreviousProject.setBounds((int)(771*x),(int)( 11*y),(int) (452*w), (int)(54*h));
		panel.add(lblPreviousProject);

		JLabel label_1 = new JLabel("|");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		label_1.setBounds((int)(581*x),(int)( 32*y),(int) (27*w),(int) (54*h));
		panel.add(label_1);

		JLabel label_2 = new JLabel("|");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		label_2.setBounds((int)(581*x),(int) (135*y), (int)(27*w),(int)( 54*h));
		panel.add(label_2);

		JLabel label_3 = new JLabel("|");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		label_3.setBounds((int)(581*x), (int)(83*y), (int)(27*w), (int)(54*h));
		panel.add(label_3);

		JLabel label_4 = new JLabel("|");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		label_4.setBounds((int)(581*x), (int)(186*y),(int)( 27*w),(int) (54*h));
		panel.add(label_4);

		JLabel label_5 = new JLabel("|");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		label_5.setBounds((int)(581*x), (int)(239*y), (int)(27*w), (int)(54*h));
		panel.add(label_5);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedFile != null) {
					// if a file is selected then do the next thing
					tabbedPane.setEnabledAt(1, true);
					tabbedPane
							.setTitleAt(1,
									"<html><H1 color=\"#163259\">Data Settings</H3></html>");
					tabbedPane.setSelectedIndex(1);
				}
			}
		});
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setBackground(new Color(128, 128, 128));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnSubmit.setBounds((int)(472*x), (int)(330*y),(int)( 276*w), (int)(108*h));
		panel.add(btnSubmit);

		JLabel lblPleaseSelectAn = new JLabel("Please Select an excel file: ");
		lblPleaseSelectAn.setForeground(Color.WHITE);
		lblPleaseSelectAn
				.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblPleaseSelectAn.setBounds((int)(10*x),(int)( 83*y),(int)( 310*w), (int)(54*h));
		panel.add(lblPleaseSelectAn);

		final JLabel lblFileSelected = new JLabel("File selected...");
		lblFileSelected.setHorizontalAlignment(SwingConstants.LEFT);
		lblFileSelected.setForeground(Color.WHITE);
		lblFileSelected.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
		lblFileSelected.setBounds((int)(182*x),(int)( 135*y), (int)(310*w),(int)( 54*h));
		panel.add(lblFileSelected);

		JButton btnChooseFile = new JButton("Choose file");
		btnChooseFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedFile = getFile();
				if (selectedFile != null)
					lblFileSelected.setText(selectedFile.toString());
			}
		});
		btnChooseFile.setBounds((int)(39*x),(int)( 145*y),(int)( 128*w),(int) (44*h));
		panel.add(btnChooseFile);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Some Project that I worked hard on" }));
		comboBox.setBounds((int)(1036*x), (int)(113*y),(int)( 148*w), (int)(36*h));
		panel.add(comboBox);

		JLabel lblPleaseSelectA = new JLabel(
				"Please Select a previous project: ");
		lblPleaseSelectA.setForeground(Color.WHITE);
		lblPleaseSelectA.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblPleaseSelectA.setBounds((int)(618*x),(int)( 99*y),(int)( 408*w),(int)( 54*h));
		panel.add(lblPleaseSelectA);
		tabbedPane.setForegroundAt(0, Color.WHITE);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));

		final JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode("#163259"));
		tabbedPane.addTab(
				"<html><H1 color=\"#EBEBEB\">Data Settings</H3></html>", null,
				panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNorthBound = new JLabel("North Bound");
		lblNorthBound.setBounds((int)(76*x),(int)( 106*y),(int)( 161*w),(int)( 27*h));
		lblNorthBound.setForeground(Color.WHITE);
		lblNorthBound.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		panel_1.add(lblNorthBound);

		JLabel lblSouthBound = new JLabel("South Bound");
		lblSouthBound.setForeground(Color.WHITE);
		lblSouthBound.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblSouthBound.setBounds((int)(299*x),(int)( 106*y), (int)(161*w),(int)( 27*h));
		panel_1.add(lblSouthBound);

		JLabel lblEastBound = new JLabel("East Bound");
		lblEastBound.setForeground(Color.WHITE);
		lblEastBound.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblEastBound.setBounds((int)(542*x),(int)( 106*y),(int)( 161*w), (int)(27*h));
		panel_1.add(lblEastBound);

		JLabel lblWestBound = new JLabel("West Bound");
		lblWestBound.setForeground(Color.WHITE);
		lblWestBound.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblWestBound.setBounds((int)(763*x), (int)(106*y), (int)(161*w), (int)(27*h));
		panel_1.add(lblWestBound);

		JLabel lblTimeInterval = new JLabel("Time Interval: ");
		lblTimeInterval.setForeground(Color.WHITE);
		lblTimeInterval.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblTimeInterval.setBounds((int)(69*x),(int)( 27*y), (int)(181*w), (int)(27*h));
		panel_1.add(lblTimeInterval);

		JLabel lblStartEnd = new JLabel("Start / End day");
		lblStartEnd.setForeground(Color.WHITE);
		lblStartEnd.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
		lblStartEnd.setBounds((int)(388*x), 0, (int)(181*w), (int)(27*h));
		panel_1.add(lblStartEnd);

		JLabel lblStartEnd_1 = new JLabel("Start / End time");
		lblStartEnd_1.setForeground(Color.WHITE);
		lblStartEnd_1.setFont(new Font("Century Schoolbook", Font.BOLD, 18));
		lblStartEnd_1.setBounds((int)(754*x), 0, (int)(181*w), (int)(27*h));
		panel_1.add(lblStartEnd_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds((int)(316*x),(int) (27*y),(int)( 125*w), (int)(33*h));
		panel_1.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds((int)(452*x), (int)(27*y), (int)(125*w), (int)(33*h));
		panel_1.add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds((int)(712*x), (int)(27*y), (int)(125*w), (int)(33*h));
		panel_1.add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds((int)(847*x), (int)(27*y), (int)(125*w),(int)( 33*h));
		panel_1.add(comboBox_4);

		JCheckBox checkBoxNBound = new JCheckBox("");
		checkBoxNBound.setBounds((int)(122*x),(int)( 140*y),(int)( 21*w), (int)(27*h));
		checkBoxNBound.setBackground(Color.decode("#163259"));
		panel_1.add(checkBoxNBound);

		JCheckBox checkBoxSBound = new JCheckBox("");
		checkBoxSBound.setBounds((int)(363*x), (int)(140*y), (int)(21*w),(int)( 27*h));
		checkBoxSBound.setBackground(Color.decode("#163259"));
		panel_1.add(checkBoxSBound);

		JCheckBox checkBoxEBound = new JCheckBox("");
		checkBoxEBound.setBounds((int)(588*x), (int)(140*y), (int)(21*w), (int)(27*h));
		checkBoxEBound.setBackground(Color.decode("#163259"));
		panel_1.add(checkBoxEBound);

		JList listNBound = new JList();
		listNBound.setBounds((int)(69*x), (int)(218*y), (int)(166*w), (int)(128*h));
		panel_1.add(listNBound);

		JComboBox cBoxNBound = new JComboBox();
		cBoxNBound.setBounds((int)(69*x), (int)(174*y), (int)(168*w), (int)(33*h));
		panel_1.add(cBoxNBound);

		JButton btnAddRouteNBound = new JButton("Add Route");
		btnAddRouteNBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddRouteNBound.setBounds((int)(69*x), (int)(357*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnAddRouteNBound);

		JButton btnRemoveRouteNBound = new JButton("Remove Route");
		btnRemoveRouteNBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemoveRouteNBound.setBounds((int)(69*x), (int)(407*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnRemoveRouteNBound);

		JComboBox cBoxSBound = new JComboBox();
		cBoxSBound.setBounds((int)(292*x), (int)(174*y), (int)(168*w), (int)(33*h));
		panel_1.add(cBoxSBound);

		JList listSBound = new JList();
		listSBound.setBounds((int)(292*x), (int)(218*y), (int)(166*w), (int)(128*h));
		panel_1.add(listSBound);

		JButton btnAddRouteSBound = new JButton("Add Route");
		btnAddRouteSBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddRouteSBound.setBounds((int)(292*x), (int)(357*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnAddRouteSBound);

		JButton btnRemoveRouteSBound = new JButton("Remove Route");
		btnRemoveRouteSBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemoveRouteSBound.setBounds((int)(292*x), (int)(407*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnRemoveRouteSBound);

		JComboBox cBoxEBound = new JComboBox();
		cBoxEBound.setBounds((int)(517*x), (int)(174*y), (int)(168*w), (int)(33*h));
		panel_1.add(cBoxEBound);

		JList listEBound = new JList();
		listEBound.setBounds((int)(517*x), (int)(218*y), (int)(166*w), (int)(128*h));
		panel_1.add(listEBound);

		JButton btnAddRouteEBound = new JButton("Add Route");
		btnAddRouteEBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddRouteEBound.setBounds((int)(517*x), (int)(357*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnAddRouteEBound);

		JButton btnRemoveRouteEBound = new JButton("Remove Route");
		btnRemoveRouteEBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemoveRouteEBound.setBounds((int)(517*x), (int)(407*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnRemoveRouteEBound);

		JComboBox cBoxWBound = new JComboBox();
		cBoxWBound.setBounds((int)(756*x), (int)(174*y), (int)(168*w), (int)(33*h));
		panel_1.add(cBoxWBound);

		JList listWBound = new JList();
		listWBound.setBounds((int)(756*x), (int)(218*y), (int)(166*w), (int)(128*h));
		panel_1.add(listWBound);

		JButton btnAddRouteWBound = new JButton("Add Route");
		btnAddRouteWBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddRouteWBound.setBounds((int)(756*x), (int)(357*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnAddRouteWBound);

		JButton btnRemoveRouteWBound = new JButton("Remove Route");
		btnRemoveRouteWBound.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemoveRouteWBound.setBounds((int)(756*x), (int)(407*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnRemoveRouteWBound);

		JCheckBox checkBoxWBound = new JCheckBox("");
		checkBoxWBound.setBounds((int)(814*x), (int)(140*y), (int)(21*w), (int)(27*h));
		checkBoxWBound.setBackground(Color.decode("#163259"));
		panel_1.add(checkBoxWBound);

		JButton btnSubmitDataSettings = new JButton("Submit");
		btnSubmitDataSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(3, true);
				tabbedPane.setTitleAt(2,
						"<html><H1 color=\"#163259\">Graphs</H3></html>");
				tabbedPane.setTitleAt(3,
						"<html><H1 color=\"#163259\">Export</H3></html>");
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnSubmitDataSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmitDataSettings.setBounds((int)(509*x), (int)(457*y), (int)(249*w), (int)(35*h));
		panel_1.add(btnSubmitDataSettings);

		JLabel lblEvents = new JLabel("Events");
		lblEvents.setForeground(Color.WHITE);
		lblEvents.setFont(new Font("Century Schoolbook", Font.BOLD, 22));
		lblEvents.setBounds((int)(1062*x), (int)(106*y), (int)(161*w), (int)(27*h));
		panel_1.add(lblEvents);

		JList list_4 = new JList();
		list_4.setBounds((int)(1062*x), (int)(218*y), (int)(166*w), (int)(128*h));
		panel_1.add(list_4);

		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditEvent.setBounds((int)(1062*x), (int)(357*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnEditEvent);

		JButton btnRemoveEvent = new JButton("Remove Event");
		btnRemoveEvent.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemoveEvent.setBounds((int)(1062*x), (int)(407*y), (int)(168*w), (int)(39*h));
		panel_1.add(btnRemoveEvent);

		Button button_8 = new Button("New Event");
		button_8.setFont(new Font("Dialog", Font.BOLD, 14));
		button_8.setBounds((int)(1062*x), (int)(174*y), (int)(168*w), (int)(33*h));
		panel_1.add(button_8);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setForegroundAt(1, new Color(30, 144, 255));

		final JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.decode("#163259"));
		tabbedPane.addTab("<html><H1 color=\"#EBEBEB\">Graphs</H3></html>",
				null, panel_2, null);
		try {
			panel_2.setLayout(null);
			Graphs graphs = new Graphs("Graphs");
			graphs.setBounds((int)(10*x), (int)(11*y), (int)(1252*w), (int)(449*h));
			panel_2.add(graphs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel_2.setSize(700, 500);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setForegroundAt(2, new Color(30, 144, 255));

		final JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.decode("#163259"));
		tabbedPane.addTab("<html><H1 color=\"#EBEBEB\">Export</H3></html>",
				null, panel_3, null);
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setForegroundAt(3, new Color(30, 144, 255));

		JLabel lblDynamicStat = new JLabel("Dynamic Stat");
		lblDynamicStat.setForeground(new Color(255, 255, 255));
		lblDynamicStat.setFont(new Font("Century Schoolbook", Font.BOLD, 44));
		lblDynamicStat.setBounds((int)(560*x), 0, (int)(371*w), (int)(54*h));
		contentPane.add(lblDynamicStat);
	}

	public File getFile() {
		// filter was taken from
		// http://stackoverflow.com/questions/18571203/how-to-browse-the-files-only-with-extensions-xls-using-jfilechooser-in-swing
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"XLSX files", "xlsx");
		fileChooser.setFileFilter(filter);
		// fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showOpenDialog(this);
		// if user clicked Cancel button on dialog, return
		File fileName = fileChooser.getSelectedFile();// get file
		return fileName;
	}// end getFile
}
