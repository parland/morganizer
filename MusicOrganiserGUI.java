import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MusicOrganiserGUI extends Application {
    MusicOrganizer mo = new MusicOrganizer();
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("play music");
        btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Hello World!");
                    mo.playRandom();
                }
            });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("PS: " + primaryStage);
        System.out.println("MO: " + mo);
    }

    public static void main(String[] args) {
        launch(args);
    }
}