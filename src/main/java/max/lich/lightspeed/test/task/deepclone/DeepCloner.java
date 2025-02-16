package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class DeepCloner {
    public <T> T deepClone(T t) {
        Class<?> aClass = t.getClass();

        Constructor<?> defaultConstructor = getDefaultConstructor(aClass);
        T newInstance = createNewInstance(defaultConstructor);

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = getFieldValue(t, field);
            setFieldValue(field, newInstance, fieldValue);
        }

        return newInstance;
    }

    private <T> void setFieldValue(Field field, T newInstance, Object o) {
        try {
            field.set(newInstance, o);
        } catch (IllegalAccessException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    private <T> Object getFieldValue(T t, Field field) {
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    private <T> T createNewInstance(Constructor<?> defaultConstructor) {
        try {
            T newInstance = (T) defaultConstructor.newInstance();
            return newInstance;
        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> getDefaultConstructor(Class<?> aClass) {
        try {
            return aClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            System.err.println("The class does not contain a default constructor");
            throw new RuntimeException(e);
        }
    }

}
