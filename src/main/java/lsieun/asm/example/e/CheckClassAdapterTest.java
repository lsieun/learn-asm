package lsieun.asm.example.e;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.V1_5;

import java.io.PrintWriter;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;
import org.objectweb.asm.util.TraceClassVisitor;

@SuppressWarnings("Duplicates")
public class CheckClassAdapterTest {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);

        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor tcv = new TraceClassVisitor(cw, printWriter);
        CheckClassAdapter cv = new CheckClassAdapter(tcv);
        cv.visit(V1_5, ACC_PUBLIC+ACC_ABSTRACT+ACC_INTERFACE, "pkg/Comparable",
                null, "java/lang/Object", new String[]{"java/io/Serializable"});
        cv.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cv.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cv.visitField(ACC_PUBLIC+ACC_FINAL+ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cv.visitMethod(ACC_PUBLIC+ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I",
                null, null).visitEnd();
        cv.visitEnd();
        byte[] bytes = cw.toByteArray();
    }
}
