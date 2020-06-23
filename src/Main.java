import java.util.Random;

public class Main {
    private static double testQueue(Queue<Integer>q, int opCount){
        long startTime =System.nanoTime();
        Random random = new Random();
        for (int i = 0; i<opCount; i++ )
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i=0; i<opCount; i++)
            q.dequeue();
        long endTime =System.nanoTime();
        return (endTime - startTime) /1000000000.0;
    }
    public static void main(String[] args) {
	// write your code here
        LoopQueue<Integer> aq = new LoopQueue<>();
        LinkedListQueue<Integer> bq = new LinkedListQueue<>();
        BiLinkedListQueue<Integer> cq = new BiLinkedListQueue<>();
        int opCount = 10000000;

        double time = testQueue(aq,opCount);
        System.out.println("LoopQue time:" +time +"s");

        time = testQueue(bq,opCount);
        System.out.println("LinkedListQue time:" +time +"s");

        time = testQueue(cq,opCount);
        System.out.println("BiLinkedListQue time:" +time +"s");


    }
}
