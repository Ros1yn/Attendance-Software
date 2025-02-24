package pl.ros1yn.attendancesoftware.attendance_list.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListDeleteService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListGetService;
import pl.ros1yn.attendancesoftware.attendance_list.service.AttendanceListPostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AttendanceListController {

    private final AttendanceListDeleteService attendanceListDeleteService;

    private final AttendanceListPostService attendanceListPostService;

    private final AttendanceListGetService attendanceListGetService;

    @GetMapping("attendancelist")
    public ResponseEntity<List<AttendanceListDTO>> getAllAttendenceLists() {
        return attendanceListGetService.getAllAttendanceLists();
    }

    @GetMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> getSingleAttendanceList(@PathVariable Integer id) {
        return attendanceListGetService.getSingleAttendanceList(id);
    }

    @DeleteMapping("attendancelist/{id}")
    public ResponseEntity<AttendanceListDTO> deleteAttendanceList(@PathVariable Integer id) {
        return attendanceListDeleteService.deleteAttendanceList(id);
    }

    @PostMapping("attendancelist")
    public ResponseEntity<AttendanceListPostDTO> addNewAttendanceList(@Valid @RequestBody AttendanceListRequestDTO attendanceListDTO) {
        return attendanceListPostService.addNewAttendanceList(attendanceListDTO);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
