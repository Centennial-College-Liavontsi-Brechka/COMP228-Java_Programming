import java.util.ArrayList;

public class QuestionBank {
    private ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public QuestionBank() {}

    public QuestionBank(Question... questionsToAdd)
    {
        for (Question question : questionsToAdd)
        {
            questions.add(question);
        }
    }

    public void addQuestionToBank(Question questionToAdd)
    {
        questions.add(questionToAdd);
    }

    public void printQuestionsInBank()
    {
        System.out.println("\n---------------------------\n" +
                "Questions in this bank:");
        for (int i = 0; i < this.questions.size(); i++)
        {
            System.out.printf("\tQuestion #%d: %s\n", i + 1, this.questions.get(i).getQuestionText());
        }
        System.out.println("---------------------------");
    }
}
