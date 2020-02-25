package no.brogger.lancasterawadein;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Pretest {
	
	
	public static void main (String [] args) {
	
		PretestForm frame = PretestForm.getForm();
		frame.setTitle ("Lancaster Red-Green Test");
		frame.setSize(500,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
	
	}