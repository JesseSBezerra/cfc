package br.com.jdsb.cfc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private ConfigurableApplicationContext applicationConext;

	@Override
	public void init() {
		applicationConext = new SpringApplicationBuilder(CfcApplication.class).run();
	}
	@Override
	public void stop() {
		//applicationConext.close();
	}


	static class StageReadlyEvent extends ApplicationEvent{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public StageReadlyEvent(Stage stage) {
			super(stage);
		}

		public Stage getStage(){
			return ((Stage) getSource());
		}

	}

	@Override
	public void start(Stage primaryStage) {
		applicationConext.publishEvent(new StageReadlyEvent(primaryStage));
	}

}
