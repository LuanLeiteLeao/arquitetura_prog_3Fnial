package test.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import model.Tabela;
import model.TabelaNparaN;

public class GamesHasGeneros extends Tabela<Integer> {

	private Integer gamesId;
	private Integer generosformasId;

	public Integer getGamesId() {
		return gamesId;
	}

	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}

	public Integer getPlataformasId() {
		return generosformasId;
	}

	public void setPlataformasId(Integer plataformasId) {
		this.generosformasId = plataformasId;
	}

	@Override
	public String getNomeTabela() {
		// TODO Auto-generated method stub
		return "games_has_generos";
	}

	@Override
	public void setCamposTabela(List<Object> list) {
		setGamesId((Integer) list.get(0));
		setPlataformasId((Integer) list.get(1));

	}

	@Override
	public Tabela getNovoObjeto() {
		return new GamesHasGeneros();
	}

	@Override
	public String getNomePk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getCamposValor() {
		List<Object> list = new ArrayList<>();

		list.add(getGamesId());
		list.add(getPlataformasId());

		return list;
	}

	@Override
	public List<String> getCamposNome() {
		List<String> list = new ArrayList<>();

		list.add(getNomePk());
		list.add("generos_id");

		return list;
	}

	@Override
	public Boolean isPkSerial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TabelaNparaN> getTabelasNparaN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCamposTabelasNparaN(List<List<Tabela<?>>> list) {
		// TODO Auto-generated method stub
		
	}




	
}
