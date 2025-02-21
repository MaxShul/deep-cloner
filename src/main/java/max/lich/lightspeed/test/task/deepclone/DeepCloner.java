package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Array;
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
            fieldValue = deepCloneValueIfNeed(fieldValue);
            ReflectionUtils.setFieldValue(field, newInstance, fieldValue);
        }

        return newInstance;
    }

    /**
     * Makes a deep clone of {@code fieldValue} if it's not a basic immutable type.
     * Otherwise, it returns {@code fieldValue} without any changes.
     *
     * @param fieldValue value to clone deeply
     * @return deep clone of {@code fieldValue} or {@code fieldValue} if there's nothing to do
     */
    private Object deepCloneValueIfNeed(Object fieldValue) {
        Class<?> fieldValueClass = fieldValue.getClass();
        if (!ReflectionUtils.isBasicImmutableType(fieldValueClass)) {
            if (fieldValueClass.isArray()) {
                fieldValue = deepCloneArray(fieldValue);
            } else if (fieldValue instanceof Collection<?>) {
                //todo
                fieldValue = deepCloneCollection(fieldValue);
            } else if (fieldValue instanceof Map<?, ?>) {
                //todo
                fieldValue = deepCloneMap(fieldValue);
            } else {
                fieldValue = deepClone(fieldValue);
            }
        }
        return fieldValue;
    }



    private Object deepCloneArray(Object array) {
        Class<?> arrayClass = array.getClass();
        int length = Array.getLength(array);
        Class<?> componentType = arrayClass.getComponentType();

        Object copiedArray = Array.newInstance(componentType, length);

        if (ReflectionUtils.isBasicImmutableType(componentType)) {
            System.arraycopy(array, 0, copiedArray, 0, length);
            return copiedArray;
        }

        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            Array.set(copiedArray, i, deepCloneValueIfNeed(element));
        }
        return copiedArray;
    }

    private Object deepCloneCollection(Object fieldValue) {

        return null;
    }

    private Object deepCloneMap(Object fieldValue) {
        return null;
    }
}
