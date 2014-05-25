import java.util.Scanner;


public class Game {

	public int wordLength; //secret word length
	public int maxGuesses;//Maximum number of gueses
	public boolean runningTotal; //Count running total of guesses?
	public String current;
	
	public void updateCurrent(String example, char guess){
		StringBuilder replacement = new StringBuilder();
		for (int i =0; i<current.length();i++){
			if(example.charAt(i) == guess){
				replacement.append(guess);
			}
			else{
				replacement.append(current.charAt(i));
			}
		}
		current = replacement.toString();		
	}
	
	public void play(){
		System.out.println("Welcome to Evil Hangman!"+ "\n");
		Bookworm bookworm = new Bookworm();
		boolean quit = false;
		while(quit == false){//Game loop
			UI ui = new UI();
			wordLength = ui.chooseWordLength();
			maxGuesses = ui.chooseMaxGuesses();
			runningTotal = ui.chooseRunningTotal();
			bookworm.read();
			while(bookworm.hasLength(wordLength)==false){
				System.out.println("There are no words of length " + wordLength + " , try again.");
				wordLength = ui.chooseWordLength();
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0 ; i<wordLength;i++){
				sb.append("?");
				
			}
			current = sb.toString();
			System.out.println("Let's get started.");
			bookworm.purgeForLength(wordLength);
			System.out.println("Your word looks like this: "+ this.current + "\n");
			int tries = 0;
			while (true) {//Guess loop
				char guess = ui.getCharGuess();
				bookworm.partition(guess);
				if(bookworm.findLetterPositions(bookworm.getWords()[0], guess).equals("e")){
					System.out.println("Nope, that letter is not in the word.");
					
					tries +=1;
					System.out.println("Your remaining guesses: "+ (this.maxGuesses - tries) + "\n");
				}
				//if the chosen partition does contain the guess. print write
				else{
					System.out.println("Correct."+ "\n");
					String sample = bookworm.getWords()[0];
					updateCurrent(sample,guess);
				}
				if(bookworm.findLetterPositions(current, '?').equals("e")){
					System.out.println("You win!");
					break;
				}
				
				if(tries>= this.maxGuesses){
					System.out.println("You lost!"+"\n");
					System.out.println("The word was:" + bookworm.getWords()[0] + "\n");
					break;
				}
				System.out.println("Your progress : " + this.current);
				
				if(runningTotal==true){
					System.out.println("Total possible words remaining: " + bookworm.getWords().length);
				}
										
			}
			if( ui.quit()){
				System.out.println("Thanks for playing.");
				break;
			}
			
		}
	}
}

	
