package view;

import java.util.Hashtable;

import controle.ICrudControler;

public abstract class ViewGerenic {
	public Hashtable<String, ICrudControler> casoDeUso;
	
	public ViewGerenic() {
		this.casoDeUso=this.casoDeUsoValido();
	}
	
	protected abstract Hashtable<String, ICrudControler> casoDeUsoValido();
	
	public ICrudControler getCasoDeUso(String casoDeUsoNome) {
		ICrudControler casoDeUso = this.casoDeUso.get(casoDeUsoNome);
		if(casoDeUso == null) {
			System.out.println("Modelo não existe");
		}
		return casoDeUso;
	}
}
