package pl.ros1yn.attendancesoftware.coding.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
import pl.ros1yn.attendancesoftware.coding.mapper.CodingMapper;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.utils.LessonFinder;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.utils.StudentFinder;

@Service
@AllArgsConstructor
@Slf4j
public class CodingPostService {

    private final StudentFinder studentFinder;
    private final LessonFinder lessonFinder;

    private final CodingMapper codingMapper;
    private final CodingRepository codingRepository;

    public ResponseEntity<CodingResponse> addCoding(CodingRequestDTO requestDTO) {

        Student student = studentFinder.find(requestDTO.getIndexNumber());
        Lesson lesson = lessonFinder.find(requestDTO.getLessonId());
        Coding coding = codingMapper.getNewCoding(requestDTO, student, lesson);

        Coding savedCoding = codingRepository.save(coding);
        CodingResponse codingResponse = codingMapper.mapToDTO(savedCoding);

        log.info("Coding added successfully. Saved coding: {}", codingResponse);
        return ResponseEntity.ok(codingResponse);
    }

}
