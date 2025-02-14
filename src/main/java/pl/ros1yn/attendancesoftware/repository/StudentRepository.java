package pl.ros1yn.attendancesoftware.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
}
