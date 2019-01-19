import java.sql.*;

//This Retrieves data from "STUDENTS_DB".

public class DBDemo_Load {

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
	      System.out.println("Now connecting to DB. Please wait...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("\nConnection successful.");
	      
	      //STEP 4: Query execution section.
	      System.out.println("\nAttempting Retrieval of records in STUDENTS_DB");
	      stmt = conn.createStatement();
	      
	      
	      // Data to be retrieved from the Database ----- Not static. Can change. 
	       
	       String sql = "SELECT Stdid, Surname, Firstname, PhoneNumber FROM StudentBasicInfo ";
	      
	      ResultSet dataset = stmt.executeQuery(sql);
	      //STEP 5: Extract data from result set query. 
	      while(dataset.next())
	      	{
	         //Retrieve by column name as created in the Database. 
	         
	    	 String ID = dataset.getString("Stdid");
	    	 String Surname = dataset.getString("Surname");
	    	 String Firstname = dataset.getString("Firstname");
	         String PhoneNumber = dataset.getString("PhoneNumber");
	         

	         //Display values retrieved. 
	         System.out.print("\nStudent ID: " + ID);
	         System.out.print("\nSurname: " + Surname);
	         System.out.print(", First name: " + Firstname);
	         System.out.print(", Phone Number: " + PhoneNumber);
	         
	         System.out.print("\n");
	         
	      }
	      dataset.close();
	      

			 }catch(SQLException se){
		      //Handle errors for JDBC driver.
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //This will be used to close resources.
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//Finally ends. 
		   }//end try
	   
	   		System.out.println("Data retrieval complete. Xie xie.");
	}

}
