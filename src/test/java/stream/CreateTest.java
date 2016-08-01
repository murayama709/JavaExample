package stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.Test;

public class CreateTest {
    @Test
    public void generate() {
        String s = Stream.generate(() -> "X").limit(3).collect(Collectors.joining());
        System.out.println(s);
        Random r = new Random();
        s = Stream.generate(() -> r.nextInt(2)).limit(5).map(String::valueOf).collect(Collectors.joining());
        System.out.println(s);
        // note : 無限長なのでlimit等を忘れずに
    }

    @Test
    public void iterate() {
        Double d1 = 2D;
        // 2ずつ加算していく
        Stream.iterate(d1, d -> d + 2D).limit(5).forEach(System.out::println);
        // 1つ前の数を２乗していく n=(n-1)^2
        Stream.iterate(d1, d -> Math.pow(d, 2)).limit(5).forEach(System.out::println);
    }
    
    @Test
    public void range_intStream() {
        IntStream.range(1,5).forEach(System.out::println);//1～4を出力
        IntStream.rangeClosed(1,5).forEach(System.out::println);//１～5を出力
    }

    @Test
    public void range_longStream() {
        LongStream.range(1,5).forEach(System.out::println);//1～4を出力
        LongStream.rangeClosed(1,5).forEach(System.out::println);//1～5を出力
    }

    @Test
    public void of() {
        Stream.of("keita","mari","kazu").forEach(System.out::println);
        Stream.of("keita").forEach(System.out::println);
    }

    @Test
    public void arrays_stream() {
        String[] a = {"keita","mari","kazu"};
        Arrays.stream(a).forEach(System.out::println);
        // note : Stream.of(a) means steam of array
    }
}
