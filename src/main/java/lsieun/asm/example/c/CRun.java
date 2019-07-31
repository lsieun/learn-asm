package lsieun.asm.example.c;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

@SuppressWarnings("Duplicates")
public class CRun {
    public static void main(String[] args) {
        String className = "lsieun.asm.sample.HelloWorld";
        String filepath = FileUtils.getFilePath(CRun.class, className);
        System.out.println(filepath);
        byte[] b1 = FileUtils.readBytes(filepath);

        //（1）构建ClassReader
        ClassReader cr = new ClassReader(b1);

        //（2）构建ClassWriter
        ClassWriter cw = new ClassWriter(cr, 0);

        //（3）串连ClassVisitor
        // cv forwards all events to cw
        ClassVisitor cv = new ChangeVersionAdapter(ASM4, cw);

        //（4）两者进行结合
        cr.accept(cv, 0);

        //（5）重新生成Class
        byte[] b2 = cw.toByteArray();

        FileUtils.writeBytes(filepath, b2);
    }
}
