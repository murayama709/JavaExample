/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 *
 * @author murayamakenichirou
 */
public class CreateTest {
    @Test
    public void generate() {
        String s = Stream.generate(() -> "X").limit(3).collect(Collectors.joining());
        System.out.println(s);
        // note : 無限長なのでlimit等を忘れずに
    }
}
