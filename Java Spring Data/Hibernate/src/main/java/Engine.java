import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Engine implements Runnable {

    EntityManager entityManager;
    Scanner scanner;

    public Engine(EntityManager em) {
        this.entityManager = em;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("Enter exercise number:");
        int exNum = Integer.parseInt(scanner.nextLine());

        switch (exNum) {
            case 2 -> exTwo();
            case 3 -> exThree();
            case 4 -> exFour();
            case 5 -> exFive();
            case 6 -> exSix();
            case 7 -> exSeven();
            case 8 -> exEight();
            case 9 -> exNine();
            case 10 -> exTen();
            case 11 -> exEleven();
            case 12 -> exTwelve();
            case 13 -> exThirteen();
        }
    }

    private void exThirteen() {
        System.out.println("Enter a town name: ");

        String townName = scanner.nextLine();
        List<Address> addresses = entityManager.createQuery("FROM Address a WHERE a.town.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();

        int deletedRows = addresses.size();

        entityManager.getTransaction().begin();
       addresses.forEach(entityManager::remove);
        entityManager.getTransaction().commit();
        System.out.printf("%d address in %s deleted", deletedRows, townName);
    }

    private void exTwelve() {

        List<Object[]> resultList = entityManager.createNativeQuery("SELECT  d.name, MAX(e.salary) max_salary FROM employees e join departments d on d.department_id = e.department_id\n" +
                        "GROUP BY d.name\n" +
                        "HAVING max_salary NOT BETWEEN 30000 AND 70000;")
                .getResultList();

        for (Object[] result : resultList) {
            String departmentName = (String) result[0];
            BigDecimal maxSalary = (BigDecimal) result[1];

            // Process the extracted data as needed
            System.out.println("Department: " + departmentName);
            System.out.println("Max Salary: " + maxSalary);
        }


    }

    private void exEleven() {
        System.out.println("Enter a prefix: ");
        String prefix = scanner.nextLine();
        List<Employee> employees = entityManager.createQuery("FROM Employee e WHERE e.firstName LIKE :like", Employee.class)
                .setParameter("like",   prefix + "%")
                .getResultList();

        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(),
                e.getLastName(),
                e.getJobTitle(),
                e.getSalary()));
    }

    private void exTen() {

       List<Integer> depIDs = List.of(1, 2, 4, 11);

        entityManager.getTransaction().begin();

        entityManager.createQuery("UPDATE Employee e SET e.salary = e.salary * 1.12 WHERE e.department.id IN (:depIDs)")
                        .setParameter("depIDs", depIDs)
                                .executeUpdate();

        List<Employee> employees = entityManager.createQuery("FROM Employee e WHERE e.department.id IN (:depIDs)", Employee.class)
                .setParameter("depIDs", depIDs)
                .getResultList();

        employees.forEach(e -> System.out.println(String.format("%s %s ($%.2f)",
                e.getFirstName(),
                e.getLastName(),
                e.getSalary())));


        entityManager.getTransaction().commit();
    }

    private void exNine() {

        List<Project> projects = entityManager.createQuery("FROM Project p ORDER BY p.startDate DESC, p.name", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.forEach(p -> System.out.println(String.format("Project name: %s\n" +
                "Project Description: %s\n" +
                "Project Start Date: %s\n" +
                "Project End Date: %s",
                p.getName(),
                p.getDescription(),
                p.getStartDate(),
                p.getEndDate() == null ? "null" : p.getEndDate())));

    }

    private void exEight() {
        System.out.println("Enter employee's ID: ");
        int Id = Integer.parseInt(scanner.nextLine());
        Employee employee = entityManager.find(Employee.class, Id);
        if (employee != null) {
            List<String> projectsName = employee.getProjects().stream()
                    .map(Project::getName)
                    .sorted(String::compareTo)
                    .toList();

            System.out.printf("%s %s - %s%n%s",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getJobTitle(),
                    String.join("\n", projectsName));
        }
    }

    private void exSeven() {

        List<Address> addresses = entityManager.createQuery("FROM Address a ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        addresses
                .forEach(address -> System.out.printf("%s, %s - %d employees%n",
                        address.getText(),
                        address.getTown() == null
                        ? "Unknown"
                        : address.getTown().getName(),
                        address.getEmployees().size()));

    }

    private void exSix() {
        System.out.println("Enter employee last name: ");
        String employeeLastName = scanner.nextLine();


        Address address = createNewAddress("Vitoshka 15");

        Employee employee = entityManager.createQuery("FROM Employee e WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", employeeLastName)
                .getSingleResult();

        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createNewAddress(String text) {
        Address address = new Address();
        address.setText(text);

        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        return address;
    }

    private void exFive() {
        String department = "Research and Development";

        List<Employee> employees = entityManager.createQuery("FROM Employee e WHERE e.department.name = :department" +
                        " ORDER BY e.salary, e.id", Employee.class)
                .setParameter("department", department)
                .getResultList();

        employees.forEach(e -> System.out.println(String.format("%s %s from %s - $%.2f",
                e.getFirstName(),
                e.getLastName(),
                e.getDepartment().getName(),
                e.getSalary())));
    }

    private void exFour() {

        entityManager.createQuery("FROM Employee e WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void exThree() {
        System.out.println("Enter employee's name: ");
        String fullName = scanner.nextLine();

        Employee employee;
        String output;

        try {
            employee = entityManager.createQuery("FROM Employee e WHERE concat(e.firstName, ' ', e.lastName) = :empName ", Employee.class)
                    .setParameter("empName", fullName)
                    .getSingleResult();

            output = "Yes";
        } catch (Exception e) {
            output = "No";
        }

        System.out.println(output);
    }

    private void exTwo() {
        entityManager.getTransaction().begin();

        int modifiedRows = entityManager.createQuery("UPDATE Town t SET t.name = upper(t.name)" +
                        " WHERE length(t.name) <= 5")
                .executeUpdate();

        System.out.println(modifiedRows);

        entityManager.getTransaction().commit();
    }
}
