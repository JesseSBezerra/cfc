package br.com.jdsb.cfc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.jdsb.cfc.Main.StageReadlyEvent;
import br.com.jdsb.negocio.service.NegocioService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
@Component
@EnableAutoConfiguration
public class StageInitializer implements ApplicationListener<StageReadlyEvent> {

	@Value("classpath:/m_cadastro_banco.fxml")
	private Resource resource;

	@Value("classpath:/m_cadastro_cliente_fornec.fxml")
	private Resource mCadastroClienteFornec;

	private Map<String, Resource> mapa = new HashMap<>();

	@Autowired
	private NegocioService service;

	@Override
	public void onApplicationEvent(StageReadlyEvent event) {
		mapa.put("m_cadastro_banco", resource);
		mapa.put("m_cadastro_cliente_fornec", mCadastroClienteFornec);

		Stage stage = event.getStage();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(mapa.get("m_cadastro_banco").getURL());
			Parent parent = fxmlLoader.load();
			AbstractController abstractController = fxmlLoader.getController();
			abstractController.carregar(service,mapa,null);
			Scene scene = stage.getScene();
			scene = new Scene(parent, 900, 700);
		//	stage.getIcons().add(new Image(resourceImage.getURL().toString()));
//			Dialogs.setUrl(resourceImage.getURL());
			stage.setTitle("Cadastro Bancário V.1.0");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
