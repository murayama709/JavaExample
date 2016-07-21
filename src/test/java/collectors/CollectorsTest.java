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
    public void groupingBy1() {
        Map<Sex, List<Friend>> m = stream()
                .collect(Collectors.groupingBy(Friend::getSex));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy() {
        Map<Sex, List<Friend>> m = stream()
                .collect(Collectors.groupingBy(Friend::getSex));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_downstream() {
        Map<Sex, List<Friend>> m = stream()
                .collect(Collectors.groupingBy(Friend::getSex, Collectors.toList()));
        m.entrySet().forEach(System.out::println);
    }

    @Test
    public void groupingBy_mapping() {
        Map<Sex, List<String>> m = stream()
                .collect(Collectors.groupingBy(Friend::getSex, 
                        Collectors.mapping(Friend::getName, Collectors.toList())));
        m.entrySet().forEach(System.out::println);
    }
}
