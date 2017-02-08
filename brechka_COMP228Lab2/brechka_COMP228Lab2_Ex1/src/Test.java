import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Test {
    private final String[] CONGRATULATORY_MESSAGES = {"Well done!",
            "You answered correctly!",
            "Awesome! Your answer is correct!",
            "Keep up the good work!"};
    private final String[] WRONG_ANSWER_MESSAGES = {"No. Please try again",
            "Wrong. Try once more",
            "Don't give up!",
            "No. Keep trying."};
    private QuestionBank testQuestionBank;
    private int numberOfCorrectAnswers;
    private int numberOfWrongAnswers;
    private int numberOfUnansweredQuestions;

    public Test(QuestionBank questionBankToTest)
    {
        this.testQuestionBank = questionBankToTest;
    }

    public void takeATest()
    {
        for (int i = 0; i < testQuestionBank.getQuestions().size(); i++)
        {
            simulateQuestion(testQuestionBank.getQuestions().get(i), i + 1);
        }
        showTestResult();
    }

    private void simulateQuestion(Question questionToSimulate, int questionNumber)
    {
        final JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(questionToSimulate.getQuestionOptions().length + 1, 1));

        final JLabel questionText = new JLabel(questionToSimulate.getQuestionText());
        questionPanel.add(questionText);

        final JRadioButton[] answerOptions = new JRadioButton[questionToSimulate.getQuestionOptions().length];
        final ButtonGroup answers = new ButtonGroup();

        //creating radio buttons with available options
        for (int i = 0; i < questionToSimulate.getQuestionOptions().length; i++)
        {
            answerOptions[i] = new JRadioButton(questionToSimulate.getQuestionOptions()[i]);
        }

        //grouping the options
        for (int i = 0; i < answerOptions.length; i++)
        {
            answers.add(answerOptions[i]);
        }

        //adding buttons to the panel
        for (JRadioButton button : answerOptions)
        {
            questionPanel.add(button);
        }

        //show a question and ask for answer
        int chosenOptionWithAnswer = JOptionPane.showConfirmDialog(null,
                questionPanel,
                String.format("Question #%d", questionNumber),
                JOptionPane.OK_CANCEL_OPTION);

        //checking the answer correctness
        if (chosenOptionWithAnswer == JOptionPane.OK_OPTION)
        {
            int selectedAnswerIndex = 0;
            for (int i = 0; i < answerOptions.length; i++)
            {
                if (answerOptions[i].isSelected())
                {
                    selectedAnswerIndex = i;
                    break;
                }
            }
            if (selectedAnswerIndex == questionToSimulate.getCorrectAnswerIndex())
            {
                numberOfCorrectAnswers += 1;
                simulateAnswer(true);
            } else
            {
                numberOfWrongAnswers += 1;
                simulateAnswer(false);
            }
        } else
        {
            numberOfUnansweredQuestions += 1;
            numberOfWrongAnswers += 1;
        }
    }

    private void simulateAnswer(boolean isCorrect)
    {
        Random answerGenerator = new Random();
        String answer;
        String title;

        if (isCorrect)
        {
            title = "Correct answer!";
            answer = CONGRATULATORY_MESSAGES[answerGenerator.nextInt(4)];
        } else
        {
            title = "Wrong answer!";
            answer = WRONG_ANSWER_MESSAGES[answerGenerator.nextInt(4)];
        }

        JOptionPane.showMessageDialog(null, answer, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showTestResult()
    {
        JOptionPane.showMessageDialog(null, String.format("Number of correct answers: %d\n" +
                        "Number of wrong answers: %d\n" +
                        "Number of unanswered questions: %d\n" +
                        "\nTotal score: %.2f%%",
                        numberOfCorrectAnswers,
                        numberOfWrongAnswers,
                        numberOfUnansweredQuestions,
                        ((float)numberOfCorrectAnswers / numberOfWrongAnswers) * 100),
                "Test Results",
                JOptionPane.INFORMATION_MESSAGE);
    }

}