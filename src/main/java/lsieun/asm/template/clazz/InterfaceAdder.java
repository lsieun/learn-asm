package lsieun.asm.template.clazz;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassVisitor;

import lsieun.asm.template.ClassAdapter;

public class InterfaceAdder extends ClassAdapter {
    private Set<String> newInterfaces;

    public InterfaceAdder(ClassVisitor classVisitor, Set newInterfaces) {
        super(classVisitor);
        this.newInterfaces = newInterfaces;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        Set<String> ints = new HashSet(newInterfaces);
        ints.addAll(Arrays.asList(interfaces));
        super.visit(version, access, name, signature, superName, (String[]) ints.toArray());
    }
}
