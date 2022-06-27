package it.prova.dottore.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.dottore.dto.DottoreDTO;
import it.prova.dottore.model.Dottore;
import it.prova.dottore.service.DottoreService;


@RestController
@RequestMapping("api/dottore")
public class DottoreController {
	@Autowired
	private DottoreService dottoreService;
	
	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAllElements());
	}

	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(value = "id", required = true) long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id);

		if (dottore == null)
			throw new RuntimeException("Dottore not found con id: " + id);

		return DottoreDTO.buildDottoreDTOFromModel(dottore);
	}
	
	@GetMapping("/verifica/{codiceDipendente}")
	public DottoreDTO findByCodice(@PathVariable(value = "codiceDipendente", required = true) String codiceDipendente) {
		Dottore dottore = dottoreService.caricaPerCodiceDipendente(codiceDipendente);

		if (dottore == null)
			throw new RuntimeException("Dottore not found con Codice: " + codiceDipendente);

		return DottoreDTO.buildDottoreDTOFromModel(dottore);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO input) {
		if (input.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		Dottore dottoreInserito = dottoreService.inserisciNuovo(input.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreInserito);
	}

	@PutMapping("/{id}")
	public DottoreDTO update(@RequestBody DottoreDTO input, @PathVariable(required = true) Long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id);

		if (dottore == null)
			throw new RuntimeException("Dottore not found con id: " + id);

		input.setId(id);
		Dottore dottoreAggiornato = dottoreService.aggiorna(input.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreAggiornato);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Dottore dottore = dottoreService.caricaSingoloElemento(id);

		if (dottore == null)
			throw new RuntimeException("Dottore not found con id: " + id);
		

		dottoreService.rimuovi(dottore);
	}
	
	@PostMapping("/impostaInVisita/{codiceDipendente}")
	@ResponseStatus(HttpStatus.CREATED)
	public void impostaInVisita(@PathVariable(value = "codiceDipendente", required = true) String codiceDipendente) {
		Dottore dottore = dottoreService.caricaPerCodiceDipendente(codiceDipendente);
		if (dottore == null)
			throw new RuntimeException("Dottore not found con Codice: " + codiceDipendente);
		
		dottore.setInVisita(true);
		dottoreService.aggiorna(dottore);
	}
}
