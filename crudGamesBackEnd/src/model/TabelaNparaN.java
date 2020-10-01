package model;

/***
 * Esta classe e responsavel por armazenar tres tabelas em relacao muitos para
 * muitos, de forma a simplificar a armazenagem de dados.
 * 
 * @author User
 *
 */
public class TabelaNparaN {
	private Tabela tabelaAux;
	private Tabela tabelaUm;
	private Tabela tabelaMuitos;

	public TabelaNparaN(Tabela tabelaAux, Tabela tabelaUm, Tabela tabelaMuitos) {
		this.tabelaAux = tabelaAux;
		this.tabelaUm = tabelaUm;
		this.tabelaMuitos = tabelaMuitos;
	}

	/***
	 * Retorna o atributo tabelaAux do tipo Tabela.
	 * 
	 * @return tabelaAux
	 */
	public Tabela getTabelaAux() {
		return tabelaAux;
	}

	/***
	 * Define valor do atributo tabelaAux.
	 * 
	 * @param tabelaAux
	 */
	public void setTabelaAux(Tabela tabelaAux) {
		this.tabelaAux = tabelaAux;
	}

	/***
	 * Retorna o atributo TabelaUm do tipo Tabela.
	 * 
	 * @return TabelaUm
	 */
	public Tabela getTabelaUm() {
		return tabelaUm;
	}

	/***
	 * Define valor do atributo TabelaUm.
	 * 
	 * @param tabelaUm
	 */
	public void setTabelaUm(Tabela tabelaUm) {
		this.tabelaUm = tabelaUm;
	}

	/***
	 * Retorna o atributo TabelaMuitos do tipo Tabela.
	 * 
	 * @return TabelaMuitos
	 */
	public Tabela getTabelaMuitos() {
		return tabelaMuitos;
	}

	/***
	 * Define valor do atributo TabelaMuitos.
	 * 
	 * @param tabelaMuitos
	 */
	public void setTabelaMuitos(Tabela tabelaMuitos) {
		this.tabelaMuitos = tabelaMuitos;
	}

}
