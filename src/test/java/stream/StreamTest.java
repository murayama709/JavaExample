/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.type.Sex;
import com.mycompany.javaexample.util.FriendUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author murayamakenichirou
 */
public class StreamTest {
    
    @Test
    public void collect() {
        Map<Sex, List<Friend>> m = stream()
                .collect(Collectors.groupingBy(Friend::getSex));
        m.forEach((s, f) -> {
            System.out.println(s);
            f.forEach(System.out::println);
        });
    }
    
    private Stream<Friend> stream() {
        return FriendUtil.getFriends().stream();
    }
}
