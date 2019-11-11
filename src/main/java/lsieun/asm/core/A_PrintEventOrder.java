package lsieun.asm.core;

import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

public class A_PrintEventOrder {
    public static void main(String[] args) throws IOException {
        String class_path = "/lsieun/asm/sample/a_event_order/MyOuterClass.class";
        String inner_class_path = "/lsieun/asm/sample/a_event_order/MyOuterClass$MyInnerClass.class";
        InputStream in = A_PrintEventOrder.class.getResourceAsStream(class_path);
        ClassReader cr = new ClassReader(in);

        EventOrderAdapter visitor = new EventOrderAdapter(null);
        cr.accept(visitor, 0);

    }
}
