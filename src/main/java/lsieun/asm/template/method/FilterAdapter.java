package lsieun.asm.template.method;

import static org.objectweb.asm.Opcodes.ASM6;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import lsieun.asm.template.ClassAdapter;

public class FilterAdapter extends ClassAdapter {
    public FilterAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (isFooMethod(name, descriptor)) {
            mv = new FooMethodAdapter(mv, name, descriptor);
        }
        return mv;
    }

    private boolean isFooMethod(String name, String desc) {
        return true;
    }

    class FooMethodAdapter extends MethodVisitor {
        private String name;
        private String descriptor;

        public FooMethodAdapter(MethodVisitor methodVisitor, String name, String descriptor) {
            super(ASM6, methodVisitor);
            this.name = name;
            this.descriptor = descriptor;
        }
    }
}
