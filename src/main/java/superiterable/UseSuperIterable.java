package superiterable;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(List.of("Fred", "Jim", "Shiela"));

    sis
        .filter(s -> s.length() > 3)
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("> " + s));

    System.out.println("--------------------");
    sis
        .filter(s -> s.length() > 3)
        .map(s -> s.length())
        .forEach(s -> System.out.println("> " + s));


  }
}
