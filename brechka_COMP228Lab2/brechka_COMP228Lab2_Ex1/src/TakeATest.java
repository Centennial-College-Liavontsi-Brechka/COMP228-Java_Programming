
public class TakeATest {
	
	public static void main(String[] args)
    {
        Question question1 = new Question("What does the zeroth element of the string array passed to the standard main method contain?",
                3,
                "The name of the class.",
                "The string \"java\".",
                "The number of arguments.",
                "The first argument of the argument list, if present.");
        Question question2 = new Question("What is the correct parameter specification for the standard main method?",
                2,
                "void",
                "Strings[ ] args",
                "String args[ ]",
                "String args");
        Question question3 = new Question("Identify the valid code fragments when occurring by themselves with in a method.",
                3,
                "long y = 123_456_L;",
                "long z = _123_456L;",
                "float f1 = 123_.345_667F;",
                "float f2 = 123_345_667F;");
        Question question4 = new Question("Which of the following is illegal ?",
                0,
                "byte b = 320;",
                "char c = 320;",
                "float f = 320;",
                "double d = 320;");
        Question question5 = new Question("Which of the following are valid classes?",
                0,
                "public class ImaginaryNumber extends Number {\n" +
                        "//implementation for abstract methods of the base class\n" +
                        "}",
                "public class ThreeWayBoolean extends Boolean {\n" +
                        "//implementation for abstract methods of the base class\n" +
                        "}",
                "public class NewSystem extends System {\n" +
                        "//implementation for abstract methods of the base class\n" +
                        "}",
                "public class ReverseString extends String {\n" +
                        "//implementation for abstract methods of the base class\n" +
                        "}"
                );

        QuestionBank bank1 = new QuestionBank(question1, question2, question3, question4, question5);

        Test test1 = new Test(bank1);
        test1.takeATest();
	}
	
}
