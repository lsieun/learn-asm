package lsieun.asm.tree.a_class;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import lsieun.utils.io.FileUtils;

public class A_Tree_Class_Generate {
    public static void main(String[] args) {
        ClassNode cn = new ClassNode();
        cn.version = V1_5;
        cn.access = ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE;
        cn.name = "pkg/Comparable";
        cn.superName = "java/lang/Object";
        cn.interfaces.add("pkg/Measurable");
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)));
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)));
        cn.fields.add(new FieldNode(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)));
        cn.methods.add(new MethodNode(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null));

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        String className = "pkg.Comparable";
        String filepath = FileUtils.getFilePath(FileUtils.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);
    }
}
