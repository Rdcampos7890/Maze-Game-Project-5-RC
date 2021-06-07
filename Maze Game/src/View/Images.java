package View;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * Image class, responsible for including images on the pop-ups messages for the user.
 */
public class Images {
	
	/*
	 * Displays an image on the JOption pop-up. It also takes care of resizing and finding the image.
	 */
	public void  display(String y, String x, String z) {
		URL resource = getClass().getResource(y);
		ImageIcon icon = new ImageIcon(resource);
		Image imageSize = icon.getImage();
		
		Image modified = imageSize.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(modified);
		
		JOptionPane.showMessageDialog(null, x, z, JOptionPane.INFORMATION_MESSAGE, icon);
	}	
}
	
