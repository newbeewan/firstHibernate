package com.ib.data;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	private String numeroVoie;
	private String voie;
	private String codepostal;
	private String ville;
	
	public String getNumeroVoie() {
		return numeroVoie;
	}
	public void setNumeroVoie(String numeroVoie) {
		this.numeroVoie = numeroVoie;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getCodepostal() {
		return codepostal;
	}
	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
}
