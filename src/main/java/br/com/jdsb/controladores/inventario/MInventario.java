package br.com.jdsb.controladores.inventario;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.jfoenix.controls.JFXTimePicker;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.Movimento;
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

public class MInventario extends AbstractController<Movimento> {

	@Override
	public void inicializar() {
		carregarDados();
		preencherTabelaTela(tblInventario);
		dtInventario.setValue(LocalDate.now());
		hrInventario.setValue(LocalTime.now());
		carregarCrud(service.getMovimentoDao());
		carregarBancos();
		ativarCheckBanco();
	}

	private Banco bancoSelecionado;

	public void carregarBancos(){
		vlSaldoAtual.setEditable(false);
		 Mascaras.mascaraValor(vlSaldoAtual);
		 ObservableList<Banco> populateTableBanco = FXCollections.observableArrayList();
		 for(Banco banco:service.getBancoDao().findAll()){
			 populateTableBanco.add(banco);
		 }
         tpBanco.setItems(populateTableBanco);
         Mascaras.mascaraValor(vlNovoSaldo);
         hrInventario.set24HourView(true);
	}

	public void ativarCheckBanco(){
		  this.tpBanco.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
	 			public void changed(ObservableValue ov, Number value, Number new_value) {
	 				if(tpBanco.getItems().get(new_value.intValue())!=null){
	 				  bancoSelecionado = tpBanco.getItems().get(new_value.intValue());
	 				  if(bancoSelecionado.getVlSaldoAtual()==null){
	 					  vlSaldoAtual.setText("0");
	 				  }else{
	 					String valor = NumberFormat.getCurrencyInstance().format(bancoSelecionado.getVlSaldoAtual());
	 					 vlSaldoAtual.setText(valor);
	 				  }
	 				}

	 			}
	 		});
	}

	public void carregarTabelaDeDados(MovimentoDao movimentoDao){
		populateTableDados = FXCollections.observableArrayList();
		populateTableDados.addAll(movimentoDao.findByTpMovimento("Inventário"));
	}

	public void carregarDados() {
		carregarTabelaDeDados(this.service.getMovimentoDao());
		tbcBanco.setCellValueFactory(new PropertyValueFactory<Movimento, Banco>("banco"));
		tbcVlNovoSaldo.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlMovimentoFormatado"));
		tbcVlSaldoAnterior.setCellValueFactory(new PropertyValueFactory<Movimento, String>("vlSaldoAnteriorFormatado"));
		tbcDsObservacao.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dsObservacaoMovimento"));
		tbcDtInventario.setCellValueFactory(new PropertyValueFactory<Movimento, String>("dtMovimentoFormatado"));
		tblInventario.setItems(populateTableDados);
	}

	@Override
	public void beforeClear() {
		tpBanco.setValue(null);
		dtInventario.setValue(LocalDate.now());
		hrInventario.setValue(LocalTime.now());
		vlNovoSaldo.setText("");
		vlSaldoAtual.setText("");

	}

	@Override
	public void afterTableView() {
		tpBanco.setValue(objetoSelecionado.getBanco());
		dtInventario.setValue(objetoSelecionado.getDtMovimento().toLocalDate());
		hrInventario.setValue(objetoSelecionado.getDtMovimento().toLocalTime());
		vlNovoSaldo.setText(objetoSelecionado.getVlMovimentoFormatado());
		vlSaldoAtual.setText(objetoSelecionado.getVlSaldoAnteriorFormatado());
		dsObvservacao.setText(objetoSelecionado.getDsObservacaoMovimento());

	}

	@FXML
	private JFXTimePicker hrInventario;

	@FXML
	private DatePicker dtInventario;

	@FXML
	private ChoiceBox<Banco> tpBanco;

	@FXML
	private TextField vlNovoSaldo;

	@FXML
	private TextArea dsObvservacao;

	@FXML
	private TableView<Movimento> tblInventario;

	@FXML
	private TableColumn<Movimento, Banco> tbcBanco;

	@FXML
	private TableColumn<Movimento, String> tbcDtInventario;

	@FXML
	private TableColumn<Movimento, String> tbcVlSaldoAnterior;

	@FXML
	private TableColumn<Movimento, String> tbcVlNovoSaldo;

	@FXML
	private TableColumn<Movimento, String> tbcDsObservacao;

	@FXML
	private Label lbMsg;

	@FXML
	private TextField vlSaldoAtual;

	@FXML
	void salvar(ActionEvent event) {
		    if(dtInventario.getValue()==null){
		    	dialogos.AletaW("Atenção","Não foi selecionado a data", "selecione a data!");
		    }else if(hrInventario.getValue()==null){
		    	dialogos.AletaW("Atenção","Não foi selecionado a hora", "selecione a hora!");
	        }else if(bancoSelecionado==null){
		    	dialogos.AletaW("Atenção","Não foi selecionado um banco", "selecione um banco!");
		    }else if (vlNovoSaldo==null || vlNovoSaldo.getText().isEmpty()){
		    	dialogos.AletaW("Atenção","Não foi informado um valor ao inventário","informe um valor!.");
		    }else {
				if(dialogos.AletaQuestion("Atenção", "Após Cadastrar um inventário o saldo do banco será modificado para o valor informado", "Confirma ?")){
                    if(objetoSelecionado==null){
                    	objetoSelecionado = new Movimento();
                    }
					LocalDateTime dhMovimento = LocalDateTime.of(dtInventario.getValue().getYear(),
							dtInventario.getValue().getMonth(), dtInventario.getValue().getDayOfMonth(), hrInventario.getValue().getHour(), hrInventario.getValue().getMinute(), hrInventario.getValue().getSecond());

					objetoSelecionado.setTpMovimento("Inventário");
					objetoSelecionado.setDtMovimento(dhMovimento);
					objetoSelecionado.setBanco(bancoSelecionado);
					objetoSelecionado.setDsObservacaoMovimento(dsObvservacao.getText());

					String valorTratado = vlNovoSaldo.getText().replace(".", "");
					BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
					bancoSelecionado.setVlSaldoAtual(valor);
					objetoSelecionado.setVlMovimento(valor);

					valorTratado = vlSaldoAtual.getText().replace(".", "");
					valor = BigDecimal.valueOf(Double.parseDouble(valorTratado.replace(",", ".")));
					objetoSelecionado.setVlSaldoAnterior(valor);
					service.getMovimentoDao().save(objetoSelecionado);
					service.getBancoDao().save(bancoSelecionado);
					dialogos.AletaI("Atenção", "Saldo do banco ajustado!", "");
				}
		    }

	}

}
