package test.model;

import java.util.Date;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

import model.Tabela;
import model.TabelaNparaN;

public class Generos extends Tabela<Integer> {

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String getNomeTabela() {
		return "generos";
	}

	@Override
	public void setCamposTabela(List<Object> list) {
		this.setPk((Integer) list.get(0));
		this.setNome((String) list.get(1));
		this.setCriacao((Date) list.get(2));
		this.setModificacao((Date) list.get(3));

	}

	@Override
	public Tabela getNovoObjeto() {

		return new Generos();
	}

	@Override
	public String getNomePk() {

		return "generos_id";
	}

	@Override
	public List<Object> getCamposValor() {
		List<Object> list = new ArrayList<>();
		list.add(this.getPk());
		list.add(this.getNome());
		list.add(this.getCriacao());
		list.add(this.getModificacao());
		return list;
	}

	@Override
	public List<String> getCamposNome() {
		List<String> list = new ArrayList<>();
		list.add( getNomeTabela()+"."+getNomePk());
		list.add("nome");
		list.add("criacao");
		list.add("modificacao");
		return list;

	}

	@Override
	public Boolean isPkSerial() {

		return true;
	}


	@Override
	public String toString() {
		String campos = "";  
		for (Object string : getCamposValor()) {
			campos+= string+"\n";
		}
		
		return campos;
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
