import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livenne.BaseMapper;
import com.livenne.utils.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORMTest {

    interface BaseMpr<T>{

    }

    class TTTT{}

    interface Tess extends BaseMpr<TTTT>{

    }

    @Test
    public void strObj() throws JsonProcessingException {
        String str = "Bearer asdfasdfas";
        System.out.println(str.substring("Bearer ".length()));
    }

    @Test
    public void t2() {
        System.out.println(true);
        System.out.println(Integer.class.isAssignableFrom(int.class));
    }

    @Test
    public void t3() {
        isPrimitive(String.class);
        isPrimitive(String[].class);
        isPrimitive(Long.class);
        isPrimitive(Double.class);
        isPrimitive(Float.class);
        isPrimitive(Integer.class);
        isPrimitive(Short.class);
        isPrimitive(Byte.class);
        isPrimitive(Character.class);
        isPrimitive(Boolean.class);
        isPrimitive(Void.class);
        isPrimitive(long.class);
        isPrimitive(double.class);
        isPrimitive(float.class);
        isPrimitive(int.class);
        isPrimitive(short.class);
        isPrimitive(byte.class);
        isPrimitive(char.class);
        isPrimitive(boolean.class);
        isPrimitive(void.class);
    }

    private void isPrimitive(Class<?> clazz) {
        System.out.println(clazz.getSimpleName() + ": " + isBasicType(clazz));
    }

    public static boolean isBasicType(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Double.class ||
                clazz == Float.class ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == String.class ||
                clazz == Void.class;
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/dev";
//        String user = "root";
//        String password = "root";
//        Connection conn = DriverManager.getConnection(url, user, password);
//        String sql = "select * from User";
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        ResultSet rs = pstmt.executeQuery();
//
//        Class<?> type = User.class;
//
//        List<Object> list = new ArrayList<>();
//        while (rs.next()){
//            Object obj = type.getConstructor().newInstance();
//            Field[] fields = type.getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                field.set(obj,rs.getObject(field.getName()));
//            }
//            list.add(obj);
//        }
//
//        String json = StringUtils.toJson(list);
//        System.out.println(json);

        Method method = ORMTest.class.getMethods()[0];
        System.out.println(method.getGenericReturnType());


    }

    public List<Class<?>> method() {
        return null;
    }

    public Object toObj(ResultSet rs, Class<?> type) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        if (type.isPrimitive()) {
            return rs.getObject(1);
        }

        Method method;

        while (rs.next()) {
            Object obj = type.getConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, rs.getObject(field.getName()));
            }
            list.add(obj);
        }
        return list;
    }
}
