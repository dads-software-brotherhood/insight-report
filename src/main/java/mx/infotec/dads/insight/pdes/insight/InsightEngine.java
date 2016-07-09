package mx.infotec.dads.insight.pdes.insight;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import mx.infotec.dads.insight.pdes.insight.rules.Rule;

/**
 * 
 * InsightEngine is the core of the Expert System
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class InsightEngine {
    private PriorityQueue<Rule> rules = new PriorityQueue<>(3);

    public List<Message> execute() {
	List<Message> messages = new ArrayList<>();
	for (Rule rule : rules) {
	    if (rule.evaluate()) {
		messages.add(rule.getMessage());
	    }
	}
	return messages;
    }

    public void addRule(Rule rule) {
	rules.add(rule);
    }
}
