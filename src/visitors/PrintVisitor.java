package visitors;

import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.node.Node;
import com.dat405.nldl.node.Start;

public class PrintVisitor extends DepthFirstAdapter {

    @Override
    public void inStart(Start node) {
        System.out.println(node.getClass().getSimpleName() + ": " + node);
    }

    @Override
    public void defaultIn(Node node) {
        System.out.println(node.getClass() + " " + node);
    }
}