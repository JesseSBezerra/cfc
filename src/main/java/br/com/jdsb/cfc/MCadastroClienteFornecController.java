package br.com.jdsb.cfc;

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

public class MCadastroClienteFornecController extends AbstractController {

	@Override
	public void inicializar() {
		ObservableList<String> optionClientFornedor = FXCollections.observableArrayList("Cliente","Fornecedor");
		tpClinteFornec.setItems(optionClientFornedor);
		tpClinteFornec.setValue("Cliente");
	    carregarDados();

	}

	private ClienteFornecedor clientFornecedorSelecionado;

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
	void cancelar(ActionEvent event) {

	}

	@FXML
	void remover(ActionEvent event) {
		if(clientFornecedorSelecionado==null){
			mensagem("Para Remover é necessário Selecionar um Registro!");
		}
	}

	public void limpar(){
		dsCelular.setText("");
		cpfCnpj.setText("");
		dsEmail.setText("");
		dsTelFixo.setText("");
		nmClienteForne.setText("");
		dsObservacao.setText("");
		tpClinteFornec.setValue("Cliente");
	}

	@FXML
	void salvar(ActionEvent event) {
       if(clientFornecedorSelecionado==null){
    	   clientFornecedorSelecionado = new ClienteFornecedor();
    	   clientFornecedorSelecionado.setDsCelular(dsCelular.getText());
    	   clientFornecedorSelecionado.setDsCpfCnpj(cpfCnpj.getText());
    	   clientFornecedorSelecionado.setDsEmail(dsEmail.getText());
    	   clientFornecedorSelecionado.setDsTelFixo(dsTelFixo.getText());
    	   clientFornecedorSelecionado.setNmClienteFornec(nmClienteForne.getText());
    	   clientFornecedorSelecionado.setTpClienteFornec(tpClinteFornec.getValue());
    	   clientFornecedorSelecionado.setDsObservacao(dsObservacao.getText());
    	   this.service.getClientFornecDao().save(clientFornecedorSelecionado);
    	   mensagem("Registro Salvo com Sucesso!");
    	   carregarDados();
    	   limpar();
       }else{
    	   clientFornecedorSelecionado.setDsCelular(dsCelular.getText());
    	   clientFornecedorSelecionado.setDsCpfCnpj(cpfCnpj.getText());
    	   clientFornecedorSelecionado.setDsEmail(dsEmail.getText());
    	   clientFornecedorSelecionado.setDsTelFixo(dsTelFixo.getText());
    	   clientFornecedorSelecionado.setNmClienteFornec(nmClienteForne.getText());
    	   clientFornecedorSelecionado.setTpClienteFornec(tpClinteFornec.getValue());
    	   clientFornecedorSelecionado.setDsObservacao(dsObservacao.getText());
    	   this.service.getClientFornecDao().save(clientFornecedorSelecionado);
    	   mensagem("Registro Atualizado com Sucesso!");
    	   carregarDados();
    	   limpar();
       }
	}

	public void carregarDados(){
		ObservableList<ClienteFornecedor> populateTableBanco = FXCollections.observableArrayList();
		for(ClienteFornecedor clienteFornecedor:service.getClientFornecDao().findAll()){
            populateTableBanco.add(clienteFornecedor);
		}

		tbcTpClienteFornec
		.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("tpClienteFornec"));
		tbcNmClienteFornec
				.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("nmClienteFornec"));
		tbcDsObservacao
		.setCellValueFactory(new PropertyValueFactory<ClienteFornecedor, String>("dsObservacao"));

		tblcClineteFornec.setItems(populateTableBanco);


	}

}
