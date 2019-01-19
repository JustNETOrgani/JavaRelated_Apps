import java.util.Scanner;

public class studentRecords {

	// student records of made up of only First name, Surname and Entry date into
	// the College.

	private static String firstname;
	private static String surname;
	private static String entryDate;

	public studentRecords(String firstname, String surname, String entryDate) {
		studentRecords.firstname = firstname;
		studentRecords.surname = surname;
		studentRecords.entryDate = entryDate;
	}

	// Change request to add Year of completion.

	public class yearComplete extends studentRecords {
		private final studentRecords stdRecords;
		private String yearCompleted;

		public yearComplete(final studentRecords stdRecords, String yearCompleted)

		{
			super(firstname, surname, entryDate);
			this.stdRecords = stdRecords;
			this.yearCompleted = yearCompleted;

		}

		public void availableStudentRecords() {
			stdRecords.availableStudentRecords();
			System.out.println("Year Completed " + yearCompleted);

		}

	}

	// Year of completion as new request ends.

	public void availableStudentRecords() {
		System.out.println("The student has records as below:");

		System.out.println("Surname is " + surname + ", Firstname is " + firstname + ", Entry Date is " + entryDate);

	}

	static void inputs() {
		System.out.println("Pleae enter the Surname of the student");
		Scanner in = new Scanner(System.in);
		String sname = in.next();
		surname = sname;

		System.out.println("Pleae enter the First name of the student");
		Scanner fn = new Scanner(System.in);
		String fname = fn.next();
		firstname = fname;

		System.out.println("Pleae enter the Entry Date of the student");
		Scanner enDt = new Scanner(System.in);
		String enDate = enDt.next();
		entryDate = enDate;

		// Close Scanner inputs
		in.close();
		fn.close();
		enDt.close();
	}

	public static void main(String[] args) {

		studentRecords.inputs();

		// studentRecords student_smallDetails = new studentRecords("JUSTICE", "ODOOOM",
		// "01/01/2018");
		studentRecords student_smallDetails = new studentRecords(firstname, surname, entryDate);

		student_smallDetails.availableStudentRecords();

		studentRecords stdrec = new studentRecords(firstname, surname, entryDate);

		studentRecords allDetails = stdrec.new yearComplete(stdrec, "01/01/2020");

		allDetails.availableStudentRecords();

	}

}
