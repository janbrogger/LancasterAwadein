import java.awt.*;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.BufferStrategy;

public class LRG extends Canvas implements KeyListener{

    LRG (){
	setSize(800,800);
	}	
   
   public SimpleScreenManager screen;
   int i = 1;	
   int j =1;
   int x =0;
   int y = 0;
   int angle = 0;
   double [] [] redpoints = new double [9][3];
   double [] [] greenpoints = new double [9][3];
   double [] [] numeric = new double [9][3];
   int counts = 0;
   int linehsize = 4;
   int linevsize = 30;
   int fudgeh = linehsize/2;
   int fudgev = linevsize/2;
   BufferedImage redline = RedLine.createRedLine();
   BufferedImage greenline = GreenLine.createGreenLine();
   int a = 0;
   int b =0;
   int c =0;
   int d =0;
   Graphics2D big;
   boolean firsttime = true;

   BufferedImage bf;
	
   public void keyPressed(KeyEvent e) {

 	if (counts < 8) {
	           switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN: y += 1; break;
		case KeyEvent.VK_UP: y -= 1; break;
		case KeyEvent.VK_LEFT: x -= 1; break;
		case KeyEvent.VK_RIGHT: x += 1; break;
		case KeyEvent.VK_X: angle += 1; break;
		case KeyEvent.VK_Z: angle -= 1; break;
		case KeyEvent.VK_ENTER: {
			if ((counts==2)||(counts ==5)) {
				i=1;
				j++;
				fudgeh = linehsize/2;
			}	 
			else{
				i++;
				fudgeh+=linehsize/2;
			}
			counts ++;
			break;
		}
		
	}  

	repaint();
	} 
	
	else {
	
	        screen = new SimpleScreenManager();
    
    	        screen.restoreScreen();

	       for (int r = 0; r <9; r++) {
		System.out.println (greenpoints[r][0]);

		}
		
	       DrawChart.drawFrame (greenpoints);
	       DrawNumeric.drawFrame (numeric);
	       try {
	       UpdateDatabase3.addVisitData ("2011-111", "12/12/2011", greenpoints);
	       }catch (Exception ex)	{
			System.out.println ("Output Failure");
			}
	}
	}
   public void keyReleased(KeyEvent e) {
	
	}

   public void keyTyped(KeyEvent e) {

	}


   public double [][] getGreenPoints () {

	double [] [] resultedGreenPoints = new double [9][3];
	resultedGreenPoints = greenpoints;
	return resultedGreenPoints;
  }

   public void writefile ()  {
	

	try {
		java.io.File file = new java.io.File("image/Hess.doc");
		java.io.PrintWriter output = new java.io.PrintWriter (file);
		for (int i = 0; i<9; i++) {
			output.println(greenpoints[i][0] + "  " + greenpoints [i][1] + "  " + greenpoints [i][2]);
		}
		output.close();	
	}
	catch (IOException e){
			System.out.println ("File already exists");
			System.exit (0);
	
	}

	finally {
		
		System.exit(0);
	}
}  


public BufferedImage createBackBuffer (Rectangle z) {

	BufferedImage bufferedImage = new BufferedImage(z.width, z.height, BufferedImage.TYPE_INT_RGB);

	// Create a graphics contents on the buffered image
	    Graphics2D g2d = bufferedImage.createGraphics();

	// Draw graphics

	int hpart = z.width/12;
	int vpart = z.height/12;
	g2d.setColor(Color.BLACK);
	g2d.fillRect(0,0,z.width,z.height);

	// Draw points
	g2d.setColor(Color.BLACK);
	for (int k =0 ; k < 11; k++) {
		for (int  l=0; l <11; l++) {
			g2d.fillRect(hpart+k*hpart, vpart+l*vpart, 2, 2);
	     }
	}

	// Draw red line
	AffineTransform atr = new AffineTransform();
	a = (z.width/4) *i ;
	b = (z.height/4) * j ;
	atr.translate(a-fudgeh,b- fudgev);
	g2d.setTransform(atr); 
	g2d.drawImage(redline, 0,0, null);

	// Draw green line
	
	AffineTransform at = new AffineTransform();
	c = a+35+x;
	d = b+10+y;
	at.translate(c,d);
	at.rotate(Math.toRadians(angle));
	g2d.setTransform(at); 
	g2d.drawImage(greenline, 0,0, null);
	greenpoints [counts][0]= (double)c/(double)(z.width);
	greenpoints [counts][1] = (double)d/(double)(z.height);
	greenpoints [counts][2] = angle;
	numeric [counts][0]= c-a;
	numeric [counts][1] = d-b+15;
	numeric [counts][2] = angle;
    	// Graphics context no longer needed so dispose it
	    g2d.dispose();

	return bufferedImage;
}

public void paint (Graphics g) {
	update(g);
}
public void update(Graphics g) {
        	Graphics2D g2 = (Graphics2D)g;

                Rectangle z = getBounds();
	bf = createBackBuffer (z);
	g2.drawImage(bf,0,0,null);	
	}
}



