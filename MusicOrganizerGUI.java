import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class MusicOrganizerGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        
        MusicOrganizer mo = new MusicOrganizer();
        btn.setText("Play music");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                MusicOrganizer.main(null);
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

 Scene scene = new Scene(root, 300, 250);
        
        GridPane grid = new GridPane();
        grid.setAlignmnet(Pos.CENTER);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}