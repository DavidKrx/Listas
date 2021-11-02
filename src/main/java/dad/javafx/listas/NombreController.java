package dad.javafx.listas;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
public class NombreController implements Initializable{
		//model
		private StringProperty seleccionado=new SimpleStringProperty();
		private ListProperty<String> nombres=new SimpleListProperty<>(FXCollections.observableArrayList());
		//view

	    @FXML
	    private BorderPane view;

	    @FXML
	    private ListView<String> nombresList;

	    @FXML
	    private Button añadirButt;

	    @FXML
	    private Button quitarButt;
		
	    public NombreController() throws IOException {
	    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/view.fxml"));
			loader.setController(this);
			loader.load();
		}
		public void initialize(URL location, ResourceBundle resources) {
			nombresList.itemsProperty().bind(nombres);
			seleccionado.bind(nombresList.getSelectionModel().selectedItemProperty());
			nombres.addAll("Perico Palote", "Juanillo Molillo");
			quitarButt.disableProperty().bind(nombresList.getSelectionModel().selectedItemProperty().isNull());
			
			nombres.addListener((Change<? extends String> c) ->{
				while(c.next()) {
				System.out.println("--->ha habido cambios:");
				
				for(String nuevo : c.getAddedSubList()) {
					System.out.println("se ha añadido: "+nuevo);
					}
				for(String quitado : c.getRemoved()) {
					System.out.println("se ha añadido: "+quitado);
					}
				}
			});
		}
		public BorderPane getView() {
			return view;
		}
		
	    @FXML
	    void OnañadirAction(ActionEvent event) {
	    	TextInputDialog dialog=new TextInputDialog();
	    	dialog.setTitle("Nuevo nombre");
	    	dialog.setHeaderText("Añadir un nuevo nombre a la lista");
	    	dialog.setContentText("Nombre:");
	    	Optional<String> resultado=dialog.showAndWait();
	    	if(resultado.isPresent()) {
	    		String nombre=resultado.get().trim();
	    		if(!nombre.isEmpty()&& ! nombres.contains(nombre))
	    		nombres.add(nombre);
	    		}	
	    }

	    @FXML
	    void OnquitarAction(ActionEvent event) {
	    	nombres.remove(seleccionado.get());
	    }

}
