package lsieun.asm.core.example.d;

import org.objectweb.asm.ClassVisitor;

public class RemoveDebugAdapter extends ClassVisitor {
    public RemoveDebugAdapter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visitSource(String source, String debug) {
        // do nothing
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        // do nothing
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        // do nothing
    }
}
