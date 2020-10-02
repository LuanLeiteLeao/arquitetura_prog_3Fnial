package controle;

import java.util.ArrayList;

import model.Tabela;
import test.Pessoa;
import test.model.Games;

/***
 * A classe e responsavel por gerenciar as operacoes de manutencao do modelo de tabela
 * Games, tendo como classe extendida ControleGeneric.
 * 
 * 
 * @author User
 *
 */
public class ManterGame extends ControleGeric<Pessoa, Integer> {

	/***
	 * Cria um novo objeto do tipo Games.
	 */
	@Override
	public Tabela getNovoObjetoModelo() {

		return new Games();
	}

}
