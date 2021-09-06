package br.com.jdsb.cfc;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;
import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.menu.NavegarAte;
import br.com.jdsb.negocio.menu.NavegarAteImplements;
import br.com.jdsb.negocio.relatorio.RelatorioController;
import br.com.jdsb.negocio.service.NegocioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public abstract class AbstractController<T> implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

    public void carregarCrud(CrudRepository<T, Long> crud){
    	this.crud = crud;
    }

	public ObservableList<String> optionSnAtivo = FXCollections.observableArrayList("Sim", "Não");
	public ObservableList<T> populateTableDados = FXCollections.observableArrayList();

	public NegocioService service;
	public Map<String, Resource> mapa;
	public DataSource dataSource;
	public String nmRelatorio;

	public T objetoSelecionado;
	public CrudRepository<T, Long> crud;

	public void carregar(NegocioService service, Map<String, Resource> mapa,String formulario) {
		this.service = service;
		this.mapa = mapa;
		inicializar();
	}

	public void carregar(NegocioService service, Map<String, Resource> mapa,String formulario,DataSource dataSource,String nmRelatorio) {
		this.service = service;
		this.mapa = mapa;
		inicializar();
		this.dataSource = dataSource;
		this.nmRelatorio = nmRelatorio;
	}

	public void carregar(NegocioService service) {
		this.service = service;
		inicializar();
	}

	public abstract void inicializar();

	@FXML
	public Label lbMsg;

	public void mensagem(String texto) {
		lbMsg.setText(texto);
	}

	@FXML
	public void cancelar(ActionEvent event) {
      clear();
	}

	@FXML
	public void imprimir(ActionEvent event) {
      imprimir();
	}

	@FXML
	public void remover(ActionEvent event) {
		if(objetoSelecionado!=null){
    		crud.delete(objetoSelecionado);
    		mensagem("Registro Removido com Sucesso!");
    		clear();
    		carregarDados();
    	}else{
    		mensagem("Para Remover é necessário Selecionar um Registro!");
    	}
	}

	public void navegarAteCadastroCliente() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_cliente_fornec",dataSource,"R_CLIENTE_FORNECEDOR");
	}

	public void navegarAteCadastroBanco() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_banco",dataSource,"R_BANCO");
	}

	public void navegarAteCadastroReceita() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_receita",dataSource,"R_RECEITA");
	}

	public void navegarAteCadastroDespesa() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_despesa",dataSource,"R_DESPESA");
	}

	public void navegarAteCadastroCentroCusto() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_centro_custo",dataSource,"R_CENTRO_CUSTO");
	}

	public void clear(){
		beforeClear();
		objetoSelecionado = null;
	}

	public abstract void beforeClear();

	public void carregarTabelaDeDados(CrudRepository<T,Long> crud){
		populateTableDados = FXCollections.observableArrayList();
		for (T t:crud.findAll()){
			populateTableDados.add(t);
		}
	}

	public void carregarDados(){

	}

	public void preencherTabelaTela(TableView<T> tabelaTela){
		tabelaTela.getSelectionModel().selectedItemProperty()
		.addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				objetoSelecionado  = tabelaTela.getSelectionModel().getSelectedItem();

				if(tabelaTela!=null){
					afterTableView();
					lbMsg.setText("");
				}

			}
		});
	}

	public void imprimir(){
        RelatorioController controller = new RelatorioController();
        try {
        	controller.imprimirRelatorioSemFiltro(dataSource.getConnection(),mapa.get(nmRelatorio));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void afterTableView();

}
