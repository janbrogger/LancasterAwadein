import java.sql.*;
import javax.swing.JOptionPane;
 import java.util.Vector;

public class UpdateDatabase3 {

   public static boolean searching(String x)
       throws SQLException, ClassNotFoundException {

     boolean alreadypresent = false;
     // Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement
    PreparedStatement qstmt = null;
    
    String querystring = "select PID from TRIAL where PID = ?";
    qstmt = connection.prepareStatement(querystring);
 	
     // Execute a statement
 		
	    qstmt.setString(1,x);
	    ResultSet resultSet = qstmt.executeQuery();
	    int rowCount = 0;  
	    while ( resultSet.next() )  
	{  
	    // Process the row.  
	    rowCount++;  
	}  
   

	if (rowCount > 0) {
	
	alreadypresent = true;
	}	
     
   // Close the connection
     connection.close();
   // return the result
	return alreadypresent;
   }

public static String [] searchoutcome(String x)
       throws SQLException, ClassNotFoundException {

     String [] outcome= new String [2];

     // Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement

     PreparedStatement rstmt = null;
    
    String outcomestring = "select FirstName, LastName from TRIAL where PID = ?";
    rstmt = connection.prepareStatement(outcomestring);

    
 	
     // Execute a statement
 		
 		
	rstmt.setString(1,x);
	ResultSet rss = rstmt.executeQuery();
    	while (rss.next()) {
	    outcome[0] = rss.getString(1);
	    outcome[1] = rss.getString(2);
	    
		}    

	
     
   // Close the connection
     connection.close();

   // return the result
	return outcome;

   }


   public static void updating(String x, String y, String z)
       throws SQLException, ClassNotFoundException {

     // Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement
    PreparedStatement stmt = null;
    
    String insertion = "insert into " + "TRIAL " +
             "values(?, ?, ?)";
    stmt = connection.prepareStatement(insertion);
 	
     // Execute a statement
 		
      stmt.setString(1,x);
      stmt.setString(2,y);
      stmt.setString(3,z);

       stmt.executeUpdate();
     
   // Close the connection
     connection.close();
   }

public static int searchvisits(String x)
       throws SQLException, ClassNotFoundException {

     boolean present = false;
     // Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement
    PreparedStatement qst = null;
    
    String querys = "select PID from VISIT where PID = ?";
    qst = connection.prepareStatement(querys);
 	
     // Execute a statement
 		
	    qst.setString(1,x);
	    ResultSet results = qst.executeQuery();
	    int rows = 0;  
	    while ( results.next() )  
	{  
	    // Process the row.  
	    rows++;  
	}  
   

	if (rows > 0) {
	
	present = true;
	}	
     
   // Close the connection
     connection.close();
   // return the result
	return rows;
   }

public static Vector searchingvisits(String x)
       throws SQLException, ClassNotFoundException {

     Vector<String> data = new Vector<String>();

     // Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement
    PreparedStatement vst = null;
    
    String queryv = "select FORMAT (Dov,'DD-MM-YYYY') from VISIT where PID = ? ";
    vst = connection.prepareStatement(queryv);
 	
     // Execute a statement
 		
	    vst.setString(1,x);
	    ResultSet resultv = vst.executeQuery(); 
	    while ( resultv.next() )  {  
	    // Process the row.  
	    	data.addElement(resultv.getString(1));  
		}  
   

   // Close the connection
     connection.close();
   // return the result
	return data;
   }

public static void addVisitData (String ID, String Date, double [] [] greenpoints) 
       throws SQLException, ClassNotFoundException {

// Load the JDBC driver
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     System.out.println("Driver loaded");

     // Establish a connection
     Connection connection = DriverManager.getConnection
       ("jdbc:odbc:HessScreen"," "," ");
     System.out.println("Database connected");

     // Create a statement
    PreparedStatement visitinsert = null;
    
    String visitdata = "insert into " + "VISITS " +
             "values(?, ?, ?)";;
    visitinsert = connection.prepareStatement(visitdata);
 	
     // Execute a statement
 		
      visitinsert.setString(1, "ahmed");
      visitinsert.setString(2, "ahmed");
      visitinsert.setString(3, "ahmed");

      visitinsert.executeUpdate();
     System.out.println ("number5");
   // Close the connection
     connection.close();
   }

 }

