package fr.leo.tpjpa.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Access;
import javax.persistence.AccessType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.leo.tpjpa.entities.Patient;
import fr.leo.tpjpa.repositories.PatientRepository;

@RestController
public class PatientsController {
	
	@Autowired
	public PatientRepository patientRepository;


	@GetMapping("/patients/{id}")
	public Patient patients(@PathVariable Long id) {
		return patientRepository.findById(id).get();
	}
	
	@GetMapping("/patients")
	public List<Patient> patients() {
		return patientRepository.findAll();
	}
	
	@GetMapping("/patients/ajout/{id}")
	public void ajoutPatient(@PathVariable Long id) {
		Patient patient = new Patient(id, "Rere", new Date(), 23, false);
		patientRepository.save(patient);
		patientRepository.flush();
	}

}
