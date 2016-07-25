/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 *
 * @author murayamakenichirou
 */
public class ObjectsTest {
    private Integer a = new Integer(1);
    private Integer b = new Integer(1);
    private Integer c = new Integer(2);
    @Test
    public void compare() throws Exception {
        System.out.println(Objects.compare(a, b, Comparator.naturalOrder()));//0
        System.out.println(Objects.compare(a, c, Comparator.naturalOrder()));//-1
        System.out.println(Objects.compare(c, a, Comparator.naturalOrder()));//1
    }
    @Test
    public void deepEquals() throws Exception {
        System.out.println(Objects.equals(new Integer[]{a}, new Integer[]{a}));//false
        System.out.println(Objects.deepEquals(new Integer[]{a}, new Integer[]{a}));//true
    }
    @Test
    public void equals() throws Exception {
        System.out.println(a==b);//false
        System.out.println(a==c);//false
        System.out.println(Objects.equals(a, b));//true
        System.out.println(Objects.equals(a, c));//false
    }
    @Test
    public void hash() throws Exception {
        System.out.println(Objects.hash(a, a));//993
        System.out.println(Objects.hash(a, b));//993
        System.out.println(Objects.hash(a, c));//993
    }
    @Test
    public void hashcode() throws Exception {
        System.out.println(Objects.hashCode(null));//0
        System.out.println(Objects.hashCode(a));//1
        System.out.println(Objects.hashCode(b));//1
        System.out.println(Objects.hashCode(c));//2
    }
    @Test
    public void tostring() throws Exception {
        System.out.println(Objects.toString(null));
        System.out.println(Objects.toString(a));
        System.out.println(Objects.toString(new Integer[]{a,b,c}));//ng
        System.out.println(Arrays.toString(new Integer[]{a,b,c}));//ok
    }
}
