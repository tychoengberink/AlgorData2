package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] arr = {5, 1, 2, 50, 40, 20, 90};// doesnt work
        //int[] arr = {5, 1, 2, 50, 40, 20, 30, 32};//works
        //int[] arr = {5, 1, 2, 10, 20, 34, 3, 8, 6}; //works
        DEPQ depq = new DEPQ();
        System.out.println("-------------------");
        depq.heap = arr;

        int[] builded = depq.build(depq.heap);
        for (int i = 0; i < builded.length; i++) {
            System.out.println("index: "+ i + " with value: " + builded[i]);
        }


        depq.insert(depq.heap, 3);
    }
}
