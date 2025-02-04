package de.rub.bi.inf.openbimrl.functions.list;

import de.rub.bi.inf.openbimrl.NodeProxy;
import de.rub.bi.inf.openbimrl.functions.AbstractFunction;
import de.rub.bi.inf.openbimrl.functions.annotations.OpenBIMRLFunction;

import java.util.ArrayList;

/**
 * @author Marcel Stepien
 */
@OpenBIMRLFunction
public class RepeatAsList extends AbstractFunction {

    public RepeatAsList(NodeProxy nodeProxy) {
        super(nodeProxy);
    }

    @Override
    public void execute() {

        Object object0 = getInput(0, Object.class);
        Object object1 = getInput(1, Object.class);

        int counter = Integer.parseInt(object1.toString());

        ArrayList<Object> results = new ArrayList();
        for (int i = 0; i < counter; i++) {
            results.add(object0);
        }

        setResult(0, results);

    }

}
