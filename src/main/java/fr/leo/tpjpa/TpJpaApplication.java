package fr.leo.tpjpa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.leo.tpjpa.entities.Patient;
import fr.leo.tpjpa.repositories.PatientRepository;
import fr.leo.tpjpa.web.PatientsController;

@SpringBootApplication
public class TpJpaApplication implements CommandLineRunner {
	
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Rere", new Date(), 2300, false));
		patientRepository.save(new Patient(null, "Vava", new Date(), 230, false));
		patientRepository.save(new Patient(null, "Lala", new Date(), 23, false));
		patientRepository.save(new Patient(null, "Toto", new Date(), 25, false));
		patientRepository.save(new Patient(null, "Kyky", new Date(), 28, true	));
		
		System.out.println("********************************");
		patientRepository.findAll().forEach(p->{
			System.out.println(p);
		});
		
		System.out.println("********************************");
		System.out.println(patientRepository.findById(3L).get());
		
		System.out.println("********************************");
		List<Patient> list = patientRepository.findByNomContains("a");
		for (Patient patient : list) {
			System.out.println("Voici : " + patient);
		}
		
		System.out.println("********************************");
		list.forEach(p->{
			System.out.println("Voici la personne: " + p);
		});
		
		System.out.println("********************************");
		List<Patient> listSCore = patientRepository.findByScore(23);
		for (Patient patient : listSCore) {
			System.out.println(patient.getNom()	+ " a le score: " + patient.getScore());
		}
		
		System.out.println("********************************");
		List<Patient> listSNomCore = patientRepository.findByNomContainsAndScore( "a", 23);
		for (Patient patient : listSNomCore) {
			System.out.println("findByNomContainsAndScore => " + patient.getNom()	+ " a le score: " + patient.getScore());
		}
		
		System.out.println("********************************");
		List<Patient> listSNomCore2 = patientRepository.findByNomContainsOrScore( "a", 23);
		for (Patient patient : listSNomCore2) {
			System.out.println("findByNomContainsOrScore => " + patient.getNom()	+ " a le score: " + patient.getScore());
		}
		
		//patientRepository.deleteById(4L);
		System.out.println("********************************");
		List<Patient> listAll = patientRepository.findAll();
		for (Patient patient : listAll) {
			System.out.println("Voici : " + patient);
		}
		
		System.out.println("********************************");
		Page<Patient> pagePatients =  patientRepository.findAll(PageRequest.of(0, 3));
		List<Patient> pageDePatients = pagePatients.getContent();
		System.out.println("Nombre de pages : " + pagePatients.getTotalPages());
		pageDePatients.forEach(p->{
			System.out.println("Page : " + p);
		});
		
		
		System.out.println("********************************");
		Page<Patient> pagePatients2 = patientRepository.findByNomContains(
								"a", PageRequest.of(0, 2));
		pagePatients2.getContent().forEach(p->{
			System.out.println("findByNomContains Par Page : " + p);
		});
		
		
		PatientsController p = new PatientsController();
		System.out.println("VOILA : " + p.patientRepository);
		
		
		System.out.println("********************************");
		System.out.println("");
		
	}

}
