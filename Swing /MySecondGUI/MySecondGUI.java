import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI
{
	public void guifunc()
	{
		//Frame 
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.red);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		frame.getContentPane().add(BorderLayout.WEST,panel1);
		//
		
	}
}
public class MySecondGUI
{
	public static void main(String []args)
	{
		GUI swing1 = new GUI();
		swing1.guifunc();
	}
}
	
