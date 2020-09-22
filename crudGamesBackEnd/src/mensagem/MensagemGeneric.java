package mensagem;

import java.util.Hashtable;

public abstract class MensagemGeneric  {
	protected final static String ERRO ="ERRO";
	protected final static String SUCESSO ="SÚCESSO"; 
	protected  Hashtable<String, String> hashtableErro=new Hashtable<String, String>();
	protected  Hashtable<String, String> hashtableSucesso=new Hashtable<String, String>();
	public abstract Mensagem getMensagemErro(String chave,String mensagemServer);
	public abstract Mensagem getMensagemSucesso(String chave);
	protected abstract void setMensagemErro();
	protected abstract  void setMensagemSucesso();
	
//deixar ela mais genérica 
//	tem metodos que deveria esta aqui 
}
