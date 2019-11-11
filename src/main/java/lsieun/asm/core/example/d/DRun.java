package lsieun.asm.core.example.d;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import lsieun.asm.core.example.c.CRun;
import lsieun.utils.io.FileUtils;

@SuppressWarnings("Duplicates")
public class DRun {
    public static void main(String[] args) {
        String className = "lsieun.asm.sample.MyOuterClass";
        String filepath = FileUtils.getFilePath(CRun.class, className);
        System.out.println(filepath);
        byte[] b1 = FileUtils.readBytes(filepath);

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(b1);

        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(cr, 0);

        //（3）串连ClassVisitor
        // cv forwards all events to cw
        //ClassVisitor cv = new RemoveDebugAdapter(ASM4, cw);
        //ClassVisitor cv = new RemoveMethodAdapter(cw, "test", "()V");
        ClassVisitor cv = new AddFieldAdapter(cw, Opcodes.ACC_PRIVATE, "intValue", "I");

        //（4）两者进行结合
        cr.accept(cv, 0);

        //（5）重新生成Class
        byte[] b2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, b2);
    }
}
