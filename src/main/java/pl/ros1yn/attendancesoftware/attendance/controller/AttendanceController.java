package pl.ros1yn.attendancesoftware.attendance.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceDeleteService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceGetService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendancePostService;
import pl.ros1yn.attendancesoftware.attendance.service.AttendanceUpdateService;

@RestController
@RequestMapping("attendance")
@AllArgsConstructor
public class AttendanceController {

    private final AttendanceDeleteService attendanceDeleteService;

    private final AttendanceGetService attendanceGetService;

    private final AttendancePostService attendancePostService;

    private final AttendanceUpdateService attendanceUpdateService;


    @GetMapping("/")
    public ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        return attendanceGetService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getSingleAttendance(@PathVariable Integer id) {
        return attendanceGetService.getAttendance(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Attendance> deleteAttendance(@PathVariable Integer id) {
        return attendanceDeleteService.deleteAttendance(id);
    }

    @PostMapping("/")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        return attendancePostService.addAttendance(attendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateFullAttendance(@PathVariable Integer id, @RequestBody AttendanceUpdateDTO updateDTO) {

        return attendanceUpdateService.updateAttendance(id, updateDTO);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendancePartially(@PathVariable Integer id, @RequestBody AttendanceUpdateDTO updateDTO) {
        return attendanceUpdateService.updatePartially(id, updateDTO);
    }


}

