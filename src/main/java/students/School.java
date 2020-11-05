package students;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Criterion<E> {
  boolean test(E s);
//  void doStuff();
}

class SmartStudent implements Criterion<Student> {
  private int threshold;
  SmartStudent(int threshold) {
    this.threshold = threshold;
  }
  @Override
  public boolean test(Student s) {
    return s.getGrade() > threshold;
  }
}

class EnthusiasticStudent implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 2;
  }
}

public class School {
  // keep apart unrelated concerns
  // keep apart what changes independently
  // remove non-smart?? -- no BAD IDEA
  // object as function argument "primarily for it BEHAVIOR" is called "Command" pattern
  // In "FP" it's simply referred to a (one kind of) "Higher order function"
//  public static List<Student> getByCriterion(List<Student> ls, StudentCriterion crit) {
  public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }
//  public static List<Student> getSmart(List<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGrade() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//  public static List<Student> getEnthusiastic(List<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
  public static <E> void show(List<E> ls) {
    for (E s : ls) {
        System.out.println("> " + s);
    }
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 76, "Math", "Physics"),
        Student.of("Jim", 58, "Art"),
        Student.of("Sheila", 96, "Math", "Physics", "Astro-physics", "Quantum Mechanics"),
        Student.of("Alice", 73, "Mechanical Engineering", "Computer Science", "Math"),
        Student.of("Maverick", 62, "History", "Political Science")
    );

    // Show all the "smart" students
//    show(getSmart(roster, 75));
    show(getByCriterion(roster, new SmartStudent(70)));
    System.out.println("----------------------");
    // smarter still???
//    show(getSmart(roster, 80));
    show(getByCriterion(roster, new SmartStudent(80)));
    System.out.println("----------------------");
    // enthusiastic???
//    show(getEnthusiastic(roster, 2));
    show(getByCriterion(roster, new EnthusiasticStudent()));

    System.out.println("----------------------");
    List<String> ls = List.of("Fred", "William", "Sheila", "Jim");

    class LongString implements Criterion<String> {
      @Override
      public boolean test(String s) {
        return s.length() > 4;
      }
    }
    show(getByCriterion(ls, new LongString()));
    System.out.println("----------------------");
    class ShortString implements Criterion<String> {
      @Override
      public boolean test(String s) {
        return s.length() < 5;
      }
    }
    // thingy to expect = a thingy
    // thingy to expect = pieces for building thingy
    // compiler attempts to build "thingy" from the pices
//    show(getByCriterion(ls, (String s) -> { return s.length() < 5; } ));
//    show(getByCriterion(ls, (s) -> { return s.length() < 5; } ));
    // doStuff ( (String a, String b) -> ???
    // doStuff ( (var a, var b) -> ???
    // doStuff ( (a, b) -> ???
    show(getByCriterion(ls, s -> s.length() < 5 ));

//    Object behavior = s -> s.length() < 5s -> s.length() < 5;
    /*
    If I need to build a criterion of string...
    1) need an object
       new XXXX
    2) need XXXX
       class XXXX implement Criterion<String> {
          boolean test(String s) {  // MUST BE IMPLEMENTING AN **INTERFACE**,
           // WITH **ONE** ABSTRACT METHOD
          ??? ooops, I need the body...
          }
          ??? any other methods??? NO NOT ALLOWED
       }
     */
  }
}
