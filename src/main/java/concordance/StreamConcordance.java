package concordance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamConcordance {
  private final static Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

  public static void main(String[] args) throws Throwable {
    try (Stream<String> in = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
      in
          .map(String::toLowerCase)
//          .flatMap(l -> WORD_BOUNDARY.splitAsStream(l))
          .flatMap(WORD_BOUNDARY::splitAsStream)
          .filter(s -> s.length() > 0)
//          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
          .entrySet().stream()
          .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
          .limit(200)
          .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
          .forEach(System.out::println);
    }
  }
}
