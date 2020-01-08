package nl.MenTych;

import java.util.Arrays;

import static java.lang.Math.log;

class DEPQ {

    public int[] heap;

    //Probably not needed anymore see get level
//    private boolean isMinLayer(int[] heap, int i) {
//        if (getParentFromCurrentElement(i) != -1) {
//            System.out.println(heap[getParentFromCurrentElement(i)]);
//            System.out.println(heap[i]);
//        }
//        if (i == 0) {
//            return true;
//        }else return heap[getParentFromCurrentElement(i)] > heap[i];
//
//    }

    private int getLevel(int i) {
        if (i == 0) {
            return 0;
        }

        return (int) (log(i + 1) / log(2));
    }

    private void swap(int[] heap, int m, int i) {
        int temp = heap[m];
        heap[m] = heap[i];
        heap[i] = temp;
        System.out.println("SWAPPING " + m + " AND " + i);
    }

    private int getGrandParent(int i) {
        return getParentFromCurrentElement(getParentFromCurrentElement(i));
    }

    private int getParentFromCurrentElement(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    int getLeftFromRoot(int i) {
        return (2 * i + 1);
    }

    int getRightFromRoot(int i) {
        return (2 * i + 2);
    }

    boolean hasLeftChild(int[] heap, int i) {
        return getLeftFromRoot(i) < size();
    }

    boolean hasRightChild(int[] heap, int i) {
        return getRightFromRoot(i) < size();
    }

    private boolean isGrandChild(int m, int i) {
        i++;
        if (m == 0 || m == 1 || m == 2) return false;
        return getParentFromCurrentElement(getParentFromCurrentElement(m)) == i;
    }

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
        return heap.length;
    }

    int findMinimum(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        return heap[0];

    }

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

    void removeSmallest(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        if (heap.length >= 2) {
            int[] newheap;

            swap(heap, 0, heap.length - 1);
            newheap = Arrays.copyOf(heap, heap.length - 1);

            this.heap = newheap;
            pushDown(this.heap, 0);
        } else {
            this.heap = null;
        }
    }

    void removeMaximum(int[] heap) {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }

        if (heap.length == 1) {
            this.heap = null;
        } else if (heap.length >= 2) {
            int[] newheap;
            System.out.println(Arrays.toString(heap));
            int largestindex = findMaximumIndex(heap);
            System.out.println("LARGEST INDEX: " + largestindex + " is " + heap[largestindex]);
            swap(heap, largestindex, heap.length - 1);
            newheap = Arrays.copyOf(heap, heap.length - 1);

            this.heap = newheap;
            pushDown(this.heap, largestindex);
        }
    }


    int[] build(int[] heap) {
        for (int i = 0; i < (size() /2); i++) {
            pushDown(heap, i);
        }
        return heap;
    }

    private int[] addToArray(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }


    private int[] getAllChildrenAndGrandChildren(int[] heap, int i, int[] positions) {
        positions = addToArray(heap, getLeftFromRoot(i));
        positions = addToArray(heap, getRightFromRoot(i));


        for (int j = 0; j < positions.length; j++) {
            if (size() < positions[j]) {
                positions[j] = -1;
            }
        }

        if (size() > getLeftFromRoot(i)) {
            getAllChildrenAndGrandChildren(heap, getLeftFromRoot(i), positions);
        }

        if (size() > getRightFromRoot(i)) {
            getAllChildrenAndGrandChildren(heap, getRightFromRoot(i), positions);
        }

        return positions;
    }

    private int getSmallestChildOrGrandchild(int[] heap, int i) {
        //All the possible positions of smaller elements
        int[] positisions = new int[0];
        positisions = getAllChildrenAndGrandChildren(heap, i, positisions);
        System.out.println(Arrays.toString(positisions));
        if (hasLeftChild(heap, i)) {
            int smallestindex = i * 2 + 1;
            for (int positision : positisions) {
                if (positision != -1 && size() > positision) {
                    if (heap[positision] < heap[smallestindex]) {
                        smallestindex = positision;
                    }
                }
            }
            return smallestindex;
        }
            return -1;
        }

    private int getLargestChildOrGrandchild(int[] heap, int i) {
        //All the possible positions of bigger elements
        int[] positisions = new int[0];
        positisions = getAllChildrenAndGrandChildren(heap, i, positisions);
        if (hasLeftChild(heap, i)) {
            int biggest = i * 2 + 1;
            for (int positision : positisions) {
                if (positision != -1 && size() > positision) {
                    if (heap[positision] > heap[biggest]) {
                        biggest = positision;
                    }
                }
            }
            return biggest;
        }
        return -1;
    }

    private boolean hasChildren(int[] heap, int i) {
        return hasLeftChild(heap, i) || hasRightChild(heap, i);
    }

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

    private void pushDownMin(int[] heap, int index) {
        if (hasChildren(heap, index)) {
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

    private void pushDownMax(int[] heap, int index) {
        if (hasChildren(heap, index)) {
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

    void insert(int[] heap, int value) {
        int[] newheap = new int[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            newheap[i] = heap[i];
        }
        newheap[heap.length] = value;
        this.heap = newheap;
        pushUp(this.heap, heap.length);
    }

    private void pushUp(int[] heap, int index) {

        if (index > 0) {
            System.out.println("PushUP: " + index);
            if (getLevel(index) % 2 == 0) {
                //Min
                if (heap[index] > heap[getParentFromCurrentElement(index)]) {
                    System.out.println(index + " val:" + heap[index] + " is BIGGER then" + getParentFromCurrentElement(index) + " val:" + heap[getParentFromCurrentElement(index)]);
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMax(heap, index);
                } else {
                    System.out.println(index + " val:" + heap[index] + " is SMALLER then" + getParentFromCurrentElement(index) + " val:" + heap[getParentFromCurrentElement(index)]);
                    pushUpMin(heap, index);
                }
            } else {
                //Max
                if (heap[index] < heap[getParentFromCurrentElement(index)]) {
                    System.out.println(index + " val:" + heap[index] + " is SMALLER then" + getParentFromCurrentElement(index) + " val:" + heap[getParentFromCurrentElement(index)]);
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMin(heap, index);
                } else {
                    System.out.println(index + " val:" + heap[index] + " is BIGGER then" + getParentFromCurrentElement(index) + " val:" + heap[getParentFromCurrentElement(index)]);
                    pushUpMax(heap, index);
                }
            }
        }
    }

    private void pushUpMin(int[] heap, int index) {

        if (hasGrandParent(index) && heap[index] < heap[getGrandParent(index)]) {
            System.out.println("pushUpMin: " + heap[index] + " GrandParent value = " + heap[getGrandParent(index)]);
            swap(heap, index, getGrandParent(index));
            pushUpMin(heap, getGrandParent(index));
        }
    }

    private void pushUpMax(int[] heap, int index) {
        if (hasGrandParent(index) && heap[index] > heap[getGrandParent(index)]) {
            System.out.println("pushUpMax: " + heap[index] + " GrandParent value = " + heap[getGrandParent(index)]);
            swap(heap, index, getGrandParent(index));
            pushUpMax(heap, getGrandParent(index));
        }
    }

}
