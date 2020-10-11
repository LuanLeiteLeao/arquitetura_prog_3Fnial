package test.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.Tabela;
import model.TabelaNparaN;
import util.ConversoDeLista;

public class Games extends Tabela<Integer> {

	private String nome;
	private String descricao;
	private Date dataLancamento;
	private Integer isJogoDoAno;
	private List<Plataformas> plataformas;
	private List<Generos> generos;

	public List<Plataformas> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Plataformas> plataformas) {
		this.plataformas = plataformas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date data_lancamento) {
		this.dataLancamento = data_lancamento;
	}

	public Integer getIsJogoDoAno() {
		return isJogoDoAno;
	}

	public void setIsJogoDoAno(Integer is_jogo_do_ano) {
		this.isJogoDoAno = is_jogo_do_ano;
	}

	@Override
	public String getNomeTabela() {
		// TODO Auto-generated method stub
		return "games";
	}

	@Override
	public void setCamposTabela(List<Object> list) {
		setPk((Integer) list.get(0));
		setNome((String) list.get(1));
		setDescricao((String) list.get(2));
		setDataLancamento((Date) list.get(3));
		setIsJogoDoAno((Integer) list.get(4));
		setCriacao((Date) list.get(5));
		setModificacao((Date) list.get(6));
	}

	@Override
	public Tabela getNovoObjeto() {

		return new Games();
	}

	@Override
	public String getNomePk() {

		return "games_id";
	}

	@Override
	public List<Object> getCamposValor() {
		List<Object> list = new ArrayList<>();

		list.add(getNomePk());
		list.add(getNome());
		list.add(getDescricao());
		list.add(getDataLancamento());
		list.add(getIsJogoDoAno());
		list.add(getCriacao());
		list.add(getModificacao());
		return list;
	}

	@Override
	public List<String> getCamposNome() {
		List<String> list = new ArrayList<>();
		list.add("games_id");
		list.add("nome");
		list.add("descricao");
		list.add("data_lancamento");
		list.add("is_jogo_do_ano");
		list.add("criacao");
		list.add("modificacao");

		return list;
	}

	@Override
	public Boolean isPkSerial() {

		return true;
	}
	
	@Override
	public ArrayList<TabelaNparaN> getTabelasNparaN() {
		ArrayList<TabelaNparaN> list = new ArrayList<>();
		//rever se precisa mesmo do this
		TabelaNparaN muitasPlataformas = new TabelaNparaN(new GamesHasPlataformas(), this, new Plataformas());
		TabelaNparaN muitasGeneros = new TabelaNparaN(new GamesHasGeneros(), this, new Generos());
		list.add(muitasPlataformas);
		list.add(muitasGeneros);

		return list;
	}

	@Override
	public void setCamposTabelasNparaN(List<List<Tabela<?>>> list) {
//		posso melhorar isso para ficar mais inchuto
		this.setPlataformas(new ConversoDeLista<Tabela<?>, Plataformas>().convertLista(list.get(0)));
		this.setGeneros(new ConversoDeLista<Tabela<?>, Generos>().convertLista(list.get(1)));

	}

	public List<Generos> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Generos> generos) {
		this.generos = generos;
	}

}
