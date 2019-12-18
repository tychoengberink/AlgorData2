package nl.MenTych;

public class DEPQ {

    private int[] Heap;
    private int size, maxsize;

    public DEPQ(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[maxsize + 1];
    }

    boolean isEmpty() {
        return size == 0;
    }

//    int size() {
//
//    }

    int getLow() {
        return Heap[0];
    }

    int getHigh() {
        if (Heap[1] > Heap[2]) {
            return Heap[1];
        } else {
            return Heap[2];
        }
    }

    void add(int x) {
        if (size + 1 >= maxsize) {
            System.out.println("ERROR MAX REACHED");
        }else {
            size = size + 1;
            Heap[size] = x;
            if(Heap[size] > Heap[size - 1] ){

            }
        }
    }
//
//    int removeLow() {
//
//    }
//
//    int removeHigh() {
//
//    }

    // representation of Heap
    void printHeap() {
        System.out.println("Array representation of Heap is:");

        for (int i = 0; i < size; ++i)
            System.out.print(Heap[i] + " ");

        System.out.println();
    }

}
