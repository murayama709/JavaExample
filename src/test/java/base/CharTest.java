package base;

import org.junit.Test;

public class CharTest {

    @Test
    public void create() {
        char c1 = 'A';
        char c2 = 0x0041;
        char c3 = '\u0041';
        char c4 = 65;
        System.out.println(c1);//A
        System.out.println(c2);//A
        System.out.println(c3);//A
        System.out.println(c4);//A
        char c5 = '\t';
        char c6 = '\n';
        char c7 = '\"';
        char c8 = '\'';
        char c9 = '\\';
        System.out.println(c5);//tab
        System.out.println(c6);//new line
        System.out.println(c7);//double quotes
        System.out.println(c8);//single quotes
        System.out.println(c9);//en
    }

    @Test
    public void equal() {
        char c1 = 'A';
        System.out.println(c1 == 'A');//true
//        System.out.println(c1 == "A");//error
        System.out.println("A".equals(c1));//false
    }

    @Test
    public void convert() {
        // to String
        char c = 'A';
        String s = String.valueOf(c);
        System.out.println(s);
        char[] a = new char[] {'A', 'B', 'C'};
        s = String.valueOf(a);
        System.out.println(s);
        // int <=> char
        int i = c;
//        c = i; can not do this
        c = (char) i;
        System.out.println(c);
        char[] cc = Character.toChars(i);
        System.out.println(cc[0]);
    }

    @Test
    public void stream() {
        String s = "ABC";
        s.chars().forEach(System.out::print);//656667
        System.out.println();
        s.chars().forEach(c -> System.out.print(Character.toChars(c)));//ABC
    }
}
