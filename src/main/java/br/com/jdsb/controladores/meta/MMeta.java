package br.com.jdsb.controladores.meta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Meta;
import br.com.jdsb.negocio.infra.Mascaras;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MMeta extends AbstractController<Meta> {

	@Override
	public void inicializar() {
		AnoBase anoBase = this.service.getAnoBaseDao().findByVlAnoBase(2021);
		List<Meta> metas = this.service.getMetaDao().findByAnoBaseOrderByDtCompetencia(anoBase);
		carregarDados(metas);
		Mascaras.mascaraValor(vlMetaReceita);
		Mascaras.mascaraValor(vlMetaDespesa);
		preencherTabelaTela(tblMeta);
		tpAnoBase.setItems(listaEstaticaAnoBase);
		tpAnoBase.setValue(service.getAnoBaseDao().findByVlAnoBase(LocalDate.now().getYear()));
		ativarChoiceAnoBase();
	}

	@Override
	public void beforeClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTableView() {
		vlMetaReceita.setText(objetoSelecionado.getVlMetaReceita().toPlainString());
		dtCompetencia.setText(objetoSelecionado.getDtCompetenciaFormatado());
		dtCompetencia.setDisable(true);
		vlMetaDespesa.setText(objetoSelecionado.getVlMetaDespesa().toPlainString());
		vlAcumulado.setDisable(true);
		vlAcumulado.setText(objetoSelecionado.getVlAcumuladoFormatado());
	}

	public void ativarChoiceAnoBase(){
		  this.tpAnoBase.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpAnoBase.getItems().get(new_value.intValue())!=null){
	 				   AnoBase anoBase = tpAnoBase.getItems().get(new_value.intValue());
	 				   List<Meta> metas  = service.getMetaDao().findByAnoBaseOrderByDtCompetencia(anoBase);
	 				   carregarDados(metas);
	 				}

	 			}
	 		});
	}

	public void carregarDados(List<Meta> metas) {
		populateTableDados = FXCollections.observableArrayList();
		for (Meta meta:metas){
			populateTableDados.add(meta);
		}
		tbcDtCompetencia.setCellValueFactory(new PropertyValueFactory<Meta, String>("dtCompetenciaFormatado"));
		tbcvlMetaReceita.setCellValueFactory(new PropertyValueFactory<Meta, String>("vlMetaReceitaFormatado"));
		tbcVlMetaDespesa.setCellValueFactory(new PropertyValueFactory<Meta, String>("vlMetaDespesaFormatado"));
		tbcVlAcumulado.setCellValueFactory(new PropertyValueFactory<Meta, String>("vlAcumuladoFormatado"));
		tblMeta.setItems(populateTableDados);
	}

	@FXML
	private TextField vlMetaReceita;

	@FXML
	private TextField vlMetaDespesa;

	@FXML
	private TextField vlAcumulado;

	@FXML
	private TableView<Meta> tblMeta;

	@FXML
	private TableColumn<Meta, String> tbcDtCompetencia;

	@FXML
	private TableColumn<Meta, String> tbcvlMetaReceita;

	@FXML
	private TableColumn<Meta, String> tbcVlMetaDespesa;

	@FXML
	private TableColumn<Meta, String> tbcVlAcumulado;

	@FXML
	private ChoiceBox<AnoBase> tpAnoBase;

	@FXML
	private TextField dtCompetencia;

	@FXML
	public void salvar(ActionEvent event) {

		if(objetoSelecionado!=null){
			String valorTratado;
			BigDecimal valor;

			valorTratado = vlMetaReceita.getText().replace(".", "");
			valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));

			objetoSelecionado.setVlMetaReceita(valor);

			valorTratado = vlMetaDespesa.getText().replace(".", "");
			valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));

			objetoSelecionado.setVlMetaDespesa(valor);

			this.service.getMetaDao().save(objetoSelecionado);
			mensagem("Registro Atualizado com Sucesso!");

			vlAcumulado.setText(objetoSelecionado.getVlAcumuladoFormatado());

			List<Meta> metas = this.service.getMetaDao().findByAnoBaseOrderByDtCompetencia(objetoSelecionado.getAnoBase());
			carregarDados(metas);
		}

	}

}
