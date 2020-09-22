package mensagem;

public class PersistenciaMensagem extends MensagemGeneric {

	public PersistenciaMensagem() {
		this.setMensagemErro();
		this.setMensagemSucesso();

	}

	@Override
	public Mensagem getMensagemErro(String chave,String mensagemServer) {
		String aux = this.hashtableErro.get(chave);
		Mensagem mensagem = null;
		if(aux!=null) {
			 mensagem = new Mensagem(chave, aux, mensagemServer, ERRO);
		}
		
		
		return mensagem;
	}

	@Override
	public Mensagem getMensagemSucesso(String chave) {
		String aux = this.hashtableSucesso.get(chave);
		Mensagem mensagem = null;
		if(aux!=null) {
			 mensagem = new Mensagem(chave, aux, null, SUCESSO);
		}
		
		
		return mensagem;
		

	}

	@Override
	public void setMensagemErro() {
		// TODO Auto-generated method stub
		this.hashtableErro.put("101", "Erro ao Deletar um registro na tabela ");
	}

	@Override
	public void setMensagemSucesso() {
		// TODO Auto-generated method stub
		this.hashtableSucesso.put("112", "Registro deletado da tabela com súcesso");

	}

}
