import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import java.io.File;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MusicOrganiserGUI extends Application {
    MusicOrganizer mo = new MusicOrganizer();

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        // http://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html
        GridPane grid = new GridPane();
        Scene scene = new Scene(root, 1200, 800);     
        root.getChildren().add(grid);
        primaryStage.setScene(scene);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Music Collector");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 1, 0);

        Label userName = new Label("Track number:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        //         Label pw = new Label("Password:");
        //         grid.add(pw, 0, 2);
        //         PasswordField pwBox = new PasswordField();
        //         grid.add(pwBox, 1, 2);

        Button playBtn = new Button();
        playBtn.setText("play music");
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("play music");
                    int index = Integer.parseInt(userTextField.getText());
                    mo.startPlaying(index);
                }
            });
        grid.add(playBtn, 0, 3);
        
        Button stopBtn = new Button();
        stopBtn.setText("stop music");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("stop music");
                    mo.stopPlaying();
                }
            });
        grid.add(stopBtn, 1, 3);
        //        Scene scene = new Scene(grid, 300, 275);
        Button addMusicBtn = new Button();
        addMusicBtn.setText("add music");
        addMusicBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("add music");
                    DirectoryChooser dirChooser = new DirectoryChooser();
                    dirChooser.setTitle("Open Music Directory");

                    File selectedDir = dirChooser.showDialog(primaryStage);
                    mo.addMP3filesFromDir(selectedDir.toString());
                }
            });
        grid.add(addMusicBtn, 0, 0);

        TableView<Track> table = new TableView<Track>();
        grid.add(table, 0, 4, 2, 1);
        table.setEditable(true);
        TableColumn libNumberCol = new TableColumn("Number");
        TableColumn playCountCol = new TableColumn("Play count");
        TableColumn artistCol = new TableColumn("Artist");
        TableColumn trackNameCol = new TableColumn("Track");
        TableColumn lengthCol = new TableColumn("Length");

        table.getColumns().addAll(libNumberCol, playCountCol,lengthCol, artistCol, trackNameCol);
        //         ObservableList<Track> tracks = mo.getTeamMembers();
        //         table.setItems(tracks);

        primaryStage.setTitle("Music Organizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}