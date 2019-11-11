package lsieun.asm.core.b_method.g;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import lsieun.utils.io.FileUtils;

public class G_StatelessTransformation {
    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(0);
        AddTimerClassAdapter6 cv = new AddTimerClassAdapter6(cw);

        String className = "lsieun.asm.core.b_method.g.G";
        ClassReader cr = new ClassReader(className);
        cr.accept(cv, 0);

        byte[] bytes = cw.toByteArray();

        String filepath = FileUtils.getFilePath(G_StatelessTransformation.class, className);
        System.out.println("file://" + filepath);
        FileUtils.writeBytes(filepath, bytes);

    }
}
