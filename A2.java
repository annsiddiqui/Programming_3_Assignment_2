import java.util.*;

/**
 * @author Qurrat-al-Ain Siddiqui
 * @date Due October 19, 2018
 * 
 *       COMP 2503 - Programming 3 Assignment 2 - Recipie Book with Linked List
 *       Instructor: Laura Marik
 * 
 * 
 *       A2 is a processor class that parses a recipe book file from standard
 *       input and then prints statistics of the food used therein to standard
 *       output.
 * 
 */
public class A2 {

	// Instance variables
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int TEN = 10;

	private SLL<Food> sl = new SLL<Food>();

	// Hhard coding of basic list
	private String[] basicList = { "baking powder", "baking soda", "cheese", "broth", "tomato paste", "tomato sauce",
			"tomato", "flour", "egg", "garlic", "cheese", "rice", "onion", "salt", "pepper", "vinegar", "baking powder",
			"carrot", "sweet potato", "potato", "cream", "milk", "bean", "green bean", "beef", "chicken", "cumin",
			"basil", "oregano", "oil", "fish" };

	/*
	 * Main method that instantiate A1 object. Calls run();
	 */
	public static void main(String[] args) {
		A2 a2 = new A2();
		a2.run();

	}

	/*
	 * Calls 3 functions that: load the basic list, read the recipe from files &
	 * print the correct output.
	 */
	public void run() {
		readList();

		readRecipe();

		topIngredient();
	}

	/*
	 * Function to load in the basic list into a singly linked list.
	 */
	public void readList() {
		Food f = null;

		for (String a : basicList) {
			f = validateFood(a);
			if (f == null) {
				Node<Food> n = new Node<Food>(new Food(a, ZERO));
				sl.add(n, Comparator.comparing(Food::nameSize).reversed());
			}
		}
	}

	/*
	 * Function to read in the recipe through standard input and parses it to pull
	 * out only the necessary ingreidents for the recipie.
	 */
	public void readRecipe() {

		Food f = null;
		String item;
		boolean lineCheck = false;

		Scanner kb = new Scanner(System.in);

		while (kb.hasNextLine()) {
			if (kb.nextLine().equals("---")) {
				lineCheck = true;

				while (lineCheck == true) {
					item = kb.nextLine();
					if (item.equals("---")) {
						lineCheck = false;
					} else {
						item = item.toLowerCase().trim();
						String[] commaSeparator = item.split(",");
						String recipeLine = commaSeparator[ZERO];
						String[] ingredient = recipeLine.split(" ");

						int size = ingredient.length;
						String food = null;

						// checks if two word ingredients that already exist in the basic list are not
						// ignored & skipped over.
						// for example, making sure baking soda is read as baking soda, and not just
						// soda. (handles error)
						if (size == ONE) {
							food = ingredient[size - ONE];
						} else {
							String test = ingredient[size - TWO] + " " + ingredient[size - ONE];
							f = validateFood(test);

							if (f == null) {
								food = ingredient[size - ONE];
							} else {
								food = ingredient[size - TWO] + " " + ingredient[size - ONE];
							}
						}

						if (!food.substring(food.length() - TWO).equals("ss")) {
							// to remove "es" from string
							int indexES = food.length() - TWO;
							if (food.indexOf("es", indexES) > ZERO) {
								food = food.substring(ZERO, food.indexOf("es", indexES));
							}

							// to remove 's' from string
							int indexS = food.length() - ONE;
							// int indexSS = food.length() - TWO;

							if (food.indexOf('s', indexS) > ZERO) {
								food = food.substring(ZERO, food.indexOf('s', indexS));
							}
						}

						// check if foodlist .contains any basiclist substring
						// for example, if eggplant contains egg
						for (String z : basicList) {
							if (food.contains(z)) {
								food = z;
							}
						}

						// validate if the food is duplicated already
						f = validateFood(food);

						/*
						 * If validator returns null, create the food node and add it to the linked
						 * list..
						 * 
						 * @return Food
						 * 
						 * Then increments the ingredient count by 1.
						 */
						if (f == null) {
							Node<Food> n = new Node<Food>(new Food(food, ONE));
							// Sorts the list by size of the name, biggest at the top
							sl.add(n, Comparator.comparing(Food::nameSize).reversed());
						} else {
							f.setNum();
						}
					}
				}
			}
		}

		kb.close();
	}

	/*
	 * Function to validate if a node already exists in our singled linked list.
	 * 
	 * @param String: name of the item to be cross reference
	 * 
	 * @return Food
	 */
	public Food validateFood(String item) {

		Food f = null;
		boolean found = false;
		int i = 0;

		while (i < sl.getSize() && !found) {
			if (sl.get(i).getName().equals(item)) {
				f = sl.get(i);
				found = true;
			}
			i++;
		}
		return f;
	}

	/*
	 * Method to print the output file using various comparators.
	 */
	public void topIngredient() {

		int listSize = sl.getSize();

		SLL<Food> topList = new SLL<Food>();

		// Sorts the list by the number in reveres and then by name
		Comparator<Food> numComparator = Comparator.comparing(Food::getNum).reversed().thenComparing(Food::getName);

		// adds nodes with value > 0 to a new list
		for (int i = 0; i < listSize; i++) {
			if (sl.get(i).getNum() > ZERO) {
				Node<Food> n = new Node<Food>(sl.get(i));
				topList.add(n, numComparator);
			}
		}

		int size = topList.getSize();

		System.out.println("The recipe book has " + size + " different ingredients");
		System.out.println("---------------------------------------");

		// If the total number of ingredients exceeds 10
		if (size > TEN) {
			size = TEN;

			System.out.println("Top " + size + " ingredients, from most common to least common");

			for (int i = ZERO; i < TEN; i++) {
				System.out.println(topList.get(i).toString());
			}

		} else {
			System.out.println("Top " + size + " ingredients, from most common to least common");

			for (int i = ZERO; i < size; i++) {
				System.out.println(topList.get(i).toString());
			}
		}

		System.out.println("---------------------------------------");
		System.out.println("The complete list of foods in the recipe book, in alphabetical order");

		SLL<Food> completeList = new SLL<Food>();

		// Sorts the list alphabetically
		Comparator<Food> alphaComparator = Comparator.comparing(Food::getName);

		for (int i = 0; i < listSize; i++) {
			if (sl.get(i).getNum() > ZERO) {
				Node<Food> n = new Node<Food>(sl.get(i));
				completeList.add(n, alphaComparator);
			}
		}

		completeList.printList();

	}

}
