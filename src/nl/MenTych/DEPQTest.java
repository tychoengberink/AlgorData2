package nl.MenTych;

import org.junit.Assert;
import org.junit.Test;


public class DEPQTest {


    @Test
    public void getLeftFromRoot() {
        final int[] dataset = {5, 1, 2};
        final int expected = 1;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        Assert.assertEquals(expected, depq.getLeftFromRoot(0));
    }

    @Test
    public void getRightFromRoot() {
        final int[] dataset = {5, 1, 2};
        final int expected = 2;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        Assert.assertEquals(expected, depq.getRightFromRoot(0));
    }

    @Test
    public void hasLeftChild() {
        final int[] dataset = {5, 1, 2};
        final boolean expected = true;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        Assert.assertEquals(expected, depq.hasLeftChild(0));
    }

    @Test
    public void hasRightChild() {
        final int[] dataset = {5, 1, 2};
        final boolean expected = true;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        Assert.assertEquals(expected, depq.hasRightChild(0));
    }

    @Test
    public void findMinimum() {
        final int[] dataset = {5, 1, 2};
        final int expected = 1;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.findMinimum(depq.heap));
    }

    @Test
    public void findMaximum() {
        final int[] dataset = {5, 1, 2};
        final int expected = 5;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.findMaximum(depq.heap));
    }

    @Test
    public void findMaximumIndex() {
        final int[] dataset = {5, 1, 2};
        final int expected = 1;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.findMaximumIndex(depq.heap));
    }

    @Test
    public void removeSmallest() {
        final int[] dataset = {5, 1, 2};
        final int[] expectedresult = {2, 5};
        final int expected = 1;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.removeSmallest(depq.heap));
        Assert.assertArrayEquals(expectedresult, depq.heap);
    }

    @Test
    public void removeMaximum() {
        final int[] dataset = {5, 1, 2};
        final int[] expectedResult = {1, 2};
        final int expected = 5;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.removeMaximum(depq.heap));
        Assert.assertArrayEquals(expectedResult, depq.heap);
    }

    @Test
    public void build() {
        final int[] dataset = {5, 1, 2};
        final int[] expected = {1, 5, 2};
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertArrayEquals(expected, depq.heap);
    }

    @Test
    public void insert() {
        final int[] dataset = {5, 1, 2};
        final int expected = 1;
        DEPQ depq = new DEPQ();
        depq.heap = dataset;
        depq.build(depq.heap);
        Assert.assertEquals(expected, depq.removeSmallest(depq.heap));
    }
}
