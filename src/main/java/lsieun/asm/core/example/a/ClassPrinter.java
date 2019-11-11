package lsieun.asm.core.example.a;

import static org.objectweb.asm.Opcodes.ASM4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    @Override
    public void visitSource(String source, String debug) {
        // do nothing
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        // do nothing
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        // do nothing
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        // do nothing
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("    " + descriptor + " " + name);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("    " + name + " " + descriptor);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
    }
}
