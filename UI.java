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
	
	/*
	 * Takes user input for a desired word length.
	 * @return max integer representing the desired word length.
	 */
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
	
	/*
	 * Takes user input to decide whether player wants to quit.
	 * @ return	boolean representing desire to  quit.
	 */
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
	
	/*
	 * @return	String of characters that have been guessed.
	 */
	public String getAlphabet(){
		return alphabet.toString();
	}
	
	/*
	 * Takes user input to decide maximum number of guesses.
	 * @return 	the desired number of guesses
	 */
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
	
	/*
	 * Takes user input for a single character guess, or a 'wild' guess at the whole word.
	 * @param	wildLength an integer representing the length of the secret word defined in the game class. Allows for error handling if word guessed is wrong length.
	 * @return	a string witht he user's guess. either 1 character long, or the length of the secret word in Game.
	 */
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
	
	/*
	 * Takes user input to decide if a running total of remaining possible words should be displayed.
	 * @return 	a boolean containing true if the user desires a display of running total.
	 */
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

	/*
	 * Adds a character to the private instance variable alphabet. 
	 * @param character	the character to be added.
	 */
	public void addCharToAlphabet(char character){
		this.alphabet.append(character);
	}
	
	/*
	 * Evaluates whether a character has already been used by checking whether it exists within the instance variable alpahbet.
	 * @param guess character to be checked.
	 * @return a boolean containing true if the character has been used.
	 */
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
	
	/*
	 * Prints the strings in a string array with line breaks in between each.
	 * @param words an array of strings to be printed
	 */
	public void printWords(String[] words){
		for (String word : words){
			System.out.println(word);
		}
	}
}
