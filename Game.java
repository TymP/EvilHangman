
public class Game {

	public int wordLength; //secret word length
	public int maxGuesses;//Maximum number of gueses
	public boolean runningTotal; //Count running total of guesses?
	public String current;
	
	//Initialises instance variables in constructor.
	
	public Game(){
		System.out.println("Welcome to Evil Hangman!"+ "\n");
		UI ui = new UI();
		wordLength = ui.chooseWordLength();
		maxGuesses = ui.chooseMaxGuesses();
	}
	
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
		UI ui = new UI();
		Bookworm bookworm = new Bookworm();
		while(true){//Game loop
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
				//if chosen partition does not contain guess, print wrong. don't update current. print current. tries ++
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
				System.out.println("Your progress : " + this.current);
				
				if(bookworm.findLetterPositions(current, '?').equals("e")){
					System.out.println("You win!");
					break;
				}
				
				if(tries == this.maxGuesses){
					System.out.println("You lost!");
					break;
				}
			
			}
			if(ui.quit()){
				break;
			}

		}
	}
}

	
