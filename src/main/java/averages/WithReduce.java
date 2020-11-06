package averages;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average include(double d) {
    return new Average(d + this.sum, this.count +1);
  }

  public Average merge(Average av) {
    return new Average(this.sum + av.sum, this.count + av.count);
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

// See compilations by JIT compiler using: -XX:+PrintCompilation
public class WithReduce {
  public static void main(String[] args) {
    long start = System.nanoTime();
//    ThreadLocalRandom.current().doubles(1_000_000_000, -Math.PI, +Math.PI)
    Stream.iterate(0.0, x -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
        .limit(1_000_000_000)
//        .mapToObj(x -> x)
        .parallel() // LAST ONE WINS
//        .boxed()
//        .sequential()
        .reduce(
            new Average(0, 0),
            (a, d) -> a.include(d),
            (a1, a2) -> a1.merge(a2)
            )
        .get()
        .map(a -> "The average is " + a)
        .ifPresent(System.out::println);
    long time = System.nanoTime() - start;
    System.out.printf("Time for processing was %7.5f\n", (time / 1_000_000_000.0));
  }
}
