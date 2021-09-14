package br.com.jdsb.negocio.infra;

import java.math.BigDecimal;

import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Mascaras {

	public static void mascaraValor(TextField vlNovoSaldo){
		vlNovoSaldo.lengthProperty().addListener(new ChangeListener<Number>() {
	           @Override
	           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	               String value = vlNovoSaldo.getText();
	               value = value.replaceAll("[^0-9]", "");
	               value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
	               value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
	               value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
	               value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
	               value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
	               vlNovoSaldo.setText(value);

	               vlNovoSaldo.textProperty().addListener(new ChangeListener<String>() {
	                   @Override
	                   public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
	                       if (newValue.length() > 17)
	                    	   vlNovoSaldo.setText(oldValue);
	                   }
	               });
	           }
	       });
	}

	public static void mascaraCPF(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==4){
                    textField.setText(textField.getText().substring(0,3));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==8){
                    textField.setText(textField.getText().substring(0,7));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==12){
                    textField.setText(textField.getText().substring(0,11));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==14) event.consume();

                if(textField.getText().length()==3){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==11){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d.-*")){
                textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

	public static void mascaraCNPJ(TextField textField){

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==3){
                    textField.setText(textField.getText().substring(0,2));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==7){
                    textField.setText(textField.getText().substring(0,6));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==11){
                    textField.setText(textField.getText().substring(0,10));
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==16){
                    textField.setText(textField.getText().substring(0,15));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==18) event.consume();

                if(textField.getText().length()==2){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==6){
                    textField.setText(textField.getText()+".");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==10){
                    textField.setText(textField.getText()+"/");
                    textField.positionCaret(textField.getText().length());
                }
                if(textField.getText().length()==15){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }

            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d./-*")){
                textField.setText(textField.getText().replaceAll("[^\\d./-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }

	 public static void mascaraTelefone(TextField textField){

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if("0123456789".contains(event.getCharacter())==false){
	                event.consume();
	            }

	            if(event.getCharacter().trim().length()==0){ // apagando

	                if(textField.getText().length()==10&&textField.getText().substring(9,10).equals("-")){
	                    textField.setText(textField.getText().substring(0,9));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if(textField.getText().length()==9&&textField.getText().substring(8,9).equals("-")){
	                    textField.setText(textField.getText().substring(0,8));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if(textField.getText().length()==4){
	                    textField.setText(textField.getText().substring(0,3));
	                    textField.positionCaret(textField.getText().length());
	                }
	                if(textField.getText().length()==1){
	                    textField.setText("");
	                }

	            }else{ //escrevendo

	                if(textField.getText().length()==14) event.consume();

	                if(textField.getText().length()==0){
	                    textField.setText("("+event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if(textField.getText().length()==3){
	                    textField.setText(textField.getText()+")"+event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if(textField.getText().length()==8){
	                    textField.setText(textField.getText()+"-"+event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if(textField.getText().length()==9&&textField.getText().substring(8,9)!="-"){
	                    textField.setText(textField.getText()+"-"+event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }
	                if(textField.getText().length()==13&&textField.getText().substring(8,9).equals("-")){
	                    textField.setText(textField.getText().substring(0,8)+textField.getText().substring(9,10)+"-"+textField.getText().substring(10,13)+event.getCharacter());
	                    textField.positionCaret(textField.getText().length());
	                    event.consume();
	                }

	            }

	        });

	        textField.setOnKeyReleased((KeyEvent evt) -> {

	            if(!textField.getText().matches("\\d()-*")){
	                textField.setText(textField.getText().replaceAll("[^\\d()-]", ""));
	                textField.positionCaret(textField.getText().length());
	            }
	        });

	    }

	 public static void mascaraEmail(TextField textField){

	        textField.setOnKeyTyped((KeyEvent event) -> {
	            if("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz._-@".contains(event.getCharacter())==false){
	                event.consume();
	            }

	            if("@".equals(event.getCharacter())&&textField.getText().contains("@")){
	                event.consume();
	            }

	            if("@".equals(event.getCharacter())&&textField.getText().length()==0){
	                event.consume();
	            }
	        });

	    }


	public static void customiseFactoryEstimativa(TableColumn<FluxoCaixaTo, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<FluxoCaixaTo, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableCell<FluxoCaixaTo, String> currentRow = this;

	                if (!isEmpty()) {

	                	 String valorAjustado = item.replace("%", "");
	                     BigDecimal valor = Mascaras.recuperaString(valorAjustado);
	                     if(valor.longValue()<0){
	                    	 currentRow.setStyle("-fx-background-color:lightgreen");
	                     }else if(valor.longValue()>0 && valor.longValue()!=100){
	                         currentRow.setStyle("-fx-background-color:lightcoral");
	                     }
	                }
	            }
	        };
	    });
	}

	public static void customiseFactoryEstimativaReceita(TableColumn<FluxoCaixaTo, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<FluxoCaixaTo, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableCell<FluxoCaixaTo, String> currentRow = this;

	                if (!isEmpty()) {

	                	 String valorAjustado = item.replace("%", "");
	                     BigDecimal valor = Mascaras.recuperaString(valorAjustado);
	                     if(valor.longValue()<0 && valor.abs().longValue()!=100){
	                    	 currentRow.setStyle("-fx-background-color:lightcoral");
	                     }else if(valor.longValue()>0 && valor.longValue()!=100){
	                         currentRow.setStyle("-fx-background-color:lightgreen");
	                     }
	                }
	            }
	        };
	    });
	}

	public static void customiseFactoryEstimativaDespesa(TableColumn<FluxoCaixaTo, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<FluxoCaixaTo, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableCell<FluxoCaixaTo, String> currentRow = this;

	                if (!isEmpty()) {

	                	 String valorAjustado = item.replace("%", "");
	                     BigDecimal valor = Mascaras.recuperaString(valorAjustado);
	                     if(valor.longValue()<0 && valor.abs().longValue()!=100){
	                    	 currentRow.setStyle("-fx-background-color:lightcoral");
	                     }else if(valor.longValue()>0 && valor.longValue()!=100){
	                         currentRow.setStyle("-fx-background-color:lightgreen");
	                     }
	                }
	            }
	        };
	    });
	}

	public static void customiseFactoryEstimativaMargemErro(TableColumn<FluxoCaixaTotalTo, String> calltypel) {
	    calltypel.setCellFactory(column -> {
	        return new TableCell<FluxoCaixaTotalTo, String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);

	                setText(empty ? "" : getItem().toString());
	                setGraphic(null);

	                TableCell<FluxoCaixaTotalTo, String> currentRow = this;

	                if (!isEmpty()) {

	                     if(item.contains("bem")){
	                    	 currentRow.setStyle("-fx-background-color:lightgreen");
	                     }else{
	                         currentRow.setStyle("-fx-background-color:lightcoral");
	                     }
	                }
	            }
	        };
	    });
	}


	public static BigDecimal recuperaString(String str)
	{
	        str = str.replace(".","");
	        str = str.replace(",", ".");
	        str = str.trim();

	        return new BigDecimal(str);
	}

}
