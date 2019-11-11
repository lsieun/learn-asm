package lsieun.asm.template.clazz;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import lsieun.asm.template.ClassAdapter;

public class MethodReplacer extends ClassAdapter {
    private String className;
    private String methodName;
    private String methodDesc;

    public MethodReplacer(ClassVisitor classVisitor, String methodName, String methodDesc) {
        super(classVisitor);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name;
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
        //...
        mv.visitCode();
        // call original method
        mv.visitVarInsn(Opcodes.ALOAD, 0); // this
        mv.visitMethodInsn(access, className, getNewName(name), descriptor, false);
        //...
        mv.visitEnd();
    }

    private static String getNewName(String name) {
        String newName = "orig$" + name;
        return newName;
    }
}
