package lsieun.asm.core.b_method.f;

import static org.objectweb.asm.Opcodes.FMUL;
import static org.objectweb.asm.Opcodes.IMUL;

import org.objectweb.asm.Type;

public class TypeTest {
    public static void main(String[] args) {
        Type t = Type.FLOAT_TYPE;
        int opcode = t.getOpcode(IMUL);
        System.out.println(opcode == FMUL);

        // java.lang.UnsupportedOperationException
//        Type ts = Type.getType(String.class);
//        int opcode_str = ts.getOpcode(IMUL);
//        System.out.println(opcode_str);
    }
}
