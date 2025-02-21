package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public final class ReflectionUtils {
    private static final Set<Class<?>> BASIC_IMMUTABLE_CLASSES = Set.of(String.class, Boolean.class, Character.class,
            Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Enum.class,
            LocalDate.class, LocalTime.class, LocalDateTime.class, Instant.class, BigInteger.class, BigDecimal.class);

    private ReflectionUtils() {
    }

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

    public static boolean isBasicImmutableType(Class<?> objectClass) {
        return objectClass.isPrimitive() || BASIC_IMMUTABLE_CLASSES.contains(objectClass);
    }
}
