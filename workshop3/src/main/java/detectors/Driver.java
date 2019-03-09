package detectors;

import java.io.FileInputStream;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class Driver {
	public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("Calculator.java");

        // parse it
        CompilationUnit cu = JavaParser.parse(in);
        VoidVisitorAdapter<BreakPoints> visitor = new UselessControlFlowDetector();
        visitor.visit(cu, new BreakPoints());
	}
}
