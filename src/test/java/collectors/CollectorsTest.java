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

    /**
     * collectした結果に対してさらにFunction操作が可能
     */
    @Test
    public void collectingAndThen() throws Exception {
        int avr = fewfriends().collect(Collectors.collectingAndThen(
                Collectors.averagingLong(Friend::getAge), Double::intValue));
        System.out.println(avr);
    }

    @Test
    public void counting() throws Exception {
        Long cnt = fewfriends().filter(friend -> friend.getSex() == Sex.FEMALE)
                .collect(Collectors.counting());
        System.out.println(cnt);
        // same as
        cnt = fewfriends().filter(friend -> friend.getSex() == Sex.FEMALE)
                .collect(Collectors.reducing(0L, e -> 1L, Long::sum));
    }

    // TODO add more groupingBy and groupingByConcurrent
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

    @Test
    public void joining() throws Exception {
        String s = fewfriends().map(Friend::getName)
                .collect(Collectors.joining());
        System.out.println(s);
    }

    @Test
    public void joining_delimiter() throws Exception {
        String s = fewfriends().map(Friend::getName)
                .collect(Collectors.joining(" : "));
        System.out.println(s);
    }

    @Test
    public void joining_delimiter_prefix_suffix() throws Exception {
        String s = fewfriends().map(Friend::getName)
                .collect(Collectors.joining(" : ", "my friends [", "]"));
        System.out.println(s);
    }

    @Test
    public void mapping() throws Exception {
        List<String> names = fewfriends().collect(Collectors.mapping(Friend::getName, Collectors.toList()));
        names.forEach(System.out::println);
        names.clear();
        // same as
        names = fewfriends().map(Friend::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
        // using with groupingBy
        Map<Sex, List<String>> m = fewfriends()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }
}
