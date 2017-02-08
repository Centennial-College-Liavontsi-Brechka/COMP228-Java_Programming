import javax.swing.*;

public class DieGame {
    private final String SHORT_INTRO = "Now you will roll die up to 3 times.\n" +
            "\tIf your number comes up, you are the WINNER!!!\n" +
            "\tBut if you don`t see you number within 3 rolls, computer will defeat you!\n\n" +
            "When you are ready, press OK button to roll dies!\n\n" +
            "GOOD LUCK!";
    private final String ROLL_TEXT = "\nThe result of current roll is: ";
    private final String WINNER_TEXT = "\n\nCongratulations!\n\n" +
            "You win a battle!\n\n" +
            "But not the whole war!\n\n" +
            "Keep playing to prove your superiority!!!";
    private final String LOOSER_TEXT = "\n\nYou loose!\n\n" +
            "But, don`t give up!\n" +
            "It`s just a battle!\n" +
            "Keep playing and you will have a chane to defeat the computer!";
    private int playerWinCount;
    private int computerWinCount;

    private int getPlayerNumber() throws NumberFormatException
    {
        int userNumber = Integer.parseInt(
                JOptionPane.showInputDialog(null,
                        "Welcome, my dear friend!\n\n" +
                                "It is a great Die Battle!\n\n" +
                                "Try to win computer by guessing the sum of two dies!\n" +
                                "Please, enter the number from 2 to 12 to play this funny game!",
                        "Let`s play the Die Battle!",
                        JOptionPane.INFORMATION_MESSAGE)
        );
        while (userNumber < 2 || userNumber > 12) {
            userNumber = Integer.parseInt(
                    JOptionPane.showInputDialog(null,
                            "You`ve entered the wrong number!\n\n" +
                                    "Please, enter the number from 2 to 12\n" +
                                    "to be able to play this game!\n" +
                                    "Or press CANCEL, if you don`t want to play.",
                            "Die Battle",
                            JOptionPane.INFORMATION_MESSAGE)
            );
        }

        JOptionPane.showMessageDialog(null,
                SHORT_INTRO,
                "Introduction",
                JOptionPane.INFORMATION_MESSAGE);

        return userNumber;
    }

    private void rollDiesThreeTimes(int playerNumber)
    {
        for (int i = 0; i < 3; i++) {
            int rollResult = (new Die().getCurrentValue()) + (new Die().getCurrentValue());
            String nextMessage = ROLL_TEXT + rollResult;

            if (rollResult == playerNumber)
            {
                playerWinCount += 1;
                nextMessage += WINNER_TEXT;
                JOptionPane.showMessageDialog(null,
                        nextMessage,
                        "You win!",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            } else if(i + 1 != 3)
            {
                nextMessage += "\nKeep going!";
                JOptionPane.showMessageDialog(null,
                        nextMessage,
                        "Roll result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else
            {
                computerWinCount += 1;
                nextMessage += LOOSER_TEXT;
                JOptionPane.showMessageDialog(null,
                        nextMessage,
                        "Roll result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showCurrentScore()
    {
        JOptionPane.showMessageDialog(null,
                String.format(
                        "Current score is:\n" +
                                "\t\t\t\t(Computer) %d : %d (Player)",
                        computerWinCount, playerWinCount
                ),
                "Game Score",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void playGame()
    {
        int playerNumber = 0;

        try {
            playerNumber = getPlayerNumber();
        } catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,
                    "Goodbye!",
                    "Die Battle",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        rollDiesThreeTimes(playerNumber);
        showCurrentScore();

        int contintue = JOptionPane.showConfirmDialog(null,
                "Do you want play more?",
                "Die Battle",
                JOptionPane.YES_NO_OPTION);

        if (contintue == JOptionPane.YES_OPTION)
        {
            playGame();
        } else
        {
            JOptionPane.showMessageDialog(null,
                    "Goodbye!",
                    "Die Battle",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

}
