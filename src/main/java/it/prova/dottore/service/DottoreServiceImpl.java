package it.prova.dottore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.dottore.model.Dottore;
import it.prova.dottore.repository.DottoreRepository;

@Service
public class DottoreServiceImpl implements DottoreService {
	@Autowired
	private DottoreRepository dottoreRepository;

	@Override
	@Transactional(readOnly = true)
	public Dottore findByNomeECognome(String string, String string2) {
		return dottoreRepository.findByNomeAndCognome(string, string2).orElse(null);
	}

	@Override
	@Transactional
	public Dottore inserisciNuovo(Dottore dottore) {
		dottore.setCodiceDipendente(dottore.getNome().toLowerCase().charAt(0) + dottore.getCognome().toLowerCase());
		dottore.setInServizio(true);
		dottore.setInVisita(false);
		return dottoreRepository.save(dottore);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dottore> listAllElements() {
		return (List<Dottore>) dottoreRepository.findAll();
	}

	@Override
	@Transactional
	public Dottore caricaSingoloElemento(long id) {
		return dottoreRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Dottore aggiorna(Dottore buildDottoreModel) {
		return dottoreRepository.save(buildDottoreModel);
	}

	@Override
	@Transactional
	public void rimuovi(Dottore dottore) {
		dottoreRepository.delete(dottore);
	}

	@Override
	@Transactional(readOnly = true)
	public Dottore caricaPerCodiceDipendente(String codiceDipendente) {
		return dottoreRepository.findFirstByCodiceDipendente(codiceDipendente).orElse(null);
	}

}
