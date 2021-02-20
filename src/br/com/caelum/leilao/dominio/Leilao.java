package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if((lances.isEmpty()
				|| !ultimoLance().getUsuario().equals(lance.getUsuario()))
				&& qtdLances(lance.getUsuario()) < 5) lances.add(lance);
	}

	public Lance ultimoLance() {
		return lances.get(lances.size() - 1);
	}

	public int qtdLances(Usuario usuario) {
		int total = 0;
		for (Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)){
				total += 1;
			}
		}

		return total;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
