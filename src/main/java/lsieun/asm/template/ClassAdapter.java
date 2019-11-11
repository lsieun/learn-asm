package lsieun.asm.template;

import static org.objectweb.asm.Opcodes.ASM6;

import org.objectweb.asm.ClassVisitor;

public class ClassAdapter extends ClassVisitor {

    public ClassAdapter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

}
