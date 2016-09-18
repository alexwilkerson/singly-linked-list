public class MyStack<T> extends SinglyLinkedList<T> {

  public MyStack() {
    super();
  }

  public T pop() {
    if (!this.isEmpty()) {
      T returnValue = this.tail.getData();
      Node<T> tempNode = this.head;
      for (int i = 0; i < this.size-1; i++) {
        tempNode = tempNode.getNextNode();
      }
      tempNode.setNextNode(null);
      this.tail = tempNode;
      this.size--;
      return returnValue;
    } else {
      throw new NullPointerException("array empty. nothing to pop.");
    }
  }

  public void push (T value) {
    this.add(value);
  }

}
