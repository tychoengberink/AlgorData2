package nl.MenTych;

public class Main {

    public static void main(String[] args) {
        // write your code here

        DEPQ depq = new DEPQ(10);
        for (int i = 0; i < 9; i++) {
            int number = (int) (Math.random() * ((100 - 1) + 1)) + 1;
            depq.add(number);
        }
        depq.printHeap();
    }
}
