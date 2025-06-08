package pl.ros1yn.attendancesoftware.coding.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.coding.repository.CodingRepository;
import pl.ros1yn.attendancesoftware.exception.CodingNotFoundException;
import pl.ros1yn.attendancesoftware.utils.Finder;

@Component
@AllArgsConstructor
@Slf4j
public class CodingFinder implements Finder<Coding, Integer> {

    private final CodingRepository codingRepository;

    @Override
    public Coding find(Integer codingId) {
        return codingRepository.findById(codingId)
                .orElseThrow(() -> {
                    log.error("Coding was not found");
                    return new CodingNotFoundException();
                });
    }
}
