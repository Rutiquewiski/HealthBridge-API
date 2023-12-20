package br.rutiquewiski.HealthBridge.controllers;


import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorDetailsDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorListingDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.repositories.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerDoctor(@RequestBody @Valid DoctorRegistrationDTO doctorRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var doctor = new Doctor(doctorRegistrationDTO);

        doctorRepository.save(doctor);

        var uri = uriComponentsBuilder.path("/api/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors(@RequestParam(required = false) String name, Pageable pageable) {

        Page<DoctorListingDTO> doctors;

        if (name != null) {

            doctors = doctorRepository.findAllByActiveTrueAndNameContaining(pageable, name).map(DoctorListingDTO::new);

        } else {

            doctors = doctorRepository.findAllByActiveTrue(pageable).map(DoctorListingDTO::new);
        }

        if (doctors.isEmpty()) {

            return ResponseEntity.ok("Empty list");

        } else {

            return ResponseEntity.ok(doctors);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorDetails(@PathVariable Integer id) {

        Doctor doctor = doctorRepository.findByIdAndActiveTrue(id);

        if (doctor == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
        }
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Integer id, @RequestBody DoctorUpdateDTO doctorUpdateDTO) {

        Doctor doctor = doctorRepository.findByIdAndActiveTrue(id);

        if (doctor == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            doctor.updateInformation(doctorUpdateDTO);
            doctorRepository.save(doctor);
            return ResponseEntity.ok("Doctor information updated");
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer id) {

        Doctor doctor = doctorRepository.findByIdAndActiveTrue(id);

        if (doctor == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            doctor.safeDelete();
            doctorRepository.save(doctor);
            return ResponseEntity.ok("Doctor deleted");
        }
    }
}
