package controle;

import java.util.ArrayList;
import java.util.List;

import mensagem.Mensagem;
import model.Tabela;

public interface ICrudControler <T extends Tabela<TipoPK>,TipoPK> {
	public  <T extends Tabela<?>> ArrayList<T>  listar();
	public <T extends Tabela<?>> T pegar();
	public Mensagem remover(T objT);
	public Mensagem editar(T objT);
	public Mensagem inserir(T objT);
}
