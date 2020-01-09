package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        int[] arr = {16,11,10,31,46,51,31,21,13,71,41,8};//works
//        int[] arr = {5, 1, 2, 50, 40, 20, 30, 32};//works
        int[] arr = {5, 1, 2, 50, 40, 20, 30, 32, 91, 90};//works

//        int[] arr = {5, 1, 2, 10, 20, 34, 3, 8, 6}; //works
        DEPQ depq = new DEPQ();
        System.out.println("-------------------");
        depq.heap = arr;

        int[] builded = depq.build(depq.heap);
        depq.printHeap(depq.heap);

//
//        depq.insert(depq.heap, 3);
//        depq.insert(depq.heap, 91);
        depq.insert(depq.heap, 60);
        depq.insert(depq.heap, 23);
        depq.insert(depq.heap, 66);
        depq.insert(depq.heap, 12);
//        depq.insert(depq.heap, 77);

        depq.printHeap(depq.heap);

        System.out.println("REMOVNG MIN " + depq.removeSmallest(depq.heap));
        depq.printHeap(depq.heap);

        System.out.println("REMOVNG MAX " + depq.removeMaximum(depq.heap));
        depq.printHeap(depq.heap);


        //WHY IS THIS FIXING EVERYTHING?
        depq.build(depq.heap);
        depq.printHeap(depq.heap);
    }
    }

