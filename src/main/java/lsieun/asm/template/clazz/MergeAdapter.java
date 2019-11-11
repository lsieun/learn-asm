package lsieun.asm.template.clazz;

import java.util.Iterator;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingMethodAdapter;
import org.objectweb.asm.commons.SimpleRemapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import lsieun.asm.template.ClassAdapter;

public class MergeAdapter extends ClassAdapter {
    private ClassNode cn;
    private String className;

    public MergeAdapter(ClassVisitor classVisitor, ClassNode cn) {
        super(classVisitor);
        this.cn = cn;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        Iterator<FieldNode> fieldIterator = cn.fields.iterator();
        while (fieldIterator.hasNext()) {
            FieldNode fieldNode = fieldIterator.next();
            fieldNode.accept(this);
        }

        Iterator<MethodNode> methodIterator = cn.methods.iterator();
        while (methodIterator.hasNext()) {
            MethodNode methodNode = methodIterator.next();
            String[] exceptions = new String[methodNode.exceptions.size()];
            methodNode.exceptions.toArray(exceptions);
            MethodVisitor mv = cv.visitMethod(methodNode.access, methodNode.name, methodNode.desc,
                    methodNode.signature, exceptions);
            methodNode.instructions.resetLabels();
            methodNode.accept(new RemappingMethodAdapter(
                    methodNode.access, methodNode.desc, mv,
                    new SimpleRemapper(className, cn.name)
            ));
        }
        super.visitEnd();
    }
}
