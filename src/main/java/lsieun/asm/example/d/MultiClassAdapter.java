package lsieun.asm.example.d;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class MultiClassAdapter extends ClassVisitor {
    protected ClassVisitor[] cvs;

    public MultiClassAdapter(ClassVisitor[] cvs) {
        super(Opcodes.ASM6);
        this.cvs = cvs;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        for(ClassVisitor cv : cvs) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }
}
