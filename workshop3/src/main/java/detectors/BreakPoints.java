package detectors;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.github.javaparser.Position;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.Statement;

/***
 * The Class BreakPoints collects  the  observed  behaviour  as  
 * the visit method(s)  implemented in detector transverses 
 * through the program structure
 * @author lorenzo
 *
 */
public class BreakPoints {
	
	
	
	private ArrayList<List<String>> br;
	private Map<String, Optional<Position>> map;
	

	public BreakPoints() {
		// TODO Auto-generated constructor stub
		br = new ArrayList<List<String>>();
		map = new HashMap<String, Optional<Position>>();
	}
	
	public MethodDeclaration getMethodDeclaration(Node n) {
		Node p = n;
		while(! (p instanceof MethodDeclaration))
			p = p.getParentNode().get();
		return (MethodDeclaration) p;
	}
	public void add(String methodName, String string, String string2) {
		// TODO Auto-generated method stub
		List<String> list = Arrays.asList(methodName,string,string2);
		br.add(list);
		System.out.println(br.toString() + "result");
	}

}
