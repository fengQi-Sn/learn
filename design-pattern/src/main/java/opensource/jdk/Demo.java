package opensource.jdk;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Calendar calendar = new Calendar.Builder().build();
        Collection xx = Arrays.asList();
        Collection collection = Collections.unmodifiableCollection(Arrays.asList());

        List<Demo.Student> students = new ArrayList<>();
        students.add(new Student("Alice", 19, 89.0f));
        students.add(new Student("Peter", 20, 78.0f));
        students.add(new Student("Leo", 18, 99.0f));

        Collections.sort(students, new AgeAscComparator());
        print(students);

        Collections.sort(students, new NameAscComparator());
        print(students);

        Collections.sort(students, new ScoreDescComparator());
        print(students);
    }

    @Getter
    @AllArgsConstructor
    static class Student implements Comparable{
        String name;
        int age;
        double score;

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public static void print(List<Student> students) {
        for (Student s : students) {
            System.out.println(s.getName() + " " + s.getAge() + " " + s.getScore());
        }
        System.out.println();
    }

    public static class AgeAscComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    public static class NameAscComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class ScoreDescComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            if (Math.abs(o1.getScore() - o2.getScore()) < 0.001) {
                return 0;
            } else if (o1.getScore() < o2.getScore()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
