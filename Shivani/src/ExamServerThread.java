import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ExamServerThread extends Thread {
     GUI g;
    //Streams strm;
    
     BufferedReader rin[];
     PrintWriter pw[];
    ExamQuestion eq;
    int flag[];
    char ans[];
    
    Socket s;
    ExamServerThread(Socket s,GUI g,BufferedReader rin[],PrintWriter pw[],ExamQuestion eq,int flag[])
    {
        this.s=s;
        this.g=g;
        this.rin=rin;
        this.pw=pw;
        this.eq=eq;
        ans=new char[10];
        this.flag=flag;

    }
      public void run()
    {
        
        int cno=Integer.parseInt(this.getName());
       
        try{
        
       
         g.tbox.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e)
           {
               g.send.doClick();
           }
        });
        
        g.send.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
 
            String s=g.tbox.getText();
            if(s.equals(""))
                return;
            g.tbox.setText("");
             g.text.setText(g.text.getText()+"\nME :"+s);
        
            try{
                for(int i=0;i<10;i++){
             pw[i].println("Server:"+s);
           pw[i].flush();   
                }
            }catch(Exception ex){}
        }
        });
       
        String cname=rin[cno].readLine();
         g.text.setText(g.text.getText()+"\nClient "+cname+" connected");
       
         for(int i=0;i<10;i++){
        pw[cno].println(eq.question[i][0]);
       pw[cno].println(eq.question[i][1]);
        pw[cno].println(eq.question[i][2]);
        pw[cno].println(eq.question[i][3]);
        pw[cno].println(eq.question[i][4]);
        pw[cno].flush();

        String instr=rin[cno].readLine();
        if(!instr.equals(""))
        {
           synchronized (this) {
            g.text.setText(g.text.getText()+"\n"+instr);
          }
           char ans=instr.charAt(0);
           int ind=ans%65; 
           eq.answers[i][ind]=eq.answers[i][ind]+"\t"+cname;
           eq.answers[i][ind+4]=Integer.toString(Integer.parseInt(eq.answers[i][ind+4])+1);
            this.sleep(200);
        }
       pw[cno].println("answer saved... Next Question \n The answers given by the clients are:-\n");
        
         }
         synchronized(this)
         {
             flag[cno]=1;
         }
         //this.sleep(999999999);
       // s.close();
       pw[cno].println("\n\nThank You . Please Wait for others to finish");
       this.stop();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
