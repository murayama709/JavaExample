package collectors;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;

import java.util.Comparator;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;

import stream.Base;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
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
        // effective to use as downstream
        Map<Sex, List<String>> m = friends()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void maxBy() throws Exception {
        Optional<Friend> oldestFriend = friends().collect(Collectors
                .maxBy(Comparator.comparingInt(Friend::getAge)));
        oldestFriend.ifPresent(System.out::println);
        // same as
        oldestFriend = friends().collect(Collectors
                .reducing(BinaryOperator.minBy(Comparator.comparingInt(Friend::getAge))));
        oldestFriend.ifPresent(System.out::println);
    }

    @Test
    public void minBy() throws Exception {
        Optional<Double> minShoeSize = friends()
                .map(Friend::getShoeSize)
                .collect(Collectors.minBy(Comparator.naturalOrder()));
        minShoeSize.ifPresent(System.out::println);
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
    public void reducing_op() throws Exception {
        Optional<Integer> oldest = friends().map(Friend::getAge)
                .collect(Collectors.reducing(Integer::max));
        oldest.ifPresent(System.out::println);
        // same as
        oldest = friends().map(Friend::getAge).reduce(Integer::max);
        oldest.ifPresent(System.out::println);
        // almost same as
        OptionalInt ageSumInt = friends().mapToInt(Friend::getAge).max();
        ageSumInt.ifPresent(System.out::println);
        // effective to use as downstream (it's not good, see reducing_identity_mapper)
        Comparator<Friend> compareAge = Comparator.comparing(Friend::getShoeSize);
        Map<Sex, Optional<Friend>> bigfoots = manyFriends().collect(Collectors
                .groupingBy(Friend::getSex,
                        Collectors.reducing(BinaryOperator.maxBy(compareAge))));
        bigfoots.forEach((k, v) -> System.out.println("Sex:" + k + "; ShoeSize:" + v.get().getShoeSize()));
    }

    /**
     * identity : default value
     */
    @Test
    public void reducing_identity_op() throws Exception {
        Integer oldest = friends().map(Friend::getAge)
                .collect(Collectors.reducing(0, Integer::max));
        System.out.println(oldest);
    }

    @Test
    public void reducing_identity_mapper_op() throws Exception {
        Integer oldest = friends()
                .collect(Collectors.reducing(0, Friend::getAge, Integer::max));
        System.out.println(oldest);
        // effective to use as downstream
        Map<Sex, Double> bigfoots = manyFriends().collect(Collectors.groupingBy(Friend::getSex,
                Collectors.reducing(0D, Friend::getShoeSize, Double::max)));
        bigfoots.forEach((k, v) -> System.out.println("Sex:" + k + "; ShoeSize:" + v));
    }

    @Test
    public void summarizingInt() throws Exception {
        IntSummaryStatistics sum = friends()
                .collect(Collectors.summarizingInt(Friend::getAge));
        System.out.println(sum);
        // note : **SummaryStatistics is not thread safe!!
    }

    @Test
    public void summarizingLong() throws Exception {
        LongSummaryStatistics sum = friends()
                .collect(Collectors.summarizingLong(Friend::getAge));
        System.out.println(sum);
    }

    @Test
    public void summarizingDouble() throws Exception {
        DoubleSummaryStatistics sum = friends()
                .collect(Collectors.summarizingDouble(Friend::getShoeSize));
        System.out.println(sum);
    }

    @Test
    public void summingInt() throws Exception {
        Integer sum = friends()
                .collect(Collectors.summingInt(Friend::getAge));
        System.out.println(sum);
    }

    @Test
    public void summingLong() throws Exception {
        Long sum = friends()
                .collect(Collectors.summingLong(Friend::getAge));
        System.out.println(sum);
    }

    @Test
    public void summingDouble() throws Exception {
        Double sum = friends()
                .collect(Collectors.summingDouble(Friend::getShoeSize));
        System.out.println(sum);
    }

    @Test
    public void toCollection() throws Exception {
        Set<Date> days = friends().map(Friend::getBirthDay)
                .collect(Collectors.toCollection(TreeSet::new));
        days.forEach(System.out::println);
    }

    @Test
    public void toConcurrentMap_keyMapper_valueMapper() throws Exception {
        Map<Integer,String> m = friends()
                .collect(Collectors.toConcurrentMap(Friend::getId, Friend::getName));
        m.entrySet().forEach(System.out::println);
        // note : if key tyoufuku then reigai hassei
    }

    @Test
    public void toConcurrentMap_keyMapper_valueMapper_mergeFunction() throws Exception {
        Map<Sex,Double> m = friends()
                .collect(Collectors.toConcurrentMap(Friend::getSex, Friend::getShoeSize, Double::sum));
        m.entrySet().forEach(System.out::println);
    }
}
