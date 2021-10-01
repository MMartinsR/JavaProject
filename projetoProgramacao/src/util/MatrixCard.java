package util;

import java.util.LinkedList;
import java.util.Random;

public class MatrixCard {

	// Foram criadas duas constantes para receber o tamanho do Array e do número de
	// Linked lists que serão criadas, desta forma facilita para modificar caso
	// tenha uma alteração.
	private final static int QUANTIDADE_ARRAY = 27;
	private final static int QUANTIDADE_LIST = 6;

	// Diferente da versão anterior, agora teremos a definição da LinkedList como
	// global, e uma definição de uma matriz de 9 posições (3x3) para guardar os
	// números randômicos gerados.

	Integer[][] randomStore = new Integer[3][3];
	LinkedList<Integer[]> matrixCard = new LinkedList<Integer[]>();
	MatCardFileHandler matrixFileHandler = new MatCardFileHandler();

	/**
	 * 
	 * método createList():
	 * 
	 * 1. Gera uma lista de arrays; 1.1. chama o método printList para imprimir a
	 * lista; 1.2. adiciona os arrays a cada posição da lista chamando o método
	 * create array;
	 * 
	 * 2. Faz a validação
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
	 * 1. imprime o cabeçalho do matrix card;
	 * 
	 */

	private void printList(int i) {

		if (i == 0) {

			System.out.println("------------Cartão Matriz------------");
			System.out.print("   1   2   3   4   5   6   7   8   9");
		}
		System.out.println();
		System.out.print((char) (65 + i));
	}

	/**
	 * método createArray():
	 * 
	 * 1. Cria arrays de tamanho QUANTIDADE_ARRAY;
	 * 
	 * 2. Utiliza a classe Random para gerar números aleatórios para cada posição da
	 * lista de arrays.
	 * 
	 * 4. Além disso, fará o espaçamento a cada 3 valores da matriz.
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
	 * 1. método para imprimir o array formatado.
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
	 * método generateRandomPosition():
	 * 
	 * 1. Utiliza a classe random para gerar 3 números aleatórios que serão a linha,
	 * coluna e posição do cartão matriz que deverão ser pedidas ao usuário.
	 * 
	 * 2. chama o método hasNumber para validar se algum dos números gerados já
	 * existem na matriz de números randômicos randomNumbers, se existirem, deverá
	 * ser feita nova randomização, senão salva os números gerados dentro da matriz
	 * de numeros randômicos.
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
	 * 1. valida se a posição de randomStore é nula, se não for valida se os valores
	 * existentes dentro de randomStore são iguais aos valores passados do array
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
	 * método isCorrectInput(Integer[] inputedValues):
	 * 
	 * 1.valida se a posição na matriz e o número inserido pelo usuário são iguais
	 * ou não;
	 * 
	 * 2. utiliza o método getMatrixCardPosition para validar esses dois valores;
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
	 * método getMatrixCardPosition(Integer[] values):
	 * 
	 * 1. retorna o valor exato segundo a posição na matriz dada.
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
	 * 1. imprime para o usuário a posição que deverá inserir;
	 * 
	 * @param values
	 */

	public void printRandomPosition(int step) {

		if (step == 0) {
			System.out.println("\n");
		}

		String text = "" + (char) (65 + randomStore[step][0]) + (randomStore[step][1] + 1) + " "
				+ (randomStore[step][2] + 1) + "º Posição: ";
		System.out.print(text);

	}



}
