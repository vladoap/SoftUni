import java.util.*;

 class Student {
    private int id;
    private String name;
    private double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }
}

 class Priorities {
    PriorityQueue<Student> priorityQueue = new PriorityQueue<>(new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.getCgpa() != o2.getCgpa()) {
                return Double.compare(o2.getCgpa(), o1.getCgpa());
            }

            if (!o1.getName().equals(o2.getName())) {
                return o1.getName().compareTo(o2.getName());
            }

            return Integer.compare(o1.getId(), o2.getId());
        }
    });


    List<Student> getStudents (List<String> events) {
        for (String event : events) {
            String[] tokens = event.split("\\s+");

            if (tokens[0].equals("ENTER")) {
                Student newStudent = new Student(Integer.parseInt(tokens[3]), tokens[1], Double.parseDouble(tokens[2]));
                priorityQueue.add(newStudent);
            } else {
                priorityQueue.poll();
            }
        }

        List<Student> result = new ArrayList<>();

        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }

        return result;
    }
}
public class Java_PriorityQueue {

    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {


        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st : students) {
                System.out.println(st.getName());
            }
        }

    }
}
