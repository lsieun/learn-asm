package lsieun.asm.example.d;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class RemoveMethodAdapter extends ClassVisitor {
    private String methodName;
    private String methodDesc;
    public RemoveMethodAdapter(ClassVisitor classVisitor, String methodName, String methodDesc) {
        super(Opcodes.ASM6, classVisitor);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if(name.equals(methodName) && descriptor.equals(methodDesc)) {
            // do not delegate to next visitor -> this removes the method
            return null;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
