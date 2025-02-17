package pl.ros1yn.attendancesoftware.utils.kodowanie;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.DTO.KodowanieRequestDTO;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.model.Zajecia;
import pl.ros1yn.attendancesoftware.repository.StudentRepository;
import pl.ros1yn.attendancesoftware.repository.ZajeciaRepository;

@Component
@AllArgsConstructor
public class KodowanieAttendanceChecker {

    private final StudentRepository studentRepository;

    private final ZajeciaRepository zajeciaRepository;

    public Student attendanceCheckerForStudent(KodowanieRequestDTO requestDTO) {
        return studentRepository.findById(requestDTO.getIndexNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student nie znaleziony"));
    }

    public Zajecia attendancecheckerForZajecia(KodowanieRequestDTO requestDTO) {
        return zajeciaRepository.findById(requestDTO.getZajeciaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zajęcia nie znalezione"));
    }
}
