package mx.infotec.dads.insight.pdes.insight.rules;

/**
 * RuleBase, implementación base de la interfaz Rule
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public abstract class RuleBase implements Rule {
    protected int priority;
    protected String ruleName;

    protected RuleBase() {
    }

    @Override
    public void setRuleName(String ruleName) {
	this.ruleName = ruleName;
    }

    @Override
    public void setPriority(int priority) {
	this.priority = priority;
    }

    @Override
    public int getPriority() {
	return priority;
    }
}
