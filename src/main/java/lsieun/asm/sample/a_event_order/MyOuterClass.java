package lsieun.asm.sample.a_event_order;

import java.io.Serializable;

@Deprecated
public class MyOuterClass extends Exception implements Serializable, Cloneable {
    private int intValue;

    public void test() {

    }

    class MyInnerClass {
        //
    }
}
