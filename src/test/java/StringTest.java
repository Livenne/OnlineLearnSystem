import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    public void test1() {
        String str = "abc.a.a.p.png";
        System.out.println(str.substring(str.lastIndexOf('.')));
    }
}
