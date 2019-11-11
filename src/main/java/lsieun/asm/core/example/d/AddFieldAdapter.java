package lsieun.asm.core.example.d;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

public class AddFieldAdapter extends ClassVisitor {
    private int fieldAccess;
    private String fieldName;
    private String fieldDesc;
    private boolean isFieldPresent;

    public AddFieldAdapter(ClassVisitor classVisitor, int fieldAccess, String fieldName, String fieldDesc) {
        super(Opcodes.ASM6, classVisitor);
        this.fieldAccess = fieldAccess;
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if(name.equals(fieldName)) {
            isFieldPresent = true;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        if(!isFieldPresent) {
            FieldVisitor fv = cv.visitField(fieldAccess, fieldName, fieldDesc, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }
}
