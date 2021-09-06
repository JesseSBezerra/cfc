package br.com.jdsb.cfc;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

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

	@Value("classpath:/m_cadastro_receita.fxml")
	private Resource mCadastroReceita;

	@Value("classpath:/m_cadastro_despesa.fxml")
	private Resource mCadastroDespesa;

	@Value("classpath:/m_cadastro_centro_custo.fxml")
	private Resource mCadastroCentroCusto;

	@Value("classpath:/R_RECEITA.jasper")
	private Resource RReceita;

	@Value("classpath:/R_BANCO.jasper")
	private Resource RBanco;

	@Value("classpath:/R_DESPESA.jasper")
	private Resource RDespesa;

	@Value("classpath:/R_CENTRO_CUSTO.jasper")
	private Resource RCentroCusto;

	@Value("classpath:/R_CLIENTE_FORNECEDOR.jasper")
	private Resource RClicenteFornecedor;

	@Autowired
	private DataSource dataSource;

	private Map<String, Resource> mapa = new HashMap<>();

	@Autowired
	private NegocioService service;

	@Override
	public void onApplicationEvent(StageReadlyEvent event) {
		mapa.put("m_cadastro_banco", resource);
		mapa.put("m_cadastro_cliente_fornec", mCadastroClienteFornec);
		mapa.put("m_cadastro_receita", mCadastroReceita);
		mapa.put("m_cadastro_despesa", mCadastroDespesa);
		mapa.put("m_cadastro_centro_custo", mCadastroCentroCusto);
		mapa.put("R_RECEITA", RReceita);
		mapa.put("R_BANCO", RBanco);
		mapa.put("R_DESPESA", RDespesa);
		mapa.put("R_CENTRO_CUSTO", RCentroCusto);
		mapa.put("R_CLIENTE_FORNECEDOR", RClicenteFornecedor);

		Stage stage = event.getStage();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(mapa.get("m_cadastro_banco").getURL());
			Parent parent = fxmlLoader.load();
			AbstractController abstractController = fxmlLoader.getController();
			abstractController.carregar(service,mapa,null,dataSource,"R_BANCO");
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
