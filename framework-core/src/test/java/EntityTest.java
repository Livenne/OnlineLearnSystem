import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityTest {
    class Entity1{
        public String name;
        public int age;

    }
    class Entity2 extends Entity1{
    }
    class Entity3 extends Entity2{
    }
    @Test
    public void Test(){
        Class<?> e1 =  Entity1.class;
        Class<?> e2 = Entity2.class;
        Class<?> e3 = Entity3.class;

        getAllFields(e1).forEach(field -> {
            System.out.println(field.getName());
        });

        System.out.println("=======================");

        getAllFields(e2).forEach(field -> {
            System.out.println(field.getName());
        });

        System.out.println("=======================");

        getAllFields(e3).forEach(field -> {
            System.out.println(field.getName());
        });
    }

    protected List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            Collections.addAll(fields, currentClass.getDeclaredFields());
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }
}
