/* Generated By:JJTree: Do not edit this line. ASTAnnotationTypeDeclaration.java */

package net.sourceforge.pmd.ast;

public class ASTAnnotationTypeDeclaration extends AccessNode {
    public ASTAnnotationTypeDeclaration(int id) {
        super(id);
    }

    public ASTAnnotationTypeDeclaration(JavaParser p, int id) {
        super(p, id);
    }


    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

}
