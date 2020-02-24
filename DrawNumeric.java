import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;

public class DrawNumeric extends JFrame {

double [] [] datapoints = new double [9][3];

public DrawNumeric(double [] [] datapoints) {
       this.datapoints = datapoints;	
      add(new numericChart(datapoints));
    }

public static void drawFrame (double [] [] datalines) {

		DrawNumeric frame = new DrawNumeric(datalines);
		frame.setTitle ("Lancaster Red-Green Test");
		frame.setSize(300,300);
		frame.setLocation(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
}	
}

class numericChart extends JPanel  {

public double [][] greenlines = new double [9][3];
String string1, string2, string3, string4, string5, string6, string7, string8, string9;

public numericChart (double [] [] greenlines) {

this.greenlines=greenlines;

}

protected void paintComponent(Graphics g) {

       Graphics2D g2 = (Graphics2D)g;
      
	Rectangle z = getBounds();
	int hpart = z.width/3;
	int vpart = z.height/3;
	

	// Draw Grid
	g2.setColor(Color.BLACK);
	g2.drawLine (hpart,0, hpart, z.height);
	g2.drawLine (hpart*2, 0, hpart*2, z.height);
	g2.drawLine (0, vpart, z.width, vpart);
	g2.drawLine (0, vpart *2, z.width, vpart*2);	

	// Draw horizontal numbers
	for (int i = 0; i <9; i++) {
		if (greenlines[i][0] >0) {
			string1 = "ET " + (int) (greenlines[i][0]/5);
			}
		else if (greenlines [i][0] <0 ){
			string1 = "XT " + (int) (greenlines[i][0]/5);
			}
		else {
			string1 = "No Horizontal";
			}
		if (i<3) {	
		g2.drawString (string1,hpart * i + 15,25);
		}

		else if (i <6) {
		g2.drawString (string1, hpart * (i-3) +15,vpart +25);
		}

		else {
		g2.drawString (string1, hpart * (i-6) +15,vpart *2 +25);
		}
	}


	// Draw vertical Numebrs
	for (int i = 0; i <9; i++) {
		if (greenlines[i][1] >0) {
			string2 = "RHT " + (int) (greenlines[i][1]);
			}
		else if (greenlines [i][1] <0 ){
			string2 = "LHT " + (int) (greenlines[i][1]);
			}
		else {
			string2 = "No vertical";
			}
		if (i<3) {	
		g2.drawString (string2,hpart * i + 15,45);
		}

		else if (i <6) {
		g2.drawString (string2, hpart * (i-3) +15,vpart +45);
		}

		else {
		g2.drawString (string2, hpart * (i-6) +15,vpart *2 +45);
		}
	}

	// Draw torsion
	for (int i = 0; i <9; i++) {
		if (greenlines[i][2] >0) {
			string3 = "Extorsion " + (int) (greenlines[i][2]);
			}
		else if (greenlines [i][2] <0 ){
			string3 = "Intorsion " + (int) (greenlines[i][2]);
			}
		else {
			string3 = "No Torsion";
			}
		if (i<3) {	
		g2.drawString (string3,hpart * i + 15,65);
		}

		else if (i <6) {
		g2.drawString (string3, hpart * (i-3) +15,vpart +65);
		}

		else {
		g2.drawString (string3, hpart * (i-6) +15,vpart *2 +65);
		}
	}


}

}


