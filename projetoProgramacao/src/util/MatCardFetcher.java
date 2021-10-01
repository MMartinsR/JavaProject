package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import application.Main;

public class MatCardFetcher {

	static char nav='\0';

	public static void cardFetcher()  {
		
		try {
			File get_ficheiro = new File("matrixCardExtract.txt");
			Scanner leitor_ficheiros = new Scanner(get_ficheiro);
			while(leitor_ficheiros.hasNextLine()){
				String dados = leitor_ficheiros.nextLine();
				System.out.println(dados);
			}
			leitor_ficheiros.close();
		}
		catch(FileNotFoundException e1){
			System.out.println("Error - You must generate a new card in \'1 - Matrix-Card\', before you access it.");
		}
		
		mainMenu();
	}
	
	//Menu control method, with try/catch block to validate, any user character input. 
	public static void mainMenu() {
		
		Scanner decide = new Scanner(System.in);
		System.out.println("\n");	
		System.out.println("Would you like to return to the main menu ? y - n ");
				
		try {
			nav = decide.next().charAt(0);
			
		} catch (InputMismatchException e2) {
			decide.nextInt();
			System.out.println("Invalid character! - Please select one of the existing options.");
			}
		
		//ClearConsole class instance.
		ClearConsole clCons = new ClearConsole();
		
		//Main class instance
		//Main mn1 = new Main();
		
		//String Array, needed to create arguments, in order to be able to load the \'main()\' method, of the Main class.
		String[] arguments = new String[] {"123"};
		
		//Switch statement sub-menu, to be able to return to the Main class, or reload the \'cardFetcher()\'
		switch (nav) {
		case 'y':
		case 'Y':
			clCons.clear();
			Main.main(arguments);
			break;
		case 'n':
		case 'N':	
			clCons.clear();
			cardFetcher();
			break;
		}
		
		decide.close();
	}
}