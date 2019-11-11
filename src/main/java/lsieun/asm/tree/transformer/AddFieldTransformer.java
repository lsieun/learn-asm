package lsieun.asm.tree.transformer;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class AddFieldTransformer extends ClassTransformer {
    private int fieldAccess;
    private String fieldName;
    private String fieldDesc;

    public AddFieldTransformer(ClassTransformer ct, int fieldAccess, String fieldName, String fieldDesc) {
        super(ct);
        this.fieldAccess = fieldAccess;
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }

    @Override
    public void transform(ClassNode cn) {
        boolean isPresent = false;
        for (FieldNode fn : cn.fields) {
            if (fieldName.equals(fn.name)) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent) {
            cn.fields.add(new FieldNode(fieldAccess, fieldName, fieldDesc, null, null));
        }
        super.transform(cn);
    }
}
