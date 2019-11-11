package lsieun.asm.template.method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ExitingAdapter extends AdviceAdapter {
    private String methodName;

    public ExitingAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String methodName) {
        super(api, methodVisitor, access, name, descriptor);
        this.methodName = methodName;
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        if (opcode == ATHROW) {
            mv.visitLdcInsn("Exiting on exception " + methodName);
        } else {
            mv.visitLdcInsn("Exiting " + methodName);
        }
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }
}
