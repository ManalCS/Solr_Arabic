// this code has been written by Manal Alnefaie 2018
// it will convert accdb files to txt files for each table


import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
 
public class accdbTOtxt
{
    public static void main(String[] args)
    {
   
    	try
        {
        	
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://D://shamela db Access file 2013//1.accdb");//db connection 
            Statement s = conn.createStatement();

            String selTable = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'PUBLIC'";//to get tables name
            s.execute(selTable);
            ResultSet rs = s.getResultSet();
          
    		
            ArrayList<String> rsB = new ArrayList<String>();//books tables 
            while((rs!=null) && (rs.next()))
            {
            	if(rs.getString(3).startsWith("B"))
                {rsB.add(rs.getString(3));}
              
                
            }
            
           
            for(int i=0;i<rsB.size();i++)
            {
            FileWriter fw = new FileWriter("path"+rsB.get(i)+".txt");// new file for the book
            String selTable1 = "SELECT nass FROM "+rsB.get(i);
            
            s.execute(selTable1);

            ResultSet rs2 = s.getResultSet();
          
         
           
          
            while(rs2.next())
            {
            	
            	String str=(rs2.getString(1));
            	
            			fw.append(str+" ");
            	
            	
                
               }   
            fw.flush();
            fw.close();
          
            }
    	
       
            // close and cleanup
            s.close();
            conn.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    	
    	

    }
     
}