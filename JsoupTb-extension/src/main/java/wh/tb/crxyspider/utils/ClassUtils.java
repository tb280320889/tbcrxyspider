package wh.tb.crxyspider.utils;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 280320889@qq.com
 * @since 1.0
 */
public abstract class ClassUtils {

    public static Set<Field> getFieldsIncludeSuperClass(Class clazz) {
        Set<Field> fields = new LinkedHashSet<Field>();
        Class current = clazz;
        while (current != null) {
            Field[] currentFields = current.getDeclaredFields();
            for (Field currentField : currentFields) {
                fields.add(currentField);
            }
            current = current.getSuperclass();
        }
        return fields;
    }

}
