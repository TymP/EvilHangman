import java.util.Scanner;


public class UI {
	private Scanner sc;
	private StringBuilder alphabet;
	
	public UI(){
		sc = new Scanner(System.in);
		alphabet = new StringBuilder();
	}
	
	public int chooseMaxWord(){
		//NEED ERROR HANDLING.
		System.out.println("Enter maximum word length.");
		int max = sc.nextInt();
		return max; 
		
	}
	
	public String getAlphabet(){
		return alphabet.toString();
	}
	public int chooseMaxGuesses(){
		//NEED ERROR HANDLING.
		System.out.println("Enter maximum number of guesses");
		int max = sc.nextInt();
		return max; 
	}
	
	
	public boolean chooseRunningTotal(){
		System.out.println("Do you want to keep track of a running total?");
		String response = sc.nextLine().toLowerCase();
		return new String("yes").equals(response);
	}
	
	public char getCharGuess(){
		System.out.println("Guess a character.");
		char input = sc.next().charAt(0);
		while(charUsed(input)){
			System.out.println("Already used, guess again.");
			input = sc.next().charAt(0);
			
		}
		return input;
		
	}
	public void addCharToAlphabet(char character){
		this.alphabet.append(character);
	}
	
	public boolean charUsed(char guess){
		boolean result = false;
		for(int i =0; i<alphabet.length();i++){
			if(alphabet.charAt(i) == guess){
				result = true;
			}
		}
		return result;
	}
	
	public void printWords(String[] words){
		for (String word : words){
			System.out.println(word);
		}
	}
}
