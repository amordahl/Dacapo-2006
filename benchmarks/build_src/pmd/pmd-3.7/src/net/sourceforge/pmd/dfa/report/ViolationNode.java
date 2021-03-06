package net.sourceforge.pmd.dfa.report;

import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.IRuleViolation;

public class ViolationNode extends AbstractReportNode {

    private IRuleViolation ruleViolation;

    public ViolationNode(IRuleViolation violation) {
        this.ruleViolation = violation;
    }

    public IRuleViolation getRuleViolation() {
        return ruleViolation;
    }

    public boolean equalsNode(AbstractReportNode arg0) {
        if (!(arg0 instanceof ViolationNode)) {
            return false;
        }

        ViolationNode vn = (ViolationNode) arg0;

        return vn.getRuleViolation().getFilename().equals(this.getRuleViolation().getFilename()) &&
                vn.getRuleViolation().getBeginLine() == this.getRuleViolation().getBeginLine() &&
                vn.getRuleViolation().getVariableName().equals(this.getRuleViolation().getVariableName());
    }

}
