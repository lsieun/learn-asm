package lsieun.asm.b_method.d;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

public class D_StatefullTransformation {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        RemoveAddZeroClassAdapter cv = new RemoveAddZeroClassAdapter(cw);

        String className = "lsieun.asm.b_method.d.D";
        ClassReader cr = new ClassReader(className);
        cr.accept(cv, 0);

        byte[] bytes = cw.toByteArray();

        String filepath = FileUtils.getFilePath(D_StatefullTransformation.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);

    }
}
