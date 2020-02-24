import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.table.*;
import java.io.*;


public class PretestForm extends JFrame {
	

	public JTextField FirstName, LastName, Date, Diagnosis, IDFill;
	public JLabel IDlabel, FirstNameLabel, LastNameLabel, DateLabel, DiagnosisLabel, DOBLabel;
	public JFormattedTextField DOB;
	public JButton Register, Cancel, Test; 
	public VisitTable MyTable;		
	public SimpleScreenManager screen;
		
	public PretestForm () {

		JLabel IDLabel = new JLabel("ID");
		JLabel FirstNameLabel = new JLabel("First Name");
		JLabel LastNameLabel = new JLabel("Last Name");
		JLabel DateLabel = new JLabel("Date");
		JLabel DiagnosisLabel = new JLabel("Diagnosis");
		JLabel DOBLabel = new JLabel("Date of Birth");

		JButton Register = new JButton("Register");
		JButton Cancel = new JButton ("Cancel");
		JButton Test = new JButton ("Test");		
		
		final VisitTable MyTable = new VisitTable();

		final JTextField IDFill = new JTextField(12);
		IDFill.setColumns(12);	
			
		final JTextField FirstName = new JTextField(12);
		final JTextField LastName = new JTextField(15);
		FirstName.setEditable(false);
		LastName.setEditable(false);
		JTextField Date = new JTextField(15);
		JTextField Diagnosis = new JTextField(15);
		JFormattedTextField DOB = new JFormattedTextField (new SimpleDateFormat("dd/MM/yyyy"));
		DOB.setColumns(12);

		SpringLayout springLayout = new SpringLayout();
 		JPanel PatientInfo = new JPanel(springLayout);

 
		PatientInfo.add(IDLabel);
		PatientInfo.add(IDFill);
		FirstName.requestFocusInWindow();
		Spring spring = Spring.constant(10);
  
      		springLayout.putConstraint(SpringLayout.NORTH, IDLabel, spring, SpringLayout.NORTH, PatientInfo);
        		springLayout.putConstraint(SpringLayout.WEST, IDLabel, spring, SpringLayout.WEST, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, IDFill, spring, SpringLayout.EAST, IDLabel);
       		springLayout.putConstraint(SpringLayout.NORTH, IDFill, 10, SpringLayout.NORTH, PatientInfo);

 
		PatientInfo.add(FirstNameLabel);
		PatientInfo.add(FirstName);

 
		PatientInfo.add(LastNameLabel);
		PatientInfo.add(LastName);

		PatientInfo.add(Register);
		PatientInfo.add(Cancel);
		PatientInfo.add(Test);

		PatientInfo.add(MyTable);

       		springLayout.putConstraint(SpringLayout.NORTH, FirstNameLabel, 50, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, FirstNameLabel, 10, SpringLayout.WEST, PatientInfo);
       		springLayout.putConstraint(SpringLayout.NORTH, FirstName, 50, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, FirstName, 10, SpringLayout.EAST, FirstNameLabel);

		springLayout.putConstraint(SpringLayout.NORTH, LastNameLabel, 50, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, LastNameLabel, 30, SpringLayout.EAST, FirstName);
       		springLayout.putConstraint(SpringLayout.NORTH, LastName, 50, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, LastName, 5, SpringLayout.EAST, LastNameLabel);

       		springLayout.putConstraint(SpringLayout.NORTH, DOBLabel, 100, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, DOBLabel, 10, SpringLayout.WEST, PatientInfo);
       		springLayout.putConstraint(SpringLayout.NORTH, DOB, 100, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, DOB, 5, SpringLayout.EAST, DOBLabel);

       		springLayout.putConstraint(SpringLayout.NORTH, DiagnosisLabel, 150, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, DiagnosisLabel, 10, SpringLayout.WEST, PatientInfo);
       		springLayout.putConstraint(SpringLayout.NORTH, Diagnosis, 150, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, Diagnosis, 20, SpringLayout.EAST, DiagnosisLabel);

       		springLayout.putConstraint(SpringLayout.NORTH, Register, 200, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, Register, 60, SpringLayout.WEST, PatientInfo);
       		springLayout.putConstraint(SpringLayout.NORTH, Cancel, 200, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, Cancel, 60, SpringLayout.EAST, Register);
       		springLayout.putConstraint(SpringLayout.NORTH, Test, 200, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, Test, 60, SpringLayout.EAST, Cancel);

       		springLayout.putConstraint(SpringLayout.NORTH, MyTable, 250, SpringLayout.NORTH, PatientInfo);
       		springLayout.putConstraint(SpringLayout.WEST, MyTable, 5, SpringLayout.WEST, PatientInfo);


		this.add(PatientInfo);



		IDFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {	
				String IDtexts = IDFill.getText();
				String [] outcomes = new String [2];
				Vector<String> dates = new Vector<String>();
				Vector<String> values = new Vector<String>();
				int i =1;
				if (UpdateDatabase.searching (IDtexts) == true) {
					outcomes = UpdateDatabase.searchoutcome(IDtexts);
					FirstName.setText(outcomes[0]);
					LastName.setText(outcomes[1]);
					int numrows = UpdateDatabase.searchvisits (IDtexts);
					if (numrows > 0) {
						for (int j = 0; j <numrows; j++) {
	 						System.out.println("4");
							dates = UpdateDatabase.searchingvisits(IDtexts);
							values.addElement(Integer.toString(i));
							values.addElement(dates.get(i-1));
							i++;
							MyTable.tableModel.addRow(new Object[] {values.get(0), values.get(1)});
							values.clear();			
						}					
					}
				}			
			}
			catch (Exception exp)	{
				System.out.println ("Failure");
			}

		}
		});		

		Test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			performTest ("ahmed", "2001");
				}
		});		


		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String IDtext = IDFill.getText();
				String FirstNametext = FirstName.getText();
				String LastNametext = LastName.getText();
				if (UpdateDatabase.searching (IDtext) == true) {
					JOptionPane.showMessageDialog(null, "This number is already registered");
				}
				else {
				UpdateDatabase.updating(IDtext, FirstNametext, LastNametext);
				JOptionPane.showMessageDialog(null, "Ok, Registered");
				IDFill.setText("2011");
				FirstName.setText("");
				LastName.setText("");
				IDFill.requestFocusInWindow();
				IDFill.setCaretPosition(5);
				}		
			}
			catch (Exception ex)	{
			System.out.println ("Failure");
			}

		}
		});
		

		}

	public static PretestForm getForm(){
		PretestForm trialframe = new PretestForm();
		return trialframe;
	}				

	public void performTest(String ID, String date) {
        
       	        DisplayMode displayMode;
	
	        displayMode = new DisplayMode(800, 600, 16,
                        DisplayMode.REFRESH_RATE_UNKNOWN);
	
	        MainTest test = new MainTest();

	        screen = new SimpleScreenManager();
    
    	        screen.setFullScreen(displayMode, test);

	        System.out.println("ahmed");
	
	      }

}

