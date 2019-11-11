package lsieun.asm.core;

import java.util.Arrays;

import org.objectweb.asm.*;

import lsieun.asm.template.ClassAdapter;

public class EventOrderAdapter extends ClassAdapter {

    public EventOrderAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    /**
     * Called when a class is visited. This is the method called first
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("visit(version, access, name, signature, superName, interfaces)");
        System.out.println(String.format("    %s: %s", "version", version));
        System.out.println(String.format("    %s: %s", "name", name));
        System.out.println(String.format("    %s: %s", "superName", superName));
        System.out.println(String.format("    %s: %s", "interfaces", Arrays.toString(interfaces)));
        super.visit(version, access, name, signature, superName, interfaces);
    }


    /**
     * When the optional source is encountered
     */
    @Override
    public void visitSource(String source, String debug) {
        System.out.println("visitSource(source, debug)");
        System.out.println(String.format("    %s: %s", "source", source));
        super.visitSource(source, debug);
    }

    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        System.out.println("visitModule(name, access, version)");
        System.out.println(String.format("    %s: %s", "name", name));
        return super.visitModule(name, access, version);
    }

    /**
     * Invoked only when the class being visited is an inner class
     */
    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        System.out.println("visitOuterClass(owner, name, desc)");
        System.out.println(String.format("    %s: %s", "owner", owner));
        System.out.println(String.format("    %s: %s", "name", name));
        System.out.println(String.format("    %s: %s", "desc", desc));
        super.visitOuterClass(owner, name, desc);
    }

    /**
     * Invoked when a class level annotation is encountered
     */
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("visitAnnotation(desc, visible)");
        System.out.println(String.format("    %s: %s", "desc", desc));
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        System.out.println("visitTypeAnnotation(typeRef, typePath, descriptor, visible)");
        System.out.println(String.format("    %s: %s", "typeRef", typeRef));
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    /**
     * When a class attribute is encountered
     */
    @Override
    public void visitAttribute(Attribute attr) {
        System.out.println("visitAttribute(attr)");
        System.out.println(String.format("    %s: %s", "attr", attr));
        System.out.println(String.format("    %s: %s", "attr.type", attr.type));
        super.visitAttribute(attr);
    }

    /**
     * When an inner class is encountered
     */
    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.println("visitInnerClass(name, outerName, innerName, access)");
        System.out.println(String.format("    %s: %s", "name", name));
        System.out.println(String.format("    %s: %s", "outerName", outerName));
        System.out.println(String.format("    %s: %s", "innerName", innerName));
        super.visitInnerClass(name, outerName, innerName, access);
    }

    /**
     * When a field is encountered
     */
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("visitField(access, name, desc, signature, value)");
        System.out.println(String.format("    %s: %s", "name", name));
        System.out.println(String.format("    %s: %s", "desc", desc));
        return super.visitField(access, name, desc, signature, value);
    }


    /**
     * When a method is encountered
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("visitMethod(access, name, desc, signature, exceptions)");
        System.out.println(String.format("    %s: %s", "name", name));
        System.out.println(String.format("    %s: %s", "desc", desc));
        return super.visitMethod(access, name, desc, signature, exceptions);
    }


    @Override
    public void visitEnd() {
        System.out.println("visitEnd()");
        super.visitEnd();
    }
}
