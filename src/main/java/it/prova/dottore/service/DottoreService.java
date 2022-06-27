package it.prova.dottore.service;

import java.util.List;

import it.prova.dottore.model.Dottore;

public interface DottoreService {

	Dottore findByNomeECognome(String string, String string2);

	Dottore inserisciNuovo(Dottore dottore);

	List<Dottore> listAllElements();

	Dottore caricaSingoloElemento(long id);

	Dottore aggiorna(Dottore buildDottoreModel);

	void rimuovi(Dottore dottore);

	Dottore caricaPerCodiceDipendente(String codiceDipendente);

}
