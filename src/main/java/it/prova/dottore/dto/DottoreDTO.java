package it.prova.dottore.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.dottore.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDipendente;
	private Boolean inServizio;
	private Boolean inVisita;

	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendente, Boolean inServizio,
			Boolean inVisita) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.inServizio = inServizio;
		this.inVisita = inVisita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDipendente() {
		return codiceDipendente;
	}

	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public Boolean getInServizio() {
		return inServizio;
	}

	public void setInServizio(Boolean inServizio) {
		this.inServizio = inServizio;
	}

	public Boolean getInVisita() {
		return inVisita;
	}

	public void setInVisita(Boolean inVisita) {
		this.inVisita = inVisita;
	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> listAllElements) {
		return listAllElements.stream().map(dottore -> DottoreDTO.buildDottoreDTOFromModel(dottore))
				.collect(Collectors.toList());
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottore) {
		return new DottoreDTO(dottore.getId(), dottore.getNome(), dottore.getCognome(), dottore.getCodiceDipendente(),
				dottore.getInServizio(), dottore.getInVisita());
	}

	public Dottore buildDottoreModel() {
		return new Dottore(id, nome, cognome, codiceDipendente, inServizio, inVisita);
	}
}
