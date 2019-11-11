package lsieun.asm.core.example.b;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.V1_5;

import org.objectweb.asm.ClassWriter;

public class BRun {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC+ACC_ABSTRACT+ACC_INTERFACE, "pkg/Comparable",
                null, "java/lang/Object", new String[]{"java/io/Serializable"});
        cw.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC+ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I",
                null, null).visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class c = myClassLoader.defineClass("pkg.Comparable", bytes);
        System.out.println(c);
    }
}
