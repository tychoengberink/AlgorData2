package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int arr[] = {10, 4, 8};
        DEPQ depq = new DEPQ();
        System.out.println("-------------------");
        depq.heap = arr;
        int[] builded = depq.build(depq.heap);
        for (int i1 : builded) {
            System.out.println(i1);
        }
    }
}
