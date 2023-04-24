package definingClasses.exercise.companyRoster;

 class Employee {

    private String name;
    private double salary;
    private String position;
    private String department;
    private String email;
    private int age;

    Employee (String name, double salary, String position, String department, String email, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = age;
    }

    Employee (String name, double salary, String position, String department, String email) {
        this (name, salary, position, department, email, -1);
    }

    Employee (String name, double salary, String position, String department, int age) {
        this (name, salary, position, department, "n/a", age);
    }

    Employee (String name, double salary, String position, String department) {
        this (name, salary, position, department, "n/a", -1);
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %s %d", name, salary, email, age);
    }
}
