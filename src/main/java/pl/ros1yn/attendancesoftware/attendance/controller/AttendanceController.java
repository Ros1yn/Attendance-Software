package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class AttendanceController {

    private final AttendanceDeleteService attendanceDeleteService;
    private final AttendanceGetService attendanceGetService;
    private final AttendancePostService attendancePostService;
    private final AttendanceUpdateService attendanceUpdateService;

    @GetMapping("/")
    ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        log.info("Recived request for getAllAttendances");
        return attendanceGetService.getAllAttendances();
    }

    @GetMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> getSingleAttendance(@PathVariable Integer attendanceId) {
        log.info("Recived request for getSingleAttendance with id: {}", attendanceId);
        return attendanceGetService.getAttendance(attendanceId);
    }

    @DeleteMapping("/{attendanceId}")
    ResponseEntity<Attendance> deleteAttendance(@PathVariable Integer attendanceId) {
        log.info("Recived request for deleteAttendance with id: {}", attendanceId);
        return attendanceDeleteService.deleteAttendance(attendanceId);
    }

    @PostMapping("/")
    ResponseEntity<AttendanceResponse> addAttendance(@RequestBody AttendanceUpdateDTO updateDTO) {
        log.info("Recived request for addAttendance with body: {}", updateDTO);
        return attendancePostService.addAttendance(updateDTO);
    }

    @PutMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> updateFullAttendance(@PathVariable Integer attendanceId, @RequestBody AttendanceUpdateDTO updateDTO) {
        log.info("Recived request for updateFullAttendance with id: {} - and body: {}", attendanceId, updateDTO);
        return attendanceUpdateService.updateAttendance(attendanceId, updateDTO);
    }

    @PatchMapping("/{attendanceId}")
    ResponseEntity<AttendanceResponse> updateAttendancePartially(@PathVariable Integer attendanceId, @RequestBody AttendanceUpdateDTO updateDTO) {
        log.info("Recived request for updateAttendancePartially with id: {} - and body: {}", attendanceId, updateDTO);
        return attendanceUpdateService.updatePartially(attendanceId, updateDTO);
    }
}

