package br.com.jdsb.negocio.menu;

import java.util.Map;

import org.springframework.core.io.Resource;

import br.com.jdsb.cfc.AbstractController;
import br.com.jdsb.negocio.service.NegocioService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavegarAteImplements implements NavegarAte{

	@Override
	public void navegarAte(Map<String, Resource> mapa,NegocioService service,String formulario) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(mapa.get(formulario).getURL());
				Parent root1 = (Parent) fxmlLoader.load();
				AbstractController controller = fxmlLoader.getController();
				controller.carregar(service,mapa,formulario);
				Stage stage = new Stage();
				stage.setResizable(false);
				Scene scene = stage.getScene();
				stage.setTitle("Cadastro Bancário V.1.0");
				stage.setResizable(false);
				stage.setScene(scene);
				stage.show();
				stage.setScene(new Scene(root1, 900, 700));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

}
