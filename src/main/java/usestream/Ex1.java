package usestream;

import java.util.stream.Stream;

public class Ex1 {
  public static void main(String[] args) {
    Stream s = Stream.of("Fred", "Jim", "Sheila");
    s.forEach(System.out::println);

//    s.forEach(System.out::println);

    Stream.iterate(1, x -> x + 1)
        .filter(x -> x % 2 == 0)
        .limit(100)
        .forEach(System.out::println);
    System.out.println("Finished");
  }
}
