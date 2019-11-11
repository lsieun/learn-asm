package lsieun.asm.template.clazz;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.tree.FieldNode;

import lsieun.asm.template.ClassAdapter;

public class FieldAdder extends ClassAdapter {
    private final FieldNode fn;

    public FieldAdder(ClassVisitor classVisitor, FieldNode fn) {
        super(classVisitor);
        this.fn = fn;
    }

    @Override
    public void visitEnd() {
        fn.accept(cv);
        super.visitEnd();
    }
}
