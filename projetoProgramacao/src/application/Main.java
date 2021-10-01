package application;

import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;
import java.util.LinkedList;

import entities.Client;
import repository.ClientRepository;
import util.MatrixCard;
import util.ClearConsole;
import util.MatCardFetcher;

public class Main {

	public static void main(String[] args) {

		MatrixCard matrix = new MatrixCard();
		Locale.setDefault(Locale.US);
		Integer inputValues[] = new Integer[3];
		ClientRepository clientRep = new ClientRepository();
		ClearConsole clCons = new ClearConsole();

		int menu = 0;
		boolean validate = true;
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("Welcome - Please select one of the following options: \n");
			System.out.println("1 - Matriz-Card \n2 - Client Area\n3 - Get last Matrix Card \n4 - Exit");
			
			try {
				validate = false;
				menu = sc.nextInt();
			}catch(InputMismatchException e) {
				sc.next();
			}

			// Menu
			switch (menu) {
			// Generate and validate Matrix Card method
			case 1:
				createValidateMatrix(matrix, inputValues, sc);

				break;
				
			// Client Area (CRUD)
			case 2:
				
				char clientMenu = '\0';
				//sub-menu for every CRUD operation associated with the client class 
				do {
				System.out.println("Client Area - Please select one of the following options : \n");
				System.out.println("C - Create Client \nR - List Clients\nU - Update Client\nD - Delete Client \nE - Deposit \nF - Withdrawal \nQ - Quit to main menu.");
				clientMenu= sc.next().charAt(0);
							
				switch(clientMenu) {
				case 'c':
				case 'C':
					newClient(clientRep);
				break;
				
				case 'r':
				case 'R':	
					listClient(clientRep);
					break;
					
				case 'u':
				case 'U':	
					editClient(clientRep, sc);
					break;
					
				case 'd':
				case 'D':	
					deleteClient(clientRep, sc);
					break;
				
				case 'e':
				case 'E':	
					
					clientDeposit(clientRep, sc);

					break;
					
				case 'f':
				case 'F':
					
					clientWithdraw(clientRep, sc); 
				
					break;
					
				case 'q':
				case 'Q':
					
					clCons.clear();
					break;
					
				default:
					System.out.println(
							"Invalid character! - Please select one of the existing options.");	
				}
				
				}while(clientMenu != 'q' || clientMenu != 'Q');
				break;

				
			// Load the last generated matrix card. 
			case 3:
				MatCardFetcher.cardFetcher();
				break;
				
			case 4:
				
				System.exit(0);
			
			default:
				System.out.println(
						"Error! - Please select one of the existing options.");
			}

		}

	}

	private static void clientWithdraw(ClientRepository clientRep, Scanner sc) {
		System.out.println("Please enter your client ID: ");
		int id = sc.nextInt();
		
		boolean withdraw;
		
		withdraw = clientRep.makeWithdraw(id);
		
		if (withdraw) {
			
			System.out.println("The withdrawal was successful");
		}
	}

	private static void clientDeposit(ClientRepository clientRep, Scanner sc) {
		System.out.println("Please enter your client ID: ");
		int id = sc.nextInt();
		
		boolean deposit;
		
		deposit = clientRep.makeDeposit(id);
		
		if (deposit) {
			
			System.out.println("The deposit was successful");
		} 
	}

	/**
	 * editClient(ClientRepository clientRep, Scanner sc)
	 * 
	 * 1. This method is responsible to get a client's ID number, call the updateClient method and validate if the update was successful.
	 * 
	 * @param clientRep
	 * @param sc
	 */
	private static void editClient(ClientRepository clientRep, Scanner sc) {
		System.out.println("Enter an ID to update a client: ");
		int id = sc.nextInt();
		
		boolean update;
		
		update = clientRep.updateClient(id);
		
		if (update) {

			System.out.println("Client updated sucessfully");
		} else {

			System.out.println("An error has ocurred when trying to update the client");
		}
	}
	
	/**
	 * createValidateMatrix(MatrixCard matrix, Integer[] inputValues, Scanner sc)
	 * 
	 * 1. This method is responsible for creating the Matrix Card, generating positions of validation and validating said results with 
	 * user's inputs.
	 * 
	 * @param matrix
	 * @param inputValues
	 * @param sc
	 */

	private static void createValidateMatrix(MatrixCard matrix, Integer[] inputValues, Scanner sc) {
		matrix.createList();
		matrix.generateRandomPosition();

		for (int i = 0; i < 3; i++) {

			matrix.printRandomPosition(i);
			inputValues[i] = sc.nextInt();

		}

		if (matrix.isCorrectInput(inputValues)) {

			System.out.println("");
			System.out.println("Correct inputs!");
		} else {

			System.out.println("");
			System.out.println("Wrong inputs!");
		}
	}
	
	/**
	 * newClient(ClientRepository clientRep)
	 * 
	 * 1. This method is used to call createClient and validate if the create new client operation was successful
	 * 
	 * @param clientRep
	 */

	private static void newClient(ClientRepository clientRep) {
		// createClient
		

		boolean saved;

		saved = clientRep.createClient();

		if (saved) {

			System.out.println("Client saved sucessfully");
		} else {

			System.out.println("An error has ocurred when trying to create new client");
		}
	}

	/**
	 * listClient(ClientRepository clientRep)
	 * 
	 * 1. This method is responsible for showing all the clients that are on the list.
	 * 
	 * @param clientRep
	 */
	private static void listClient(ClientRepository clientRep) {
		// showClient
		LinkedList<Client> clientList = new LinkedList<>();
		
		clientList = clientRep.showClient();
		
		for (Client client : clientList) {
			
			System.out.println(client.toString());
		}
	}
	
	/**
	 * deleteClient(ClientRepository clientRep, Scanner sc)
	 * 
	 * 1. This method is responsible for receiving from the user an ID to remove a client and validating if the operation as successful
	 * @param clientRep
	 * @param sc
	 */

	private static void deleteClient(ClientRepository clientRep, Scanner sc) {
		
		// removeClient
		System.out.println("Enter an ID to remove a client: ");
		int id = sc.nextInt();
		
		boolean removed;
		
		removed = clientRep.removeClient(id);
		
		if (removed) {

			System.out.println("Client removed sucessfully");
		} else {

			System.out.println("An error has ocurred when trying to remove the client");
		}
	}

}
