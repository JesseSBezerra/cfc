package br.com.jdsb.controladores.despesa;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Despesa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MDespesa extends AbstractController<Despesa> {

	@Override
	public void inicializar() {
		ObservableList<String> optionDespesas = FXCollections.observableArrayList("Despesa Com Produtos",
				"Despesa Com Serviços", "Despesa Financeira", "Outras Despesas");
		tpDespesa.setItems(optionDespesas);
		tpDespesa.setValue("Despesa Com Produtos");
		carregarDados();
		carregarCrud(service.getDesepesaDao());
		preencherTabelaTela(tblDespesa);

	}

	@FXML
	private ChoiceBox<String> tpDespesa;

	@FXML
	private TextField dsDespesa;

	@FXML
	private TableView<Despesa> tblDespesa;

	@FXML
	private TableColumn<Despesa, String> tbcTpDespesa;

	@FXML
	private TableColumn<Despesa, String> tbcDsDespesa;

	@FXML
	public void cancelar(ActionEvent event) {

	}


	@FXML
	public void salvar(ActionEvent event) {
		if (objetoSelecionado == null) {
			objetoSelecionado = new Despesa();
			objetoSelecionado.setDsDespesa(dsDespesa.getText());
			objetoSelecionado.setTpDespesa(tpDespesa.getValue());
			this.service.getDesepesaDao().save(objetoSelecionado);
			mensagem("Registro Salvo com Sucesso!");
			carregarDados();
			clear();
		} else {
			objetoSelecionado.setDsDespesa(dsDespesa.getText());
			objetoSelecionado.setTpDespesa(tpDespesa.getValue());
			this.service.getDesepesaDao().save(objetoSelecionado);
			mensagem("Registro Atualizado com Sucesso!");
			carregarDados();
			clear();
		}

	}

	public void carregarDados(){
		carregarTabelaDeDados(this.service.getDesepesaDao());
		tbcDsDespesa.setCellValueFactory(new PropertyValueFactory<Despesa, String>("dsDespesa"));
		tbcTpDespesa.setCellValueFactory(new PropertyValueFactory<Despesa, String>("tpDespesa"));
		tblDespesa.setItems(populateTableDados);
	}

	@Override
	public void beforeClear() {
		dsDespesa.setText("");
		tpDespesa.setValue("Despesa Com Produtos");

	}

	@Override
	public void afterTableView() {
		dsDespesa.setText(objetoSelecionado.getDsDespesa());
		tpDespesa.setValue(objetoSelecionado.getTpDespesa());

	}

}
