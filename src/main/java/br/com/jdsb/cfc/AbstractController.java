package br.com.jdsb.cfc;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;
import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.Receita;
import br.com.jdsb.negocio.infra.Dialogos;
import br.com.jdsb.negocio.menu.NavegarAte;
import br.com.jdsb.negocio.menu.NavegarAteImplements;
import br.com.jdsb.negocio.menu.NavergarAteImplementsMedio;
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

	public Dialogos dialogos;
    public void carregarCrud(CrudRepository<T, Long> crud){
    	this.crud = crud;
    }

	public ObservableList<String> optionSnAtivo = FXCollections.observableArrayList("Sim", "Não");
	public ObservableList<String> optionFormaPagamento = FXCollections.observableArrayList("Dinheiro", "Cheque","Cartão de Débio","Cartão de Crédito","Transferencia","Boleto Bancário","Pix");
	public ObservableList<String> optionReceitas = FXCollections.observableArrayList("Receita Com Produtos","Receita Com Serviços","Receita Financeira","Outras Receitas");
	public ObservableList<String> optionDespesas = FXCollections.observableArrayList("Despesa Com Produtos",
			"Despesa Com Serviços", "Despesa Financeira", "Outras Despesas");
	public ObservableList<String> optionOperacao = FXCollections.observableArrayList("=",">", "<");
	public ObservableList<T> populateTableDados = FXCollections.observableArrayList();

	public static ObservableList<Banco> listaEstaticaBancos = FXCollections.observableArrayList();
	public static ObservableList<ClienteFornecedor> listaEstaticaCliente = FXCollections.observableArrayList();
	public static ObservableList<ClienteFornecedor> listaEstaticaFornecedor = FXCollections.observableArrayList();
	public static ObservableList<Receita> listaEstaticaReceita = FXCollections.observableArrayList();
	public static ObservableList<Despesa> listaEstaticaDespesa= FXCollections.observableArrayList();
	public static ObservableList<CentroCusto> listaEstaticaCentroCusto= FXCollections.observableArrayList();
	public static ObservableList<AnoBase> listaEstaticaAnoBase = FXCollections.observableArrayList();
	public static ObservableList<FluxoCaixaTo> listaEstaticaFluxoCaixa= FXCollections.observableArrayList();
	public static ObservableList<FluxoCaixaTotalTo> listaEstaticaFluxoCaixaTotal= FXCollections.observableArrayList();
	public static AnoBase anoBaseAtual;
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
		dialogos = new Dialogos(this.mapa.get("icone"));
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
		ate.navegarAte(mapa, service,"m_cadastro_cliente_fornec",dataSource,"R_CLIENTE_FORNECEDOR","Cadastro de Clientes e Fornecedores");
	}

	public void navegarAteCadastroBanco() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_banco",dataSource,"R_BANCO","Cadastro Bancario");
	}

	public void navegarAteCadastroReceita() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_receita",dataSource,"R_RECEITA","Cadastro de Receitas");
	}

	public void navegarAteCadastroDespesa() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_despesa",dataSource,"R_DESPESA","Cadastro de Despesas");
	}

	public void navegarAteCadastroCentroCusto() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_centro_custo",dataSource,"R_CENTRO_CUSTO","Cadastro de Centros de Custo");
	}

	public void navegarAteCadastroInventario() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_inventario",dataSource,"R_CENTRO_CUSTO","Lançamento de Inventário");
	}

	public void navegarAteCadastroMeta() {
		NavegarAte ate = new NavegarAteImplements();
		ate.navegarAte(mapa, service,"m_cadastro_metas",dataSource,"R_CENTRO_CUSTO","Cadastro de Metas");
	}

	public void navegarAteLancamentoReceita() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_lancamento_receita",dataSource,"R_CENTRO_CUSTO","Lançamento de Receita");
	}

	public void navegarAteLancamentoDespesa() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_lancamento_despesa",dataSource,"R_CENTRO_CUSTO","Lançamento de Despesas");
	}

	public void navegarAteAnaliseMovimento() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_analise_movimento",dataSource,"R_CENTRO_CUSTO","Analise de Movimentos");
	}

	public void navegarAteAnaliseMetas() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_analise_metas",dataSource,"R_CENTRO_CUSTO","Analise de Metas");
	}

	public void navegarAteAnaliseFluxoCaixa() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_analise_fluxo_caixa",dataSource,"R_CENTRO_CUSTO","Analise de Fluxo de Caixas");
	}

	public void navegarAtePrincipal() {
		NavegarAte ate = new NavergarAteImplementsMedio();
		ate.navegarAte(mapa, service,"m_principal",dataSource,"R_CENTRO_CUSTO","Sistema para Acompanhamento Financeiro V.1.0");
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
