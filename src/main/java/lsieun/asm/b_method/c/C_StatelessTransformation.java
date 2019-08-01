package lsieun.asm.b_method.c;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

public class C_StatelessTransformation {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        AddTimerClassAdapter cv = new AddTimerClassAdapter(cw);

        String className = "lsieun.asm.b_method.c.C";
        ClassReader cr = new ClassReader(className);
        cr.accept(cv, 0);

        byte[] bytes = cw.toByteArray();

        String filepath = FileUtils.getFilePath(C_StatelessTransformation.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);

    }
}
