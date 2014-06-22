import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bookworm {
	
	public ArrayList<String> dictionary = null;
	public static final int[] EMPTY_CONDITION = {-1};
	
	public Bookworm(){
		dictionary = new ArrayList<String>();//Initialise the dictionary.
	}
	
	/*
	 * Reads the text file 'dictionary.txt'. Adds each term to the  ArrayList instance variable 'dictionary'.
	 */
	public void read(){
		BufferedReader br = null;
		
		try { 
			String currentLine; 
			br = new BufferedReader(new FileReader("C:\\Users\\Tim\\workspace\\EvilHangman\\src\\dictionary.txt"));
			
			while((currentLine = br.readLine()) != null){ 
				//define currentLine object in while loop condition. as long as there is something to read:
				dictionary.add(currentLine);
			}
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(br!= null){
					br.close();
				}
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}			
	}
		
	/*
	 * Compares two int arrays, decides if they are identical.
	 * @param a the first int arrray
	 * @param b the second int array
	 * @return boolean containing true if they are identical
	 */
	public boolean compareIntArrays(int[] a, int[] b){
		boolean result = true;
		if(a.length !=b.length){
			return false;
		}
		for(int i = 0; i<a.length; i++){
			if (a[i] != b[i]){
				result = false;
			}
		}
		return result;
	}
	
	/*
	 * Finds all the positions of a char in a string.
	 * @param word the string to search for occurences of a character
	 * @param letter the character to look for within word
	 * @return a string of int containing the indexes of all occorunces of the letter in word.
	 * returns EMPTY_CONDITION ("e") if there are no occurences of @param letter in @param word.
	 */
//	public String findLetterPositions(String word, char letter){
//		StringBuilder sb = new StringBuilder();
//		
//		for(int i =0; i<word.length();i++){
//			if (word.charAt(i)==letter){
//				sb.append(i);
//			}
//		}
//		if (sb.length() == 0){
//			return EMPTY_CONDITION;
//		}
//		String result = sb.toString();
//		return result;
//	}
	
	public int[] findLetterPositions(String word, char letter){
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(int i = 0; i<word.length(); i++){
			if (word.charAt(i)==letter){
				positions.add(i);
			}
		}
		if(positions.size() ==0){
			return EMPTY_CONDITION;
		}
		int[] result = new int[positions.size()];
		for(int i = 0; i<positions.size();i++){
			result[i] = positions.get(i).intValue();
		}
		return result;
	}
	
	/*
	 * Checks if the instance variable dictionary contains a string.
	 * @param word the word to look for.
	 * @return a boolean containing true if the word was found in dicitonary.
	 */
	public boolean contains(String word){
		boolean result = false;
		for(String entry : this.dictionary){
			if(entry.equals(word)){
				result = true;
			}
		}
		return result;
	}
	
	/*
	 * Removes a word from the instance variable dictionary.
	 * @param word a String to remove
	 */
	public void removeWord(String word){
		int position =-1;
		for (int i =0;i<this.dictionary.size();i++){
			if(word.equals(dictionary.get(i))){
				position = i;
			}
			if(position!=-1){
				this.dictionary.remove(i);
			}
		}
	}
	
	/*
	 * Takes a character input and 
	 * splits the dictionary into partitions containing
	 * the character at different positions (species)
	 * overwrites the instance variable dictionary with
	 * the most common partition.
	 * @param letter the character to use to partition 
	 * the instance variable dictionary
	 */
	public void partition(char letter){
		int[][] speciesList = new int[dictionary.size()][];
		for(int i = 0; i<dictionary.size(); i++){
			speciesList[i] = findLetterPositions(dictionary.get(i),letter);
		}
		//String[] speciesList = new String[dictionary.size()];
		ArrayList<String> replacement = new ArrayList<String>();
		for(int i =0; i<speciesList.length;i++){
			speciesList[i] = findLetterPositions(dictionary.get(i),letter);
		}
		int[] winner = getMode(speciesList);//fix this
		for(int i =0; i<speciesList.length;i++){
			if(compareIntArrays(speciesList[i],winner)){
				replacement.add(dictionary.get(i));
			}
		}
		this.dictionary=replacement;//Overwrite
	}
	
	/*
	 * Finds the mode of an array int arrays
	 * @param list an array int arrays
	 * @return the most common int array within @param list.
	 */
	public String getMode(String[] list){
		int maxCount =0;
		String winner = "e";//error
		for (int i = 0; i<list.length;i++){
			int count =0;
			String target = list[i];
			for( int j = 0; j<list.length;j++){
				if (list[j].equals(target)){
					count ++;
				}
			}
			if(count>maxCount){
				winner = target;
				maxCount = count;
			}
		}
		return winner;
	}
	public int[] getMode(int[][] list){
		int maxCount = 0;
		int[] winner = EMPTY_CONDITION; //error
		for(int i = 0; i<list.length; i++){
			int count = 0;
			int[] target = list[i];
			for(int j =0; j<list.length;j++){
				if(compareIntArrays(list[j],target)){
					count++;
				}
				
			}
			if(count>maxCount){
				winner = target;
				maxCount = count;
			}
		}
		return winner;
	}
	
	/*
	 * Takes in a desired word length and elimenates all strings of
	 * a different length from this.dictionary.
	 * @param length the desired word length
	 */
	public void purgeForLength(int length){
		ArrayList<String> replacement = new ArrayList<String>();
		for(String word : dictionary){
			if (word.length() == length){
				replacement.add(word);
			}
		}
		this.dictionary = replacement;//Overwrite
	}
	
	/*
	 * Gets this.dictionary as a String array
	 * @return a string array containing the words in this.dictionary.
	 */
	public String[] getWords(){
		String[] result = new String[dictionary.size()];
		result = dictionary.toArray(result);//passing result as type 
		return result;		
	}
	
	/*
	 * Checks if there are any words of a desired length in this.dictionary
	 * @param choice the desired word length
	 * @return true if there is a word of desired length in this.dictionary
	 */
	public boolean hasLength(int choice){
		boolean result = false;
		for(String word : this.dictionary){
			if(word.length() == choice){
				result = true;
				break;
			}
		}
		return result;
	}
}
