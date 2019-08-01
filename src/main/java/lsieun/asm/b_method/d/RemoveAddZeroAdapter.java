package lsieun.asm.b_method.d;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_0;

import org.objectweb.asm.MethodVisitor;

public class RemoveAddZeroAdapter extends PatternMethodAdapter {
    private static int SEEN_ICONST_0 = 1;

    public RemoveAddZeroAdapter(MethodVisitor mv) {
        super(ASM4, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        if (state == SEEN_ICONST_0) {
            if (opcode == IADD) {
                state = SEEN_NOTHING;
                return;
            }
        }
        visitInsn();
        if (opcode == ICONST_0) {
            state = SEEN_ICONST_0;
            return;
        }
        mv.visitInsn(opcode);
    }

    @Override
    protected void visitInsn() {
        if (state == SEEN_ICONST_0) {
            mv.visitInsn(ICONST_0);
        }
        state = SEEN_NOTHING;
    }
}
