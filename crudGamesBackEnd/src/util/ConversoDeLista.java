package util;

import java.util.ArrayList;
import java.util.List;


public class ConversoDeLista<TipoLista,TipoConverter> {
	
	
	public List<TipoConverter> convertLista(List<TipoLista> list) {
		List<TipoConverter> newList = new ArrayList<>();

		for (TipoLista auxList : list) {
			newList.add((TipoConverter) auxList);
		}

		return newList;
	}
}
