package comp3350.srsys.persistence;

import java.util.List;

import comp3350.srsys.objects.Student;

public interface StudentPersistence {
    List<Student> getStudentSequential();

    List<Student> getStudentRandom(final Student currentStudent);

    Student insertStudent(final Student currentStudent);

    Student updateStudent(final Student currentStudent);

    void deleteStudent(final Student currentStudent);
}
