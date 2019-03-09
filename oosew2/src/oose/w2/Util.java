package oose.w2;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * 
 * @author <firstName> <lastName> , <RegistrationNo>
 *
 */
public class Util {
	/**
	 * A custom reader that reads first n characters from a file and swaps 
	 * every even char with a blank then prints the output. For a file with
	 * less than n chars, customCharReader does not care about how many 
	 * characters were actually read by the BufferedReader.
	 * @param file
	 * @param n
	 */
	public void closeStream(Closeable s){
	    try{
	        if(s!=null)s.close();
	    }catch(IOException e){
	        //Log or rethrow as unchecked (like RuntimException) ;)
	    }
	}
	
	
	public void customCharReader(File file, int n) throws IOException{
		int start;
		char[] buffer = new char[n];

		try {
			//BEFORE FIX:
			//BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF-8"));
	
			//
			//BEFORE FIX
			//reader.read(buffer,0,n);
			//FIX:
			if(reader.read(buffer,0,n) == -1)
			{
				throw new IOException();
			}
			closeStream(reader);//FIX
			//BEFORE THE FIX
			//reader.close();
		}
		catch(IOException e) {
			System.out.println("Something went wrong");
		}
		
		

		for(int i=1;i<=buffer.length;i++) {
			int swap = i % 2;
			//before Fix if statement was:
			//Integer.toString(swap) == Integer.toString(1)
			if (Integer.toString(swap).equals(Integer.toString(1))) { 
				buffer[i] = ' ';
			}			
		}
		System.out.print(buffer);
	}
	
	/**
	 * This function swaps an old character with a new one in a text string
	 * @param text
	 * @param oldchar
	 * @param newchar
	 * @return String
	 */
	public static String charReplace(String text, char oldchar, char newchar) {
		return text.replace(oldchar, newchar);
		/**
		 * Correction number 1
		 * Before COrrection
		 * text.replace(oldchar, newchar);	
		 * return text;	
		 */
		
	}
}
