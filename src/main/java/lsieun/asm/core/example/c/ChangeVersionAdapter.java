package lsieun.asm.core.example.c;

import static org.objectweb.asm.Opcodes.ASM6;
import static org.objectweb.asm.Opcodes.V9;

import org.objectweb.asm.ClassVisitor;

public class ChangeVersionAdapter extends ClassVisitor {
    public ChangeVersionAdapter(ClassVisitor cv) {
        super(ASM6, cv);
    }

    public ChangeVersionAdapter(final int api, final ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(V9, access, name, signature, superName, interfaces);
    }
}
