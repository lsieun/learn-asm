package lsieun.asm.b_method.e;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

public class E_StatefullTransformation {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        RemoveGetFieldPutFieldClassAdapter cv = new RemoveGetFieldPutFieldClassAdapter(cw);

        String className = "lsieun.asm.b_method.e.E";
        ClassReader cr = new ClassReader(className);
        cr.accept(cv, 0);

        byte[] bytes = cw.toByteArray();

        String filepath = FileUtils.getFilePath(E_StatefullTransformation.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);

    }
}
