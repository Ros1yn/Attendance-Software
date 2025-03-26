package pl.ros1yn.attendancesoftware.coding.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.mapper.CodingMapper;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CodingUpdateService {

    private final ClassFinder classFinder;
    private final CodingMapper codingMapper;

    @Transactional
    public ResponseEntity<CodingResponse> updateCoding(Integer codingId, CodingRequestDTO requestDTO) {

        Coding coding = classFinder.findCoding(codingId);
        Student student = classFinder.findStudent(requestDTO.getIndexNumber());
        Lesson lesson = classFinder.findLesson(requestDTO.getLessonId());

        coding.setStudent(student);
        coding.setLesson(lesson);

        Optional.ofNullable(requestDTO.getGroup())
                .ifPresentOrElse(coding::setGroup, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group must be filled");
                });

        return ResponseEntity.ok(codingMapper.mapToDTO(coding));
    }

    @Transactional
    public ResponseEntity<CodingResponse> updateCodingPartially(Integer codingId, CodingRequestDTO requestDTO) {

        Coding coding = classFinder.findCoding(codingId);

        Optional.ofNullable(requestDTO.getIndexNumber())
                .ifPresent(indexNumber -> {
                    Student student = classFinder.findStudent(requestDTO.getIndexNumber());
                    coding.setStudent(student);
                });

        Optional.ofNullable(requestDTO.getLessonId())
                .ifPresent(lessonId -> {
                    Lesson lesson = classFinder.findLesson(lessonId);
                    coding.setLesson(lesson);
                });

        Optional.ofNullable(requestDTO.getGroup())
                .ifPresent(coding::setGroup);

        return ResponseEntity.ok(codingMapper.mapToDTO(coding));
    }
}
