import java.util.*;

public class SinglyLinkedList<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public SinglyLinkedList() {
    this.head = new Node(null);
    this.tail = new Node(null);
    this.size = 0;
  }

  public void add(T element) {
    Node<T> node = new Node(element);

    if (this.size == 0) {
      this.head = node;
    } else {
      this.tail.setNextNode(node);
    }
    this.tail = node;
    this.size++;
  } 

  public void insertAt(T data, int index) {

  }

  /*
  public void remove(T data) {
    Node<T> previousNode;
    Node<T> currentNode = this.head;
    for (int i = 0; i < this.size; i++) {
      if (currentNode.getData() == data && i == 0) {
        this.head.setNextNode(currentNode.getNextNode());
        currentNode.setNextNode(null);
        this.size--;
        return;
      } else if (currentNode.getData() == data && i == this.size){
        previousNode.setNextNode(null);
        this.tailNode.setNextNode(previousNode);
        currentNode.setNextNode(null);
        this.size--;
        return;
      } else if (currentNode.getData() == data) {
        previousNode.setNextNode(currentNode.getNextNode());
        currentNode.setNextNode(null);
        this.size--;
        return;
      }
      previousNode = currentNode;
      currentNode = currentNode.getNextNode();
    }
  }
  */

  public void clear() {

  }

  public boolean isEmpty() {

  }

  public int size() {

  }

  public T getNthFromFirst(int n) {

  }

  public T getNthFromLast(int n) {

  }

  public SinglyLinkedListIterator<T> iterator() {
    return new SinglyLinkedListIterator();
  }

  public String toString() {

  }

  /***********************************
   * Node class
   ***********************************/
  private static class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }

    public void setData(T data) {
      this.data = data;
    }

    public T getData() {
      return this.data;
    }

    public Node<T> getNextNode() {
      return next;
    }

    public void setNextNode(Node<T> next) {
      this.next = next;
    }
  } // end of Node<T>

  /***********************************
   * SinglyLinkedListIterator
   ***********************************/
  private class SinglyLinkedListIterator implements Iterable<T> {
    public SinglyLinkedListIterator() {

    }
    public boolean hasNext() {

    }

    public T next() {

    }

    public void remove () {
      throw new UnsupportedOperationException("remove operation is not supported by this iterator");
    }
  }

}
