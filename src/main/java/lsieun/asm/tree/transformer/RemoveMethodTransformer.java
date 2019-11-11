package lsieun.asm.tree.transformer;

import java.util.Iterator;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class RemoveMethodTransformer extends ClassTransformer {
    private String methodName;
    private String methodDesc;

    public RemoveMethodTransformer(ClassTransformer ct, String methodName, String methodDesc) {
        super(ct);
        this.methodName = methodName;
        this.methodDesc = methodDesc;
    }

    @Override
    public void transform(ClassNode cn) {
        Iterator<MethodNode> it = cn.methods.iterator();
        while(it.hasNext()) {
            MethodNode mn = it.next();
            if (methodName.equals(mn.name) && methodDesc.equals(mn.desc)) {
                it.remove();
            }
        }
        super.transform(cn);
    }
}
