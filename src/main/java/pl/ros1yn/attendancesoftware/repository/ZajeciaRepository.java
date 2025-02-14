package pl.ros1yn.attendancesoftware.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ros1yn.attendancesoftware.model.Zajecia;

public interface ZajeciaRepository extends CrudRepository<Zajecia, Integer> {
}
