package br.com.jdsb.controladores.principal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.Movimento;
import br.com.jdsb.negocio.dao.FluxoCaixa;
import br.com.jdsb.negocio.dao.FluxoCaixaCentroCusto;
import br.com.jdsb.negocio.dao.FluxoCaixaDiario;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class MPrincipal extends AbstractController<Movimento> {

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
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

	@FXML
	private BarChart<String, BigDecimal> barCompartivoMensal;

	@FXML
	private BarChart<String, BigDecimal> barLucratividade;

	@FXML
	private PieChart barCentroCusto;

	@FXML
	private Label lbValor;

	@FXML
	private Label lbRealizado;

	@FXML
	private Label lbRealizadoPercent;


    @FXML
    private Label lbValorDespesa;

    @FXML
    private Label lbRealizado1;

    @FXML
    private Label lbRealizadoDespesaPercent;

    @FXML
    private Label lbValorLucroPrejuizo;

    @FXML
    private Label lbRealizadoLpPercent;

    @FXML
    private Label lbVlMetaMesReceita;

    @FXML
    private Label lbVlMetaMesDespesa;

    @FXML
    private LineChart<String, BigDecimal> chartLinhaDia;

	public void carregarDados() {

		List<FluxoCaixa> caixas = service.getMovimentoDao().consultaValorTotalMes(null, null, null, null, null, null,
				null, null, null, null, AbstractController.anoBaseAtual, LocalDateTime
						.of(AbstractController.anoBaseAtual.getVlAnoBase(), LocalDateTime.now().getMonth(), 1, 0, 0));
		List<FluxoCaixaTotalTo> totalizadores = new ArrayList<FluxoCaixaTotalTo>();
		List<FluxoCaixaTo> fluxos = service.obterFluxosDeCaixa(caixas, totalizadores);

		XYChart.Series<String, BigDecimal> meta = new XYChart.Series<String, BigDecimal>();
		meta.setName("Meta");
		meta.getData().add(new XYChart.Data<String, BigDecimal>(totalizadores.get(0).getTipoMovimentoCaixa(),
				totalizadores.get(0).getVlTotalMeta()));
		meta.getData().add(new XYChart.Data<String, BigDecimal>(totalizadores.get(1).getTipoMovimentoCaixa(),
				totalizadores.get(1).getVlTotalMeta()));
		barCompartivoMensal.getData().add(meta);

		XYChart.Series<String, BigDecimal> resultado = new XYChart.Series<String, BigDecimal>();
		resultado.setName("Resultado");
		resultado.getData().add(new XYChart.Data<String, BigDecimal>(totalizadores.get(0).getTipoMovimentoCaixa(),
				totalizadores.get(0).getVlTotalMovimento()));
		resultado.getData().add(new XYChart.Data<String, BigDecimal>(totalizadores.get(1).getTipoMovimentoCaixa(),
				totalizadores.get(1).getVlTotalMovimento()));
		barCompartivoMensal.getData().add(resultado);
		lbValor.setText(totalizadores.get(0).getVlTotalMovimentoFormatada());
		lbRealizadoPercent.setText(totalizadores.get(0).getVlPercentualMargemDeErroRealizadoFormamada());

		lbValorLucroPrejuizo.setText(totalizadores.get(2).getVlTotalMovimentoFormatada());
		lbRealizadoLpPercent.setText(totalizadores.get(2).getVlPercentualRealizadoFormamada());
		lbValorDespesa.setText(totalizadores.get(1).getVlTotalMovimentoFormatada());
		lbRealizadoDespesaPercent.setText(totalizadores.get(1).getVlPercentualRealizadoFormamada());

		lbVlMetaMesReceita.setText(totalizadores.get(0).getVlTotalMetaFormatada());
		lbVlMetaMesDespesa.setText(totalizadores.get(1).getVlTotalMetaFormatada());


		XYChart.Series<String, BigDecimal> metaLucratividadeMeta = new XYChart.Series<String, BigDecimal>();
		metaLucratividadeMeta.setName("Meta");
		metaLucratividadeMeta.getData()
				.add(new XYChart.Data<String, BigDecimal>("Lucratividade", totalizadores.get(2).getVlTotalMeta()));
		barLucratividade.getData().add(metaLucratividadeMeta);

		XYChart.Series<String, BigDecimal> metaLucratividadeApurado = new XYChart.Series<String, BigDecimal>();
		metaLucratividadeApurado.setName("Resultado");
		metaLucratividadeApurado.getData()
				.add(new XYChart.Data<String, BigDecimal>("Lucratividade", totalizadores.get(2).getVlTotalMovimento()));
		barLucratividade.getData().add(metaLucratividadeApurado);

		for (FluxoCaixaCentroCusto centroCusto : this.service.getMovimentoDao().consultaSaldoCentroCusto(null, null,
				null, null, null, null, null, null,
				LocalDateTime.of(AbstractController.anoBaseAtual.getVlAnoBase(),
						LocalDateTime.now().getMonth().getValue(), 1, 0, 0),
				LocalDateTime.of(AbstractController.anoBaseAtual.getVlAnoBase(),
						LocalDateTime.now().getMonth().getValue() + 1, 1, 0, 0),
				null, null)) {
			PieChart.Data valor = new PieChart.Data(centroCusto.getDsCentroCusto(),
					centroCusto.getVlMovimento().doubleValue());
			barCentroCusto.getData().add(valor);
		}
		barCentroCusto.setTitle("Lucratividade por Centro de Custo");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDateTime dataPesquisa = LocalDateTime.of(2021, 9, 1, 0, 0);
		chartLinhaDia.setTitle("Analise de Fluxo Diário. Competência ".concat(dataPesquisa.format(formatter)));

		List<FluxoCaixaDiario> fluxoDiario = this.service.getMovimentoDao().consultaValorTotalDia(null, null, null, null, null, null, null, null, null, null, dataPesquisa);
		XYChart.Series<String, BigDecimal> receita = new XYChart.Series<String, BigDecimal> ();
		receita.setName("Receitas");

	    XYChart.Series<String, BigDecimal> despesa = new XYChart.Series<String, BigDecimal> ();
	    despesa.setName("Despesas");

		for(FluxoCaixaDiario f:fluxoDiario){
			receita.getData().add(new XYChart.Data<String, BigDecimal>(f.getVlDia().toString(), f.getVlMovimentoReceita()));
			despesa.getData().add(new XYChart.Data<String, BigDecimal>(f.getVlDia().toString(), f.getVlMovimentoDespesa()));
		}

		chartLinhaDia.getData().add(receita);
		chartLinhaDia.getData().add(despesa);

		// dataSeries1.getData().add(new XYChart.Data<String,
		// BigDecimal>("Java", new BigDecimal("1.973")));
		// barCompartivoMensal.getData().add(dataSeries1);
	}

}
