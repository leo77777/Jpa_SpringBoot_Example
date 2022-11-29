package fr.leo.tpjpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.leo.tpjpa.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	public List<Patient> findByNomContains(String name);
	public List<Patient> findByScore( Integer score);
	public List<Patient> findByNomContainsAndScore(String name, Integer score  );
	public List<Patient> findByNomContainsOrScore(String name, Integer score  );
	
	public Page<Patient> findByNomContains(String name, Pageable pa);
}
