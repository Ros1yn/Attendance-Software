package pl.ros1yn.attendancesoftware.attendance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceUpdateHelperTest {

    private AttendanceUpdateDTO updateDTO;
    private Attendance attendance;
    private Student studentAfterUpdate;
    private AttendanceList attendanceList;

    @Mock
    private ClassFinder classFinder;

    @InjectMocks
    private AttendanceUpdateHelper attendanceUpdateHelper;

    @BeforeEach
    void setup() {

        studentAfterUpdate = Student.builder()
                .indexNumber(12345)
                .name("John")
                .surname("Flower")
                .build();

        updateDTO = AttendanceUpdateDTO.builder()
                .isAttendance("PRESENT")
                .activity(1)
                .indexNumber(12345)
                .listId(1)
                .build();

        attendance = Attendance.builder()
                .id(1)
                .isAttendance("NOT PRESENT")
                .activity(0)
                .student(
                        Student.builder()
                        .indexNumber(54321)
                        .name("Matt")
                        .surname("House")
                        .build())
                .build();

        attendanceList = AttendanceList.builder()
                .id(1)
                .build();
    }

    @Test
    void shouldReturnUpdatedAttendanceDTOCorrectly() {
        //given
        when(classFinder.findStudent(updateDTO.getIndexNumber()))
                .thenReturn(studentAfterUpdate);
        when(classFinder.findAttendanceList(updateDTO.getListId()))
                .thenReturn(attendanceList);

        //when
        attendanceUpdateHelper.updateAttendanceFromPatchDTO(updateDTO, attendance);

        //then
        assertEquals(updateDTO.getIsAttendance(), attendance.getIsAttendance());
        assertEquals(updateDTO.getActivity(), attendance.getActivity());
        assertEquals(attendanceList.getId(), attendance.getAttendanceList().getId());
        assertEquals(studentAfterUpdate.getIndexNumber(), attendance.getStudent().getIndexNumber());

        verify(classFinder, times(1)).findStudent(updateDTO.getIndexNumber());
        verify(classFinder, times(1)).findAttendanceList(updateDTO.getListId());
    }

    @Test
    void shouldReturnFullyUpdatedAttendanceDTOCorrectly() {
        //given
        when(classFinder.findStudent(updateDTO.getIndexNumber()))
                .thenReturn(studentAfterUpdate);
        when(classFinder.findAttendanceList(updateDTO.getListId()))
                .thenReturn(attendanceList);

        //when
        attendanceUpdateHelper.updateAttendanceFromPutDTO(updateDTO, attendance);

        //then
        assertEquals(updateDTO.getIsAttendance(), attendance.getIsAttendance());
        assertEquals(updateDTO.getActivity(), attendance.getActivity());
        assertEquals(attendanceList.getId(), attendance.getAttendanceList().getId());
        assertEquals(studentAfterUpdate.getIndexNumber(), attendance.getStudent().getIndexNumber());

        verify(classFinder, times(1)).findStudent(updateDTO.getIndexNumber());
        verify(classFinder, times(1)).findAttendanceList(updateDTO.getListId());
    }

    @Test
    void shouldThrowNOT_FOUNDWhenIsAttendanceIsNull() {

        //given

        AttendanceUpdateDTO updateDTOwhereisAttendanceIsNull = AttendanceUpdateDTO.builder()
                .isAttendance(null)
                .activity(1)
                .indexNumber(12345)
                .listId(1)
                .build();

        when(classFinder.findStudent(updateDTOwhereisAttendanceIsNull.getIndexNumber()))
                .thenReturn(studentAfterUpdate);
        when(classFinder.findAttendanceList(updateDTOwhereisAttendanceIsNull.getListId()))
                .thenReturn(attendanceList);

        //when
        ResponseStatusException resultException = assertThrows(ResponseStatusException.class, () -> attendanceUpdateHelper.updateAttendanceFromPutDTO(updateDTOwhereisAttendanceIsNull, attendance));

        assertEquals(HttpStatus.NOT_FOUND, resultException.getStatusCode());
        assertEquals("isAttendance must be filled", resultException.getReason());

    }

    @Test
    void shouldThrowNOT_FOUNDWhenActivityIsNull() {

        //given

        AttendanceUpdateDTO updateDTOwhereisAttendanceIsNull = AttendanceUpdateDTO.builder()
                .isAttendance("PRESENT")
                .activity(null)
                .indexNumber(12345)
                .listId(1)
                .build();

        when(classFinder.findStudent(updateDTOwhereisAttendanceIsNull.getIndexNumber()))
                .thenReturn(studentAfterUpdate);
        when(classFinder.findAttendanceList(updateDTOwhereisAttendanceIsNull.getListId()))
                .thenReturn(attendanceList);

        //when
        ResponseStatusException resultException = assertThrows(ResponseStatusException.class, () -> attendanceUpdateHelper.updateAttendanceFromPutDTO(updateDTOwhereisAttendanceIsNull, attendance));

        assertEquals(HttpStatus.NOT_FOUND, resultException.getStatusCode());
        assertEquals("activity must be filled", resultException.getReason());

    }

}
