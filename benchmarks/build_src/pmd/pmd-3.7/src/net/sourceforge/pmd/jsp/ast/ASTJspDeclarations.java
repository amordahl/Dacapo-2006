/* Generated By:JJTree: Do not edit this line. ASTJspDeclarations.java */

package net.sourceforge.pmd.jsp.ast;

public class ASTJspDeclarations extends SimpleNode {
    public ASTJspDeclarations(int id) {
        super(id);
    }

    public ASTJspDeclarations(JspParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(JspParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}
