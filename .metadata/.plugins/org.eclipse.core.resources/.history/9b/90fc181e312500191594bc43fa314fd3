
public class University {

	public interface School {

		void ComputerScience();
		
	}

	public class Jiangsu implements School {

		public void ComputerScience() {
			System.out.println("Computer Science is available here.");
		}

	}

	public class SWUST implements School {

		public void ComputerScience() {
			System.out.println("Computer Science and Control Systems available here.");
		}

	}

	public abstract class CompSciDecor implements School {

		protected School CompSciDecor;

		public CompSciDecor(School CompSciDecor) {
			this.CompSciDecor = CompSciDecor;
		}

		public void ComputerScience() {
			CompSciDecor.ComputerScience();

		}

	}

	public class infoEngineering extends CompSciDecor {

		public infoEngineering(School CompSciDecor) {
			super(CompSciDecor);
		}

		public void ComputerScience() {
			CompSciDecor.ComputerScience();
			newDept(CompSciDecor);
		}

		private void newDept(School CompSciDecor) {
			System.out.println("Information Engineering is now added.");
		}
	}

	public static void main(String[] args) {
		University univ = new University();
		School js = univ.new Jiangsu();

		School anotherDeptJ = univ.new infoEngineering(univ.new Jiangsu());

		School anotherDeptS = univ.new infoEngineering(univ.new SWUST());

		System.out.println("A School with only one Course.");
		js.ComputerScience();

		System.out.println("\n Another course on the way.");
		anotherDeptJ.ComputerScience();

		System.out.println("\n SWUST now offering extra package");
		anotherDeptS.ComputerScience();

	}

}
