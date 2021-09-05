package br.com.jdsb.cfc;

import br.com.jdsb.negocio.Banco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MCadastroBanco extends AbstractController implements Initializable{

	private Banco bancoSelecionado;

	@Override
	public void inicializar() {
		carregarBancos();
	       tblBanco.getSelectionModel().selectedItemProperty()
	    			.addListener((obs, oldSelection, newSelection) -> {
	    				if (newSelection != null) {
	    					bancoSelecionado  = tblBanco.getSelectionModel().getSelectedItem();

	    					if(bancoSelecionado!=null){
	    						nmBanco.setText(bancoSelecionado.getNmBanco());
	    						dsCodigoBanco.setText(bancoSelecionado.getDsCodigoBanco());
	    						lbMsg.setText("");
	    					}

	    				}
	    			});

	}

	public void carregarBancos(){
		ObservableList<Banco> populateTableBanco = FXCollections.observableArrayList();
		for(Banco banco:service.getBancoDao().findAll()){
            populateTableBanco.add(banco);
		}

		tbcDsCodigoBanco
		.setCellValueFactory(new PropertyValueFactory<Banco, String>("dsCodigoBanco"));
		tbcNmBanco
				.setCellValueFactory(new PropertyValueFactory<Banco, String>("nmBanco"));

		tblBanco.setItems(populateTableBanco);


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
    void remover(ActionEvent event) {
    	if(bancoSelecionado!=null){
    		service.getBancoDao().delete(bancoSelecionado);
    		mensagem("Registro Removido com Sucesso!");
    	}else{
    		mensagem("Para Remover é necessário Selecionar um banco!");
    	}
    }

    @Override
    public void salvar(ActionEvent event) {
       if(bancoSelecionado==null){
    	   bancoSelecionado = new Banco();
    	   bancoSelecionado.setDsCodigoBanco(dsCodigoBanco.getText());
    	   bancoSelecionado.setNmBanco(nmBanco.getText());
    	   service.getBancoDao().save(bancoSelecionado);
    	   mensagem("Registro Salvo com Sucesso!");
       }else{
    	   bancoSelecionado.setDsCodigoBanco(dsCodigoBanco.getText());
    	   bancoSelecionado.setNmBanco(nmBanco.getText());
    	   service.getBancoDao().save(bancoSelecionado);
    	   mensagem("Registro Atualizado com Sucesso!");
       }

       carregarBancos();
       Limpar();
    }

    public void Limpar(){
    	dsCodigoBanco.setText(null);
    	nmBanco.setText(null);
    }

    @FXML
    void cancelar(ActionEvent event) {

    }


}
