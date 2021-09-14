package br.com.jdsb.controladores.lancamento;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.repository.CrudRepository;

import com.jfoenix.controls.JFXTimePicker;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Movimento;
import br.com.jdsb.negocio.Receita;
import br.com.jdsb.negocio.dao.MovimentoDao;
import br.com.jdsb.negocio.infra.Mascaras;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MLancamentoReceita extends AbstractController<Movimento>{

	@Override
	public void inicializar() {
		carregarDados();
		preencherTabelaTela(tblLancamento);
		carregarCrud(service.getMovimentoDao());
		carregarOpicoesConstantes();
		vlSaldoAnterior.setEditable(false);
		ativarChoiceBanco();
		ativarChoiceReceita();
		ativarChoiceReceitaItem();
		ativarChoiceCentroCusto();
		ativarChoiceCliente();

	}

	private Banco bancoSelecionado;
	private Receita receitaSelecionada;
	private ClienteFornecedor clienteSelecionado;
	private CentroCusto centroCustoSelecionado;

	public void carregarOpicoesConstantes(){


        tpBanco.setItems(AbstractController.listaEstaticaBancos);


        tpCentroCusto.setItems(AbstractController.listaEstaticaCentroCusto);


        tpCliente.setItems(AbstractController.listaEstaticaCliente);

		tpDespesa.setItems(optionReceitas);
		tpFormaPagamento.setItems(optionFormaPagamento);

		Mascaras.mascaraValor(vlMovimento);
		hrMovimento.set24HourView(true);
		dtMovimento.setValue(LocalDate.now());
		hrMovimento.setValue(LocalTime.now());

	}

	public void ativarChoiceBanco(){
		  this.tpBanco.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpBanco.getItems().get(new_value.intValue())!=null){
	 				  bancoSelecionado = tpBanco.getItems().get(new_value.intValue());
	 				  if(bancoSelecionado.getVlSaldoAtual()==null){
	 					 vlSaldoAnterior.setText("0");
	 				  }else{
	 					String valor = NumberFormat.getCurrencyInstance().format(bancoSelecionado.getVlSaldoAtual());
	 					vlSaldoAnterior.setText(valor);
	 				  }
	 				}

	 			}
	 		});
	}

	public void ativarChoiceReceita(){
		  this.tpDespesa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpDespesa.getItems().get(new_value.intValue())!=null){
	 				  String selecaco = tpDespesa.getItems().get(new_value.intValue());
	 				  ObservableList<Receita> receitas = FXCollections.observableArrayList();
	 				  receitas.addAll(service.getReceitaDao().findByTpReceita(selecaco));
	 				  tpReceitaItem.setValue(null);
	 				  tpReceitaItem.setItems(receitas);
	 				}

	 			}
	 		});
	}

	public void ativarChoiceReceitaItem(){
		  this.tpReceitaItem.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpReceitaItem.getItems().get(new_value.intValue())!=null){
	 				  receitaSelecionada = tpReceitaItem.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void ativarChoiceCentroCusto(){
		  this.tpCentroCusto.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpCentroCusto.getItems().get(new_value.intValue())!=null){
	 				  centroCustoSelecionado = tpCentroCusto.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void ativarChoiceCliente(){
		  this.tpCliente.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpCliente.getItems().get(new_value.intValue())!=null){
	 				  clienteSelecionado = tpCliente.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void carregarDados() {
		carregarTabelaDeDados(this.service.getMovimentoDao());
		tbcBanco.setCellValueFactory(new PropertyValueFactory<Movimento, Banco>("banco"));
		tbcTipoDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, String>("classificacao"));
		tbcDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, Receita>("receita"));
		tbcVlMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlMovimentoFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlSaldoAnteriorFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dtMovimentoFormatado"));
		tbcCentroCusto.setCellValueFactory(new PropertyValueFactory<Movimento, CentroCusto>("centroCusto"));
		tbcCliente.setCellValueFactory(new PropertyValueFactory<Movimento, ClienteFornecedor>("clienteFornecedor"));
		tbcFpagamento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dsFormaPagamento"));
		tblLancamento.setItems(populateTableDados);
	}

	public void carregarTabelaDeDados(MovimentoDao movimentoDao){
		populateTableDados = FXCollections.observableArrayList();
		populateTableDados.addAll(movimentoDao.findByTpMovimento("Lançamento de Receita"));
	}

	@FXML
    private ChoiceBox<String> tpDespesa;

    @FXML
    private TextArea dsObservacao;

    @FXML
    private DatePicker dtMovimento;

    @FXML
    private TableView<Movimento> tblLancamento;

    @FXML
    private TableColumn<Movimento, String> tbcDtMovimento;

    @FXML
    private TableColumn<Movimento, String> tbcTipoDespesa;

    @FXML
    private TableColumn<Movimento, Receita> tbcDespesa;

    @FXML
    private TableColumn<Movimento, String> tbcFpagamento;

    @FXML
    private TableColumn<Movimento, Banco> tbcBanco;

    @FXML
    private TableColumn<Movimento, CentroCusto> tbcCentroCusto;

    @FXML
    private TableColumn<Movimento, ClienteFornecedor> tbcCliente;

    @FXML
    private TableColumn<Movimento, String> tbcVlMovimento;

    @FXML
    private Label lbMsg;

    @FXML
    private TextField vlMovimento;

    @FXML
    private ChoiceBox<Receita> tpReceitaItem;

    @FXML
    private ChoiceBox<String> tpFormaPagamento;

    @FXML
    private ChoiceBox<Banco> tpBanco;

    @FXML
    private ChoiceBox<CentroCusto> tpCentroCusto;

    @FXML
    private ChoiceBox<ClienteFornecedor> tpCliente;

    @FXML
    private TextField vlSaldoAnterior;

    @FXML
    private JFXTimePicker hrMovimento;


    @FXML
    public void remover(ActionEvent event) {

    }

    @FXML
    public void salvar(ActionEvent event) {

    	if(receitaSelecionada==null){
    		dialogos.AletaW("Atenção", "Não foi informada uma Receita", "Informe uma receita");
    	}else if(dtMovimento.getValue()==null){
    		dialogos.AletaW("Atenção", "Não foi informada uma data de movimento", "Informe a data de movimento");
    	}else if(hrMovimento.getValue()==null){
    		dialogos.AletaW("Atenção", "Não foi informada uma hora de movimento", "Informe a hora de movimento");
    	}else if(clienteSelecionado==null){
    		dialogos.AletaW("Atenção", "Não foi informada um cliente", "Informe um cliente");
    	}else if(bancoSelecionado==null){
    		dialogos.AletaW("Atenção", "Não foi informada um banco", "Informe um banco");
    	}else if(tpFormaPagamento.getValue()==null ||tpFormaPagamento.getValue().isEmpty()){
    		dialogos.AletaW("Atenção", "Não foi informada uma forma de pagamento", "Informe uma forma de pagamento");
    	}else if(vlMovimento.getText()==null || vlMovimento.getText().isEmpty()){
    		dialogos.AletaW("Atenção", "Não foi informado o valor movimento", "Informe o valor movimento");
    	}else{
    		objetoSelecionado = new Movimento();
    		objetoSelecionado.setTpMovimento("Lançamento de Receita");
    		objetoSelecionado.setBanco(bancoSelecionado);
    		objetoSelecionado.setCentroCusto(centroCustoSelecionado);
    		objetoSelecionado.setClienteFornecedor(clienteSelecionado);
    		objetoSelecionado.setDsFormaPagamento(tpFormaPagamento.getValue());
    		objetoSelecionado.setVlSaldoAnterior(bancoSelecionado.getVlSaldoAtual());
    		objetoSelecionado.setDsObservacaoMovimento(dsObservacao.getText());
    		objetoSelecionado.setReceita(receitaSelecionada);
    		LocalDateTime dhMovimento = LocalDateTime.of(dtMovimento.getValue().getYear(),
    				dtMovimento.getValue().getMonth(), dtMovimento.getValue().getDayOfMonth(), hrMovimento.getValue().getHour(), hrMovimento.getValue().getMinute(), hrMovimento.getValue().getSecond());
    		objetoSelecionado.setDtMovimento(dhMovimento);

    		String valorTratado = vlMovimento.getText().replace(".", "");
			BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
			objetoSelecionado.setVlMovimento(valor);

			boolean confirma = true;
//			if(bancoSelecionado.getVlSaldoAtual().subtract(objetoSelecionado.getVlMovimento()).longValue()<0){
//				confirma = false;
//				dialogos.AletaQuestion("Atenção", "O valor da movimentação é maior que o saldo do banco", "Confirma");
//			}

			if(confirma){
				bancoSelecionado.setVlSaldoAtual(bancoSelecionado.getVlSaldoAtual().add(objetoSelecionado.getVlMovimento()));
				service.getMovimentoDao().save(objetoSelecionado);
				service.getBancoDao().save(bancoSelecionado);
			}
            clear();
			carregarDados();
    	}
    }

    @Override
	public void beforeClear() {
    	dtMovimento.setValue(LocalDate.now());
		hrMovimento.setValue(LocalTime.now());
		objetoSelecionado = null;
		bancoSelecionado = null;
		receitaSelecionada = null;
		centroCustoSelecionado = null;
		clienteSelecionado = null;
		tpBanco.setValue(null);
		tpCliente.setValue(null);
		tpDespesa.setValue(null);
		dsObservacao.setText(null);
		tpCentroCusto.setValue(null);
		tpFormaPagamento.setValue(null);
		tpReceitaItem.setValue(null);
		vlMovimento.setText("0");
		vlSaldoAnterior.setText("0");
	}

	@Override
	public void afterTableView() {
          dtMovimento.setValue(objetoSelecionado.getDtMovimento().toLocalDate());
  		  hrMovimento.setValue(objetoSelecionado.getDtMovimento().toLocalTime());
  		  tpDespesa.setValue(objetoSelecionado.getReceita().getTpReceita());
  		  tpReceitaItem.setValue(objetoSelecionado.getReceita());
  		  tpFormaPagamento.setValue(objetoSelecionado.getDsFormaPagamento());
  		  dsObservacao.setText(objetoSelecionado.getDsObservacaoMovimento());
  		  tpBanco.setValue(objetoSelecionado.getBanco());
  		  tpCliente.setValue(objetoSelecionado.getClienteFornecedor());
  		  tpCentroCusto.setValue(objetoSelecionado.getCentroCusto());
  		  vlMovimento.setText(objetoSelecionado.getVlMovimentoFormatado());
	}

}
