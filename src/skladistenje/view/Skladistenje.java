package skladistenje.view;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author korisnik
 */
public class Skladistenje extends Application {;
    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root, 600, 320);
        
        primaryStage.setTitle("Skladistenje robe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}