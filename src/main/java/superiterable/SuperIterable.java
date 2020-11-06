package superiterable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

//  public void forEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }

  public SuperIterable<E> peek(Consumer<E> op) {
    for (E e : self) {
      op.accept(e);
    }
    return this;
  }

  public SuperIterable<E> filter(Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }

  public SuperIterable<E> distinct() {
    List<E> res = new ArrayList<>();
    Set<E> seen = new HashSet<>();
    for (E s : self) {
      if (seen.add(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }
  //  interface Function<E, F> {
//    F apply(E e);
//  }
  // "container with map"
  // Called a "Functor"
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
//    for (E s : self) {
//      res.add(op.apply(s));
//    }
    self.forEach(e -> res.add(op.apply(e)));
    return new SuperIterable<>(res);
  }

  // bucket o'data with a flatMap is called "Monad"
  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E s : self) {
      SuperIterable<F> manyF = op.apply(s);
      for (F f : manyF) {
        res.add(f);
      }
    }
//    self.forEach(e -> op.apply(e).forEach(f -> res.add(f)));
    return new SuperIterable<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
