package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int N = 1000;
        while(N <= 128000){
            timeGetLastWithN(N,Ns,times,opCounts);
            N *= 2;
        }
        printTimingTable(Ns,times,opCounts);
    }
    public static void timeGetLastWithN(int N,AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts){
        Ns.addLast(N);
        int opCount = 0;
        SLList<Integer> list = new SLList<>();
        for(int i = 0; i < N; i ++) list.addLast(i);
        int M = 10000;
        Stopwatch sw = new Stopwatch();
        for(int i = 0; i < M; i ++) list.addLast(i);
        double timeInSeconds = sw.elapsedTime();
        times.addLast(timeInSeconds);
        opCounts.addLast(M);
    }

}
