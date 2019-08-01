package lsieun.jmx;

import java.lang.reflect.Field;

public class Game implements GameMBean{
    @Override
    public String display(String fqcn, String staticField) {
        try {
            Class clazz = Class.forName(fqcn);
            Field field = clazz.getDeclaredField(staticField);
            Object value = field.get(null);
            String str = String.valueOf(value);
            System.out.println("value = " + value);
            return str;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
