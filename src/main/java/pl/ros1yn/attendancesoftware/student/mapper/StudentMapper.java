package pl.ros1yn.attendancesoftware.student.mapper;

import org.mapstruct.Mapper;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDTO(Student student);

}
