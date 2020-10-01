package util;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * Esta classe e responsavel por receber um tipo de lista e um tipo de lista
 * para converter a primeira, atravez de uma iteracao a lista e convertida para
 * o tipo desejado.
 * 
 * @author User
 *
 * @param <TipoLista>
 * @param <TipoConverter>
 * @return newList
 */
public class ConversoDeLista<TipoLista, TipoConverter> {

	public List<TipoConverter> convertLista(List<TipoLista> list) {
		List<TipoConverter> newList = new ArrayList<>();

		for (TipoLista auxList : list) {
			newList.add((TipoConverter) auxList);
		}

		return newList;
	}
}
