import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    private int nbClic = 0;
    private int nb421 = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        BorderPane borderPane = new BorderPane();


        //HBox du centre
        HBox center = new HBox();
        Label l1 = new Label("1");
        Label l2 = new Label("1");
        Label l3 = new Label("1");

        center.getChildren().addAll(l1, l2, l3);
        center.setSpacing(10);
        borderPane.setCenter(center);


        //HBox en haut
        HBox score = new HBox();
        Label nbCoupJoue = new Label("Coup joué : " + nbClic);
        Label nb421Fait = new Label("Nombre de 421 : " + nb421);
        score.setSpacing(10);
        score.getChildren().addAll(nbCoupJoue, nb421Fait);
        borderPane.setTop(score);

        //Button du bas
        Button button = new Button("Jouer");
        borderPane.setBottom(button);

        button.setOnAction(actionEvent -> {
            nbClic++;

            Random r = new Random();
            l1.setText(String.valueOf(r.nextInt(6) + 1));
            l2.setText(String.valueOf(r.nextInt(6) + 1));
            l3.setText(String.valueOf(r.nextInt(6) + 1));

            if(existChiffre(l1, l2, l3, 4) && existChiffre(l1, l2, l3, 2) && existChiffre(l1, l2, l3, 1)){
                nb421++;
            }
            nbCoupJoue.setText("Coup joué : " + nbClic);
            nb421Fait.setText("Nombre de 421 : " + nb421);
        });

        //Button de gauche
        Button quit = new Button();

        ImageView quitButton = new ImageView(new Image("ImageQuit.png"));
        quitButton.setFitHeight(30);
        quitButton.setFitWidth(30);
        quit.setGraphic(quitButton);

        quit.setOnAction(actionEvent -> Platform.exit());
        borderPane.setLeft(quit);

        //Affichage de la scène
        Scene root = new Scene(borderPane, 600, 500);
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public boolean existChiffre(Label l1, Label l2, Label l3, int i){
        return l1.getText().equals(String.valueOf(i)) || l2.getText().equals(String.valueOf(i)) || l3.getText().equals(String.valueOf(i));
    }
}