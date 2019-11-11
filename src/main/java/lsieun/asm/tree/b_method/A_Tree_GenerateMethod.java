package lsieun.asm.tree.b_method;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import lsieun.utils.io.FileUtils;

public class A_Tree_GenerateMethod {
    public static void main(String[] args) {
        ClassNode cn = new ClassNode(ASM6);
        cn.version = V1_6;
        cn.access = ACC_PUBLIC + ACC_SUPER;
        cn.name = "pkg/Bean";
        cn.superName = "java/lang/Object";

        cn.fields.add(new FieldNode(ACC_PRIVATE, "f", "I", null, null));

        MethodNode mn = new MethodNode(ACC_PUBLIC, "checkAndSetF", "(I)V", null, null);
        InsnList il = mn.instructions;
        il.add(new VarInsnNode(ILOAD, 1));
        LabelNode label = new LabelNode();
        il.add(new JumpInsnNode(IFLT, label));
        il.add(new VarInsnNode(ALOAD, 0));
        il.add(new VarInsnNode(ILOAD, 1));
        il.add(new FieldInsnNode(PUTFIELD, "pkg/Bean", "f", "I"));
        LabelNode end = new LabelNode();
        il.add(new JumpInsnNode(GOTO, end));
        il.add(label);
        il.add(new FrameNode(F_SAME, 0, null, 0, null));
        il.add(new TypeInsnNode(NEW, "java/lang/IllegalArgumentException"));
        il.add(new InsnNode(DUP));
        il.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "()V", false));
        il.add(new InsnNode(ATHROW));
        il.add(end);
        il.add(new FrameNode(F_SAME, 0, null, 0, null));
        il.add(new InsnNode(RETURN));
        mn.maxStack = 2;
        mn.maxLocals = 2;

        cn.methods.add(mn);

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        String className = "pkg.Bean";
        String filepath = FileUtils.getFilePath(FileUtils.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);
    }
}
