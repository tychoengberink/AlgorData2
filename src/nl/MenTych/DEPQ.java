package nl.MenTych;

public class DEPQ {

    int heap[];

    boolean isMinLayer(int[] heap, int i) {
        if(i == 0){
            return true;
        }else return heap[getParentFromCurrentElement(i)] > heap[i];
    }

    void swap(int[] heap, int m, int i) {
        int temp = heap[m];
        heap[m] = heap[i];
        heap[i] = temp;
        System.out.println("SWAP");
    }

    int getGrandParent(int i) {
        return getParentFromCurrentElement(getParentFromCurrentElement(i));
    }

    int getParentFromCurrentElement(int i) {
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

    boolean isGrandChild(int child, int i) {
        if(getParentFromCurrentElement(i) == 0){
            return false;
        }else{
            return true;
        }
    }

    boolean hasGrandParent(int i) {
        return getParentFromCurrentElement(getParentFromCurrentElement(i)) < size();
    }

    int[] build(int[] heap) {
        for (int i = 0; i < size()/2; i++) {
            pushDown(heap, i);
        }
        return heap;
    }

    int getSmallestChildOrGrandchild(int[] heap, int i) {
        if (isMinLayer(heap, i)) {
            return i;
        } else {
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
    }

    int getLargestChildOrGrandchild(int[] heap, int i) {
        if(!isMinLayer(heap, i)){
            return i;
        }else{
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
        }
        return -1;
    }


    boolean hasChildren(int[] heap, int i) {
        int indexleft = getLeftFromRoot(i);
        int indexright = getRightFromRoot(i);
        System.out.println("SIZE " + size());
        System.out.println("ROOT " + i);
        System.out.println("LEFT " + indexleft);
        System.out.println("RIGHT " + indexright);
        if (hasLeftChild(heap, i) && hasRightChild(heap, i)) {
            return true;
        } else {
            return false;
        }
    }

    void pushDown(int[] heap, int index) {
        System.out.println(isMinLayer(heap, index));
        if (isMinLayer(heap, index)) {
            pushDownMin(heap, index);
        } else {
            pushDownMax(heap, index);
        }
    }

    void pushDownMin(int[] heap, int index) {
        if (hasChildren(heap, index)) {
            int m = getSmallestChildOrGrandchild(heap, index);
            System.out.println("M = " + m);
            if (isGrandChild(m, index)) {
                System.out.println("IS GRANDCHILD");
                if (m != -1 && heap[m] < heap[index]) {
                    swap(heap, m, index);
                    if (heap[m] > heap[getParentFromCurrentElement(m)]) {
                        swap(heap, m, getParentFromCurrentElement(m));
                    }
                    pushDownMin(heap, m);
                }
            } else if (m != -1 && heap[m] < heap[index]) {
                System.out.println("IS NOT GRANDCHILD");
                System.out.println("SWAPPING A: " + heap[m] + " B: " + heap[index]);
                swap(heap, m, index);
            }
        }
    }

    void pushDownMax(int[] heap, int index) {
        if (hasChildren(heap, index)) {
            int m = getLargestChildOrGrandchild(heap, index);
            System.out.println("M = " + m);
            if (isGrandChild(m, index)) {
                if (m != -1 && heap[m] > heap[index]) {
                    swap(heap, m, index);
                    if (heap[m] < heap[getParentFromCurrentElement(m)]) {
                        swap(heap, m, getParentFromCurrentElement(m));
                    }
                    pushDownMax(heap, m);
                }
            } else if (m != -1 && heap[m] > heap[index]) {
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
        int newheap[] = new int[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            newheap[i] = heap[i];
        }
        newheap[heap.length + 1] = value;

        this.heap = newheap;

        return heap.length + 1;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return heap.length;
    }

}
