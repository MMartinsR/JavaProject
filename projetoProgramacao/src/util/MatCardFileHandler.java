package util;


import java.io.PrintWriter;
import java.util.LinkedList;

public class MatCardFileHandler {

	// This method prints the Matrix Card inside a txt file 
	public boolean fileHandler(LinkedList<Integer[]> matrixCard) {
		
		try {
			PrintWriter prArr = new PrintWriter("matrixCardExtract.txt");
			
			prArr.println("------------ Matrix Card ------------");
			prArr.print("   1   2   3   4   5   6   7   8   9\n");

			for (int i = 0; i < matrixCard.size(); i++) {
				prArr.print((char) (65 + i));
				Integer[] lista = matrixCard.get(i);				
				
					for (int j = 0; j < lista.length; j++) {
						
						if (j % 3 == 0) {
							
							prArr.print(" ");
						}
						prArr.print(lista[j]);
					}
		
				prArr.println("");				
			}
			prArr.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : Couldn't find matrixCardExtract file.txt.");
			return false;
		}
	}
}
