package detectors;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class PolymorphicRecursion extends VoidVisitorAdapter<ArrayList<BreakPoints>> {
	@Override
	public void visit(MethodCallExpr method,ArrayList<BreakPoints> breakpointlist)
	{
		String methodName = method.getNameAsString();
		Node daddy = method.getParentNode().get();
		while((daddy != null) && (daddy instanceof MethodDeclaration))
		{
			daddy = daddy.getParentNode().get();
		}
		if(daddy!=null && ((MethodDeclaration) daddy).getNameAsString().equals(methodName))
				{
					String begin = daddy.getBegin().toString();
					String end = daddy.getEnd().toString();
					BreakPoints results = new BreakPoints();
					results.add(methodName, begin, end);
				}
	}

}
