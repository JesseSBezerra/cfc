package br.com.jdsb.negocio.infra;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.Resource;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Dialogos {

	private Resource icone;

	public Dialogos(Resource icone) {
		this.icone = icone;
	}

	public void AletaW(String titulo,String mensagem,String acao) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.setContentText(acao);
		try {
			((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(icone.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.showAndWait();
	}
	public  void AletaE(String titulo,String mensagem,String acao){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		alert.setContentText(acao);
		try {
			((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(icone.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.showAndWait();
	}

	public  void AletaI(String titulo,String mensagem,String acao) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(mensagem);
		try {
			((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(icone.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.showAndWait();
	}

	public  boolean AletaQuestion(String titulo,String mensagem,String questao) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle(titulo);
	alert.setHeaderText(mensagem);
	alert.setContentText(questao);
	try {
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(icone.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){
		return true;
	} else {
		return false;
	}
	}
}
