import java.util.*;

public class SinglyLinkedList<T> implements Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int size;

  public SinglyLinkedList() {
    this.head = new Node<T>(null);
    this.tail = new Node<T>(null);
    this.size = 0;
  }

  public void add(T element) {
    Node<T> node = new Node<T>(element);

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
    return true; //STUB
  }

  public int size() {
    return 0; //STUB
  }

  public T getNthFromFirst(int n) {
    return head.getData(); //STUB
  }

  public T getNthFromLast(int n) {
    return head.getData(); //STUB
  }

  public SinglyLinkedListIterator iterator() {
    return new SinglyLinkedListIterator();
  }

  public String toString() {
    return ""; //STUB
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
  private class SinglyLinkedListIterator implements Iterator<T> {
    private Node<T> currentNode;

    public SinglyLinkedListIterator() {
      currentNode = head;
    }

    public boolean hasNext() {
      return (currentNode.getNextNode() == null) ? false : true;
    }

    public T next() {
      if (currentNode.getNextNode() == null) {
        throw new NoSuchElementException("iterator has reached the end");
      }
      currentNode = currentNode.getNextNode();
      return currentNode.getNextNode().getData();
    }

    public void remove() {
      throw new UnsupportedOperationException("remove operation is not supported by this iterator");
    }

  } // e SinglyLinkedListIterator

}
