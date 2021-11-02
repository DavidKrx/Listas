package dad.javafx.listas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	private NombreController control;

	@Override
	public void start(Stage primaryStage) throws Exception {
		control=new NombreController();
		
		primaryStage.setTitle("Calculadora");
		
		primaryStage.setScene(new Scene(control.getView()));
	
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
