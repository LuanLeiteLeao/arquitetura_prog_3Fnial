package controle;

import java.util.ArrayList;
import java.util.List;

import mensagem.Mensagem;
import model.DAOGeneric;
import model.Tabela;

public class ControleGeric<T extends Tabela<TipoPK>,TipoPK>  implements ICrudControler<Tabela<TipoPK>, TipoPK>{
	private DAOGeneric persistencias;
	
	public ControleGeric() {
		this.persistencias = new DAOGeneric();
	}

	@Override
	public <T extends Tabela<?>> ArrayList<T> listar() {
		
		return this.persistencias.listar(this.getNovoObjeto()); 
	}

	@Override
	public Mensagem remover(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem editar(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensagem inserir(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tabela getNovoObjeto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Tabela<?>> T pegar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
