package lsieun.asm.core.b_method.b;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class RemoveNopClassAdapter extends ClassVisitor {
    public RemoveNopClassAdapter(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if(mv != null && !name.equals("<init>")) {
            mv = new RemoveNopAdapter(mv);
        }
        return mv;
    }
}
