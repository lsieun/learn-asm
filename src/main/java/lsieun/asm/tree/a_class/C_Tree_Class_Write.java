package lsieun.asm.tree.a_class;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class C_Tree_Class_Write {
    public static void main(String[] args) {
        ClassNode cn = null; // should not null

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);

        byte[] bytes = cw.toByteArray();
    }
}
