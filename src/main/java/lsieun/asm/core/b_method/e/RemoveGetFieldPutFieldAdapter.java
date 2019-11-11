package lsieun.asm.core.b_method.e;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.PUTFIELD;

import org.objectweb.asm.MethodVisitor;

import lsieun.asm.core.b_method.d.PatternMethodAdapter;

public class RemoveGetFieldPutFieldAdapter extends PatternMethodAdapter {
    private final static int SEEN_ALOAD_0 = 1;
    private final static int SEEN_ALOAD_0ALOAD_0 = 2;
    private final static int SEEN_ALOAD_0ALOAD_0GETFIELD = 3;

    private String fieldOwner;
    private String fieldName;
    private String fieldDesc;

    public RemoveGetFieldPutFieldAdapter(MethodVisitor methodVisitor) {
        super(ASM4, methodVisitor);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        switch (state) {
            case SEEN_NOTHING:
                if (opcode == ALOAD && var == 0) {
                    state = SEEN_ALOAD_0;
                    return;
                }
                break;
            case SEEN_ALOAD_0:
                if (opcode == ALOAD && var == 0) {
                    state = SEEN_ALOAD_0ALOAD_0;
                    return;
                }
                break;
            case SEEN_ALOAD_0ALOAD_0:
                if (opcode == ALOAD && var == 0) {
                    mv.visitVarInsn(opcode, var);
                    return;
                }
                break;
        }
        visitInsn();
        mv.visitVarInsn(opcode, var);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (state) {
            case SEEN_ALOAD_0ALOAD_0:
                if (opcode == GETFIELD) {
                    state = SEEN_ALOAD_0ALOAD_0GETFIELD;
                    fieldOwner = owner;
                    fieldName = name;
                    fieldDesc = descriptor;
                    return;
                }
                break;
            case SEEN_ALOAD_0ALOAD_0GETFIELD:
                if (opcode == PUTFIELD && name.equals(fieldName)) {
                    state = SEEN_NOTHING;
                    return;
                }
                break;
        }
        visitInsn();
        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }

    @Override
    protected void visitInsn() {
        switch (state) {
            case SEEN_ALOAD_0:
                mv.visitVarInsn(ALOAD, 0);
                break;
            case SEEN_ALOAD_0ALOAD_0:
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(ALOAD, 0);
                break;
            case SEEN_ALOAD_0ALOAD_0GETFIELD:
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, fieldOwner, fieldName, fieldDesc);
                break;
        }
        state = SEEN_NOTHING;
    }
}
