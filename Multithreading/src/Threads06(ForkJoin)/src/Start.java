import java.util.concurrent.ForkJoinPool;

public class Start {
    public static void main(String[] args) {

     final int TASKSIZE= 4000;
        Long beginT = System.nanoTime();
        
        ForkJoinPool fjp = new ForkJoinPool();
        Stream task = new Stream(TASKSIZE, 0, TASKSIZE-1);
        fjp.invoke(task);
        
        Long endT = System.nanoTime();
        Long timebetweenStartEnd = endT - beginT;
        System.out.println("=====time========\n" +timebetweenStartEnd/1E9);

    }
}