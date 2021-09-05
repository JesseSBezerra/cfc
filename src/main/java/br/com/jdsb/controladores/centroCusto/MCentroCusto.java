package br.com.jdsb.controladores.centroCusto;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.CentroCusto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MCentroCusto extends AbstractController<CentroCusto> {

	@Override
	public void inicializar() {
		tpSnAtivo.setItems(optionSnAtivo);
		tpSnAtivo.setValue("Sim");
		carregarDados();
		preencherTabelaTela(tblCentroCusto);
		carregarCrud(service.getCentroCustoDao());
	}

	@FXML
	private ChoiceBox<String> tpSnAtivo;

	@FXML
	private TextField dsCentroCusto;

	@FXML
	private TableView<CentroCusto> tblCentroCusto;

	@FXML
	private TableColumn<CentroCusto, String> tbcSnAtivo;

	@FXML
	private TableColumn<CentroCusto, String> tbcDsCentroCusto;


	@FXML
	public void cancelar(ActionEvent event) {

	}


	@FXML
	public void salvar(ActionEvent event) {
        if(objetoSelecionado==null){
        	objetoSelecionado = new CentroCusto();
        	objetoSelecionado.setDsCentroCusto(dsCentroCusto.getText());
        	objetoSelecionado.setSnAtivo(tpSnAtivo.getValue());
            this.service.getCentroCustoDao().save(objetoSelecionado);
            carregarDados();
            clear();
            mensagem("Registro Salvo com Sucesso!");
        }else{
        	objetoSelecionado.setDsCentroCusto(dsCentroCusto.getText());
        	objetoSelecionado.setSnAtivo(tpSnAtivo.getValue());
            this.service.getCentroCustoDao().save(objetoSelecionado);
            carregarDados();
            clear();
            mensagem("Registro Atualizado com Sucesso!");
        }
	}

	@Override
	public void remover(ActionEvent event) {
		super.remover(event);
		carregarDados();
	}

	public void carregarDados(){
		carregarTabelaDeDados(this.service.getCentroCustoDao());
		tbcDsCentroCusto.setCellValueFactory(new PropertyValueFactory<CentroCusto, String>("dsCentroCusto"));
		tbcSnAtivo.setCellValueFactory(new PropertyValueFactory<CentroCusto, String>("snAtivo"));
		tblCentroCusto.setItems(populateTableDados);
	}

	@Override
	public void beforeClear() {
		dsCentroCusto.setText("");
		tpSnAtivo.setValue("Sim");
	}

	@Override
	public void afterTableView() {
		tpSnAtivo.setValue(objetoSelecionado.getSnAtivo());
		dsCentroCusto.setText(objetoSelecionado.getDsCentroCusto());
	}



}
