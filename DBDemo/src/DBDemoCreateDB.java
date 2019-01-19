import java.sql.*;

//This creates a new DB and Two tables.

public class DBDemoCreateDB {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.DRIVER";  
	   static String DB_URL = "jdbc:mysql://localhost/";
	   

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	
	public static void main(String[] args) {
		
			   Connection conn = null;
			  
			   Statement stmt = null;
			   Statement stmt_2 = null;
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      
			      
			    //STEP 4: Create the DB query.
			      System.out.println("\nCreating database...");
			      stmt = conn.createStatement();
			      
			      
			      String sqlCreateDB = "CREATE DATABASE STUDENTS_DB";
			      stmt.executeUpdate(sqlCreateDB);
			      System.out.println("\nDatabase created successfully...");
			      
			      DB_URL = "jdbc:mysql://localhost/STUDENTS_DB";
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      
			    //STEP 5: Create the StudentsBasicInfo table
			      System.out.println("\nCreating StudentsBasicInfo table in the created Database");
			      stmt = conn.createStatement();
			      
			      String sqlCreateTblstd = "CREATE TABLE StudentBasicInfo " +
			                   "(Stdid INTEGER not NULL, " +
			                   " Surname VARCHAR(30), " + 
			                   " Firstname VARCHAR(30), " + 
			                   " PhoneNumber VARCHAR(11), " + 
			                   " PRIMARY KEY ( Stdid ))"; 

			      stmt.executeUpdate(sqlCreateTblstd);
			      
			      
			   //STEP 5: Create the Department table
			      System.out.println("\nCreating Department table in the created Database");
			      stmt_2 = conn.createStatement();
			      
			      String sqlCreateTbldpt = "CREATE TABLE Department" +
			                   "(Deptid INTEGER not NULL, " +
			                   " DeptName VARCHAR(50), " + 
			                   " DeptHead VARCHAR(30), " + 
			                   " PRIMARY KEY ( Deptid ))"; 

			      stmt_2.executeUpdate(sqlCreateTbldpt);
			    
			  
			   }
			   
			   catch(SQLException se){
				      //Handle errors for JDBC
				      se.printStackTrace();
				   }catch(Exception e){
				      //This will take care of possible errors for Class.forName
				      e.printStackTrace();
				   }finally{
				      //To close resources
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
				   }//Try ends. 
			   
				   System.out.println("\nDatabase and Tables created successfully. Xie xie.");

	}

}
