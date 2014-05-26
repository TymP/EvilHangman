public class Game {

	public int wordLength; //secret word length
	public int maxGuesses;//Maximum number of guesses
	public boolean runningTotal; //Count running total of guesses?
	public String current; //The letters in indexes currently known by the player.
	
	/*
	 * Takes a sample of the words in the dictionary, and the player's character guess. 
	 * Notes all the positions where the guess appears in the sample
	 * and copies these indexes to the running total,updating it.
	 * @param example	A String within the dictionary, within which letter positions will be found.
	 * @param guess		A single character, its positions within example will be evaluated.
	 */
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
	
	/*
	 * Re-assigns instance variable 'current' to a String of ? of the secret word's length. 
	 */
	public void createCurrent(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i<wordLength;i++){
			sb.append("?");
		}
		current = sb.toString();
	}
	
	/*
	 * Plays Evil Hangman. Calls UI class to take user input for word length, number of guesses, 
	 * running total display, and character and word guesses.
	 */
	public void play(){
		System.out.println("Welcome to Evil Hangman!"+ "\n");
		Bookworm bookworm = new Bookworm();
		boolean quit = false;
		//GAME LOOP
		while(quit == false){
			UI ui = new UI();
			//Initialise instance variables with user input.
			wordLength = ui.chooseWordLength();
			maxGuesses = ui.chooseMaxGuesses();
			runningTotal = ui.chooseRunningTotal();
			bookworm.read(); //Read the dictionary text file.
			//Check a word of the desired length exists.
			while(bookworm.hasLength(wordLength)==false){
				System.out.println("There are no words of length " + wordLength + " , try again.");
				wordLength = ui.chooseWordLength();
			}
			createCurrent();
			//Purge the dictionary, leaving only words of desired length.
			bookworm.purgeForLength(this.wordLength);
			//PLAY
			System.out.println("Let's get started."+"\n");
			System.out.println("Your word looks like this: "+ this.current + "\n");
			int tries = 0;
			//GUESS LOOP
			while (true) {
				String guess = ui.getGuess(this.wordLength);
				//WILDGUESS CASE______________________________________________________________________
				if(guess.length()>1){//User took a wild guess: tried the whole word.
					if(bookworm.contains(guess)&&bookworm.getWords().length==1){//
						System.out.println("Correct!, you win.");
						break;
					}
					else{
						System.out.println("Nope,that word is wrong.");
						bookworm.removeWord(guess);
						tries+=1;
					}
				}
				//CHARACTER GUESS CASE___________________________________________________________________
				else{
					char charGuess = guess.charAt(0);
					bookworm.partition(charGuess);
					//If the guess is not in a sample of this partition:
					if(bookworm.findLetterPositions(bookworm.getWords()[0], charGuess).equals(bookworm.EMPTY_CONDITION)){
						System.out.println("Nope, that letter is not in the word.");
						tries +=1;
						System.out.println("Your remaining guesses: "+ (this.maxGuesses - tries) + "\n");
					}
					else{
						System.out.println("Correct."+ "\n");
						String sample = bookworm.getWords()[0];
						updateCurrent(sample,charGuess);
					}
				}
				if(bookworm.findLetterPositions(current, '?').equals(bookworm.EMPTY_CONDITION)){//If there are no ?? left.
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

	
