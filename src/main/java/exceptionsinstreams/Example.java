package exceptionsinstreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

// Library "VAVR"

interface ExFunction<A, B> {
  B apply(A a) throws Throwable;
}
public class Example {
//  public static Stream<String> getLines(String fn) {
//    try {
//      return Files.lines(/*Paths.get*/Path.of(fn));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
//  public static Optional<Stream<String>> getLines(String fn) {
//    try {
//      return Optional.of(Files.lines(Path.of(fn)));
//    } catch (IOException e) {
//      return Optional.empty();
//    }
//  }

  public static <A, B> Function<A, Optional<B>> wrap(ExFunction<A, B> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable throwable) {
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "c.txt")
        .map(wrap(n -> Files.lines(Path.of(n))))
        .peek(opt -> { if (opt.isEmpty()) System.out.println("Ooops, problem"); })
        .filter(Optional::isPresent)
        .flatMap(Optional::get)
        .forEach(System.out::println);
  }
}
