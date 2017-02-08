import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Lab5 {
    static final long NUMBER_TO_COUNT = (long)10e17;

    public static void main(String[] args) {
    	final int[] numberOfThreads = {1, 2, 4, 8, 16};
    	
    	System.out.println("Program started!");
    	
    	for (int i = 0; i < numberOfThreads.length; i++) {
    		Long beginT = System.nanoTime();
            ForkJoinPool fjp = new ForkJoinPool();
            Stream task = new Stream(NUMBER_TO_COUNT, 1, (long)Math.sqrt(NUMBER_TO_COUNT), numberOfThreads[i]);
            fjp.invoke(task);
            Long endT = System.nanoTime();
            Stream.FACTORS = new ArrayList<>();
            Long timebetweenStartEnd = endT - beginT;
            System.out.printf("%d thread(s): %.2fs\n", numberOfThreads[i], timebetweenStartEnd / 1E9);
    	}
    
    	Stream.showFactors();	
    }
}

class Stream extends RecursiveAction {
    final int CPUCOUNT = Runtime.getRuntime().availableProcessors();
    static List<Long> FACTORS = new ArrayList<>();
    final int taskSplit;
    long forSplit;
    long start;
    long end;


    Stream(long taskSize, long startPoint, long endPoint, int taskSplit) {
        forSplit = taskSize;
        start = startPoint;
        end = endPoint;
        this.taskSplit = taskSplit;
    }

    private void go(long numberForCalc, long start, long end) {
        for (long i = start; i < end; i++) {
            if (numberForCalc % i == 0) {
                FACTORS.add(i);
                FACTORS.add(numberForCalc / i);
            }
        }
        if (numberForCalc % end == 0) FACTORS.add(end);
    }

    protected void compute() {
        if (CPUCOUNT == 1 || end - start + 1 <= (Math.sqrt(forSplit) / taskSplit)) {
            go(forSplit, start, end);
        } else {
            long middle = (start + end - 1) / 2;
            invokeAll(new Stream(forSplit, start, middle, taskSplit),
                    new Stream(forSplit, middle + 1, end, taskSplit));
        }
    }
    
    public static void showFactors() {
        Collections.sort(FACTORS);
        for (Long i : FACTORS) {
            System.out.println(i);
        }
    }
}