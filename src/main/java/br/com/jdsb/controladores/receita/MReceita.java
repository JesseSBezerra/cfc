package br.com.jdsb.controladores.receita;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Receita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MReceita extends AbstractController<Receita>{

	@Override
	public void inicializar() {
		ObservableList<String> optionReceitas = FXCollections.observableArrayList("Receita Com Produtos","Receita Com Serviços","Receita Financeira","Outras Receitas");
		tpReceita.setItems(optionReceitas);
		tpReceita.setValue("Receita Com Produtos");
		carregarDados();
		carregarCrud(service.getReceitaDao());
		preencherTabelaTela(tblReceita);

	}

	@FXML
    private ChoiceBox<String> tpReceita;

    @FXML
    private TextField dsReceita;

    @FXML
    private TableView<Receita> tblReceita;

    @FXML
    private TableColumn<Receita, String> tbcTpReceita;

    @FXML
    private TableColumn<Receita, String> tbcDsReceita;


    @FXML
    public void cancelar(ActionEvent event) {

    }

    @FXML
    public void salvar(ActionEvent event) {
    	if(objetoSelecionado==null){
    		objetoSelecionado = new Receita();
    		objetoSelecionado.setDsReceita(dsReceita.getText());
    		objetoSelecionado.setTpReceita(tpReceita.getValue());
    		this.service.getReceitaDao().save(objetoSelecionado);
    		mensagem("Registro Salvo com Sucesso!");
    		carregarDados();
    		clear();
    	}else{
    		objetoSelecionado.setDsReceita(dsReceita.getText());
    		objetoSelecionado.setTpReceita(tpReceita.getValue());
    		this.service.getReceitaDao().save(objetoSelecionado);
    		mensagem("Registro Atualizado com Sucesso!");
    		carregarDados();
    		clear();
    	}

    }

    public void carregarDados(){
		carregarTabelaDeDados(this.service.getReceitaDao());
		tbcDsReceita
		.setCellValueFactory(new PropertyValueFactory<Receita, String>("dsReceita"));
		tbcTpReceita
				.setCellValueFactory(new PropertyValueFactory<Receita, String>("tpReceita"));
		tblReceita.setItems(populateTableDados);
	}

	@Override
	public void beforeClear() {
		dsReceita.setText("");
        tpReceita.setValue("Receita Com Produtos");
	}

	@Override
	public void afterTableView() {
		dsReceita.setText(objetoSelecionado.getDsReceita());
		tpReceita.setValue(objetoSelecionado.getTpReceita());
	}


}
