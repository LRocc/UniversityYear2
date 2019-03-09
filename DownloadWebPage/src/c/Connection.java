package c;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection 
{
	public static void main(String[] args) {
	    String url = "https://stackoverflow.com/questions/238547/how-do-you-programmatically-download-a-webpage-in-java";
	    String file = "C:\\Users\\lorenzo\\Desktop\\filename.html";
	    try {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		URL myurl = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		InputStream in = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(isr);
		String inputLine;		
		while((inputLine = bfr.readLine()) != null)
			{
				bw.write(inputLine);
			}
	    }catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
}
