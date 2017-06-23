
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ExamClientThread extends JFrame{
   JPanel pan;
    JTextArea text;
    JTextField tbox;
    JButton send;
    JScrollPane jsp;
    
    BufferedReader rin;
    PrintWriter pw;
    
    String name;
    static int count=0;
    Socket s;
    ExamClientThread(String name) throws IOException
    {
        this.name=name;
         
        this.setTitle("Client-"+name);
        pan=new JPanel();
        text=new JTextArea();
        tbox=new JTextField();
        send=new JButton("send");
        
        this.setSize(515,530);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pan.setLayout(null);
       // pan.setSize(490,490);
        this.add(pan);
        text.setEnabled(false);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        jsp=new JScrollPane(text); 
        jsp.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        jsp.setBounds(0,0,500,400);
        pan.add(jsp);
        tbox.setBounds(0,400,350,100);
        pan.add(tbox);
         tbox.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
           {
               send.doClick();
           }
        });
        send.setBounds(350,400,150,100);
        pan.add(send);
        send.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            String s=tbox.getText();
            if(s.equals(""))
                return;
            tbox.setText("");
            text.setText(text.getText()+"\nME :"+s);
            if(s.equals(name))
                pw.println(name);
            else{
            pw.println(s.toUpperCase());
            }
            pw.flush();
            //send.removeActionListener(this);
        }
        });
        s=new Socket("127.0.0.1",2244);
        text.setText("Conection Established");
        rin=new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw=new PrintWriter(s.getOutputStream());
        tbox.setText(name);
        send.doClick();
       while(true){ 
           String instr=rin.readLine();
       if(instr.equals("quit"))
           break;
       if(!instr.equals(""))
        {
            text.setText(text.getText()+"\n"+instr);
        }}
       s.close();
    }
 
    public static void main(String args[])throws IOException
    {
        System.out.println("Enter your name (without spaces)");
        String s=new Scanner(System.in).next();
        ExamClientThread m=new ExamClientThread(s);
    }  
}
