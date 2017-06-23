import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ExamServerMain {
ServerSocket ss;
    Socket s;
    int ClientNo=2;
    void MySer() throws IOException
    {
        int count=0;
        boolean finish=false;
        GUI g=new GUI();
        //Streams strm=new Streams();
        BufferedReader rin[]=new BufferedReader[10];
    PrintWriter pw[]=new PrintWriter[10];
        ExamQuestion eq=new ExamQuestion();
        int flag[]=new int[10];
        for(int i=0;i<10;i++)
            flag[i]=0;
        ss=new ServerSocket(2244);
        ExamServerThread sm[]=new ExamServerThread[10];
       for(int j=0;j<ClientNo;j++){
          
        s=ss.accept();
      
         rin[count]=new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw[count]=new PrintWriter(s.getOutputStream());
        
        sm[j]=new ExamServerThread(s,g,rin,pw,eq,flag);
        sm[j].setName(Integer.toString(count));
        sm[j].start();
         
        count++;
        }
       System.out.println("done");
       while(true){
          finish=true;
       for(int p=0;p<ClientNo;p++)
       {
           if(sm[p].isAlive())
               finish=false;
          
       }
       if(finish)
           break;
       }
       for(int i=0;i<10;i++)
       {
           for(int k=0;k<ClientNo;k++){
           pw[k].println("QUESTION "+i+" -->");
           pw[k].println("OPTION A --\t"+eq.answers[i][0]);
           pw[k].println("OPTION B --\t"+eq.answers[i][1]);
           pw[k].println("OPTION C --\t"+eq.answers[i][2]);
           pw[k].println("OPTION D--\t"+eq.answers[i][3]);
           pw[k].flush();
           }
       }
    }
    public static void main(String args[])throws IOException
    {
       ExamServerMain ex=new ExamServerMain();
       ex.MySer();
    }
}
  

