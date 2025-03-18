package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceDeleteService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceGetService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendancePostService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceUpdateService;

@RestController
@RequestMapping("attendance")
@AllArgsConstructor
class AttendanceController {

    private final AttendanceDeleteService attendanceDeleteService;
    private final AttendanceGetService attendanceGetService;
    private final AttendancePostService attendancePostService;
    private final AttendanceUpdateService attendanceUpdateService;

    @GetMapping("/")
    ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        return attendanceGetService.getAllAttendances();
    }

    @GetMapping("/{id}")
    ResponseEntity<AttendanceResponse> getSingleAttendance(@PathVariable Integer id) {
        return attendanceGetService.getAttendance(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Attendance> deleteAttendance(@PathVariable Integer id) {
        return attendanceDeleteService.deleteAttendance(id);
    }

    @PostMapping("/")
    ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        return attendancePostService.addAttendance(attendance);
    }

    @PutMapping("/{id}")
    ResponseEntity<AttendanceResponse> updateFullAttendance(@PathVariable Integer id, @RequestBody AttendanceUpdateDTO updateDTO) {
        return attendanceUpdateService.updateAttendance(id, updateDTO);

    }

    @PatchMapping("/{id}")
    ResponseEntity<AttendanceResponse> updateAttendancePartially(@PathVariable Integer id, @RequestBody AttendanceUpdateDTO updateDTO) {
        return attendanceUpdateService.updatePartially(id, updateDTO);
    }
}

