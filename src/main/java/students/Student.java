package students;

import java.util.ArrayList;
import java.util.List;

public final class Student {
  private final String name;
  private final int grade;
  private final List<String> courses;

  private Student(String name, int grade, List<String> courses) {
    if (grade < 0 || grade > 100) {
      throw new IllegalArgumentException(grade + " is invalid as a percentage");
    }
    this.name = name;
    this.grade = grade;
    this.courses = List.copyOf(courses);
  }

  public static Student of(String name, int grade, String ... courses) {
    return new Student(name, grade, List.of(courses));
  }

  public String getName() {
    return name;
  }

  public int getGrade() {
    return grade;
  }

  public List<String> getCourses() {
    return courses;
  }

  public Student withName(String name) {
    return new Student(name, this.grade, this.courses);
  }

  public Student withGrade(int grade) {

    return new Student(this.name, grade, this.courses);
  }

  public Student withCourses(String ... courses) {
    return new Student(this.name, this.grade, List.of(courses));
  }

  public Student withAdditionalCourses(String ... courses) {
    List<String> allCourses = new ArrayList<>(this.courses);
    allCourses.addAll(List.of(courses));
    return new Student(this.name, this.grade, allCourses);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }
}
