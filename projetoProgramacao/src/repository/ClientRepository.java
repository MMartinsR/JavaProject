package repository;

import java.util.LinkedList;
import java.util.Scanner;

import entities.Client;

public class ClientRepository {

	LinkedList<Client> clientList = new LinkedList<>();
	Client cl;
	Scanner sc = new Scanner(System.in);

	/**
	 * createClient()
	 * 
	 * 1. This method receives data from the user and creates a new client object on
	 * the list, validates NIF number and if the operation was successful and prints the number of
	 * clients that are on the list.
	 * 
	 * @return
	 */
	public boolean createClient() {

		String input = "";
		int NIF = 0;

		System.out.print("Name: ");
		String name = sc.nextLine();

		do {

			System.out.print("NIF: ");
			input = sc.nextLine();
			if (input.length() == 9) {

				NIF = Integer.parseInt(input);
			} else {

				System.out.println("Invalid NIF number");
			}

		} while (input.length() != 9);

		System.out.print("Birthdate (DD/MM/YYYY): ");
		String birthDate = sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("Inicial Deposit: ");
		double balance = sc.nextDouble();

		cl = new Client(name, NIF, birthDate, address, balance);

		try {

			clientList.add(cl);

		} catch (Exception e) {

			return false;
		}

		System.out.println("Number of Clients: " + clientList.size());

		return true;
	}

	/**
	 * showClient()
	 * 
	 * 1. This method validates if the list is empty, if not, returns all the
	 * clients on the list.
	 * 
	 * @return
	 */

	public LinkedList<Client> showClient() {

		if (clientList.isEmpty()) {

			System.out.println("Error - There are no clients on the list!");
		}
		return clientList;

	}
	
	/**
	 * showClientByID(int id)
	 * 
	 * 1. This method allows you to search for a specific client.
	 * 
	 * @param id
	 */

	private void showClientByID(int id) {

		for (int i = 0; i < clientList.size(); i++) {

			if (clientList.get(i).getId() == id) {

				System.out.println(clientList.get(i).toString());
			}
		}

	}

	/**
	 * updateClient(int id)
	 * 
	 * 1. Validates if the list is empty, if not, try to update data of a given
	 * client.
	 * 
	 * @param id
	 * @return
	 */

	public boolean updateClient(int id) {

		if (clientList.isEmpty()) {

			System.out.println("Error - There are no clients on the list!");
			return false;
		}

		sc.nextLine();
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Birthdate (DD-MM-YYYY): ");
		String birthDate = sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();

		try {
			clientList.get(id - 1).setName(name);
			clientList.get(id - 1).setBirthDate(birthDate);
			clientList.get(id - 1).setAddress(address);

			showClientByID(id);

		} catch (Exception e) {

			e.printStackTrace();
			return false;

		}

		return true;
	}

	/**
	 * removeClient(int id)
	 * 
	 * 1. This method tries to remove the client with the id that was inserted by
	 * the user.
	 * 
	 * @param id
	 * @return
	 */

	public boolean removeClient(int id) {

		try {

			clientList.removeIf(client -> client.getId() == id);
		} catch (Exception e) {

			return false;
		}

		return true;
	}
	
	/**
	 * makeDeposit(int id)
	 * 
	 * 1. This method is responsible to validate if the list is empty; if the id is valid for a client and to make the deposit.
	 * 
	 * @param id
	 * @return
	 */

	public boolean makeDeposit(int id) {

		double value;

		if (clientList.isEmpty()) {

			System.out.println("Error - There are no clients on the list!");
			return false;
		}

		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getId() == id) {

				System.out.println("Please enter the amount for deposit: ");
				value = sc.nextDouble();

				clientList.get(i).deposit(value);
				return true;
			} else {

				System.out.println("This client ID is invalid or not registered!");
			}

		}

		return false;

	}
	
	/**
	 * makeWithdraw(int id)
	 * 
	 * 1. This method validates if the list is empty; if the client is exists and if he can make a withdraw.
	 * 
	 * @param id
	 * @return
	 */

	public boolean makeWithdraw(int id) {

		double value;

		if (clientList.isEmpty()) {

			System.out.println("Error - There are no clients on the list!");
			return false;
		}

		for (int i = 0; i < clientList.size(); i++) {

			if (clientList.get(i).getId() == id) {

				System.out.println("Current balance: " + clientList.get(i).getBalance() + " �");

				System.out.println("Please enter the amount for withdrawal: ");
				value = sc.nextDouble();

				if (value > clientList.get(i).getBalance() || value < 10) {

					System.out.println(
							"You can't withdraw more than " + clientList.get(i).getBalance() + "� or less than 10 �.");
					return false;
				}

				if (value > 200.00) {

					System.out.println("You can't withdraw more than 200 � at once");
					return false;

				} else {

					clientList.get(i).withdraw(value);
					return true;
				}

			} else {

				System.out.println("This client ID is invalid or not registered!");

			}
		}

		return false;
	}

}
