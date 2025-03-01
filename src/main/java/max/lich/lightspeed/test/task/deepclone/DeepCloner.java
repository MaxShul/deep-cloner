package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DeepCloner {
    public <T> T deepClone(T objectToClone) {
        Class<T> aClass = (Class<T>) objectToClone.getClass();
        T newInstance = ReflectionUtils.createNewInstance(aClass);

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
            } else if (fieldValue instanceof Collection<?> valueCollection) {
                fieldValue = deepCloneCollection(valueCollection);
            } else if (fieldValue instanceof Map<?, ?> valueMap) {
                fieldValue = deepCloneMap(valueMap);
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

    private Collection<?> deepCloneCollection(Collection<?> oldCollection) {
        Collection<Object> newCollection = createNewCollection(oldCollection);
        oldCollection.stream()
                .map(this::deepCloneValueIfNeed)
                .forEach(newCollection::add);

        return newCollection;
    }

    private Collection<Object> createNewCollection(Collection<?> collection) {
        if (collection instanceof List<?>) {
            return new ArrayList<>();
        }
        if (collection instanceof SortedSet<?>) {
            return new TreeSet<>();
        }
        if (collection instanceof LinkedHashSet<?>) {
            return new LinkedHashSet<>();
        }
        if (collection instanceof Set<?>) {
            return new HashSet<>();
        }
        if (collection instanceof Deque<?>) {
            return new ArrayDeque<>();
        }

        return ReflectionUtils.createNewInstance(collection.getClass());
    }

    private Object deepCloneMap(Map<?, ?> oldMap) {
        Map<Object, Object> newMap = createNewMap(oldMap);
        oldMap.entrySet().stream()
                .map(entry -> {
                    Object key = deepCloneValueIfNeed(entry.getKey());
                    Object value = deepCloneValueIfNeed(entry.getValue());
                    return new AbstractMap.SimpleEntry<>(key, value);
                })
                .forEach(entry -> newMap.put(entry.getKey(), entry.getValue()));

        return newMap;
    }

    private Map<Object, Object> createNewMap(Object oldMap) {
        if (oldMap instanceof NavigableMap<?,?>) {
            return new TreeMap<>();
        }
        if (oldMap instanceof LinkedHashMap<?,?>) {
            return new LinkedHashMap<>();
        }
        if (oldMap instanceof ConcurrentMap<?,?>) {
            return new ConcurrentHashMap<>();
        }
        return new HashMap<>();
    }
}
