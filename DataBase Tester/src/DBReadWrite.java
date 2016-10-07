import java.sql.*;
import java.util.Scanner;

public class DBReadWrite
{
    public void insert() throws ClassNotFoundException , SQLException
    {
        Scanner sc=new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");

        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/DBMS_1_SIULibrary","root","aditya123");
        /*System.out.println("Enter number of rows");
        int num=sc.nextInt();

        String sqlQ = "";
        System.out.println(sqlQ);
        PreparedStatement ps = con.prepareStatement(sqlQ);
        for(int i=0;i<num;i++)
        {
            System.out.println("Enter details");
            int i1=sc.nextInt();
            //String f2=sc.next();
            int i2 = sc.nextInt();
            int i3 = sc.nextInt();
            int i4 = sc.nextInt();
            //String f3=sc.next();
            sqlQ= "insert into purchase (LID,SlID,PID,BID) values ("+i1+","+i2+","+i3+","+i4+")";
            System.out.println(sqlQ);
            ps.executeUpdate(sqlQ);

        }*/
        Statement stmt=con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from books");
        while(rs.next())
        {
            String bName = rs.getString("BName");
            System.out.println(bName);
        }

        con.close();
    }

    public static void main(String[] args)
    {
        DBReadWrite dbobj = new DBReadWrite();
        try{dbobj.insert();}
        catch(Exception e){e.printStackTrace();}
    }

}
