package students;

import java.util.ArrayList;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

class SmartStudent implements StudentCriterion {
  @Override
  public boolean test(Student s) {
    return s.getGrade() > 70;
  }
}

class EnthusiasticStudent implements StudentCriterion {
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
  public static List<Student> getByCriterion(List<Student> ls, StudentCriterion crit) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
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
  public static void show(List<Student> ls) {
    for (Student s : ls) {
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
    show(getByCriterion(roster, new SmartStudent()));
    System.out.println("----------------------");
    // smarter still???
//    show(getSmart(roster, 80));
//    System.out.println("----------------------");
    // enthusiastic???
//    show(getEnthusiastic(roster, 2));
    show(getByCriterion(roster, new EnthusiasticStudent()));
  }
}
