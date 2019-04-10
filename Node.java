/**
 * 
 */

/**
 * @author Qurrat-al-Ain Siddiqui
 * @date Due October 19, 2018 
 * COMP 2503 - Programming 3 
 * 
 * Assignment 2 - Recipie book with Linked List \
 * Instructor: Laura Marik
 * 
 * The node class.
 */

public class Node<T> {

	private T data;
	private Node<T> next;

	/**
	 * Constructor for objects of class Node
	 */
	public Node(T d) {
		data = d;
		next = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T o) {
		data = o;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> n) {
		next = n;
	}

	public String toString() {
		return "Node: " + getData().toString();
	}

}