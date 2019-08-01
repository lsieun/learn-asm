package lsieun.asm.b_method.b;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

public class B_TransformMethod {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        RemoveNopClassAdapter cv = new RemoveNopClassAdapter(cw);

        ClassReader cr = new ClassReader("pkg.Bean");
        cr.accept(cv, 0);

        byte[] bytes = cw.toByteArray();

        String className = "pkg.Bean2";
        String filepath = FileUtils.getFilePath(B_TransformMethod.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);
    }
}
