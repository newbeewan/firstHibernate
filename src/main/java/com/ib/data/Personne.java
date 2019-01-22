package com.ib.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nom;

	private String prenom;

	@ManyToMany(mappedBy = "personnes")
	private List<Loisir> loisirs = new ArrayList<>();

	@Embedded
	private Adresse adresse;

	/**
	 * Surcharge des noms de colonnes de l'objet Adresse
	 */
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "numeroVoie", column = @Column(name = "numeroVoieFacturation")),
		@AttributeOverride(name = "voie", column = @Column(name = "voieFacturation")),
		@AttributeOverride(name = "codepostal", column = @Column(name = "codepostalFacturation")),
		@AttributeOverride(name = "ville", column = @Column(name = "villeFacturation")) })
	private Adresse adresseFacturation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Loisir> getLoisirs() {
		return loisirs;
	}

	public void setLoisirs(List<Loisir> loisirs) {
		this.loisirs = loisirs;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
