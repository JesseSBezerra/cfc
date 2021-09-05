package br.com.jdsb.controladores.banco;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Banco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MCadastroBanco extends AbstractController<Banco> implements Initializable {

	@Override
	public void inicializar() {
		carregarDados();
		carregarCrud(service.getBancoDao());
		preencherTabelaTela(tblBanco);
	}

	@FXML
	private TextField nmBanco;

	@FXML
	private TextField dsCodigoBanco;

	@FXML
	private TableView<Banco> tblBanco;

	@FXML
	private TableColumn<Banco, String> tbcDsCodigoBanco;

	@FXML
	private TableColumn<Banco, String> tbcNmBanco;

	@FXML
	public void salvar(ActionEvent event) {
		if (objetoSelecionado == null) {
			objetoSelecionado = new Banco();
			objetoSelecionado.setDsCodigoBanco(dsCodigoBanco.getText());
			objetoSelecionado.setNmBanco(nmBanco.getText());
			service.getBancoDao().save(objetoSelecionado);
			mensagem("Registro Salvo com Sucesso!");
			carregarDados();
			clear();
		} else {
			objetoSelecionado.setDsCodigoBanco(dsCodigoBanco.getText());
			objetoSelecionado.setNmBanco(nmBanco.getText());
			service.getBancoDao().save(objetoSelecionado);
			mensagem("Registro Atualizado com Sucesso!");
			carregarDados();
			clear();
		}

	}

	public void carregarDados() {
		carregarTabelaDeDados(this.service.getBancoDao());
		tbcDsCodigoBanco.setCellValueFactory(new PropertyValueFactory<Banco, String>("dsCodigoBanco"));
		tbcNmBanco.setCellValueFactory(new PropertyValueFactory<Banco, String>("nmBanco"));

		tblBanco.setItems(populateTableDados);
	}

	@FXML
	public void cancelar(ActionEvent event) {

	}

	@Override
	public void beforeClear() {
		dsCodigoBanco.setText(null);
		nmBanco.setText(null);

	}

	@Override
	public void afterTableView() {
		nmBanco.setText(objetoSelecionado.getNmBanco());
		dsCodigoBanco.setText(objetoSelecionado.getDsCodigoBanco());

	}

}
