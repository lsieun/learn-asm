package lsieun.asm.b_method.a;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import lsieun.utils.io.FileUtils;

public class A_GenerateMethod {
    public static void main(String[] args) {
        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cv.visit(V1_6, ACC_PUBLIC | ACC_SUPER, "pkg/Bean", null, "java/lang/Object", null);
        cv.visitField(ACC_PRIVATE, "f", "I", null, null).visitEnd();

        MethodVisitor mv1 = cv.visitMethod(ACC_PUBLIC, "getF", "()I", null, null);
        mv1.visitCode();
        mv1.visitVarInsn(ALOAD, 0);
        mv1.visitInsn(NOP);
        mv1.visitFieldInsn(GETFIELD, "pkg/Bean", "f", "I");
        mv1.visitInsn(IRETURN);
        mv1.visitMaxs(1, 1);
        mv1.visitEnd();

        MethodVisitor mv2 = cv.visitMethod(ACC_PUBLIC, "checkAndSetF", "(I)V", null, null);
        mv2.visitCode();
        mv2.visitInsn(NOP);
        mv2.visitVarInsn(ILOAD, 1);

        Label label = new Label();
        mv2.visitJumpInsn(IFLT, label);
        mv2.visitVarInsn(ALOAD, 0);
        mv2.visitVarInsn(ILOAD, 1);
        mv2.visitFieldInsn(PUTFIELD, "pkg/Bean", "f", "I");

        Label end = new Label();
        mv2.visitJumpInsn(GOTO, end);

        mv2.visitLabel(label);
        mv2.visitFrame(F_SAME, 0, null, 0, null);
        mv2.visitTypeInsn(NEW, "java/lang/IllegalArgumentException");
        mv2.visitInsn(DUP);
        mv2.visitMethodInsn(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V");
        mv2.visitInsn(ATHROW);

        mv2.visitLabel(end);
        mv2.visitFrame(F_SAME, 0, null, 0, null);
        mv2.visitInsn(RETURN);

        mv2.visitMaxs(2, 2);
        mv2.visitEnd();

        cv.visitEnd();

        byte[] bytes = cv.toByteArray();

        String className = "pkg.Bean";
        String filepath = FileUtils.getFilePath(A_GenerateMethod.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);
    }
}
