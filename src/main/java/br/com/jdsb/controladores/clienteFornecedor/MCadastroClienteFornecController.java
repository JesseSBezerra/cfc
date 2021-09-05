package br.com.jdsb.controladores.clienteFornecedor;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.ClienteFornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MCadastroClienteFornecController extends AbstractController<ClienteFornecedor> {

	@Override
	public void inicializar() {
		ObservableList<String> optionClientFornedor = FXCollections.observableArrayList("Cliente","Fornecedor");
		tpClinteFornec.setItems(optionClientFornedor);
		tpClinteFornec.setValue("Cliente");
	    carregarDados();
	    carregarCrud(service.getClientFornecDao());
	    preencherTabelaTela(tblcClineteFornec);

	}

    @FXML
    private TableView<ClienteFornecedor> tblcClineteFornec;

	@FXML
	private ChoiceBox<String> tpClinteFornec;

	@FXML
	private TextField nmClienteForne;

	@FXML
	private TextField cpfCnpj;

	@FXML
	private TextField dsCelular;

	@FXML
	private TextField dsTelFixo;

	@FXML
	private TextField dsEmail;

	@FXML
	private TableColumn<ClienteFornecedor, String> tbcTpClienteFornec;

	@FXML
	private TableColumn<ClienteFornecedor, String> tbcNmClienteFornec;

	@FXML
	private TableColumn<ClienteFornecedor, String> tbcDsObservacao;

	@FXML
	private Label lbMsg;

	@FXML
	private TextField dsObservacao;

	@FXML
	public void cancelar(ActionEvent event) {

	}


	@FXML
	public void salvar(ActionEvent event) {
       if(objetoSelecionado==null){
    	   objetoSelecionado = new ClienteFornecedor();
    	   objetoSelecionado.setDsCelular(dsCelular.getText());
    	   objetoSelecionado.setDsCpfCnpj(cpfCnpj.getText());
    	   objetoSelecionado.setDsEmail(dsEmail.getText());
    	   objetoSelecionado.setDsTelFixo(dsTelFixo.getText());
    	   objetoSelecionado.setNmClienteFornec(nmClienteForne.getText());
    	   objetoSelecionado.setTpClienteFornec(tpClinteFornec.getValue());
    	   objetoSelecionado.setDsObservacao(dsObservacao.getText());
    	   this.service.getClientFornecDao().save(objetoSelecionado);
    	   mensagem("Registro Salvo com Sucesso!");
    	   carregarDados();
    	   clear();
       }else{
    	   objetoSelecionado.setDsCelular(dsCelular.getText());
    	   objetoSelecionado.setDsCpfCnpj(cpfCnpj.getText());
    	   objetoSelecionado.setDsEmail(dsEmail.getText());
    	   objetoSelecionado.setDsTelFixo(dsTelFixo.getText());
    	   objetoSelecionado.setNmClienteFornec(nmClienteForne.getText());
    	   objetoSelecionado.setTpClienteFornec(tpClinteFornec.getValue());
    	   objetoSelecionado.setDsObservacao(dsObservacao.getText());
    	   this.service.getClientFornecDao().save(objetoSelecionado);
    	   mensagem("Registro Atualizado com Sucesso!");
    	   carregarDados();
    	   clear();
       }
	}

	public void carregarDados(){
		carregarTabelaDeDados(this.service.getClientFornecDao());
		tbcTpClienteFornec
		.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("tpClienteFornec"));
		tbcNmClienteFornec
				.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("nmClienteFornec"));
		tbcDsObservacao
		.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("dsObservacao"));
		tblcClineteFornec.setItems(populateTableDados);
	}

	@Override
	public void beforeClear() {
		dsCelular.setText("");
		cpfCnpj.setText("");
		dsEmail.setText("");
		dsTelFixo.setText("");
		nmClienteForne.setText("");
		dsObservacao.setText("");
		tpClinteFornec.setValue("Cliente");

	}

	@Override
	public void afterTableView() {
	   dsCelular.setText(objetoSelecionado.getDsCelular());
	   cpfCnpj.setText(objetoSelecionado.getDsCpfCnpj());
 	   dsCelular.setText(objetoSelecionado.getDsEmail());
 	   dsTelFixo.setText(objetoSelecionado.getDsTelFixo());
 	   nmClienteForne.setText(objetoSelecionado.getNmClienteFornec());
 	   tpClinteFornec.setValue(objetoSelecionado.getTpClienteFornec());
 	   dsObservacao.setText(objetoSelecionado.getDsObservacao());
	}

}
