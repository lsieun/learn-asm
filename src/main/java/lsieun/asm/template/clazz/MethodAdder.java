package lsieun.asm.template.clazz;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import lsieun.asm.template.ClassAdapter;

public class MethodAdder extends ClassAdapter {
    private int methodAccess;
    private String methodName;
    private String methodDesc;
    private String methodSignature;
    private String[] methodExceptions;

    public MethodAdder(ClassVisitor classVisitor, int methodAccess, String methodName, String methodDesc, String methodSignature, String[] methodExceptions) {
        super(classVisitor);
        this.methodAccess = methodAccess;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.methodSignature = methodSignature;
        this.methodExceptions = methodExceptions;
    }

    @Override
    public void visitEnd() {
        MethodVisitor mv = cv.visitMethod(methodAccess, methodName, methodDesc, methodSignature, methodExceptions);
        // create method body
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        super.visitEnd();
    }
}
