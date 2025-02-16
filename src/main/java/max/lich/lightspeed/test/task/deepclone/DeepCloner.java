package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class DeepCloner {
    public <T> T deepClone(T t) {
        Class<?> aClass = t.getClass();
        return createClone(t, aClass);
    }

    private <T> T createClone(T t, Class<?> aClass) {
        Constructor<?> defaultConstructor = ReflectionUtils.getDefaultConstructor(aClass);
        T newInstance = ReflectionUtils.createNewInstance(defaultConstructor);

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = ReflectionUtils.getFieldValue(t, field);
            ReflectionUtils.setFieldValue(field, newInstance, fieldValue);
        }

        return newInstance;
    }

}
