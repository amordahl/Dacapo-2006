/* Generated By:JJTree: Do not edit this line. ASTContent.java */

package net.sourceforge.pmd.jsp.ast;

public class ASTContent extends SimpleNode {
    public ASTContent(int id) {
        super(id);
    }

    public ASTContent(JspParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(JspParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
