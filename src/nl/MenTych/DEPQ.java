package nl.MenTych;

class DEPQ {

    int heap[];

    private boolean isMinLayer(int[] heap, int i) {
        System.out.println(i);
        System.out.println(getParentFromCurrentElement(i));
        if(i == 0){
            return true;
        }else return heap[getParentFromCurrentElement(i)] > heap[i];

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
        return (i - 1) / 2;
    }

    private int getLeftFromRoot(int i) {
        return (2 * i + 1);
    }

    private int getRightFromRoot(int i) {
        return (2 * i + 2);
    }

    private boolean hasLeftChild(int[] heap, int i) {
        return getLeftFromRoot(i) < size();
    }

    private boolean hasRightChild(int[] heap, int i) {
        return getRightFromRoot(i) < size();
    }

    private boolean isGrandChild(int m, int i) {
//        i++;
        //need to change
        return getParentFromCurrentElement(getParentFromCurrentElement(m)) == i;
//        if (m == i * 4 || m == (i * 4) + 1 || m == (i * 4) + 2 || m == (i * 4) + 3) {
//            // is grandchild
//            return true;
//        } else {
//            // is something else
//            return false;
//        }
    }

    private boolean hasGrandParent(int i) {
        return getParentFromCurrentElement(getParentFromCurrentElement(i)) < size();
    }


    int[] build(int[] heap) {
//        for ( int i = size()/2; i > 0; i--){
        for (int i = 0; i < (size() /2); i++) {
            pushDown(heap, i);
        }
        return heap;
    }

    private int getSmallestChildOrGrandchild(int[] heap, int i) {
            if (hasLeftChild(heap, i)) {
                int indexleft = getLeftFromRoot(i);
                if (hasRightChild(heap, i)) {
                    int indexright = getRightFromRoot(i);
                    if (heap[indexleft] < heap[indexright]) {
                        return indexleft;
                    } else {
                        return indexright;
                    }
                } else {
                    return indexleft;
                }
            }
            return -1;
        }

    private int getLargestChildOrGrandchild(int[] heap, int i) {
            if(hasLeftChild(heap, i)){
                int indexleft = getLeftFromRoot(i);
                if(hasRightChild(heap, i)){
                    int indexright = getRightFromRoot(i);
                    if(heap[indexleft] > heap[indexright]){
                        return indexleft;
                    }else{
                        return indexright;
                    }
                }else{
                    return indexleft;
                }
            }
        return -1;

    }


    private boolean hasChildren(int[] heap, int i) {
        return hasLeftChild(heap, i) || hasRightChild(heap, i);
    }

    private void pushDown(int[] heap, int index) {
        if (isMinLayer(heap, index)) {
            System.out.println("MIN");
            pushDownMin(heap, index);
        } else {
            System.out.println("MAX");
            pushDownMax(heap, index);
        }
    }

    private void pushDownMin(int[] heap, int index) {
        if (hasChildren(heap, index)) {
            int m = getSmallestChildOrGrandchild(heap, index);
            System.out.println("Index M: " + m);
            System.out.println("Index parentM: " + getParentFromCurrentElement(m));
            System.out.println("Value m: " + heap[m]);
            System.out.println("Value i: " + heap[index]);
            if (isGrandChild(m + 1, index + 1)) {
                System.out.println(m + " IS GRANDCHILD OF " + index);
                if (heap[m] < heap[index]) {
                    System.out.println("PUSH DOWN MIN 1");
                    swap(heap, m, index);
                    if (heap[m] > heap[getParentFromCurrentElement(m)]) {
                        System.out.println("PUSH DOWN MIN 2");
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
            System.out.println("Index M: " + m);
            System.out.println("Index parentM: " + getParentFromCurrentElement(m));
            System.out.println("Value m: " + heap[m]);
            System.out.println("Value i: " + heap[index]);;
            if (isGrandChild(m + 1, index + 1)) {
                System.out.println(m + " IS MAX GRANDCHILD OF " + index);
                if (heap[m] > heap[index]) {
                    System.out.println("PUSH DOWN MAX 1");
                    swap(heap, m, index);
                    if (heap[m] < heap[getParentFromCurrentElement(m)]) {
                        System.out.println("PUSH DOWN MAX 2");
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
        int index = insertInArray(heap, value);
        pushUp(heap, index);
    }

    void pushUp(int[] heap, int index) {
        if (index != 0) {
            if (isMinLayer(heap, index)) {
                if (heap[index] > heap[getParentFromCurrentElement(index)]) {
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMax(heap, index);
                } else {
                    pushUpMin(heap, index);
                }
            } else {
                if (heap[index] < heap[getParentFromCurrentElement(index)]) {
                    swap(heap, index, getParentFromCurrentElement(index));
                    pushUpMin(heap, index);
                } else {
                    pushUpMax(heap, index);
                }
            }
        }
    }

    void pushUpMin(int[] heap, int index) {
        if (hasGrandParent(index) && heap[index] < heap[getGrandParent(index)]) {
            swap(heap, index, getGrandParent(index));
            pushUpMin(heap, getGrandParent(index));
        }
    }

    void pushUpMax(int[] heap, int index) {
        if (hasGrandParent(index) && heap[index] > heap[getGrandParent(index)]) {
            swap(heap, index, getGrandParent(index));
            pushUpMax(heap, getGrandParent(index));
        }
    }


    int insertInArray(int[] heap, int value) {
        int[] newheap = new int[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            newheap[i] = heap[i];
        }
        newheap[heap.length ] = value;

        this.heap = newheap;


        return heap.length;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return heap.length;
    }

}
