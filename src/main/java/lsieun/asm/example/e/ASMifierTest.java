package lsieun.asm.example.e;

import java.io.IOException;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;

public class ASMifierTest {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        ASMifier.main(new String[] {"lsieun.asm.example.a.ARun"});

        // 下面的代码是来源于{@code org.objectweb.asm.util.Printer#main}
        int parsingOptions = 0;
        String className = "lsieun.asm.sample.HelloWorld";
        Printer printer = new ASMifier();
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, printer, new PrintWriter(System.out));
        new ClassReader(className).accept(traceClassVisitor, parsingOptions);
    }
}
