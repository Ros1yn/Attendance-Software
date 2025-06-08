package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceListMapperTest {

    @Mock
    private AttendanceMapper attendanceMapper;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private AttendanceListMapper attendanceListMapper;

    private AttendanceList attendanceList;
    private Attendance attendance;
    private Lesson lesson;
    private LocalDate expectedDate;
    private int expectedId;
    private int expectedAttendancesSize;

    @BeforeEach
    void setup(){

        expectedDate = LocalDate.of(2025, 1, 1);
        expectedId = 1;
        expectedAttendancesSize = 1;

        lesson = Lesson.builder()
                .id(1)
                .build();

        attendance = Attendance.builder()
                .id(1)
                .build();

        attendanceList = AttendanceList.builder()
                .id(1)
                .date(LocalDate.of(2025, 1, 1))
                .lesson(lesson)
                .attendances(List.of(attendance))
                .build();


    }

    @Test
    void shouldReturnAttendanceListRepsonseCorretly(){

        //given
        AttendanceResponse attendanceResponse = AttendanceResponse.builder()
                .id(1)
                .build();

        LessonResponse lessonResponse = LessonResponse.builder()
                .id(1)
                .build();

        when(attendanceMapper.mapToAttendanceResponse(attendance)).thenReturn(attendanceResponse);
        when(lessonMapper.mapToDTO(lesson)).thenReturn(lessonResponse);
        //when

        AttendanceListResponse result = attendanceListMapper.mapToResponseDTO(attendanceList);

        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertEquals(expectedDate, result.getDateOfAttendanceList());
        assertEquals(expectedAttendancesSize, result.getAttendanceResponseList().size());
        assertEquals(lessonResponse, result.getLessonResponse());
        assertEquals(attendanceResponse, result.getAttendanceResponseList().getFirst());

        verify(attendanceMapper, times(1)).mapToAttendanceResponse(attendance);
        verify(lessonMapper, times(1)).mapToDTO(lesson);
    }

}