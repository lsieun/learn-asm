package lsieun.asm.core.b_method.e;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;


public class RemoveGetFieldPutFieldClassAdapter extends ClassVisitor {
    public RemoveGetFieldPutFieldClassAdapter(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if (mv != null && !name.equals("<init>")) {
            mv = new RemoveGetFieldPutFieldAdapter(mv);
        }
        return mv;
    }
}
