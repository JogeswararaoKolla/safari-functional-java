package superiterable;

import students.Student;

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

    System.out.println("--------------------------------");
    List<Student> roster = List.of(
        Student.of("Fred", 76, "Math", "Physics"),
        Student.of("Jim", 58, "Art"),
        Student.of("Sheila", 96, "Math", "Physics", "Astro-physics", "Quantum Mechanics"),
        Student.of("Alice", 73, "Mechanical Engineering", "Computer Science", "Math"),
        Student.of("Maverick", 62, "History", "Political Science")
    );

    SuperIterable<Student> rosterSI = new SuperIterable<>(roster);
    rosterSI
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
//        .forEach(s -> System.out.println(s));
        .forEach(System.out::println); // Method reference

    /*
    In every case, start with:
    rosterSI
    and end with:
    .forEach(s -> System.out.println(s));
    1) Print all student names with their grades
    2) Print name and grade of "smart" students
    3) Print courses taken by "enthusiastic" students
    4) Need to change/add to SuperIterable: print courses without duplicates
    5) Print "Name takes course" for all student/course combinations
     */
    rosterSI
        .map(s -> s.getName() + " has grade " + s.getGrade())
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .filter(s -> s.getGrade() > 75)
        .map(s -> s.getName() + " has grade " + s.getGrade())
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .filter(s -> s.getCourses().size() > 2)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .distinct()
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .flatMap(s -> {
          return new SuperIterable<>(s.getCourses())
              .map(c -> s.getName() + " takes course " + c);
        })
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .flatMap(s -> new SuperIterable<>(s.getCourses()).map(c -> s.getName() + " takes course " + c))
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");


    System.out.println("Using Streams");
    roster.stream()
        .map(s -> s.getName() + " has grade " + s.getGrade())
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    roster.stream()
        .filter(s -> s.getGrade() > 75)
        .map(s -> s.getName() + " has grade " + s.getGrade())
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    roster.stream()
        .filter(s -> s.getCourses().size() > 2)
        .flatMap(s -> s.getCourses().stream())
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    roster.stream()
        .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes course " + c))
        .forEach(s -> System.out.println(s));
    System.out.println("----------------------------");
    rosterSI
        .peek(s -> System.out.println("before> " + s))
        .filter(s -> s.getGrade() > 75)
        .peek(s -> System.out.println("after> " + s))
        .forEach(System.out::println);

    System.out.println("----------------------------");
    roster.stream()
        .peek(s -> System.out.println("before> " + s))
        .filter(s -> s.getGrade() > 75)
        .peek(s -> System.out.println("after> " + s))
        .forEach(System.out::println);
//    roster.stream()
    System.out.println("----------------------------");
  }
}
