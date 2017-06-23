import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class GUI {
    JPanel pan;
    JTextArea text;
    JTextField tbox;
    JButton send;
    JFrame jf;
    JScrollPane jsp;
  GUI()
    {
        jf=new JFrame();
        jf.setTitle("SERVER");
        pan=new JPanel();
        text=new JTextArea();
        tbox=new JTextField();
        send=new JButton("send");
        jf.setSize(515,530);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pan.setLayout(null);
       // pan.setSize(490,490);
        jf.add(pan);
       
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        
        //pan.add(text);
        jsp=new JScrollPane(text); 
        jsp.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        jsp.setBounds(0,0,500,400);
        pan.add(jsp);
        tbox.setBounds(0,400,350,100);
        pan.add(tbox);
        send.setBounds(350,400,150,100);
        pan.add(send);
       // text.setEditable(false);
       
    }
  public static void main(String st[])
  {
      GUI g=new GUI();
  }
}
