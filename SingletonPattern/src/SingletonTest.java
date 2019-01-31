//	This uses Singleton Design Pattern.

public class SingletonTest {
	
	public static class ceo{
		
		private static ceo kofi = new ceo();
		
		private ceo() {}
		
		public static ceo getInstance(){
		      return kofi;
		   }
		
		public void ceoName(){
		      System.out.println("My name is : " + "JustNET");
		   }
		
		
	}
	
	public static void main(String[] args) {
		
		ceo object = ceo.getInstance();

	      //Display the message in CEOName method.
	      object.ceoName();
	   }
	

}
