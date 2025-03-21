package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.mapper.CodingMapper;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class CodingPostService {

    private final ClassFinder classFinder;
    private final CodingMapper codingMapper;
    private final CodingRepository codingRepository;

    public ResponseEntity<CodingResponse> addCoding(CodingRequestDTO requestDTO) {

        Student student = classFinder.findStudent(requestDTO.getIndexNumber());
        Lesson lesson = classFinder.findLesson(requestDTO.getLessonId());
        Coding coding = codingMapper.getNewCoding(requestDTO, student, lesson);
        Coding savedCoding = codingRepository.save(coding);

        return ResponseEntity.ok(codingMapper.mapToDTO(savedCoding));
    }

}
