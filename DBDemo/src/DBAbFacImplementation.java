
public class DBAbFacImplementation {
	
	/*
	 * To implement Abstract factory in DB issues as assignment, consider the following;
	 * Main issue is that you have a DbTable in which two tables exist stds and Dept. */
	
	 public interface DBtable {
	    void saveData();
	    void loadData();
	} 
	
	 public class students implements DBtable {
		    @Override
		    public void saveData() {
		        System.out.println("Data being saved inside students");
		    }
		    public void loadData() {
		        System.out.println("Data being loaded from students.");
		    }
		}
	 
	 public class department implements DBtable {
		    @Override
		    public void saveData() {
		        System.out.println("Data being saved inside department");
		    }
		    public void loadData() {
		        System.out.println("Data being loaded from department.");
		    }
		}
	 
	 public abstract class AbstractFactory {
		   abstract DBtable getDBtable(String dbtbl);
		   
		}
	 
	 public class DBFactory extends AbstractFactory {
			
		   @Override
		   public DBtable getDBtable(String tblType){
		   
		      if(tblType == null){
		         return null;
		      }		
		      if(tblType.contains("students"))
		      	{
		          return new students();
		          
		      	}
		      else if(tblType.contains("department"))
		      	{
		          return new department();
		      	}
	 
		  return null;
		   }
	 
	 }
	 
	 public static class FactoryProducer {
		 
		   public static AbstractFactory getFactoryType(String option){
		   
		      if(option.contains("students")){
		         return new DBFactory();
		         
		      }else if(option.contains("department")){
		         return new DBFactory();
		      }
		      
		      return null;
		   }
		}
	 
	 
	 
	
	 
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

	}

}
