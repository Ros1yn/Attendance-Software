package pl.ros1yn.attendancesoftware.attendance_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttendanceListController {

    private final AttendanceListService attendanceListService;

    @GetMapping("attendancelist")
    public ResponseEntity<List<AttendanceListDTO>> getAllAttendenceLists() {
        return attendanceListService.getAllAttendanceLists();
    }

}
