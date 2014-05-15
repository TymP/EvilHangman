import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bookworm {
	
	public ArrayList<String> dictionary = null;
	
	public Bookworm(){
		dictionary = new ArrayList<String>();//Initialise the dictionary.
	}
	
	public void read(){
		BufferedReader br = null;
		
		try { //because we are reading a file.
			String currentLine; 
			br = new BufferedReader(new FileReader("C:\\Users\\Tim\\workspace\\EvilHangman\\src\\dictionary.txt"));
			
			while((currentLine = br.readLine()) != null){ //define currentLine object in while loop condition. while the current line, which is br.readLine() is something:
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
	
	public int findLetterPositions(String word, char letter){
		StringBuilder sb = new StringBuilder();
		
		for(int i =0; i<word.length();i++){
			if (word.charAt(i)==letter){
				sb.append(i);
			}
		}
		if (sb.length() == 0){
			return -1;
		}
		int result = Integer.parseInt(sb.toString());
		return result;
	}
	
	/*
	 * Simplest solution to the partition method.
	 */
	public void partition(char letter){
		//Creat array same length of dictionary, each index holds the species of the String at the same index in the dicitonary.
		int[] speciesList = new int[dictionary.size()];
		ArrayList<String> replacement = new ArrayList<String>();
		for(int i =0; i<speciesList.length;i++){
			speciesList[i] = findLetterPositions(dictionary.get(i),letter);
		}
		int winner = getMode(speciesList);
		for(int i =0; i<speciesList.length;i++){
			if(speciesList[i] ==winner){
				replacement.add(dictionary.get(i));
			}
		}
		this.dictionary=replacement;//Overwrite
	}
	
	public int getMode(int[] list){
		int maxCount =0;
		int winner = -2;//error
		for (int i = 0; i<list.length;i++){
			int count =0;
			int target = list[i];
			for( int j = 0; j<list.length;j++){
				if (list[j]==target){
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
	public boolean compareInt(int a, int b){
		if (a == b){
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void purgeForLength(int length){
		ArrayList<String> replacement = new ArrayList<String>();
		for(String word : dictionary){
			if (word.length() == length){
				replacement.add(word);
			}
		}
		this.dictionary = replacement;//Overwrite
	}
	
	
	public String[] getWords(){
		return convertToStringArray(dictionary);
	}
	
	
	
	public String[] convertToStringArray(ArrayList<String> stuff){
		int howMany = stuff.size();
		String[] result = new String[howMany];
		result = stuff.toArray(result);//passing result as type 
		return result;
		}
		

}