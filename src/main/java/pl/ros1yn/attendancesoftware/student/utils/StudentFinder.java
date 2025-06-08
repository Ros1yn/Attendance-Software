package pl.ros1yn.attendancesoftware.student.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.utils.Finder;

@Component
@AllArgsConstructor
@Slf4j
public class StudentFinder implements Finder<Student, Integer> {

    private final StudentRepository studentRepository;

    @Override
    public Student find(Integer indexNumber) {
        return studentRepository.findById(indexNumber)
                .orElseThrow(() -> {
                    log.error("Student was not found");
                    return new StudentNotFoundException();
                });
    }
}
