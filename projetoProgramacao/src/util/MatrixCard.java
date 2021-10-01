package util;

import java.util.LinkedList;
import java.util.Random;

public class MatrixCard {

	// Foram criadas duas constantes para receber o tamanho do Array e do n�mero de
	// Linked lists que ser�o criadas, desta forma facilita para modificar caso
	// tenha uma altera��o.
	private final static int QUANTIDADE_ARRAY = 27;
	private final static int QUANTIDADE_LIST = 6;

	// Diferente da vers�o anterior, agora teremos a defini��o da LinkedList como
	// global, e uma defini��o de uma matriz de 9 posi��es (3x3) para guardar os
	// n�meros rand�micos gerados.

	Integer[][] randomStore = new Integer[3][3];
	LinkedList<Integer[]> matrixCard = new LinkedList<Integer[]>();
	MatCardFileHandler matrixFileHandler = new MatCardFileHandler();

	/**
	 * 
	 * m�todo createList():
	 * 
	 * 1. Gera uma lista de arrays; 1.1. chama o m�todo printList para imprimir a
	 * lista; 1.2. adiciona os arrays a cada posi��o da lista chamando o m�todo
	 * create array;
	 * 
	 * 2. Faz a valida��o
	 * 
	 */

	public LinkedList<Integer[]> createList() {

		// 1.

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
	 * 1. imprime o cabe�alho do matrix card;
	 * 
	 */

	private void printList(int i) {

		if (i == 0) {

			System.out.println("------------Cart�o Matriz------------");
			System.out.print("   1   2   3   4   5   6   7   8   9");
		}
		System.out.println();
		System.out.print((char) (65 + i));
	}

	/**
	 * m�todo createArray():
	 * 
	 * 1. Cria arrays de tamanho QUANTIDADE_ARRAY;
	 * 
	 * 2. Utiliza a classe Random para gerar n�meros aleat�rios para cada posi��o da
	 * lista de arrays.
	 * 
	 * 4. Al�m disso, far� o espa�amento a cada 3 valores da matriz.
	 * 
	 */
	private Integer[] createArray() {
		Integer[] array = new Integer[QUANTIDADE_ARRAY];

		Random random = new Random();

		for (int j = 0; j < QUANTIDADE_ARRAY; j++) {
			array[j] = random.nextInt(9); // atribui o valor aleat�rio � posi��o i
			printArray(array, j);

		}
		return array;
	}
	
	/**
	 * printArray(Integer[] array, int j)
	 * 
	 * 1. m�todo para imprimir o array formatado.
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
	 * m�todo generateRandomPosition():
	 * 
	 * 1. Utiliza a classe random para gerar 3 n�meros aleat�rios que ser�o a linha,
	 * coluna e posi��o do cart�o matriz que dever�o ser pedidas ao usu�rio.
	 * 
	 * 2. chama o m�todo hasNumber para validar se algum dos n�meros gerados j�
	 * existem na matriz de n�meros rand�micos randomNumbers, se existirem, dever�
	 * ser feita nova randomiza��o, sen�o salva os n�meros gerados dentro da matriz
	 * de numeros rand�micos.
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
	 * 1. valida se a posi��o de randomStore � nula, se n�o for valida se os valores
	 * existentes dentro de randomStore s�o iguais aos valores passados do array
	 * values, se for retorna true;
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
	 * m�todo isCorrectInput(Integer[] inputedValues):
	 * 
	 * 1.valida se a posi��o na matriz e o n�mero inserido pelo usu�rio s�o iguais
	 * ou n�o;
	 * 
	 * 2. utiliza o m�todo getMatrixCardPosition para validar esses dois valores;
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
	 * m�todo getMatrixCardPosition(Integer[] values):
	 * 
	 * 1. retorna o valor exato segundo a posi��o na matriz dada.
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
	 * 1. imprime para o usu�rio a posi��o que dever� inserir;
	 * 
	 * @param values
	 */

	public void printRandomPosition(int step) {

		if (step == 0) {
			System.out.println("\n");
		}

		String text = "" + (char) (65 + randomStore[step][0]) + (randomStore[step][1] + 1) + " "
				+ (randomStore[step][2] + 1) + "� Posi��o: ";
		System.out.print(text);

	}



}
