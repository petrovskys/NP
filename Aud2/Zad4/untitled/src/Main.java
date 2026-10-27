import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Student{
    private final String index;
    private  String name;
    private int grade;
    private int attendance;
    public Student(String index, String name, int grade, int attendance){
        this.index = index;
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
    }

    @Override
    public String toString(){
        return index + " " + name + " " + grade + " " + attendance+"%";
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        if(grade > 10) grade = 10;
        else if (grade < 5) grade = 5;
        this.grade = grade;
    }
    public int getAttendance(){
        return attendance;
    }
}
class Course{
    private String name;
    private Student[] students;
    private int num_students;
    public void enroll(Supplier<Student> supplier){
        if (num_students < students.length){
            students[num_students++] = supplier.get();
        }
    }
    public void forEach(Consumer<Student> action){
        for (int i = 0; i < num_students; i++){
            action.accept(students[i]);
        }
    }
    public int count(Predicate<Student> predicate){
        int counter = 0;
        for(int i = 0; i < num_students; i++){
            if(predicate.test(students[i])) counter++;
        }
        return counter;
    }
    public Student findFirst(Predicate<Student> predicate){
        for(int i = 0; i < num_students; i++){
            if(predicate.test(students[i])) return students[i];
        }
        return null;
    }
    public Student[] filter(Predicate<Student> predicate){
        int j = 0;
        Student[] filtered = new Student[count(predicate)];
        for(int i = 0; i < num_students; i++){
            if(predicate.test(students[i])) filtered[j++] = students[i];
        }
        return filtered;
    }
    public String[] mapToLabels(Function<Student,String> mapper){
        String[] mapped = new String[num_students];
        for(int i = 0; i < num_students; i++){
            mapped[i]=mapper.apply(students[i]);
        }
        return mapped;
    }
    public void mutate(Consumer<Student> mutator){
        for(int i = 0; i < num_students; i++){
            mutator.accept(students[i]);
        }
    }
    public void conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator){
        for(int i = 0; i < num_students; i++){
            if(condition.test(students[i])) mutator.accept(students[i]);
        }
    }
    @Override
    public String toString(){
        return name+", "+num_students+", students: \n"+ Arrays.toString(students);
    }
}
public class Main{
    public static void main(String[] args) {
        Course course = new Course();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Supplier<Student> reader = () -> {
            String index = sc.next();
            String name = sc.next();
            int grade = sc.nextInt();
            int attendance = sc.nextInt();
            sc.nextLine();
            return new Student(index, name, grade, attendance);
        };
        for  (int i = 0; i < n; i++) {
            course.enroll(reader);
        }
        Consumer<Student> print = student -> student.toString();
        course.forEach(print);
        Predicate<Student> grade_filter = (student) -> student.getGrade()>=6;
        Predicate<Student> attendance_filter = (student) -> student.getAttendance()>=70;
        Predicate<Student> complete_filter = grade_filter.and(attendance_filter);
        Student[] good_students = course.filter(complete_filter);
        for(Student student : good_students){
            System.out.println(student);
        }
        Predicate<Student> high_grade = student -> student.getGrade()>=9;
        System.out.println(course.findFirst(high_grade));
        Consumer<Student> increase_grade = (student) -> student.setGrade(student.getGrade() + 1);
        course.mutate(increase_grade);
        Predicate<Student> very_high_attendance = (student) -> student.getAttendance()>=90;
        course.conditionalMutate(very_high_attendance,increase_grade);
    }
}