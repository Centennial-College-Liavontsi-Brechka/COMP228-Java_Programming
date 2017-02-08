import java.util.Random;

public class Die {
    private int currentValue;

    public int getCurrentValue() {
        return currentValue;
    }

    public Die()
    {
        this.currentValue = (new Random()).nextInt(6) + 1;
    }

    public static void main(String[] args)
    {
        (new DieGame()).playGame();
    }
}
