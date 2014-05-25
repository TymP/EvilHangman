import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	private Scanner sc;
	private StringBuilder alphabet;
	private static final int DEFAULT_INT = 1;
	
	public UI(){
		sc = new Scanner(System.in);
		alphabet = new StringBuilder();
	}
	
	public int chooseWordLength(){
		int max=DEFAULT_INT;
		boolean again = true;
		System.out.print("Enter word length.");
		while(again) {
			try{
				max = sc.nextInt();
				again = false;
			} 
			catch(InputMismatchException ex){
				System.out.println("Invalid input, try again.");
				sc.next();
			}
			
		}
		
		return max; 		
	}
	
	public boolean quit(){
		boolean again = true;
		String in = "error";
		System.out.println("Quit? (yes or no)");
		while(again){
			in = sc.next().toLowerCase();
			if(in.equals("yes") || in.equals("no")){
				break;
			}
			System.out.println("Invalid input. Type yes or no.");
			
		}
		return in.toLowerCase().equals("yes");
	}
	
	
	public String getAlphabet(){
		return alphabet.toString();
	}
	
	public int chooseMaxGuesses(){
		System.out.println("Enter maximum number of guesses");
		int max = DEFAULT_INT;
		boolean again =true;
		while(again){
			try{
				max = sc.nextInt();
				again = false;
				if(max <1 || max >26){
					again =true;
					System.out.println("Number out of reasonable range, enter again.");
				}
			}
			catch(Exception InputMismatchException){
				System.out.println("Invalid input, try again.");
				sc.next();
			}
		}
		return max; 
	}
	
	public String getGuess(int wildLength){
		String guess= "error";
		boolean again = true;
		System.out.println("Guess a character, or try the whole word.");
		while(again){
			try{
				guess = sc.next();
				again = false;
				if(guess.length()!=1 &&guess.length()!=wildLength){
					System.out.println("Invalid guess length. Guess a single character, or the entire word.");
					again = true;
				}
				if(guess.length() ==1){//The guess is a character.
					char character = guess.charAt((0));
					if(charUsed(character)){
						again = true;
						System.out.println("Character already used, try again.");
					}
					else{
						addCharToAlphabet(character);
					}
				}	
											
			}
			catch(Exception InputMismatchException){
				System.out.println("Invalid input. Guess must be a letter or a word.");
				sc.next();
			}
		}
				
		return guess;
	}
	public boolean chooseRunningTotal(){
		System.out.println("Do you want to keep track of a running total?");
		String response = "error";
		boolean again = true;
		while(again){
			try{
				response = sc.next().toLowerCase();
				again = false;
				if(response.equals("yes")==false && response.equals("no") ==false){
					again = true;
					System.out.println("Invalid. Input yes or no.");
				}
			}
			catch(Exception InputMismatchException){
				System.out.println("Invalid input, try again.");
				sc.next();
			}
		}
		return new String("yes").equals(response);
	}
	
	public char getCharGuess(){
		System.out.println("Guess a character.");
		//Try to get full word input first
		char input = sc.next().charAt(0);
		while(charUsed(input)){
			System.out.println("Already used, guess again.");
			input = sc.next().charAt(0);
			
			
		}
		addCharToAlphabet(input);
		return input;
		
	}
	public void addCharToAlphabet(char character){
		this.alphabet.append(character);
	}
	
	public boolean charUsed(char guess){
		boolean result = false;
		char tried = Character.toLowerCase(guess);
		for(int i =0; i<alphabet.length();i++){
			if(alphabet.charAt(i) == tried){
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
