package test.model;

import java.util.Date;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

import model.Tabela;

public class Plataformas extends Tabela<Integer> {

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String getNomeTabela() {
		return "plataformas";
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

		return new Plataformas();
	}

	@Override
	public String getNomePk() {

		return "plataformas_id";
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
	public <T extends Tabela<?>> List<List<T>> getNparaN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Tabela<?>> List<T> tabelasEstrangeirasNparaN() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String campos = "";  
		for (Object string : getCamposValor()) {
			campos+= string+"\n";
		}
		
		return campos;
	}
}
