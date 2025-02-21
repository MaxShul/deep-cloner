package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class DeepCloner {
    public <T> T deepClone(T objectToClone) {
        Class<?> aClass = objectToClone.getClass();
        Constructor<?> defaultConstructor = ReflectionUtils.getDefaultConstructor(aClass);
        T newInstance = ReflectionUtils.createNewInstance(defaultConstructor);

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = ReflectionUtils.getFieldValue(objectToClone, field);
            if (ReflectionUtils.notBasicImmutableType(fieldValue)) {
                if (fieldValue.getClass().isArray() || fieldValue instanceof Collection<?> || fieldValue instanceof Map<?,?>) {
                    //todo
                } else {
                    fieldValue = deepClone(fieldValue);
                }
            }
            ReflectionUtils.setFieldValue(field, newInstance, fieldValue);
        }

        return newInstance;
    }

}
