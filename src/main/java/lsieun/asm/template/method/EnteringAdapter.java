package lsieun.asm.template.method;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class EnteringAdapter extends AdviceAdapter {
    private String methodName;
    private int timeVar;
    private Label timeVarStart = new Label();
    private Label timeVarEnd = new Label();

    public EnteringAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor, String methodName) {
        super(ASM6, methodVisitor, access, name, descriptor);
        this.methodName = methodName;
    }

    @Override
    protected void onMethodEnter() {
        visitLabel(timeVarStart);
        int timeVar = newLocal(Type.getType("J"));
        visitLocalVariable("timeVar", "J", null, timeVarStart, timeVarEnd, timeVar);
        super.visitFieldInsn(GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        super.visitLdcInsn("Entering " + methodName);
        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        visitLabel(timeVarEnd);
        super.visitMaxs(maxStack, maxLocals);
    }
}
