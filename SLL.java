import java.util.Comparator;

/**
 * @author Qurrat-al-Ain Siddiqui
 * @date Due October 19, 2018 COMP 2503 - Programming 3 Assignment 2 - Recipie
 *       Book with Linked List Instructor: Laura Marik
 * 
 *       The class for the Single Linked List
 */

public class SLL<T extends Comparable<T>> {

	// instance variable
	Node<T> head, tail;
	int size;

	/*
	 * Default constructor to create an empty list
	 */
	public SLL() {
		head = null;
		tail = null;
	}

	/*
	 * Boolean method to check if the list is currently empty
	 * 
	 * @return true if head points at null
	 * 
	 * @return false if head != null.
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Method to add Node to our list
	 * 
	 * @Param Node<T>; node to be added Comparator<T>; and what sorting thats got to
	 * be applied
	 */
	public void add(Node<T> n, Comparator<T> c) {
		// empty list
		if (head == null) {
			head = n;
			tail = n;
		}
		// if n.data is less than head.data, add to beginning of list ("add to head")
		else if (c.compare(n.getData(), head.getData()) <= 0) {
			addHead(n);
		}
		// search through list for curr.data < n.data and curr.next.data > n.data
		else {
			Node<T> curr = head;
			// a while loop that stops when curr.next.data is bigger than n.data
			while (curr.getNext() != null && c.compare(n.getData(), curr.getNext().getData()) > 0) {
				curr = curr.getNext();

			}
			if (curr.getNext() == null) {
				// add to end of list ("tail of list")
				addTail(n);
			} else {
				// add between curr and curr.next
				n.setNext(curr.getNext());
				curr.setNext(n);
			}
		}
	}

	/*
	 * Method to add the next node.
	 * 
	 * @Param Node<T>; Node to be added
	 */
	public void addHead(Node<T> n) {
		if (head == null) // empty list
		{
			head = n;
			tail = n;
		} else {
			n.setNext(head);
			head = n;
		}
	}

	/*
	 * Method to add the last node. ("tail")
	 * 
	 * @Param Node<T>; Node to be added
	 */
	public void addTail(Node<T> n) {
		if (tail == null) // list is empty
		{
			head = n;
			tail = n;
		} else {
			tail.setNext(n);
			tail = n;
		}
	}

	/*
	 * Method to print the entire listof ingredients/.
	 */
	public void printList() {
		Node<T> curr = head;
		while (curr != null) {
			System.out.println(curr.toString());
			curr = curr.getNext();
		}

	}

	/*
	 * Method to get the total size of the list.
	 * 
	 * @Return int size of the list
	 */
	public int getSize() {
		Node<T> curr = head;
		int s = 0;
		while (curr != null) {
			s++;
			curr = curr.getNext();
		}
		return s;
	}

	/*
	 * Method to get specific Node Data in the list.
	 * 
	 * @Return T data
	 */
	public T get(int index) {
		T data = null;
		int length = getSize();

		if (index < length && index >= 0) {
			Node<T> curr = head;
			for (int count = 0; count < index; count++)
				curr = curr.getNext();
			data = curr.getData();
		}
		return data;

	}

	/*
	 * To String method to print data of the nodes.
	 * 
	 * @Return String data
	 */
	public String toString() {
		String str = "";
		Node<T> curr = head;
		while (curr != null) {
			str += curr.getData() + "\n";
			curr = curr.getNext();
		}
		return str;
	}

}
