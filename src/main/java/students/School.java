package students;

import java.util.List;

public class School {
  public static void showSmart(List<Student> ls) {
    for (Student s : ls) {
      if (s.getGrade() > 70) {
        System.out.println("smart> " + s);
      }
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
    showSmart(roster);
  }
}
