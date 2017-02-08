import java.util.concurrent.RecursiveAction;

public class Stream extends RecursiveAction {

    final int CPUCOUNT = Runtime.getRuntime().availableProcessors();
    final int MINTASKSIZE= 200;
    int start;
    int end;
    int forSplit;

    Stream(int taskSize, int startPoint, int endPoint) {
        forSplit = taskSize;
        start = startPoint;
        end = endPoint;
    }
    
    protected void compute() {
        if (CPUCOUNT == 1 || end - start + 1 <= MINTASKSIZE) {
            System.out.println("Run already! "+":" + (end - start + 1) + ":" + start +":" + end);
            for(int i = start; i <= end; i++) {
                new Calc().go(i);
            }
        } else {
            int middle = (start + end)/ 2;
            invokeAll(new Stream(forSplit, start, middle),
                    new Stream(forSplit, middle+1, end));
            //System.out.println("InvokeAll"+":" + (ForSplit) + ":" + middle+":" + end);
            
        }
    }
}