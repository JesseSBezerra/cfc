package br.com.jdsb.controladores.mAnaliseMetas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.dao.FluxoCaixa;
import br.com.jdsb.negocio.infra.Mascaras;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MAnaliseMetas extends AbstractController<FluxoCaixaTo> {

	@Override
	public void inicializar() {
		carregarDados();
		tpAnoBase.setItems(listaEstaticaAnoBase);
		tpAnoBase.setValue(anoBaseAtual);
		ativarChoiceAnoBase();
	}

	@Override
	public void beforeClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTableView() {
		// TODO Auto-generated method stub

	}

	public void ativarChoiceAnoBase(){
		  this.tpAnoBase.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpAnoBase.getItems().get(new_value.intValue())!=null){
	 				    AnoBase anoBase = tpAnoBase.getItems().get(new_value.intValue());
                        AbstractController.anoBaseAtual = anoBase;
	 				    List<FluxoCaixa> caixas = service.getMovimentoDao().consultaValorTotal(null, null, null, null, null, null, null, null, null, null, AbstractController.anoBaseAtual);
	 					List<FluxoCaixaTotalTo> totalizadores= new ArrayList<FluxoCaixaTotalTo>();
	 					List<FluxoCaixaTo> fluxos = service.obterFluxosDeCaixa(caixas, totalizadores);

	 					AbstractController.listaEstaticaFluxoCaixa.clear();
	 					AbstractController.listaEstaticaFluxoCaixaTotal.clear();

	 					AbstractController.listaEstaticaFluxoCaixa.addAll(fluxos);
	 					AbstractController.listaEstaticaFluxoCaixaTotal.addAll(totalizadores);

	 					carregarDados();
	 				}

	 			}
	 		});
	}

	private void customiseFactory(TableColumn<FluxoCaixaTo, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<FluxoCaixaTo, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableCell<FluxoCaixaTo, String> currentRow = this;

	                if (!isEmpty()) {

	                	 String valorAjustado = item.replace("R$ ", "");
	                     BigDecimal valor = Mascaras.recuperaString(valorAjustado);
	                     if(valor.longValue()>0){
	                    	 currentRow.setStyle("-fx-background-color:lightgreen");
	                     }
	                     //currentRow.setStyle("-fx-background-color:lightcoral");
	                }
	            }
	        };
	    });
	}



	public void carregarDados(){
		tbcDtCompetencia.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("dtCompetenciaFormatada"));
		tbcVlMetaReceita.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMetaReceitaFormatada"));
		tbcVlReceitaRealizada.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMovimentoReceitaFormatada"));
		tbcVlPercentualDiferencaReceita.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlPercentualDiferencaReceitaFormatada"));
		Mascaras.customiseFactoryEstimativaReceita(tbcVlPercentualDiferencaReceita);

		tbcVlMetaDespesa.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMetaDespesaFormatada"));
		tbcVlDespesaRealizada.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlMovimentoDespesaFormatada"));
		tbcVlPercentualDiferencaDespesa.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlPercentualDiferencaDespesaFormatada"));
		Mascaras.customiseFactoryEstimativaDespesa(tbcVlPercentualDiferencaDespesa);

		tbcVlMetaResultado.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlResultadoMetaFormatada"));
		tbcVlResultadoRealizado.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlResultadoReceitaFormatada"));
		tbcVlPercentualDiferencaResultado.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTo, String>("vlPercentualDiferencaFormatada"));
		Mascaras.customiseFactoryEstimativa(tbcVlPercentualDiferencaResultado);
		tblMetas.setItems(listaEstaticaFluxoCaixa);

		for(FluxoCaixaTotalTo total:listaEstaticaFluxoCaixaTotal){
           switch (total.getTipoMovimentoCaixa()) {
					case "Receitas":
						vlMetaReceitaTotal.setText(total.getVlTotalMetaFormatada());
						vlMetaReceitaParcial.setText(total.getVlParcialMetaFormatada());
						vlReceitaTotal.setText(total.getVlTotalMovimentoFormatada());
						vlReceitaParcial.setText(total.getVlParcialMovimentoFormatada());
						vlPercentualDiferencaReceitaTotal.setText(total.getVlPercentualDiferencaFormamada());
						vlPercentualDiferencaReceitaParcial.setText(total.getVlPercentualParcialDiferencaFormamada());
					break;

					case "Despesas":
						vlMetaDespesaTotal.setText(total.getVlTotalMetaFormatada());
						vlMetaDespesaParcial.setText(total.getVlParcialMetaFormatada());
						vlDespesaTotal.setText(total.getVlTotalMovimentoFormatada());
						vlDespesaParcial.setText(total.getVlParcialMovimentoFormatada());
						vlPercentualDiferencaDespesaTotal.setText(total.getVlPercentualDiferencaFormamada());
						vlPercentualDiferencaDespesaParcial.setText(total.getVlPercentualParcialDiferencaFormamada());
					break;

					case "Lucro/Prejuízo":
						vlMetaResultadoTotal.setText(total.getVlTotalMetaFormatada());
						vlMetaResultadoParcial.setText(total.getVlParcialMetaFormatada());
						vlResultadoTotal.setText(total.getVlTotalMovimentoFormatada());
						vlResultadoParcial.setText(total.getVlParcialMovimentoFormatada());
						//vlPercentualDiferencaResultadoTotal.setText(total.getVlPercentualDiferencaFormamada());
					break;



					default:
						break;
					}
				}

		tbcPaDsMovimento.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("tipoMovimentoCaixa"));
		tbcPaMetaRealizada.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("vlTotalMovimentoFormatada"));
        tbcPaMetaTotal.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("vlTotalMetaFormatada"));
        tbcPaPercentualDiferenca.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("vlPercentualRealizadoFormamada"));
        tbcPaDsMargemErro.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("vlPercentualMargemDeErroRealizadoFormamada"));
        tbcPaDsOrientacao.setCellValueFactory(new PropertyValueFactory<FluxoCaixaTotalTo, String>("dsMensagemMargemErro"));
        Mascaras.customiseFactoryEstimativaMargemErro(tbcPaDsOrientacao);
		tblPlanejamentoFinanceiro.setItems(listaEstaticaFluxoCaixaTotal);

	}

	@FXML
	private ChoiceBox<AnoBase> tpAnoBase;

	@FXML
	private TableView<FluxoCaixaTo> tblMetas;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcDtCompetencia;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlMetaReceita;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlReceitaRealizada;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlPercentualDiferencaReceita;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlMetaDespesa;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlDespesaRealizada;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlPercentualDiferencaDespesa;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlMetaResultado;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlResultadoRealizado;

	@FXML
	private TableColumn<FluxoCaixaTo, String> tbcVlPercentualDiferencaResultado;

	@FXML
	private TextField vlMetaReceitaTotal;

	@FXML
	private TextField vlMetaReceitaParcial;

	@FXML
	private TextField vlReceitaTotal;

	@FXML
	private TextField vlReceitaParcial;

	@FXML
	private TextField vlPercentualDiferencaReceitaTotal;

	@FXML
	private TextField vlPercentualDiferencaReceitaParcial;

	@FXML
	private TextField vlMetaDespesaTotal;

	@FXML
	private TextField vlMetaDespesaParcial;

	@FXML
	private TextField vlDespesaTotal;

	@FXML
	private TextField vlDespesaParcial;

	@FXML
	private TextField vlPercentualDiferencaDespesaTotal;

	@FXML
	private TextField vlPercentualDiferencaDespesaParcial;

	@FXML
	private TextField vlMetaResultadoTotal;

	@FXML
	private TextField vlResultadoTotal;

	@FXML
	private TextField vlResultadoParcial;

	@FXML
	private TextField vlMetaResultadoParcial;

	@FXML
	private TableView<FluxoCaixaTotalTo> tblPlanejamentoFinanceiro;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaDsMovimento;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaMetaTotal;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaMetaRealizada;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaPercentualDiferenca;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaDsMargemErro;

	@FXML
	private TableColumn<FluxoCaixaTotalTo, String> tbcPaDsOrientacao;

	@FXML
	private Label lbAnoBase;

}
