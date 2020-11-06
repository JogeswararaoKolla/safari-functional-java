package concordance;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SparkConcordance {
  private final static Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

  public static void main(String[] args) throws Throwable {
    SparkConf conf = new SparkConf()
        .setAppName("Concordance")
        .setMaster("local");
    JavaSparkContext context = new JavaSparkContext(conf);

    context.textFile("PrideAndPrejudice.txt")
        .map(String::toLowerCase)
        .flatMap(l -> Arrays.asList(WORD_BOUNDARY.split(l)).iterator())
        .filter(s -> s.length() > 0)
        .mapToPair(w -> new Tuple2<>(w, 1))
        .reduceByKey((v1, v2) -> v1 + v2)
        .mapToPair(t -> new Tuple2<>(t._2, t._1))
        .sortByKey(false)
        .map(t -> String.format("%20s : %5d", t._2, t._1))
        .take(200)
        .forEach(System.out::println);
  }
}
