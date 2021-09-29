package fox;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

public class VideoMonitor {
	private static GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice device = environment.getDefaultScreenDevice();
	private static GraphicsConfiguration gconf = device.getDefaultConfiguration();

	public VideoMonitor() {
		environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
		gconf = device.getDefaultConfiguration();
	}
	
	public static DisplayMode getDisplayMode() {
		return device.getDisplayMode();
	}
	
	public static void setDisplayMode(DisplayMode mode) {
		device.setDisplayMode(mode);
	}
	
	public static void switchFullscreen(Window win) {
		device.setFullScreenWindow(win);
	}
	
	public static void getRefreshRate(Window win) {
		device.getDisplayMode().getRefreshRate();
	}

	public static GraphicsEnvironment getGraphicsEnvironment() {return environment;}

	public static GraphicsDevice getGraphicsDevice() {return device;}

	public static GraphicsConfiguration getGraphicsConfiguration() {return gconf;}	
}