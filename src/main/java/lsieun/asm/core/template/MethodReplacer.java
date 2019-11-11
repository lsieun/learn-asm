package lsieun.asm.core.template;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class MethodReplacer extends ClassVisitor {
    private String className;
    private String methodName;
    private String methodDesc;

    public MethodReplacer(ClassVisitor classVisitor, String methodName, String methodDesc) {
        super(ASM6, classVisitor);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        String newName = name;
        if (name.equals(methodName) && descriptor.equals(methodDesc)) {
            newName = getNewName(name);
            generateNewBody(access, name, descriptor, signature, exceptions);
        }
        return super.visitMethod(access, newName, descriptor, signature, exceptions);
    }

    private void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        mv.visitCode();
        generateNewBody(mv);
        mv.visitEnd();
    }

    protected void generateNewBody(MethodVisitor mv) {
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, className, methodName, methodDesc, false);
        mv.visitInsn(RETURN);
    }

    public String getNewName(String name) {
        return "orig$" + name;
    }
}
