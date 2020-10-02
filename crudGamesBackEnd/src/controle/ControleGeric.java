package controle;

import java.util.ArrayList;
import java.util.List;

import mensagem.Mensagem;
import model.Tabela;
import persistencia.DAO;

/***
 * A classe tem a responsabilidade de gerenciar de forma generica as operacoes
 * de manutencao de qualquer modelo de tabela.
 * 
 * @author User
 *
 * @param <T>
 * @param <TipoPK>
 */
public abstract class ControleGeric<T extends Tabela<TipoPK>, TipoPK>
		implements ICrudControler<Tabela<TipoPK>, TipoPK> {
	private DAO persistencias;

	/***
	 * Cria uma instancia da classe DAO.
	 */
	public ControleGeric() {
		this.persistencias = new DAO();
	}

	/***
	 * Lista a tabela recebida.
	 */
	@Override
	public <T extends Tabela<?>> ArrayList<T> listar() {

		return this.persistencias.listar(this.getNovoObjetoModelo());
	}
	/***
	 * Remove a tabela recebida.
	 */
	@Override
	public Mensagem remover(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}
	/***
	 * Edita a tabela recebida.
	 */
	@Override
	public Mensagem editar(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}
	/***
	 * Insere a tabela recebida.
	 */
	@Override
	public Mensagem inserir(Tabela<TipoPK> objT) {
		// TODO Auto-generated method stub
		return null;
	}
	/***
	 * ?
	 * @return
	 */
	public abstract Tabela getNovoObjetoModelo();
	/***
	 * ?
	 */
	@Override
	public <T extends Tabela<?>> T pegar() {
		// TODO Auto-generated method stub
		return null;
	}

}
