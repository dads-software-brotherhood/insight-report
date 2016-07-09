package mx.infotec.dads.insight.pdes.insight.rules;

import mx.infotec.dads.insight.pdes.insight.Message;

/**
 * Rule interface used for expert system.
 * 
 * @author Daniel Cortes Pichardo
 * 
 *
 */
public interface Rule {
    /**
     * This method encapsulates the rule's conditions.
     * 
     * @param context
     * @return true if the rule sould be applied
     */
    boolean evaluate();

    /**
     * This method encapsulates the rule's actions.
     * 
     * @return the message if the rule is evaluate true
     */
    Message getMessage();

    /**
     * Sete rule Name
     * 
     * @param ruleName
     */
    void setRuleName(String ruleName);

    /**
     * Set the priority of the rule
     * 
     * @param number
     *            of priority
     */
    void setPriority(int priority);

    /**
     * Get the priority of the Rule
     * 
     * @return number of the priority
     */
    int getPriority();
}
