package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionUtils {
    public static Constructor<?> getDefaultConstructor(Class<?> aClass) {
        try {
            return aClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            System.err.println("The class " + aClass.getName() + " does not contain a default constructor");
            throw new RuntimeException(e);
        }
    }

    static <T> void setFieldValue(Field field, T newInstance, Object o) {
        try {
            field.set(newInstance, o);
        } catch (IllegalAccessException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    static  <T> Object getFieldValue(T t, Field field) {
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    static  <T> T createNewInstance(Constructor<?> defaultConstructor) {
        try {
            return (T) defaultConstructor.newInstance();
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }
}
