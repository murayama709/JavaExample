package collectors;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;

import stream.Base;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class CollectorsTest extends Base {

    @Test
    public void averagingDouble() throws Exception {
        Double avr = fewfriends().collect(Collectors.averagingDouble(Friend::getShoeSize));
        System.out.println(avr);
        // same as
        avr = fewfriends().mapToDouble(Friend::getShoeSize).average().orElse(0);
        System.out.println(avr);
    }

    @Test
    public void averagingInt() throws Exception {
        Double avr = fewfriends().collect(Collectors.averagingInt(Friend::getAge));
        System.out.println(avr);
       // same as
        avr = fewfriends().mapToInt(Friend::getAge).average().orElse(0);
        System.out.println(avr);
    }

    @Test
    public void averagingLong() throws Exception {
        Double avr = fewfriends().collect(Collectors.averagingLong(Friend::getAge));
        System.out.println(avr);
       // same as
        avr = fewfriends().mapToLong(Friend::getAge).average().orElse(0);
        System.out.println(avr);
    }

    @Test
    public void groupingBy() throws Exception {
        Map<Sex, List<Friend>> m = friends()
                .collect(Collectors.groupingBy(Friend::getSex));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_downstream() throws Exception {
        Map<Sex, List<Friend>> m = friends()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.toList()));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_mapping() throws Exception {
        Map<Sex, List<String>> m = friends()
                .collect(Collectors.groupingBy(Friend::getSex, 
                        Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }
}
