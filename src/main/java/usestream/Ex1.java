package usestream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class Average {
  public int sum;
  public int count;

  public Average(int sum, int count) {
    this.sum = sum;
    this.count = count;
  }

  public Average merge(Average av) {
    return new Average(this.sum + av.sum, this.count + av.count);
  }
}
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
    int sum = Stream.iterate(1, x -> x + 1)
        .limit(10)
//        .reduce(0, (a, b) -> a + b);
        .reduce(0, Integer::sum);
    System.out.println("sum is " + sum);

    int sum2 = IntStream.iterate(1, x -> x + 1)
        .limit(10)
        .reduce(0, Integer::sum);
    System.out.println("sum is " + sum);

    IntStream.iterate(1, x -> x + 1)
        .limit(10)
        .mapToObj(i -> "" + i)
//        .mapToLong(s -> Long.parseLong(s))
        .mapToLong(Long::parseLong)
        .forEach(System.out::println);

//    System.out.println("----------------------");
//    boolean b = IntStream.iterate(1, x -> x + 1)
////        .allMatch(x -> x % 9 == 0);
//        .allMatch(x -> x > 0);
//    System.out.println(b);

    System.out.println("----------------------");
    Average av = IntStream.iterate(1, x -> x + 1)
        .limit(10)
        .mapToObj(x -> new Average(x, 1))
//        .reduce(new Average(0, 0), (a1, a2) -> a1.merge(a2));
        .reduce(new Average(0, 0), Average::merge);
    System.out.println("average is " + (av.sum/av.count));
  }
}
