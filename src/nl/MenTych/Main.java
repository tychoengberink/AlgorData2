package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] arr = {5, 1, 2, 50, 40, 20, 90};//works
        //int[] arr = {5, 1, 2, 50, 40, 20, 30, 32};//works
//        int[] arr = {5, 1, 2, 10, 20, 34, 3, 8, 6}; //works
        DEPQ depq = new DEPQ();
        System.out.println("-------------------");
        depq.heap = arr;

        int[] builded = depq.build(depq.heap);
        for (int i = 0; i < builded.length; i++) {
            System.out.print("index: " + i + " with value: " + builded[i] + " ");
            if (depq.hasLeftChild(builded, i)) {
                System.out.print(" LEFT CHILD " + depq.getLeftFromRoot(i) + " ");
            }
            if (depq.hasRightChild(builded, i)) {
                System.out.print(" RIGHT CHILD " + depq.getRightFromRoot(i) + " ");
            }
            System.out.println();
        }

//
        depq.insert(depq.heap, 3);
        depq.insert(depq.heap, 91);
        depq.insert(depq.heap, 60);
        depq.insert(depq.heap, 23);
        depq.insert(depq.heap, 66);
        depq.insert(depq.heap, 12);
        depq.insert(depq.heap, 77);

        for (int i = 0; i < depq.heap.length; i++) {
            System.out.print("index: " + i + " with value: " + depq.heap[i] + " ");
            if (depq.hasLeftChild(depq.heap, i)) {
                System.out.print(" LEFT CHILD " + depq.getLeftFromRoot(i) + " ");
            }
            if (depq.hasRightChild(depq.heap, i)) {
                System.out.print(" RIGHT CHILD " + depq.getRightFromRoot(i) + " ");
            }
            System.out.println();
        }

//        System.out.println("REMOVNG MIN");
//
//        System.out.println(depq.findMinimum(depq.heap));
//
//
//        depq.removeSmallest(depq.heap);
//
//        for (int i = 0; i < depq.heap.length; i++) {
//            System.out.print("index: " + i + " with value: " + depq.heap[i] + " ");
//            if (depq.hasLeftChild(depq.heap, i)) {
//                System.out.print(" LEFT CHILD " + depq.getLeftFromRoot(i) + " ");
//            }
//            if (depq.hasRightChild(depq.heap, i)) {
//                System.out.print(" RIGHT CHILD " + depq.getRightFromRoot(i) + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("REMOVNG MAX");
//        System.out.println(depq.findMaximum(depq.heap));
//        depq.removeMaximum(depq.heap);
//
//        for (int i = 0; i < depq.heap.length; i++) {
//            System.out.print("index: " + i + " with value: " + depq.heap[i] + " ");
//            if (depq.hasLeftChild(depq.heap, i)) {
//                System.out.print(" LEFT CHILD " + depq.getLeftFromRoot(i) + " ");
//            }
//            if (depq.hasRightChild(depq.heap, i)) {
//                System.out.print(" RIGHT CHILD " + depq.getRightFromRoot(i) + " ");
//            }
//            System.out.println();
//        }
//    }
    }
}
