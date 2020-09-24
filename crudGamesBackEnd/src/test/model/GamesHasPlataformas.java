package test.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import model.Tabela;

public class GamesHasPlataformas extends Tabela<Integer> {

	private Integer gamesId;
	private Integer plataformasId;

	public Integer getGamesId() {
		return gamesId;
	}

	public void setGamesId(Integer gamesId) {
		this.gamesId = gamesId;
	}

	public Integer getPlataformasId() {
		return plataformasId;
	}

	public void setPlataformasId(Integer plataformasId) {
		this.plataformasId = plataformasId;
	}

	@Override
	public String getNomeTabela() {
		// TODO Auto-generated method stub
		return "games_has_plataformas";
	}

	@Override
	public void setCamposTabela(List<Object> list) {
		setGamesId((Integer) list.get(0));
		setPlataformasId((Integer) list.get(1));

	}

	@Override
	public Tabela getNovoObjeto() {
		return new GamesHasPlataformas();
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

		list.add("games_id");
		list.add("plataformas_id");

		return list;
	}

	@Override
	public Boolean isPkSerial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends Tabela<?>> List<List<T>> getNparaN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Tabela<?>> List<T> tabelasEstrangeirasNparaN() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
