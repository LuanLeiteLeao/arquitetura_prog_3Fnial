package mensagem;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Mensagem {
	private String status;
	private String mensagemCliente;
	private String mensagemServer;
	private String tipoMensagem;
	private boolean isSucesso;

	public boolean isSucesso() {
		return isSucesso;
	}

	public void setSucesso(boolean isSucesso) {
		this.isSucesso = isSucesso;
	}

	public Mensagem(String status, String mensagemCliente, String mensagemServer,String tipoMensagem) {
		setStatus(status);
		setMensagemCliente(mensagemCliente);
		setMensagemServer(mensagemServer);
		setTipoMensagem(tipoMensagem);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagemCliente() {
		return mensagemCliente;
	}

	public void setMensagemCliente(String mensagemCliente) {
		this.mensagemCliente = mensagemCliente;
	}

	public String getMensagemServer() {
		return mensagemServer;
	}

	public void setMensagemServer(String mensagemServer) {
		this.mensagemServer = mensagemServer;
	}

	public String getTipoMensagem() {
		return tipoMensagem;
	}

	public void setTipoMensagem(String tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}

	@Override
	public String toString() {
		return 	"status: "+status+" \n"+
				"mensagem cliente: "+mensagemCliente+" \n"+
				"mensagem server: "+mensagemServer+" \n"+
				"tipo mensagem: "+tipoMensagem;
		
	}
}
