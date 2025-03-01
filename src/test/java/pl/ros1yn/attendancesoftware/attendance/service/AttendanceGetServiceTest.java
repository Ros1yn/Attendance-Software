package pl.ros1yn.attendancesoftware.attendance.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceGetServiceTest {

    @Mock
    AttendanceRepository attendanceRepository;

    @InjectMocks
    AttendanceGetService service;

    @Test
    void test1(){

        //given
        int listId = 2;
        Attendance attendance = Mockito.mock(Attendance.class);
        Optional<Attendance> optionalAttendance = Optional.of(attendance);
        when(attendanceRepository.findById(listId)).thenReturn(optionalAttendance);
        //when
        ResponseEntity<AttendanceResponse> result = service.getAttendance(listId);
        //then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}