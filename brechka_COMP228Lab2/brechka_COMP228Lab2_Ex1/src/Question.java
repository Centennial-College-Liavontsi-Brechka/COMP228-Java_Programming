public class Question {
    private String questionText;
    private String[] questionOptions;
    private int correctAnswerIndex;

    public String getQuestionText() {
        return questionText;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String[] getQuestionOptions() {
        return questionOptions;
    }

    public Question(String questionText, int correctAnswerIndex, String... questionOptions)
    {
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.questionOptions = new String[questionOptions.length];
        for (int i = 0; i < questionOptions.length; i++)
        {
            this.questionOptions[i] = questionOptions[i];
        }
    }
}
