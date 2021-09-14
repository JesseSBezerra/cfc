package br.com.jdsb.controladores.lancamento;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.plaf.metal.MetalBorders.OptionDialogBorder;

import org.springframework.data.repository.CrudRepository;

import com.jfoenix.controls.JFXTimePicker;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.Movimento;
import br.com.jdsb.negocio.Receita;
import br.com.jdsb.negocio.dao.DesepesaDao;
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

public class MLancamentoDespesa extends AbstractController<Movimento>{

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
	private Despesa receitaSelecionada;
	private ClienteFornecedor clienteSelecionado;
	private CentroCusto centroCustoSelecionado;

	public void carregarOpicoesConstantes(){

        tpBanco.setItems(AbstractController.listaEstaticaBancos);

        tpCentroCusto.setItems(AbstractController.listaEstaticaCentroCusto);

        tpFornecedor.setItems(AbstractController.listaEstaticaFornecedor);

		tpDespesa.setItems(optionDespesas);
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
	 				  ObservableList<Despesa> receitas = FXCollections.observableArrayList();
	 				  receitas.addAll(service.getDesepesaDao().findByTpDespesa(selecaco));
	 				  tpDespesaItem.setValue(null);
	 				  tpDespesaItem.setItems(receitas);
	 				}

	 			}
	 		});
	}

	public void ativarChoiceReceitaItem(){
		  this.tpDespesaItem.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpDespesaItem.getItems().get(new_value.intValue())!=null){
	 				  receitaSelecionada = tpDespesaItem.getItems().get(new_value.intValue());
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
		  this.tpFornecedor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpFornecedor.getItems().get(new_value.intValue())!=null){
	 				  clienteSelecionado = tpFornecedor.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void carregarDados() {
		carregarTabelaDeDados(this.service.getMovimentoDao());
		tbcBanco.setCellValueFactory(new PropertyValueFactory<Movimento, Banco>("banco"));
		tbcTipoDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, String>("classificacao"));
		tbcDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, Despesa>("despesa"));
		tbcVlMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlMovimentoFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlSaldoAnteriorFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dtMovimentoFormatado"));
		tbcCentroCusto.setCellValueFactory(new PropertyValueFactory<Movimento, CentroCusto>("centroCusto"));
		tbcFornecedor.setCellValueFactory(new PropertyValueFactory<Movimento, ClienteFornecedor>("clienteFornecedor"));
		tbcFpagamento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dsFormaPagamento"));
		tblLancamento.setItems(populateTableDados);
	}

	public void carregarTabelaDeDados(MovimentoDao movimentoDao){
		populateTableDados = FXCollections.observableArrayList();
		populateTableDados.addAll(movimentoDao.findByTpMovimento("Lançamento de Despesa"));
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
    private TableColumn<Movimento, Despesa> tbcDespesa;

    @FXML
    private TableColumn<Movimento, String> tbcFpagamento;

    @FXML
    private TableColumn<Movimento, Banco> tbcBanco;

    @FXML
    private TableColumn<Movimento, CentroCusto> tbcCentroCusto;

    @FXML
    private TableColumn<Movimento, ClienteFornecedor> tbcFornecedor;

    @FXML
    private TableColumn<Movimento, String> tbcVlMovimento;

    @FXML
    private Label lbMsg;

    @FXML
    private TextField vlMovimento;

    @FXML
    private ChoiceBox<Despesa> tpDespesaItem;

    @FXML
    private ChoiceBox<String> tpFormaPagamento;

    @FXML
    private ChoiceBox<Banco> tpBanco;

    @FXML
    private ChoiceBox<CentroCusto> tpCentroCusto;

    @FXML
    private ChoiceBox<ClienteFornecedor> tpFornecedor;

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
    		dialogos.AletaW("Atenção", "Não foi informada uma Despesa", "Informe uma despesa");
    	}else if(dtMovimento.getValue()==null){
    		dialogos.AletaW("Atenção", "Não foi informada uma data de movimento", "Informe a data de movimento");
    	}else if(hrMovimento.getValue()==null){
    		dialogos.AletaW("Atenção", "Não foi informada uma hora de movimento", "Informe a hora de movimento");
    	}else if(clienteSelecionado==null){
    		dialogos.AletaW("Atenção", "Não foi informado um fornecedor", "Informe um fornecedor");
    	}else if(bancoSelecionado==null){
    		dialogos.AletaW("Atenção", "Não foi informada um banco", "Informe um banco");
    	}else if(tpFormaPagamento.getValue()==null ||tpFormaPagamento.getValue().isEmpty()){
    		dialogos.AletaW("Atenção", "Não foi informada uma forma de pagamento", "Informe uma forma de pagamento");
    	}else if(vlMovimento.getText()==null || vlMovimento.getText().isEmpty()){
    		dialogos.AletaW("Atenção", "Não foi informado o valor movimento", "Informe o valor movimento");
    	}else{
    		objetoSelecionado = new Movimento();
    		objetoSelecionado.setTpMovimento("Lançamento de Despesa");
    		objetoSelecionado.setBanco(bancoSelecionado);
    		objetoSelecionado.setCentroCusto(centroCustoSelecionado);
    		objetoSelecionado.setClienteFornecedor(clienteSelecionado);
    		objetoSelecionado.setDsFormaPagamento(tpFormaPagamento.getValue());
    		objetoSelecionado.setVlSaldoAnterior(bancoSelecionado.getVlSaldoAtual());
    		objetoSelecionado.setDsObservacaoMovimento(dsObservacao.getText());
    		objetoSelecionado.setDespesa(receitaSelecionada);
    		LocalDateTime dhMovimento = LocalDateTime.of(dtMovimento.getValue().getYear(),
    				dtMovimento.getValue().getMonth(), dtMovimento.getValue().getDayOfMonth(), hrMovimento.getValue().getHour(), hrMovimento.getValue().getMinute(), hrMovimento.getValue().getSecond());
    		objetoSelecionado.setDtMovimento(dhMovimento);

    		String valorTratado = vlMovimento.getText().replace(".", "");
			BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
			objetoSelecionado.setVlMovimento(valor);

			boolean confirma = true;
			if(bancoSelecionado.getVlSaldoAtual().subtract(objetoSelecionado.getVlMovimento()).longValue()<0){
				confirma = false;
				dialogos.AletaQuestion("Atenção", "O valor da movimentação é maior que o saldo do banco", "Confirma");
			}

			if(confirma){
				bancoSelecionado.setVlSaldoAtual(bancoSelecionado.getVlSaldoAtual().subtract(objetoSelecionado.getVlMovimento()));
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
		tpFornecedor.setValue(null);
		tpDespesa.setValue(null);
		dsObservacao.setText(null);
		tpCentroCusto.setValue(null);
		tpFormaPagamento.setValue(null);
		tpDespesaItem.setValue(null);
		vlMovimento.setText("0");
		vlSaldoAnterior.setText("0");
	}

	@Override
	public void afterTableView() {
          dtMovimento.setValue(objetoSelecionado.getDtMovimento().toLocalDate());
  		  hrMovimento.setValue(objetoSelecionado.getDtMovimento().toLocalTime());
  		  tpDespesa.setValue(objetoSelecionado.getDespesa().getTpDespesa());
  		  tpDespesaItem.setValue(objetoSelecionado.getDespesa());
  		  tpFormaPagamento.setValue(objetoSelecionado.getDsFormaPagamento());
  		  dsObservacao.setText(objetoSelecionado.getDsObservacaoMovimento());
  		  tpBanco.setValue(objetoSelecionado.getBanco());
  		  tpFornecedor.setValue(objetoSelecionado.getClienteFornecedor());
  		  tpCentroCusto.setValue(objetoSelecionado.getCentroCusto());
  		  vlMovimento.setText(objetoSelecionado.getVlMovimentoFormatado());
	}

}
