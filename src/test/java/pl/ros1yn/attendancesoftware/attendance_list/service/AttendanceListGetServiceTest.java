package pl.ros1yn.attendancesoftware.attendance_list.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceListGetServiceTest {

    @Mock
    private AttendanceListRepository attendanceListRepository;

    @Mock
    private AttendanceListMapper attendanceListMapper;

    @InjectMocks
    private AttendanceListGetService attendanceListGetService;

    private AttendanceResponse attendanceResponse;

    @BeforeEach
    void setup(){
        attendanceResponse = new AttendanceResponse();
    }

    @Test
    void shouldReturnAllAttendances() {

        //When
        List<AttendanceList> listOfAttendances = attendanceListRepository.findAll();
        AttendanceListResponse attendanceListResponse = attendanceListMapper.mapToResponseDTO(listOfAttendances);
        when(attendanceListRepository.findAll()).thenReturn(listOfAttendances);
        when(attendanceListMapper.mapToResponseDTO(listOfAttendances.getFirst())).thenReturn()
    }

}