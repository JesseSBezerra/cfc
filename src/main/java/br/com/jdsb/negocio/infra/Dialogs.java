package br.com.jdsb.negocio.infra;


import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Dialogs {

	public static void AletaW(String titulo,String mensagem,String acao) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.setContentText(acao);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(new Dialogs().getClass().getResourceAsStream("nave.png")));
		alert.showAndWait();
	}
	public static void AletaE(String titulo,String mensagem,String acao) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.setContentText(acao);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(new Dialogs().getClass().getResourceAsStream("nave.png")));
		alert.showAndWait();
	}

	public static void AletaI(String titulo,String mensagem,String acao) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(new Dialogs().getClass().getResourceAsStream("nave.png")));
		alert.showAndWait();
	}

	public static boolean AletaQuestion(String titulo,String mensagem,String questao) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle(titulo);
	alert.setHeaderText(mensagem);
	alert.setContentText(questao);
	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(new Dialogs().getClass().getResourceAsStream("nave.png")));

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		return true;
	} else {
		return false;
	}
	}
}
