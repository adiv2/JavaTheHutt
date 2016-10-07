import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics.*;
class MyDrawPanel extends JPanel
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		int red= (int) (Math.random()*255);
		int blue= (int) (Math.random()*255);
		int green= (int) (Math.random()*255);
		Color startcol = new Color(red,blue,green);
		green= (int) (Math.random()*255);
		red= (int) (Math.random()*255);
		blue= (int) (Math.random()*255);
		Color endcol = new Color(red,blue,green);
		GradientPaint grad = new GradientPaint(70,70,startcol,350,350,endcol);
		g2d.setPaint(grad);
		g2d.fillOval(70,70,300,300);
	}
}
public class MyFirstGUI implements ActionListener
{
	JFrame frame = new JFrame();
	public void framefunc()
	{
		JButton button = new JButton("Click here");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.SOUTH,button);
		button.addActionListener(this);
		
		MyDrawPanel drawboard = new MyDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER,drawboard);
		frame.setVisible(true);
		frame.setSize(1280,720);
	}
	public void actionPerformed(ActionEvent event)
	{
		frame.repaint();
	}
	public static void main(String []a)
	{
		MyFirstGUI swing1 = new MyFirstGUI();
		swing1.framefunc();
	}
}
