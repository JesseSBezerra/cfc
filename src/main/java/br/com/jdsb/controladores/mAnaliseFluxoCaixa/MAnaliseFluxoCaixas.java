package br.com.jdsb.controladores.mAnaliseFluxoCaixa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.Movimento;
import br.com.jdsb.negocio.Receita;
import br.com.jdsb.negocio.dao.FluxoCaixa;
import br.com.jdsb.negocio.infra.Mascaras;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class MAnaliseFluxoCaixas extends AbstractController<Movimento> {

	@Override
	public void inicializar() {
		carregarConstantes();
		carregarDados();

	}

	@Override
	public void beforeClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTableView() {
		// TODO Auto-generated method stub

	}

	public void carregarConstantes() {
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

		ativarChoiceCliente();
		ativarChoiceDespesa();
		ativarChoiceFornecedor();
		ativarChoiceReceita();

		tpAnoBase.setItems(listaEstaticaAnoBase);
		tpAnoBase.setValue(anoBaseAtual);

		Mascaras.mascaraValor(vlMovimento);
	}

	public void carregarDados() {
		tbcDtCompetencia.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("dtCompetenciaFormatada"));
		tbcVlReceita.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMovimentoReceitaFormatada"));
		tbcVlSaldoInicial
				.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlSaldoAnteriorFormatada"));
		tbcVlDespesa.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMovimentoDespesaFormatada"));
		tbcVlAcumulado
				.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMovimentoAcumuladoFormatada"));

		tbcVlLucro.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlResultadoReceitaFormatada"));
		tblLancamento.setItems(listaEstaticaFluxoCaixa);

	}

	public void ativarChoiceDespesa() {
		this.tpDespesa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				try {
					if (tpDespesa.getItems().get(new_value.intValue()) != null) {
						String tipoDespesaSelecionada = tpDespesa.getItems().get(new_value.intValue());
						ObservableList<Despesa> receitas = FXCollections.observableArrayList();
						receitas.addAll(service.getDesepesaDao().findByTpDespesa(tipoDespesaSelecionada));
						tpDespesaItem.setValue(null);
						tpDespesaItem.setItems(receitas);
					}
				} catch (Exception e) {
					tpDespesaItem.setValue(null);
					tpDespesaItem.setItems(null);
				}

			}
		});
	}

	public void ativarChoiceReceita() {
		this.toReceita.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				try {
					if (toReceita.getItems().get(new_value.intValue()) != null) {
						String tipoReceitaSelecionada = toReceita.getItems().get(new_value.intValue());
						ObservableList<Receita> receitas = FXCollections.observableArrayList();
						receitas.addAll(service.getReceitaDao().findByTpReceita(tipoReceitaSelecionada));
						tpReceitaItem.setValue(null);
						tpReceitaItem.setItems(receitas);
					}
				} catch (Exception e) {
					tpReceitaItem.setValue(null);
					tpReceitaItem.setItems(null);
				}

			}
		});
	}

	public void ativarChoiceCliente() {
		this.tpCliente.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if (tpCliente.getItems().get(new_value.intValue()) != null) {
					tpCliente.getItems().get(new_value.intValue());
				}

			}
		});
	}

	public void ativarChoiceFornecedor() {
		this.tpFornecedor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if (tpFornecedor.getItems().get(new_value.intValue()) != null) {
					tpFornecedor.getItems().get(new_value.intValue());
				}

			}
		});
	}

	@FXML
	private ChoiceBox<String> toReceita;

	@FXML
	private TableView<FluxoCaixaTo> tblLancamento;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcDtCompetencia;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlSaldoInicial;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlReceita;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlDespesa;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlLucro;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlAcumulado;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlLucratividade;

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
	private ChoiceBox<AnoBase> tpAnoBase;

	@FXML
	public void cancelar(ActionEvent event) {

	}

	@FXML
	public void imprimir(ActionEvent event) {

	}

	@FXML
	public void pesquisar(ActionEvent event) {
		BigDecimal valor = null;
		if (vlMovimento.getText() != null && !vlMovimento.getText().isEmpty()) {
			String valorTratado = vlMovimento.getText().replace(".", "");
			valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
		}

		ClienteFornecedor clienteSelecionado = tpCliente.getValue();

		List<FluxoCaixa> caixas = service.getMovimentoDao().consultaValorTotal(clienteSelecionado,
				tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(),
				tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), toReceita.getValue(),
				tpDespesa.getValue(), tpAnoBase.getValue());
		List<FluxoCaixaTotalTo> totalizadores = new ArrayList<FluxoCaixaTotalTo>();
		List<FluxoCaixaTo> fluxos = service.obterFluxosDeCaixa(caixas, totalizadores);

		AbstractController.listaEstaticaFluxoCaixa.clear();
		AbstractController.listaEstaticaFluxoCaixaTotal.clear();

		AbstractController.listaEstaticaFluxoCaixa.addAll(fluxos);
		AbstractController.listaEstaticaFluxoCaixaTotal.addAll(totalizadores);

		carregarDados();

		LocalDateTime menorMovimento = service.getMovimentoDao().consultaMenorData(clienteSelecionado,
				tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(),
				tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()),  LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase(), 1, 1, 0, 0), LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase()+1, 1, 1, 0, 0),
				toReceita.getValue(), tpDespesa.getValue());
		BigDecimal valorAnteriorEntrada = service.getMovimentoDao().consultaSaldoAtenterior(LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase(), 1, 1, 0, 0));
		BigDecimal valorAnteriorSaida = service.getMovimentoDao().consultaSaldoAtenteriorSaida(menorMovimento);

		BigDecimal valorAnterior = BigDecimal.ZERO;
		if (valorAnteriorEntrada == null) {
			valorAnteriorEntrada = BigDecimal.ZERO;
		}
		if (valorAnteriorSaida == null) {
			valorAnteriorSaida = BigDecimal.ZERO;
		}

		BigDecimal valorEntrada = service.getMovimentoDao().consultaSaldoEntrada(clienteSelecionado,
				tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(),
				tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()), LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase(), 1, 1, 0, 0), LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase()+1, 1, 1, 0, 0),
				toReceita.getValue(), tpDespesa.getValue());
		BigDecimal valorSaida = service.getMovimentoDao().consultaSaldoSaida(clienteSelecionado,
				tpCentroCusto.getValue(), tpBanco.getValue(), tpReceitaItem.getValue(), tpDespesaItem.getValue(),
				tpFormaPagamento.getValue(), valor, retornaTpOperacao(tpOperacao.getValue()),  LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase(), 1, 1, 0, 0), LocalDateTime.of(tpAnoBase.getValue().getVlAnoBase()+1, 1, 1, 0, 0),
				toReceita.getValue(), tpDespesa.getValue());

		if (valorEntrada == null) {
			valorEntrada = BigDecimal.ZERO;
		}
		if (valorSaida == null) {
			valorSaida = BigDecimal.ZERO;
		}

		valorAnterior = valorAnteriorEntrada.subtract(valorAnteriorSaida);
		vlInicial.setText(NumberFormat.getCurrencyInstance().format(valorAnterior));

		vlCredito.setText(NumberFormat.getCurrencyInstance().format(valorEntrada));
		vlDebito.setText(NumberFormat.getCurrencyInstance().format(valorSaida));

		vlFinal.setText(
				NumberFormat.getCurrencyInstance().format(valorAnterior.add(valorEntrada).subtract(valorSaida)));

	}

	public String retornaTpOperacao(String valor) {
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
