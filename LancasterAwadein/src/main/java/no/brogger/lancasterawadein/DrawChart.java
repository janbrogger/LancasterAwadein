package no.brogger.lancasterawadein;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JFrame;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;

public class DrawChart extends JFrame {

double [] [] datapoints = new double [9][3];

public DrawChart(double [] [] datapoints) {
       this.datapoints = datapoints;	
      add(new charts(datapoints));
    }

public static void drawFrame (double [] [] datalines) {

		DrawChart frame = new DrawChart(datalines);
		frame.setTitle ("Lancaster Red-Green Test");
		frame.setSize(300,300);
		frame.setLocation(600,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
}	
}

class charts extends JPanel  {

public int [][] redlines = new int [9] [3];
public double [][] greenlines = new double [9][3];
Rectangle rec1;
Rectangle rec2;

public charts(double [] [] greenlines) {

this.greenlines=greenlines;

}

protected void paintComponent(Graphics g) {

       Graphics2D g2 = (Graphics2D)g;
      
	Rectangle z = getBounds();
	int hpart = z.width/12;
	int vpart = z.height/12;
	int  marks = z.height/30;
	int i = 0;	
	int j =1;
                int x =0;
                int y = 0;
	int linehsize = 4;
	int linevsize = 30;
	int fudgeh = linehsize/2;
	int fudgev = linevsize/2;

	// Draw points
	g2.setColor(Color.BLACK);
	for (int k =0 ; k < 11; k++) {
		for (int l =0; l <11; l++) {
			g2.fillRect(hpart+k*hpart, vpart+l*vpart, 2, 2);
	     }
	}
	
	// Draw Strings
	g2.setColor(Color.BLACK);
	g2.drawString ("LSR", 20, 65);
	g2.drawString ("LLR", 20, 135);	
	g2.drawString ("LIR", 20, 200);
	g2.drawString ("LIO", 250, 65);
	g2.drawString ("LMR", 250, 135);	
	g2.drawString ("LLR", 250, 200);


	for (int counts = 0; counts <9; counts ++) {
		if ((counts==3)|| (counts ==6))  {
				i=1;
				j++;
				fudgeh = linehsize/2;
		
				}				 
		else if (counts ==0){
				i++;
		


			}
		else{
				i++;
				fudgeh+= (linehsize/2);
		
			}

		// Draw red line
		AffineTransform atr = new AffineTransform();
		int a = (z.width/4) *i ;
		int b = (z.height/4)*j ;

		atr.translate(a-fudgeh,b- fudgev);
		g2.setTransform(atr); 
		g2.setColor(Color.RED);
		rec1 = new Rectangle();
		rec1.setRect(0,0,linehsize,linevsize);
		g2.fill(rec1);

	
		// Draw green line
		AffineTransform at = new AffineTransform();

		int c = (int) (greenlines[counts][0]*z.width);
		int d = (int) (greenlines[counts][1]*z.height);
		at.translate(c,d);
		at.rotate(Math.toRadians(greenlines[counts][2]));
		g2.setTransform(at); 
		g2.setColor(Color.GREEN);
		rec2 = new Rectangle();
		rec2.setRect(0,0,linehsize,linevsize);
		g2.fill(rec2);

		}
	
}

}


