package lsieun.asm.core.b_method.b;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.NOP;

import org.objectweb.asm.MethodVisitor;

public class RemoveNopAdapter extends MethodVisitor {

    public RemoveNopAdapter(MethodVisitor methodVisitor) {
        super(ASM4, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode != NOP) {
            mv.visitInsn(opcode);
        }
    }
}
