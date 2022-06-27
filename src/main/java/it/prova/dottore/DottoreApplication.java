package it.prova.dottore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.dottore.model.Dottore;
import it.prova.dottore.service.DottoreService;

@SpringBootApplication
public class DottoreApplication implements CommandLineRunner{
	@Autowired
	private DottoreService dottoreService;

	public static void main(String[] args) {
		SpringApplication.run(DottoreApplication.class, args);
	}

	public void run(String... args) throws Exception {
		if (dottoreService.findByNomeECognome("Gaetano", "Di Pasquale") == null) {
			dottoreService.inserisciNuovo(new Dottore("Gaetano", "Di Pasquale"));
		}
		
	}

}
