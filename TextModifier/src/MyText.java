//	This uses Decorator Design pattern.

import java.util.Scanner;

public class MyText {

	static String enteredText;
	static String username;

	static void userText() {
		System.out.println("Ni hao...Please enter any text.");
		Scanner in = new Scanner(System.in);
		String userText = in.next();
		enteredText = userText;

	}

	static class decor {

		static class displayUserInput {

			static void displayText() {
				System.out.println("The text you entered was " + enteredText + ".");
			}
		}

		static class lengthOfText extends displayUserInput {
			static void textCount() {
				int lengthCount = enteredText.length();
				System.out.println("Number of characters in the entered text is : " + lengthCount);
			}

		}

		static class yourNameAndText extends displayUserInput {

			static void nameText() {
				System.out.println("What is your name? Please, enter it.");
				Scanner nw = new Scanner(System.in);
				username = nw.next();
				System.out.println("Hi  " + username + ",  the text you entered was  " + enteredText);
				nw.close();
			}

		}

		// Change request for hashing function on input text.
		static class securedText extends displayUserInput {

			static void hashText() {

				int h = enteredText.hashCode();
				System.out.println("The encrypted text of " + enteredText + " is " + h);
			}

		}

		// Change request for Character count
		static class charText extends displayUserInput {

			static void charCount() {

				int counter = 0;

				char[] cText = enteredText.toCharArray();

				for (int i = 0; i < cText.length; i++) {
					for (int j = 0; j < cText.length; j++) {
						if (cText[i] == cText[j]) {
							counter++;
						}
					}

					System.out.printf("Character count of " + "%s : ", cText[i]);

					System.out.printf("%d\n", counter);

					counter = 0;
				}
			}
		}
	}

	public static void main(String[] args) {

		userText();

		MyText.decor.lengthOfText.textCount();

		MyText.decor.yourNameAndText.nameText();

		MyText.decor.securedText.hashText();

		MyText.decor.charText.charCount();
	}

}
