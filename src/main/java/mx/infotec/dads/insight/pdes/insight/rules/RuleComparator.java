package mx.infotec.dads.insight.pdes.insight.rules;

import java.util.Comparator;

/**
 * Rule Comparator
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class RuleComparator implements Comparator<Rule> {

    @Override
    public int compare(Rule ruleOne, Rule ruleTwo) {
	if (ruleOne.getPriority() > ruleTwo.getPriority()) {
	    return 1;
	}
	if (ruleOne.getPriority() < ruleTwo.getPriority()) {
	    return -1;
	}
	return 0;
    }
}