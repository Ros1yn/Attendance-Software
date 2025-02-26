package pl.ros1yn.attendancesoftware.attendance.mapper;

import org.mapstruct.*;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTO;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.utils.StudentMapperHelper;

@Mapper(componentModel = "spring", uses = StudentMapperHelper.class)
public interface AttendanceMapper {

    @Mapping(target = "student", source = "indexNumber", qualifiedByName = "mapIndexToStudent")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAttendanceFromUpdateDTO(AttendanceUpdateDTO updateDTO, @MappingTarget Attendance attendance);

    @Mapping(target = "studentDTO", source = "student")
    AttendanceDTO toDTO(Attendance attendance);


}
