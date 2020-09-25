package model;

public class TabelaNparaN {
	private Tabela tabelaAux;
	private Tabela tabelaUm;
	private Tabela tabelaMuitos;
	
	public TabelaNparaN(Tabela tabelaAux,Tabela tabelaUm,Tabela tabelaMuitos) {
		this.tabelaAux=tabelaAux;
		this.tabelaUm=tabelaUm;
		this.tabelaMuitos=tabelaMuitos;
	}
	
	public Tabela getTabelaAux() {
		return tabelaAux;
	}
	public void setTabelaAux(Tabela tabelaAux) {
		this.tabelaAux = tabelaAux;
	}
	public Tabela getTabelaUm() {
		return tabelaUm;
	}
	public void setTabelaUm(Tabela tabelaUm) {
		this.tabelaUm = tabelaUm;
	}
	public Tabela getTabelaMuitos() {
		return tabelaMuitos;
	}
	public void setTabelaMuitos(Tabela tabelaMuitos) {
		this.tabelaMuitos = tabelaMuitos;
	}
	
	 
	
	
	
}
