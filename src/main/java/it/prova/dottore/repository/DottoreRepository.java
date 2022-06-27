package it.prova.dottore.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.dottore.model.Dottore;

public interface DottoreRepository extends CrudRepository<Dottore, Long> {

	Optional<Dottore> findByNomeAndCognome(String string, String string2);

	Optional<Dottore> findFirstByCodiceDipendente(String codiceDipendente);

}
