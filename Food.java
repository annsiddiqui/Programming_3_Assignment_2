/**
 * @author Qurrat-al-Ain Siddiqui
 * @date Due October 19, 2018
 * 
 *       COMP 2503 - Programming 3 Assignment 2 - Recipie Book with Linked List
 *       Instructor: Laura Marik
 * 
 *       The Food class.
 */
public class Food implements Comparable<Food> {

	// instance variable
	private String name;
	private int num;

	/*
	 * Constructor to create food object
	 * 
	 * @param: String name
	 * 
	 * @param Integer number
	 */
	public Food(String name, int num) {
		this.name = name;
		this.num = num;
	}

	// Setter to increment the ingredient count
	public void setNum() {
		this.num++;
	}

	/*
	 * Getter to get name of the ingredient
	 * 
	 * @Return String; name of the food
	 */
	public String getName() {
		return name;
	}

	/*
	 * Accessor method to get number of the ingredients
	 * 
	 * @return int frequency of the food object
	 */
	public int getNum() {
		return num;
	}

	/*
	 * Accessor method to get length of the ingredient name
	 * 
	 * @return int length of the food name
	 */
	public int nameSize() {
		return name.length();
	}

	@Override
	public int compareTo(Food food) {
		return getName().compareToIgnoreCase(food.getName());
	}

	@Override
	public String toString() {
		return "(" + this.name + "," + this.num + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Food)) {
			return false;
		}

		Food other = (Food) obj;

		if (name != other.name) {
			return false;
		}

		return true;
	}

}
