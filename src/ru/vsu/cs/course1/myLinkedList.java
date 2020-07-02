package ru.vsu.cs.course1;

public class myLinkedList {
    private class Node {
        Node linkToNextElement;
        Integer element;

        public Node(Integer element) {
            this.element = element;
            this.linkToNextElement = null;
        }
    }

    private Node headElement;
    private int size = 0;
    private Node tailElement;

    public boolean isEmpty() {
        return size == 0;
    }

    public void addElement(Integer val) {
        Node newElement = new Node(val);
        if (isEmpty()) {
            headElement = tailElement = newElement;
        } else {
            tailElement.linkToNextElement = newElement;
            tailElement = newElement;
            newElement.linkToNextElement = headElement;
        }
        size++;
    }

    public void deleteElement(int index) throws Exception {
        if (isEmpty()) {
            throw new Exception("IsEmpty");
        } else {
            Node currentNode = headElement;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.linkToNextElement;
            }
            currentNode.linkToNextElement = currentNode.linkToNextElement.linkToNextElement;
            --size;
        }
    }

    public Integer getValue(int index) {
        Node currentNode = headElement;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.linkToNextElement;
        }
        return currentNode.element;
    }

    public Integer lastLeftover(int k) {
        Node currentNode = headElement;
        int n = 1;
        while (size > 1) {
            if (n == k - 1) {
                currentNode.linkToNextElement = currentNode.linkToNextElement.linkToNextElement;
                --size;
                n = 0;
                ++n;
                currentNode = currentNode.linkToNextElement;
            } else {
                ++n;
                currentNode = currentNode.linkToNextElement;
            }
        }

        return currentNode.element;
    }

    private int length() {
        return size;
    }

    public int[][] toMatrix() {
        int[][] array = new int[length()][1];
        for (int i = 0; i < length(); i++)
            array[i][0] = getValue(i);
        return array;
    }

    public static int[][] usingMyLinkedListClass(int[][] matrix) {
        myLinkedList listOfLeftovers = new myLinkedList();

        for (int i = 0; i < matrix.length; i++) {

            myLinkedList myList = new myLinkedList();

            for (int j = 0; j < matrix[0].length - 1; j++)
                myList.addElement(matrix[i][j]);

            listOfLeftovers.addElement(myList.lastLeftover(matrix[i][matrix[0].length-1]));
        }

        return listOfLeftovers.toMatrix();
    }
}
