package br.com.jdsb.cfc;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.core.io.Resource;

import br.com.jdsb.negocio.menu.NavegarAte;
import br.com.jdsb.negocio.menu.NavegarAteImplements;
import br.com.jdsb.negocio.service.NegocioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public abstract class AbstractController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public NegocioService service;
	public Map<String, Resource> mapa;

	public void carregar(NegocioService service, Map<String, Resource> mapa,String formulario) {
		this.service = service;
		this.mapa = mapa;
		inicializar();
	}

	public void carregar(NegocioService service) {
		this.service = service;
		inicializar();
	}

	public abstract void inicializar();

	@FXML
	public Label lbMsg;

	@FXML
	void salvar(ActionEvent event) {

	}

	public void mensagem(String texto) {
		lbMsg.setText(texto);
	}

	public void Limpar() {
	}

	@FXML
	void cancelar(ActionEvent event) {

	}

	public void navegarAteCadastroCliente() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_cliente_fornec");
	}

	public void navegarAteCadastroBanco() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_banco");
	}

}
