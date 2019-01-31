import java.sql.*;

//This Saves data into "STUDENTS_DB".
public class DBDemo {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.DRIVER";  
	   static final String DB_URL = "jdbc:mysql://localhost/STUDENTS_DB";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	
	public static void main(String[] args) throws SQLException {
		
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","root");
			Statement stmt = null;
			
		   try{
		      //STEP 2: JDBC driver registration
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open connection
		      System.out.println("Now connecting to DB");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connection successful.");
		      
		      //STEP 4: Query execution
		      System.out.println("Inserting records into the table. Please wait...");
		      stmt = conn.createStatement();
		      
		      
		      // Data to be saved into the Database ----- Dynamic.
		      
		       
		      String sql = "INSERT INTO StudentBasicInfo " +
		                   "VALUES (1, 'JJ', 'JUSTICE', '112012')";
		      stmt.executeUpdate(sql);
		      sql = "INSERT INTO StudentBasicInfo " +
		                   "VALUES(2, 'KJ', 'ALEX', '45215121')";
		      stmt.executeUpdate(sql);
		      System.out.println("Records inserted into the table StudentBasicInfo."); 
		      
		     
		      
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Congratulations");
		

	}

}
