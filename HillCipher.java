package cryptography;

import java.util.Scanner;

/**
 * 
 * @author matthewarcher
 * Program enciphers a user input using the Hill Cipher method
 */
public class HillCipher {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a string to be enciphered: ");
		String input = scanner.nextLine();
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int[] alphValue = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,0};
	
		//stores user input as a character array
		char[] charInput = input.toUpperCase().toCharArray();
		
		//initializes inputValue
		int[] inputValue = new int[charInput.length];
		for (int i=0; i<= inputValue.length-1;i++) {
			inputValue[i] = 0;
		}
		
		//converts the user input into an array of the value of each character in the input
		for (int y=0; y<= charInput.length-1;y++) {
			for (int z=0; z<= alphabet.length-1;z++) {
				if (charInput[y] == alphabet[z]) {
					inputValue[y] = alphValue[z];
				}
			}
		}
		
		int[][] hill = {{1,2},{0,3}};
		
	}
}
