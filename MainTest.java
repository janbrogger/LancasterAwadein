import java.awt.*;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class MainTest extends JFrame{

    double [] [] greendata = new double [9][3];
	 	
     public MainTest (){
	      LRG canvas = new LRG();
	      add(canvas);
	      addKeyListener(canvas);
	}	


    public double [][] getGreenPoints (LRG canvas) {

	double [] [] resultedGreenPoints = new double [9][3];
	resultedGreenPoints = canvas.getGreenPoints();
	return resultedGreenPoints;
  }

  
}

