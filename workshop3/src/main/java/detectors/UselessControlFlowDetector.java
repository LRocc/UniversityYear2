package detectors;
import java.io.BufferedReader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
public class UselessControlFlowDetector extends VoidVisitorAdapter<BreakPoints> {
    @Override
    public void visit(IfStmt n, BreakPoints arg)  {
    	super.visit(n, arg);
        /* here you can access the attributes of the method.
         this method will be called for all methods in this 
         CompilationUnit, including inner class methods */
    	//System.out.println(n.getType());
    	//Optional<BlockStmt> block = n.getBody();
    	BreakPoints results = new BreakPoints();
    	// check whether n has an empty body
    	//System.out.println(n.getThenStmt());
    	if(n.getThenStmt().isEmptyStmt())
    	{
    		//System.out.println("Found an empty statement: " + n);
    		String methodName = results.getMethodDeclaration(n.getParentNode().get()).getNameAsString();
    	    String begin = n.getBegin().toString();
    		String end = n.getEnd().toString();
    		results.add(methodName, begin, end);
    	}
    	
    	else if(n.getThenStmt().isBlockStmt()) {

    		Statement stmt = n.getThenStmt();
    		if(stmt.getChildNodes().stream().allMatch(child -> child instanceof Comment)) 
    		{
    			 String methodName = results.getMethodDeclaration(stmt.getParentNode().get()).getNameAsString();
    			 Optional<Position> begin = n.getBegin();
    	    	 Optional<Position> end = n.getEnd();
    	    	 //System.out.println(methodName + "HEY METHOD NAME");
    	    	 //System.out.println(begin.toString()+ "HEY METHOD BEGIN");
    	    	 //System.out.println(end.toString() + "HEY METHOD END");
    	    	 
    	    	 results.add(methodName,begin.toString(),end.toString());
    	    	 //System.out.println(methodName);
    	    	 //System.out.println("Found an empty block statement: " + n);
    	    	 
     			
    		}
    	}
    }
    
    
    public void visit(MethodDeclaration n, BreakPoints arg)  {
    	super.visit(n, arg);
    	List<BlockStmt> node = n.findAll(BlockStmt.class);
    	for(BlockStmt n1: node)
    	{
    		if(isEmptyBlock(n1))
    		{
    			String methodNAme =arg.getMethodDeclaration(n1.getParentNode().get()).getDeclarationAsString();
    			String begin = n1.getBegin().toString();
    			String end = n1.getEnd().toString();
    			BreakPoints result = new BreakPoints();
    			result.add(methodNAme, begin, end);
    			
    		}
    	}
    }
    

    private static boolean isEmptyBlock(BlockStmt statement) {
		if(statement.getChildNodes().size() == 0)
		{
			//System.out.println("true");
			return true;
		}
		return false;
	}
}