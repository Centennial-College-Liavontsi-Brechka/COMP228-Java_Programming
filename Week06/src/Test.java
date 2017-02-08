import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {

	public static void main(String[] args) {
		Dog fido = new Dog();
		ArrayList <String> breedList= new ArrayList<String>();
		breedList.add("Collie");
		breedList.add("Poodle");
		breedList.add("Chihuahua");
		
		Mix mutt = new Mix(7, 7, breedList);
		Mix mutt2 = new Mix(6, 6, new ArrayList<String>
				(Collections.unmodifiableList
						(Arrays.asList("Shih Tzu", "Bulldog", "Husky"))));
		
		PureBreed pure = new PureBreed(2, 2, "German Shepherd");

		//print function takes "Dogs" (or Objects)
		print(fido); //printing a "Dog" here
		print(mutt); //printing a "Mix" here
		print(mutt2); //printing a "Mix" here
		print(pure); //printing a "PureBreed" here

	}

	public static void print(Dog d)
	{ //type: Dog, Object, etc.
		System.out.println(d.toString());
	}
}
