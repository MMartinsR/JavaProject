package util;

import java.util.LinkedList;
import java.util.Random;

public class MatrixCard {

	//Two constants were created in order to get size of the Array, and the number of
	//Linked Lists to be created later on. This way, we will be able to perform
	//much easier code alterations
	private final static int QUANTIDADE_ARRAY = 27;
	private final static int QUANTIDADE_LIST = 6;

	//Different from the previous version, now we defined the LinkesList as
	//global, as well as a 9 position matrix (3x3) in order to store any
	//randomly generated numbers.

	Integer[][] randomStore = new Integer[3][3];
	LinkedList<Integer[]> matrixCard = new LinkedList<Integer[]>();
	MatCardFileHandler matrixFileHandler = new MatCardFileHandler();

	/**
	 * 
	 * createList():
	 * 
	 * 1. A array list is generated; 1.1. The printList method, is called in order to print the list;
	 * 1.2. Adds the the arrays in each position in the list, by calling the method create array;
	 * create array;
	 * 
	 * 2. Validates if the matrix card was saved.
	 * 
	 */

	public LinkedList<Integer[]> createList() {


		for (int i = 0; i < QUANTIDADE_LIST; i++) {
			printList(i);
			matrixCard.add(createArray());
		}
		
		if (matrixFileHandler.fileHandler(matrixCard)) {
			System.out.println("");
			System.out.print("Matrix Card saved successfully! ");
		}
		
		return matrixCard;

	}

	/**
	 * printList():
	 * 
	 * 1. prints the matrix card header; 
	 * 
	 */

	private void printList(int i) {

		if (i == 0) {

			System.out.println("------------ Matrix Card ------------");
			System.out.print("   1   2   3   4   5   6   7   8   9");
		}
		System.out.println();
		System.out.print((char) (65 + i));
	}

	/**
	 * createArray():
	 * 
	 * 1. Create size arrays QUANTIDADE_ARRAY;
	 * 
	 * 2. The Ramdom class is used to generate random numbers to each position in the array list.
	 * 
	 * 4. Besides, it will add the spacing at every 3 values in the matrix.
	 * 
	 */
	private Integer[] createArray() {
		Integer[] array = new Integer[QUANTIDADE_ARRAY];

		Random random = new Random();

		for (int j = 0; j < QUANTIDADE_ARRAY; j++) {
			array[j] = random.nextInt(9); // atribui o valor aleatório à posição i
			printArray(array, j);

		}
		return array;
	}
	
	/**
	 * printArray(Integer[] array, int j)
	 * 
	 * 1. this method will print the formatted array.
	 * @param array
	 * @param j
	 */

	public void printArray(Integer[] array, int j) {
		if (j % 3 == 0) {
			System.out.print(" ");
		}
		System.out.print(array[j]);
	}

	/**
	 * generateRandomPosition():
	 * 
	 * 1. Employs the random class in order to generate 3 random numbers, that will be the line,
	 * column and position in the matrix card, that will be asked from the user.
	 * 
	 * 2. The hasNumber method is called in order to validate, if any generated number already exists 
	 * in the randomNumbers matrix, should they exist, a new random number generation must occur, otherwise 
	 * the generated numbers will be stored in the matrix.
	 * 
	 * 
	 */

	public void generateRandomPosition() {
		randomStore = new Integer[3][3];
		Random random = new Random();

		for (int i = 0; i < 3; i++) {
			Integer[] values = new Integer[3];

			do {
				values[0] = random.nextInt(QUANTIDADE_LIST);
				values[1] = random.nextInt(QUANTIDADE_ARRAY / 3);
				values[2] = random.nextInt(3);

			} while (hasNumber(values));

			randomStore[i] = values;

		}

	}

	/**
	 * hasNumber(Integer[] values):
	 * 
	 * 1. validates whether the randomStore position is null. If it's not null, the existing
	 * values inside randomStore are equal to the passed values from the values array, it returns true
	 * should the values be equal. 
	 * 
	 * @param values
	 * @return
	 */

	private boolean hasNumber(Integer[] values) {
		for (int i = 0; i < 3; i++) {
			if (randomStore[i] == null) {
				return false;
			}

			if (randomStore[i][0] == values[0] && randomStore[i][1] == values[1] && randomStore[i][2] == values[2]) {
				return true;
			}

		}
		return false;

	}

	/**
	 * isCorrectInput(Integer[] inputedValues):
	 * 
	 * 1.validates whether the matrix position, and the number inserted by the user match.
	 * 
	 * 2. getMatrixCardPosition method is employed, in order to validate both values;
	 * 
	 * @param inputedValues
	 * @return
	 */

	public boolean isCorrectInput(Integer[] inputedValues) {
		for (int i = 0; i < 3; i++) {
			if (getMatrixCardPosition(randomStore[i]) != inputedValues[i]) {
				return false;
			}
		}
		return true;

	}

	/**
	 * getMatrixCardPosition(Integer[] values):
	 * 
	 * 1. returns the precise value, lodged in it's position of the matrix
	 * 
	 * @param values
	 * @return
	 */

	private int getMatrixCardPosition(Integer[] values) {
		
		return matrixCard.get(values[0])[values[1] * 3 + values[2]];
	}

	/**
	 * printRandomPosition(Integer[] values):
	 * 
	 * 1. prints up the exact position for the user to insert the requested value
	 * 
	 * @param values
	 */

	public void printRandomPosition(int step) {

		if (step == 0) {
			System.out.println("\n");
		}

		String text = "" + (char) (65 + randomStore[step][0]) + (randomStore[step][1] + 1) + " "
				+ (randomStore[step][2] + 1) + "º Position: ";
		System.out.print(text);

	}



}
