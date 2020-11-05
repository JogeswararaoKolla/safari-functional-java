package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

  public SuperIterable<E> filter(Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }

  //  interface Function<E, F> {
//    F apply(E e);
//  }
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
//    for (E s : self) {
//      res.add(op.apply(s));
//    }
    self.forEach(e -> res.add(op.apply(e)));
    return new SuperIterable<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
