package pl.ros1yn.attendancesoftware.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
