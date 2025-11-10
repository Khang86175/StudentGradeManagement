package test;


import dao.AssignmentDAO;
import model.Assignment;

public class TestAssignmentDAO {
    public static void main(String[] args) {
        AssignmentDAO dao = new AssignmentDAO();

        // Create test data
        Assignment assignment = new Assignment(301, 1, 1, 1);

        // 1️⃣ Insert
        System.out.println("Insert assignment: " + dao.insert(assignment));

        // 2️⃣ Get all
        System.out.println("All assignments:");
        dao.getAll().forEach(System.out::println);

        // 3️⃣ Get by ID
        System.out.println("Find by ID = 301: " + dao.getById(301));

        // 4️⃣ Update
        assignment.setSubjectId(2);
        System.out.println("Update assignment: " + dao.update(assignment));

        // 5️⃣ Delete
        System.out.println("Delete assignment: " + dao.delete(301));
    }
}
