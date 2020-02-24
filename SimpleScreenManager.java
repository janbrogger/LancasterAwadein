import java.awt.*;
import javax.swing.JFrame;
import java.awt.image.BufferStrategy;

/**
    The SimpleScreenManager class manages initializing and
    displaying full screen graphics modes.
*/
public class SimpleScreenManager {

    private GraphicsDevice device;

    /**
        Creates a new SimpleScreenManager object.
    */
    public SimpleScreenManager() {
        GraphicsEnvironment environment =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = environment.getDefaultScreenDevice();
    }


    /**
        Enters full screen mode and changes the display mode.
    */

    public void setFullScreen(DisplayMode displayMode,
        JFrame window)
    {
        window.setUndecorated(true);
        window.setResizable(false);

        device.setFullScreenWindow(window);
        if (displayMode != null &&
            device.isDisplayChangeSupported())
        {
            try {
                device.setDisplayMode(displayMode);
            }
            catch (IllegalArgumentException ex) {
                // ignore - illegal mode for this device
            }
         window.createBufferStrategy(2);  	
        }
    }


    /**
        Returns the window currently used in full screen mode.
    */


   public Graphics2D getGraphics() {
        Window window = device.getFullScreenWindow();
        if (window != null) {
            BufferStrategy strategy = window.getBufferStrategy();
            return (Graphics2D)strategy.getDrawGraphics();
        }
        else {
            return null;
        }
    }

    public void update() {
        Window window = device.getFullScreenWindow();
        if (window != null) {
            BufferStrategy strategy = window.getBufferStrategy();
            if (!strategy.contentsLost()) {
                strategy.show();
            }
        }
        // Sync the display on some systems.
        // (on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();
    }

    public Window getFullScreenWindow() {
        return device.getFullScreenWindow();
    }


    /**
        Restores the screen's display mode.
    */
    public void restoreScreen() {
        Window window = device.getFullScreenWindow();
        if (window != null) {
            window.dispose();
        }
        device.setFullScreenWindow(null);
    }


}

