package no.brogger.lancasterawadein;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.Color;

public class RedLine extends JPanel {		
		
		public static BufferedImage createRedLine () {
			int linehsize = 4;
			int linevsize = 30; 
			BufferedImage bufferedImage = new BufferedImage(linehsize, linevsize, BufferedImage.TYPE_INT_RGB);

			// Create a graphics contents on the buffered image
			    Graphics2D g2d = bufferedImage.createGraphics();

			// Draw graphics
			Color myred = new Color (140,0,0);
			g2d.setColor(myred);
			Rectangle rec1 = new Rectangle();
			rec1.setRect(0,0,linehsize,linevsize);
			g2d.fill(rec1);
		    	// Graphics context no longer needed so dispose it
			    g2d.dispose();

			return bufferedImage;
		}

		public void paint(Graphics g) {
			super.paintComponent(g);
			g.drawImage(createRedLine(), 0,0, null);
    	}
	}