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
    private boolean lockDe1 = false;
    private boolean lockDe2 = false;
    private boolean lockDe3 = false;
    private int nbDe1 = 1;
    private int nbDe2 = 1;
    private int nbDe3 = 1;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        BorderPane borderPane = new BorderPane();

        //HBox du centre
        HBox center = new HBox();
        Button de1 = new Button();
        Button de2 = new Button();
        Button de3 = new Button();

        de1.setOnAction(actionEvent -> {
            lockDe1 = !lockDe1;
            ImageView imChang1 = lockDe1 ? new ImageView(new Image("De/de" + nbDe1 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe1 + ".png"));
            imChang1.setFitHeight(40); imChang1.setFitWidth(40);
            de1.setGraphic(imChang1);
        });
        de2.setOnAction(actionEvent -> {
            lockDe2 = !lockDe2;
            ImageView imChang2 = lockDe2 ? new ImageView(new Image("De/de" + nbDe2 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe2 + ".png"));
            imChang2.setFitHeight(40); imChang2.setFitWidth(40);
            de2.setGraphic(imChang2);
        });
        de3.setOnAction(actionEvent -> {
            lockDe3 = !lockDe3;
            ImageView imChang3 = lockDe3 ? new ImageView(new Image("De/de" + nbDe3 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe3 + ".png"));
            imChang3.setFitHeight(40); imChang3.setFitWidth(40);
            de3.setGraphic(imChang3);
        });

        ImageView imBase1 = new ImageView(new Image("De/de1.png"));
        imBase1.setFitWidth(40); imBase1.setFitHeight(40);
        ImageView imBase2 = new ImageView(new Image("De/de1.png"));
        imBase2.setFitWidth(40); imBase2.setFitHeight(40);
        ImageView imBase3 = new ImageView(new Image("De/de1.png"));
        imBase3.setFitWidth(40); imBase3.setFitHeight(40);

        de1.setGraphic(imBase1);
        de2.setGraphic(imBase2);
        de3.setGraphic(imBase3);


        center.getChildren().addAll(de1, de2, de3);
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
            if(!lockDe1){
                nbDe1 = r.nextInt(6) + 1;
            }
            if(!lockDe2){
                nbDe2 = r.nextInt(6) + 1;
            }
            if(!lockDe3){
                nbDe3 = r.nextInt(6) + 1;
            }

            ImageView im1;
            ImageView im2;
            ImageView im3;

            im1 = lockDe1 ? new ImageView(new Image("De/de" + nbDe1 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe1 + ".png"));
            im2 = lockDe2 ? new ImageView(new Image("De/de" + nbDe2 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe2 + ".png"));
            im3 = lockDe3 ? new ImageView(new Image("De/de" + nbDe3 + "Lock.png")) : new ImageView(new Image("De/de" + nbDe3 + ".png"));

            im1.setFitWidth(40); im1.setFitHeight(40);
            im2.setFitWidth(40); im2.setFitHeight(40);
            im3.setFitWidth(40); im3.setFitHeight(40);

            de1.setGraphic(im1);
            de2.setGraphic(im2);
            de3.setGraphic(im3);

            if(existChiffre(nbDe1, nbDe2, nbDe3, 4) && existChiffre(nbDe1, nbDe2, nbDe3, 2) && existChiffre(nbDe1, nbDe2, nbDe3, 1)){
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

    public boolean existChiffre(int nb1, int nb2, int nb3, int i){
        return nb1 == i || nb2 == i || nb3 == i;
    }
}