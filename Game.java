
public class Game {

	public int maxWord; //maximum word length
	public int maxGuesses;//Maximum number of gueses
	public boolean runningTotal; //Count running total of guesses?
	public String current;
	
	
	//Initialises instance variables in constructor.
	
	public Game(){
		UI ui = new UI();
		maxWord = ui.chooseMaxWord();
		maxGuesses = ui.chooseMaxGuesses();
		runningTotal = ui.chooseRunningTotal();
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i<maxWord;i++){
			sb.append("?");
			
		}
		current = sb.toString();
		
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
		while (true) {
			Bookworm bookworm = new Bookworm();
			bookworm.read();
			bookworm.purgeForLength(maxWord);
			char guess = ui.getCharGuess();
			bookworm.partition(guess);
			updateCurrent(bookworm.getWords()[0],guess);
			System.out.println("Progress: " + current);
			
			
		}

	}

}
