import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;




public class SortingLAlgorithms {
	
	public static void main (String...args) throws FileNotFoundException
	{
		
		String myDir = new String ("C:\\Users\\lorenzo\\eclipse-workspace\\TestSortingAlgorithms\\lists");
		File f = new File(myDir);
		String line;
		ArrayList<Integer> toSort = new ArrayList<Integer>();
		File[] paths;
		paths = f.listFiles();
		for(File child : paths)
		{
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(child));
					
					while((line = reader.readLine())!= null )
					{
						toSort.add(Integer.parseInt(line));
					}
					//Do Stuff with the list
				
					Algorithms obj1 = new Algorithms();
					obj1.insertionSort(toSort);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
		}
	}
	

}
