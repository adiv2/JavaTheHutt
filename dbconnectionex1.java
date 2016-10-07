import java.sql.*; 
/*
 * dbconnectionex1.java
 * 
 * Copyright 2016 Aditya Gholba <aditya@aditya-ubuntu>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 	
 */ 
public class dbconnectionex1
{  
	public static void main(String args[])
	{  
    try
    {  
    Class.forName("com.mysql.jdbc.Driver");  
    //DriverManager.registerDriver(new com.mysql.jdbc.Driver()); not working 
      
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost/test","aditya","aditya123");  
     
      
    Statement stmt=con.createStatement();  
      
    ResultSet rs = stmt.executeQuery("select * from student");  
     while(rs.next())
     {
		 String prn = rs.getString("prn");
		 System.out.println(prn);
	 }
    con.close();  
      
    }
    catch(Exception e){ System.out.println(e);}  
      
    }  
} 
