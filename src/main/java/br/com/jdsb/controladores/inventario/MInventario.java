package br.com.jdsb.controladores.inventario;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Movimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MInventario extends AbstractController<Movimento> {

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTableView() {
		// TODO Auto-generated method stub

	}

	@FXML
	private DatePicker dtInventario;

	@FXML
	private ChoiceBox<?> tpBanco;

	@FXML
	private TextField vlNovoSaldo;

	@FXML
	private TextArea dsObvservacao;

	@FXML
	private TableView<?> tblInventario;

	@FXML
	private TableColumn<?, ?> tbcBanco;

	@FXML
	private TableColumn<?, ?> tncDtInventario;

	@FXML
	private TableColumn<?, ?> tbcVlSaldoAnterior;

	@FXML
	private TableColumn<?, ?> tbcVlNovoSaldo;

	@FXML
	private TableColumn<?, ?> tbcDsObservacao;

	@FXML
	private Label lbMsg;

	@FXML
	private TextField vlSaldoAtual;

	@FXML
	void salvar(ActionEvent event) {

	}

}
