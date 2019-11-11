package lsieun.asm.tree.a_class;

import static org.objectweb.asm.Opcodes.ASM4;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class D_Tree_Class_Pattern {
    public static void main(String[] args) throws IOException {
        ClassNode cn = new ClassNode(ASM4);
        ClassReader cr = new ClassReader("xxx.yyy.zzz");
        cr.accept(cn, 0);
        // here transform ClassNode as you want
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();
    }
}
