package pl.ros1yn.attendancesoftware.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.DTO.KodowanieDTO;
import pl.ros1yn.attendancesoftware.DTO.KodowanieRequestDTO;
import pl.ros1yn.attendancesoftware.model.Kodowanie;
import pl.ros1yn.attendancesoftware.model.Student;
import pl.ros1yn.attendancesoftware.model.Zajecia;
import pl.ros1yn.attendancesoftware.repository.KodowanieRepository;
import pl.ros1yn.attendancesoftware.utils.kodowanie.KodowanieAddNew;
import pl.ros1yn.attendancesoftware.utils.kodowanie.KodowanieAttendanceChecker;
import pl.ros1yn.attendancesoftware.utils.kodowanie.KodowanieToDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KodowanieService {

    private final KodowanieRepository kodowanieRepository;

    private final KodowanieToDTO kodowanieToDTO;

    private final KodowanieAttendanceChecker kodowanieAttendanceChecker;

    private final KodowanieAddNew kodowanieAddNew;

    public ResponseEntity<List<KodowanieDTO>> getAllKodowanie() {
        Iterable<Kodowanie> allKodowanie = kodowanieRepository.findAll();

        List<KodowanieDTO> allKodowanieDTO = new ArrayList<>();

        for (Kodowanie kodowanie : allKodowanie) {
            allKodowanieDTO.add(kodowanieToDTO.convertToDTO(kodowanie));
        }

        return ResponseEntity.ok(allKodowanieDTO);
    }


    public ResponseEntity<KodowanieDTO> getKodowanie(Integer id) {

        return kodowanieRepository.findById(id)
                .map(kodowanieToDTO::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<KodowanieDTO> deleteKodowanieById(Integer id) {

        Optional<Kodowanie> foundedKodowanie = kodowanieRepository.findById(id);

        if (foundedKodowanie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        kodowanieRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<KodowanieDTO> addNewKodowanie(KodowanieRequestDTO requestDTO) {

        Student foundedStudent = kodowanieAttendanceChecker.attendanceCheckerForStudent(requestDTO);

        Zajecia foundedZajecia = kodowanieAttendanceChecker.attendancecheckerForZajecia(requestDTO);

        Kodowanie kodowanie = kodowanieAddNew.getNewKodowanie(requestDTO, foundedStudent, foundedZajecia);

        Kodowanie savedKodowanie = kodowanieRepository.save(kodowanie);

        return ResponseEntity.ok(kodowanieToDTO.convertToDTO(savedKodowanie));

    }




}
