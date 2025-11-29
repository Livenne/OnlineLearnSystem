import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ORMTest {

    @Test
    public void t2(){
        String test = "{abcde}";
        System.out.println(test.substring(1, test.length() - 1));
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

    public Object toObj(ResultSet rs,Class<?> type) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        if (type.isPrimitive()){
            return rs.getObject(1);
        }

        Method method;

        while (rs.next()){
            Object obj = type.getConstructor().newInstance();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj,rs.getObject(field.getName()));
            }
            list.add(obj);
        }
        return list;
    }
}
