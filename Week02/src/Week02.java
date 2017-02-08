import java.util.Scanner;

public class Week02 {

	public static void main(String[] args) {
		String str1 = "string 1";
		String str2 = "string 2";
		System.out.print("Hello Welt!\n" + str1 + "\n" + str2);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter your text: ");
		System.out.println(scanner.nextLine());
		System.out.println("\nEnter your number: ");
		System.out.println(scanner.nextDouble());
	}

}
