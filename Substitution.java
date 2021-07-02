package cryptography;

import java.util.Scanner;

/**
 * 
 * @author matthewarcher 
 * Program enciphers an entered test from the user using the substitution method
 */
public class Substitution {

	public static void main(String[] args) {
		// User input
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a string to encipher using the substitution method: ");
		// stores users input into 'input'
		String input = scanner.nextLine();
		System.out.println();

		// alphabet in order [0-25]
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char[] ciphered = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		char stored;
		
		//algorithim for enciphering alphabet
		for (int i=0; i<= alphabet.length-3; i=i+2) {
			stored = ciphered[i];
			ciphered[i] = ciphered[i+2];
			ciphered[i+2] = stored;
		}
		
		//output the alphabet
		System.out.println("The unenciphered array of the alphabet: ");
		System.out.print("Plain =");
		for (int b=0; b<= alphabet.length-1;b++) {
			System.out.print(" " + alphabet[b]);
		}
		System.out.println();
		System.out.println();
		
		//output the ciphered array
		System.out.println("The enciphered array of the alphabet: ");
		System.out.print("Enciphered =");
		for (int a=0; a<= ciphered.length-1;a++) {
			System.out.print(" " + ciphered[a]);
		}
		System.out.println();
		System.out.println();
		
		//converts the input to all upper case, then to a character array 
		char[] charInput = input.toUpperCase().toCharArray();
		
		//alogorithim for enciphering the user input
		for (int y=0; y <= charInput.length-1;y++) {
			for (int z=0; z<=alphabet.length-1; z++) {
				if (charInput[y] == alphabet[z]) {
					charInput[y] = ciphered[z];
				}
			}
		}
		
		//outputs the enciphered input from user
		System.out.print("Your enciphered input: ");
		for (int x=0; x <= charInput.length-1;x++) {
			System.out.print(charInput[x]);
		}
	}
}
