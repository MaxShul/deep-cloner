package max.lich.lightspeed.test.task.deepclone;

import java.lang.reflect.Field;

public class DeepCloner {
    public <T> T deepClone(T t) {
        Class<?> aClass = t.getClass();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }


        return null;
    }

}
