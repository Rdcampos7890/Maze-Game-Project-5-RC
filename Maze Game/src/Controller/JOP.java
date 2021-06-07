package Controller;

import javax.swing.JOptionPane;

/*
 * JOP class, uses JOption to communicate with the user and take input.
 */
public class JOP {

	/*
	 * Prints the parameter as a JOption message.
	 */
	public static void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	/*
	 * Return the input of the user into a JOption input.
	 */
	public static String in(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
}
