package collectors;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;
import java.util.Comparator;

import stream.Base;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

public class CollectorsTest extends Base {

    @Test
    public void averagingDouble() throws Exception {
        Double avr = friends().collect(Collectors.averagingDouble(Friend::getShoeSize));
        System.out.println(avr);
        // same as
        avr = friends().mapToDouble(Friend::getShoeSize).average().orElse(0);
        System.out.println(avr);
    }

    @Test
    public void averagingInt() throws Exception {
        Double avr = friends().collect(Collectors.averagingInt(Friend::getAge));
        System.out.println(avr);
       // same as
        avr = friends().mapToInt(Friend::getAge).average().orElse(0);
        System.out.println(avr);
    }

    @Test
    public void averagingLong() throws Exception {
        Double avr = friends().collect(Collectors.averagingLong(Friend::getAge));
        System.out.println(avr);
       // same as
        avr = friends().mapToLong(Friend::getAge).average().orElse(0);
        System.out.println(avr);
    }

    /**
     * collectした結果に対してさらにFunction操作が可能
     */
    @Test
    public void collectingAndThen() throws Exception {
        int avr = friends().collect(Collectors.collectingAndThen(
                Collectors.averagingLong(Friend::getAge), Double::intValue));
        System.out.println(avr);
    }

    @Test
    public void counting() throws Exception {
        Long cnt = friends().filter(friend -> friend.getSex() == Sex.FEMALE)
                .collect(Collectors.counting());
        System.out.println(cnt);
        // same as
        cnt = friends().filter(friend -> friend.getSex() == Sex.FEMALE)
                .collect(Collectors.reducing(0L, e -> 1L, Long::sum));
        System.out.println(cnt);
    }

    // TODO add more groupingBy and groupingByConcurrent
    @Test
    public void groupingBy() throws Exception {
        Map<Sex, List<Friend>> m = manyFriends()
                .collect(Collectors.groupingBy(Friend::getSex));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_downstream() throws Exception {
        Map<Sex, List<Friend>> m = manyFriends()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.toList()));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_mapping() throws Exception {
        Map<Sex, List<String>> m = manyFriends()
                .collect(Collectors.groupingBy(Friend::getSex, 
                        Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void joining() throws Exception {
        String s = friends().map(Friend::getName)
                .collect(Collectors.joining());
        System.out.println(s);
    }

    @Test
    public void joining_delimiter() throws Exception {
        String s = friends().map(Friend::getName)
                .collect(Collectors.joining(" : "));
        System.out.println(s);
    }

    @Test
    public void joining_delimiter_prefix_suffix() throws Exception {
        String s = friends().map(Friend::getName)
                .collect(Collectors.joining(" : ", "my friends [", "]"));
        System.out.println(s);
    }

    @Test
    public void mapping() throws Exception {
        List<String> names = friends().collect(Collectors.mapping(Friend::getName, Collectors.toList()));
        names.forEach(System.out::println);
        names.clear();
        // same as
        names = friends().map(Friend::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
        // using with groupingBy
        Map<Sex, List<String>> m = friends()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void maxBy() throws Exception {
        Friend f = friends().collect(Collectors
                .maxBy(Comparator.comparingInt(Friend::getAge))).get();
        System.out.println(f);
    }

    @Test
    public void minBy() throws Exception {
        Double minShoeSize = friends()
                .map(Friend::getShoeSize)
                .collect(Collectors.minBy(Comparator.naturalOrder())).get();
        System.out.println(minShoeSize);
    }

    @Test
    public void partitioningBy() throws Exception {
        Map<Boolean,List<Friend>> m = friends()
                .collect(Collectors.partitioningBy(f -> f.getAge() > 30));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void partitioningBy_downstream() throws Exception {
        Map<Boolean, Set<Integer>> m = friends()
                .map(Friend::getAge).collect(Collectors.partitioningBy(age -> age > 30, Collectors.toSet()));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void reducing() throws Exception {
        Optional<Integer> ageSum = friends().map(Friend::getAge)
                .collect(Collectors.reducing(Integer::max));
        ageSum.ifPresent(System.out::println);
        // almost same as
        OptionalInt ageSumInt = friends().mapToInt(Friend::getAge).max();
        ageSumInt.ifPresent(System.out::println);
    }
}
