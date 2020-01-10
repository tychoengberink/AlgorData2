package nl.MenTych;

import java.util.Arrays;

import static java.lang.Math.log;

class DEPQ {

    public int[] heap;

    //WORKS!
    private int getLevel(int i) {
        if (i == 0) {
            return 0;
        }

        return (int) (log(i + 1) / log(2));
    }

    //WORKS!
    private void swap(int[] heap, int m, int i) {
        int temp = heap[m];
        heap[m] = heap[i];
        heap[i] = temp;
    }

    //WORKS!
    private int getGrandParent(int i) {
        return getParentFromCurrentElement(getParentFromCurrentElement(i));
    }

    //WORKS!
    private int getParentFromCurrentElement(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    //WORKS!
    int getLeftFromRoot(int i) {
        return (2 * i + 1);
    }

    //WORKS!
    int getRightFromRoot(int i) {
        return (2 * i + 2);
    }

    //WORKS!
    boolean hasLeftChild(int i) {
        return getLeftFromRoot(i) < size();
    }

    //WORKS!
    boolean hasRightChild(int i) {
        return getRightFromRoot(i) < size();
    }

    //WORKS!
    private boolean isGrandChild(int m, int i) {
        if (m == 0 || m == 1 || m == 2) return false;
        return getParentFromCurrentElement(getParentFromCurrentElement(m)) == i;
    }

    //WORKS!
    private boolean hasGrandParent(int i) {
        int parent = getParentFromCurrentElement(i);
        return getParentFromCurrentElement(parent) != -1;
    }

    private boolean isEmpty() {
        if (this.heap == null) {
            throw new RuntimeException("heap is null");
        }
        return size() == 0;
    }

    private int size() {
        return this.heap.length;
    }

    int findMinimum(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }

    //WORKS!
    int findMaximum(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        if (heap.length > 1) {
            if (heap[1] > heap[2]) {
                return heap[1];
            } else {
                return heap[2];
            }
        } else {
            return heap[0];
        }
    }

    //WORKS!
    int findMaximumIndex(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        if (heap.length > 1) {
            if (heap[1] > heap[2]) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    //WORKS!
    int removeSmallest(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        int smallestval = heap[0];
            int[] newheap;
            swap(heap, 0, heap.length - 1);
            newheap = Arrays.copyOf(heap, heap.length - 1);

            this.heap = newheap;
            pushDown(this.heap, 0);

        return smallestval;
    }

    //WORKS
    int removeMaximum(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        int[] newheap;
        int largestindex;

        if (heap.length == 1) {
            largestindex = 0;
        } else if (heap.length == 2) {
            largestindex = 1;
        } else {
            largestindex = findMaximumIndex(heap);
        }
        int biggestval = heap[largestindex];
        swap(heap, largestindex, heap.length - 1);
        newheap = Arrays.copyOf(heap, heap.length - 1);
        this.heap = newheap;
        pushDown(newheap, largestindex);
        return biggestval;
    }

    //WORKS!
    int[] build(int[] heap) {
        for (int i = 0; i < (size() / 2); i++) {
            pushDown(heap, i);

        }
        return heap;
    }

    //WORKS!
    private int getSmallestChildOrGrandchild(int[] heap, int i) {
        //All the possible positions of smaller elements
        int[] positisions = new int[]{i * 2 + 2, i * 4 + 3, i * 4 + 4, i * 4 + 5, i * 4 + 6};
        if (hasLeftChild(i)) {
            int smallestindex = i * 2 + 1;
            for (int positision : positisions) {
                if (positision < size() - 1) {
                    if (heap[positision] < heap[smallestindex]) {
                        smallestindex = positision;
                    }
                }
            }
            return smallestindex;
        }
        return -1;
    }

    //WORKS!
    private int getLargestChildOrGrandchild(int[] heap, int i) {
        //All the possible positions of bigger elements
        int[] positisions = new int[]{i * 2 + 2, i * 4 + 3, i * 4 + 4, i * 4 + 5, i * 4 + 6};
        if (hasLeftChild(i)) {
            int biggest = i * 2 + 1;
            for (int positision : positisions) {
                if (positision < size() - 1) {
                    if (heap[positision] > heap[biggest]) {
                        biggest = positision;
                    }
                }
            }
            return biggest;
        }
        return -1;
    }

    //WORKS!
    private boolean hasChildren(int i) {
        return hasLeftChild(i) || hasRightChild(i);
    }

    //WORKS!
    private void pushDown(int[] heap, int index) {

        if (getLevel(index) == 0) {
            //Min
            pushDownMin(heap, index);
        } else if ((getLevel(index) % 2) == 0) {
            //Min
            pushDownMin(heap, index);
        } else {
            //Max
            pushDownMax(heap, index);
        }
    }

    //WORKS!
    private void pushDownMin(int[] heap, int index) {
        if (hasChildren(index)) {
            int m = getSmallestChildOrGrandchild(heap, index);
            if (isGrandChild(m, index)) {
                if (heap[m] < heap[index]) {
                    swap(heap, m, index);
                    if (heap[m] > heap[getParentFromCurrentElement(m)]) {
                        swap(heap, m, getParentFromCurrentElement(m));
                    }
                    pushDownMin(heap, m);
                }
            } else if (heap[m] < heap[index]) {
                swap(heap, m, index);
            }
        }
    }

    //WORKS!
    private void pushDownMax(int[] heap, int index) {
        if (hasChildren(index)) {
            int m = getLargestChildOrGrandchild(heap, index);
            if (isGrandChild(m, index)) {
                if (heap[m] > heap[index]) {
                    swap(heap, m, index);
                    if (heap[m] < heap[getParentFromCurrentElement(m)]) {
                        swap(heap, m, getParentFromCurrentElement(m));
                    }
                    pushDownMax(heap, m);
                }
            } else if (heap[m] > heap[index]) {
                swap(heap, m, index);
            }
        }
    }

    //WORKS!
    void insert(int[] heap, int value) {
        int[] newheap = new int[heap.length + 1];
        System.arraycopy(heap, 0, newheap, 0, heap.length);
        newheap[heap.length] = value;
        this.heap = newheap;
        pushUp(this.heap, heap.length);
    }

    //WORKS!
    private void pushUp(int[] heap, int index) {

        if (index > 0) {
            if (getLevel(index) % 2 == 0) {
                //Min
                if (heap[index] > heap[getParentFromCurrentElement(index)]) {
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMax(heap, index);
                } else {
                    pushUpMin(heap, index);
                }
            } else {
                //Max
                if (heap[index] < heap[getParentFromCurrentElement(index)]) {
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMin(heap, index);
                } else {
                    pushUpMax(heap, index);
                }
            }
        }
    }

    //WORKS!
    private void pushUpMin(int[] heap, int index) {
        if (hasGrandParent(index) && heap[index] < heap[getGrandParent(index)]) {
            swap(heap, index, getGrandParent(index));
            pushUpMin(heap, getGrandParent(index));
        }
    }

    //WORKS!
    private void pushUpMax(int[] heap, int index) {
        if (hasGrandParent(index) && heap[index] > heap[getGrandParent(index)]) {
            swap(heap, index, getGrandParent(index));
            pushUpMax(heap, getGrandParent(index));
        }
    }

    //WORKS!
    void printHeap(int[] heap) {
        for (int i = 0; i < heap.length; i++) {
            System.out.print("index: " + i + " with value: " + heap[i] + " ");
            if (hasLeftChild(i)) {
                System.out.print(" LEFT CHILD " + getLeftFromRoot(i) + " ");
            }
            if (hasRightChild(i)) {
                System.out.print(" RIGHT CHILD " + getRightFromRoot(i) + " ");
            }
            System.out.println();
        }
    }
}
