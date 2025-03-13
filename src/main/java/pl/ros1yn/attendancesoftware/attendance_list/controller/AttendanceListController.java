package pl.ros1yn.attendancesoftware.attendance_list.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttedanceListUpdateService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;

import java.util.List;

@RestController
@RequestMapping("attendancelist")
@AllArgsConstructor
class AttendanceListController {

    private final AttendanceListDeleteService attendanceListDeleteService;
    private final AttendanceListPostService attendanceListPostService;
    private final AttendanceListGetService attendanceListGetService;
    private final AttedanceListUpdateService attedanceListUpdateService;

    @GetMapping("/")
    ResponseEntity<List<AttendanceListResponse>> getAllAttendenceLists() {
        return attendanceListGetService.getAllAttendanceLists();
    }

    @GetMapping("/{id}")
    ResponseEntity<AttendanceListResponse> getSingleAttendanceList(@PathVariable Integer id) {
        return attendanceListGetService.getSingleAttendanceList(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AttendanceListResponse> deleteAttendanceList(@PathVariable Integer id) {
        return attendanceListDeleteService.deleteAttendanceList(id);
    }

    @PostMapping("/")
    ResponseEntity<AttendanceListResponse> addNewAttendanceList(@RequestBody AttendanceListRequestDTO attendanceListDTO) {
        return attendanceListPostService.addNewAttendanceList(attendanceListDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<AttendanceListResponse> updateAttendanceList(@PathVariable Integer id, @RequestBody AttendanceListRequestDTO requestDTO) {
        return attedanceListUpdateService.updateAttendanceList(id, requestDTO);
    }

    @PatchMapping("/{id}")
    ResponseEntity<AttendanceListResponse> updateAttendanceListPartially(@PathVariable Integer id, @RequestBody AttendanceListRequestDTO requestDTO) {
        return attedanceListUpdateService.updateAttendanceListPartially(id, requestDTO);
    }
}

