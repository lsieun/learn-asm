package lsieun.asm.tree.transformer;

import java.util.ListIterator;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class AddTimerTransformer extends ClassTransformer {
    public AddTimerTransformer(ClassTransformer ct) {
        super(ct);
    }

    @Override
    public void transform(ClassNode cn) {
        for (MethodNode mn : cn.methods) {
            if ("<init>".equals(mn.name) || "<clinit>".equals(mn.name)) {
                continue;
            }
            InsnList insns = mn.instructions;
            if (insns.size() == 0) {
                continue;
            }
            ListIterator<AbstractInsnNode> it = insns.iterator();
            while (it.hasNext()) {
                AbstractInsnNode insnNode = it.next();
                int opcode = insnNode.getOpcode();
                if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                    InsnList il = new InsnList();
                    il.add(new FieldInsnNode(GETSTATIC, cn.name, "timer", "J"));
                    il.add(new MethodInsnNode(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false));
                    il.add(new InsnNode(LADD));
                    il.add(new FieldInsnNode(PUTSTATIC, cn.name, "timer", "J"));
                    insns.insert(insnNode.getPrevious(), il);
                }
            }

            InsnList il = new InsnList();
            il.add(new FieldInsnNode(GETSTATIC, cn.name, "timer", "J"));
            il.add(new MethodInsnNode(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false));
            il.add(new InsnNode(LSUB));
            insns.insert(il);
            mn.maxStack += 4;
        }
        int acc = ACC_PUBLIC + ACC_STATIC;
        cn.fields.add(new FieldNode(acc, "timer", "J", null, null));
        super.transform(cn);
    }
}
