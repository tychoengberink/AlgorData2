package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 50, 40, 20, 30, 32, 91, 90};

        DEPQ depq = new DEPQ();
        depq.heap = arr;

        depq.build(depq.heap);
        depq.printHeap(depq.heap);

        depq.insert(depq.heap, 3);
        depq.insert(depq.heap, 91);
        depq.insert(depq.heap, 60);
        depq.insert(depq.heap, 23);
        depq.insert(depq.heap, 66);
        depq.insert(depq.heap, 12);
        depq.insert(depq.heap, 77);

        System.out.println("REMOVNG MIN " + depq.removeSmallest(depq.heap));
        depq.printHeap(depq.heap);
        System.out.println("REMOVNG MAX " + depq.removeMaximum(depq.heap));
        depq.printHeap(depq.heap);
    }
}

