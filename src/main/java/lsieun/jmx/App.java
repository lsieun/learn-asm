package lsieun.jmx;

import java.lang.management.ManagementFactory;

import javax.management.*;

import lsieun.asm.core.b_method.c.C;

public class App {
    public static long staticField = 10;

    public static void main(String[] args) {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            Game gameObj = new Game();

            ObjectName objectName = new ObjectName("lsieun.jmx.config:type=basic,name=game");
            server.registerMBean(gameObj, objectName);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }

        System.out.println("Registration for Game mbean with the platform server is successfull");
        System.out.println("Please open jconsole to access Game mbean");

        C c = new C();

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                c.m();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //game.display("lsieun.asm.core.b_method.c.C", "timer");
        }
    }
}
