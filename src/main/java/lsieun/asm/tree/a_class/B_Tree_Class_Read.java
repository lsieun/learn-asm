package lsieun.asm.tree.a_class;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class B_Tree_Class_Read {
    public static void main(String[] args) throws IOException {
        // 主要部分
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader("lsieun.asm.sample.MyOuterClass");
        cr.accept(cn, 0);

        // 不重要部分：显示内容
        System.out.println("=== === ===  === === ===  === === ===");
        System.out.println(String.format("%s extends %s", cn.name, cn.superName));
        System.out.println("Fields:");
        for (int i=0; i<cn.fields.size(); i++) {
            FieldNode fn = cn.fields.get(i);
            System.out.println(String.format("    %s", fn.name));
        }
        System.out.println("Methods:");
        for (int i=0; i<cn.methods.size(); i++) {
            MethodNode mn = cn.methods.get(i);
            System.out.println(String.format("    %s", mn.name));
        }
    }
}
