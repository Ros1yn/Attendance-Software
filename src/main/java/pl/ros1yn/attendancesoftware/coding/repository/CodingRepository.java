package pl.ros1yn.attendancesoftware.coding.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.coding.model.Coding;

public interface CodingRepository extends CrudRepository<Coding, Integer> {
}
