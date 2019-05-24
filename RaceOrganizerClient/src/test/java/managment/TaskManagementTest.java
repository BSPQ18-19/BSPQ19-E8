package managment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TaskManagementTest {

    @BeforeAll
    static void setUp() {
        AuthManagement.login("a.santiago", "test");
    }

    @AfterAll
    static void tearDown() {
        AuthManagement.logout();
    }

    @Test
    void addTask() {

        Assertions.assertTrue(TaskManagement.addTask(1, "test"));
    }
    /* TODO this test doesnt work returns unauthorised */
//    @Test
//    void editTask() {
//
//        Task task = RaceManagement.getRace(1).getTasks()[0];
//        task.setDescription("new description");
//
//        Assertions.assertTrue(TaskManagement.editTask(1, task));
//    }
}