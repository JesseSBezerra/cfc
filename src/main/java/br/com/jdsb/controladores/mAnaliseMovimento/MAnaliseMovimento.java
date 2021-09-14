package br.com.jdsb.controladores.mAnaliseMovimento;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MAnaliseMovimento extends AbstractController<Movimento>{

	@Override
	public void inicializar() {
		carregarCrud(service.getMovimentoDao());
		carregarDados();
		carregarConstantes();

	}

	private Banco bancoSelecionado;
	private CentroCusto centroCustoSelecionado;
	private Receita receitaSelecionada;
	private Despesa despesaSelecionada;
	private String tipoDespesaSelecionada;
	private String tipoReceitaSelecionada;
	private ClienteFornecedor clienteSelecionado;
	private ClienteFornecedor fornecedorSelecionado;

	@Override
	public void beforeClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTableView() {
		// TODO Auto-generated method stub

	}


	public void carregarConstantes(){
		vlCredito.setStyle("-fx-background-color:lightgreen");
		vlDebito.setStyle("-fx-background-color:lightcoral");
		optionReceitas.add(null);
		toReceita.setItems(optionReceitas);
		optionDespesas.add(null);
		tpDespesa.setItems(optionDespesas);
		tpOperacao.setItems(optionOperacao);
		tpOperacao.setValue("=");
		tpBanco.setItems(listaEstaticaBancos);
		tpCliente.setItems(listaEstaticaCliente);
		tpFornecedor.setItems(listaEstaticaFornecedor);
		optionFormaPagamento.add(null);
		tpFormaPagamento.setItems(optionFormaPagamento);
		tpCentroCusto.setItems(listaEstaticaCentroCusto);

		ativarChoiceBanco();
		ativarChoiceCentroCusto();
		ativarChoiceCliente();
		ativarChoiceDespesa();
		ativarChoiceDespesaItem();
		ativarChoiceFornecedor();
		ativarChoiceReceita();
		ativarChoiceReceitaItem();

		dtInicial.setValue(LocalDate.now());
		dtFinal.setValue(LocalDate.now());

		Mascaras.mascaraValor(vlMovimento);
	}

	private void customiseFactory(TableColumn<Movimento, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<Movimento, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableRow<Movimento> currentRow = getTableRow();

	                if (!isEmpty()) {

	                	switch (item) {
						case "Inventário":
							 currentRow.setStyle("-fx-background-color:lightgreen");
							break;
						case "Receita Com Produtos":
							 currentRow.setStyle("-fx-background-color:lightgreen");
							break;
						case "Receita Com Serviços":
							 currentRow.setStyle("-fx-background-color:lightgreen");
							break;
						case "Receita Financeira":
							 currentRow.setStyle("-fx-background-color:lightgreen");
							break;
						case "Outras Receitas":
							 currentRow.setStyle("-fx-background-color:lightgreen");
							break;
						default:
							currentRow.setStyle("-fx-background-color:lightcoral");
							break;
						}
	                }
	            }
	        };
	    });
	}

	public void ativarChoiceBanco(){
		  this.tpBanco.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpBanco.getItems().get(new_value.intValue())!=null){
	 				  bancoSelecionado = tpBanco.getItems().get(new_value.intValue());
	 				}
	 			}
	 		});
	}

	public void ativarChoiceDespesa(){
		  this.tpDespesa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				try{
		 				if(tpDespesa.getItems().get(new_value.intValue())!=null){
		 				  tipoDespesaSelecionada = tpDespesa.getItems().get(new_value.intValue());
		 				  ObservableList<Despesa> receitas = FXCollections.observableArrayList();
		 				  receitas.addAll(service.getDesepesaDao().findByTpDespesa(tipoDespesaSelecionada));
		 				  tpDespesaItem.setValue(null);
		 				  tpDespesaItem.setItems(receitas);
		 				}
	 				}catch (Exception e) {
	 					tpDespesaItem.setValue(null);
	 					tpDespesaItem.setItems(null);
					}

	 			}
	 		});
	}

	public void ativarChoiceDespesaItem(){
		  this.tpDespesaItem.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpDespesaItem.getItems().get(new_value.intValue())!=null){
	 				  despesaSelecionada = tpDespesaItem.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void ativarChoiceReceita(){
		  this.toReceita.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				try{
		 				if(toReceita.getItems().get(new_value.intValue())!=null){
		 				  tipoReceitaSelecionada = toReceita.getItems().get(new_value.intValue());
		 				  ObservableList<Receita> receitas = FXCollections.observableArrayList();
		 				  receitas.addAll(service.getReceitaDao().findByTpReceita(tipoReceitaSelecionada));
		 				  tpReceitaItem.setValue(null);
		 				  tpReceitaItem.setItems(receitas);
		 				}
	 				}catch (Exception e) {
	 					  tpReceitaItem.setValue(null);
	 					 tpReceitaItem.setItems(null);
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

	public void ativarChoiceFornecedor(){
		  this.tpFornecedor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpFornecedor.getItems().get(new_value.intValue())!=null){
	 				  fornecedorSelecionado = tpFornecedor.getItems().get(new_value.intValue());
	 				}

	 			}
	 		});
	}

	public void carregarTabelaDeDados(MovimentoDao movimentoDao){
		populateTableDados = FXCollections.observableArrayList();
		populateTableDados.addAll(movimentoDao.findAll());
	}

	public void carregarDados() {
		carregarTabelaDeDados(this.service.getMovimentoDao());
		tbcBanco.setCellValueFactory(new PropertyValueFactory<Movimento, Banco>("banco"));
		tbcTipoDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, String>("classificacao"));
		customiseFactory(tbcTipoDespesa);
		tbcDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, Receita>("receita"));
		tbcVlMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlMovimentoFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlSaldoAnteriorFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dtMovimentoFormatado"));
		tbcCentroCusto.setCellValueFactory(new PropertyValueFactory<Movimento, CentroCusto>("centroCusto"));
		tbcCliente.setCellValueFactory(new PropertyValueFactory<Movimento, ClienteFornecedor>("clienteFornecedor"));
		tbcFpagamento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dsFormaPagamento"));
		tblLancamento.setItems(populateTableDados);
	}

	public void carregarPersonalizado(List<Movimento> lista){
		populateTableDados = FXCollections.observableArrayList();
		populateTableDados.addAll(lista);
		tbcBanco.setCellValueFactory(new PropertyValueFactory<Movimento, Banco>("banco"));
		tbcTipoDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, String>("classificacao"));
		customiseFactory(tbcTipoDespesa);
		tbcDespesa.setCellValueFactory(new PropertyValueFactory<Movimento, Receita>("receita"));
		tbcVlMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlMovimentoFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlSaldoAnteriorFormatado"));
		tbcDtMovimento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dtMovimentoFormatado"));
		tbcCentroCusto.setCellValueFactory(new PropertyValueFactory<Movimento, CentroCusto>("centroCusto"));
		tbcCliente.setCellValueFactory(new PropertyValueFactory<Movimento, ClienteFornecedor>("clienteFornecedor"));
		tbcFpagamento.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dsFormaPagamento"));
		tblLancamento.setItems(populateTableDados);
	}

	@FXML
    private DatePicker dtInicial;

    @FXML
    private ChoiceBox<String> toReceita;

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
    private DatePicker dtFinal;

    @FXML
    private ChoiceBox<ClienteFornecedor> tpFornecedor;

    @FXML
    private ChoiceBox<String> tpDespesa;

    @FXML
    private ChoiceBox<Despesa> tpDespesaItem;

    @FXML
    private ChoiceBox<String> tpOperacao;

    @FXML
    private TextField vlInicial;

    @FXML
    private TextField vlCredito;

    @FXML
    private TextField vlDebito;

    @FXML
    private TextField vlFinal;

    @FXML
    public void cancelar(ActionEvent event) {

    }

    @FXML
    public void imprimir(ActionEvent event) {

    }

    @FXML
    public void pesquisar(ActionEvent event) {

     if(dtInicial.getValue()==null){
    	 dialogos.AletaW("Atenção", "A data inicial não foi informada!", "Informe a data incial!");
    	 return;
     }
     LocalDateTime dataIncio = null;
     if(dtInicial.getValue()!=null){
    	 dataIncio= dataIncio.of(dtInicial.getValue().getYear(), dtInicial.getValue().getMonth(), dtInicial.getValue().getDayOfMonth(), 0, 0);
     }
     LocalDateTime dataFim = null;
     if(dtFinal.getValue()!=null){
    	 dataFim= dataFim.of(dtFinal.getValue().getYear(), dtFinal.getValue().getMonth(), dtFinal.getValue().getDayOfMonth(), 23, 59,59);
     }

     BigDecimal valor = null;
	     if(vlMovimento.getText()!=null && !vlMovimento.getText().isEmpty()){
		     String valorTratado = vlMovimento.getText().replace(".", "");
			 valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
	     }

     List<Movimento> listaPesquisa = service.getMovimentoDao().consultaPersonalidada(clienteSelecionado, tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(), tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), dataIncio, dataFim,toReceita.getValue() , tpDespesa.getValue());
     carregarPersonalizado(listaPesquisa);

     LocalDateTime menorMovimento = service.getMovimentoDao().consultaMenorData(clienteSelecionado, tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(), tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), dataIncio, dataFim,toReceita.getValue() , tpDespesa.getValue());
     BigDecimal valorAnteriorEntrada = service.getMovimentoDao().consultaSaldoAtenterior(menorMovimento);
     BigDecimal valorAnteriorSaida= service.getMovimentoDao().consultaSaldoAtenteriorSaida(menorMovimento);

     BigDecimal valorAnterior = BigDecimal.ZERO;
     if(valorAnteriorEntrada==null){
    	 valorAnteriorEntrada = BigDecimal.ZERO;
     }
     if(valorAnteriorSaida==null){
        valorAnteriorSaida = BigDecimal.ZERO;
     }

     BigDecimal valorEntrada = service.getMovimentoDao().consultaSaldoEntrada(clienteSelecionado, tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(), tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), dataIncio, dataFim,toReceita.getValue() , tpDespesa.getValue());
     BigDecimal valorSaida= service.getMovimentoDao().consultaSaldoSaida(clienteSelecionado, tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(), tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), dataIncio, dataFim,toReceita.getValue() , tpDespesa.getValue());

     if(valorEntrada==null){
    	 valorEntrada = BigDecimal.ZERO;
     }
     if(valorSaida==null){
    	 valorSaida = BigDecimal.ZERO;
     }

     valorAnterior = valorAnteriorEntrada.subtract(valorAnteriorSaida);
     vlInicial.setText(NumberFormat.getCurrencyInstance().format(valorAnterior));

     vlCredito.setText(NumberFormat.getCurrencyInstance().format(valorEntrada));
     vlDebito.setText(NumberFormat.getCurrencyInstance().format(valorSaida));

     vlFinal.setText(NumberFormat.getCurrencyInstance().format(valorAnterior.add(valorEntrada).subtract(valorSaida)));
    }

    public String retornaTpOperacao(String valor){
    	String retorno = null;
    	switch (valor) {
		case "=":
			retorno = "A";
			break;
		case ">":
			retorno = "B";
			break;
		case "<":
			retorno = "C";
			break;
		default:
			break;
		}
    	return retorno;
    }

}