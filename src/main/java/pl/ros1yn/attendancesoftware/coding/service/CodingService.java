package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.coding.utils.CodingAddNew;
import pl.ros1yn.attendancesoftware.coding.utils.CodingAttendanceChecker;
import pl.ros1yn.attendancesoftware.coding.utils.CodingToDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CodingService {

    private final CodingRepository codingRepository;

    private final CodingToDTO codingToDTO;

    private final CodingAttendanceChecker codingAttendanceChecker;

    private final CodingAddNew codingAddNew;

    public ResponseEntity<List<CodingDTO>> getAllCodings() {
        Iterable<Coding> codings = codingRepository.findAll();

        List<CodingDTO> codingsDTO = new ArrayList<>();

        for (Coding coding : codings) {
            codingsDTO.add(codingToDTO.convertToDTO(coding));
        }

        return ResponseEntity.ok(codingsDTO);
    }

    public ResponseEntity<CodingDTO> getSingleCoding(Integer id) {

        return codingRepository.findById(id)
                .map(codingToDTO::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<CodingDTO> deleteCoding(Integer id) {

        Optional<Coding> optionalCoding = codingRepository.findById(id);

        if (optionalCoding.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        codingRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<CodingDTO> addCoding(CodingRequestDTO requestDTO) {

        Student foundedStudent = codingAttendanceChecker.attendanceCheckerForStudent(requestDTO);

        Lesson foundedLesson = codingAttendanceChecker.attendanceCheckerForLesson(requestDTO);

        Coding coding = codingAddNew.getNewCoding(requestDTO, foundedStudent, foundedLesson);

        Coding savedCoding = codingRepository.save(coding);

        return ResponseEntity.ok(codingToDTO.convertToDTO(savedCoding));

    }


}
