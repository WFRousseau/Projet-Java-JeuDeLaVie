package applicationV1.controleur;
import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.Loup;
import applicationV1.modele.Mouton;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement e;

    @FXML
    private ToggleGroup rond;

    @FXML
    private TextField txtFIeldLancer;

    @FXML
    private RadioButton radMou;

    @FXML
    private RadioButton radLoup;

    @FXML
    private TextField txtFieldAj;

    @FXML
    private Label labTours;

    @FXML
    private Label labVivant;

    @FXML
    private Label labLoup;

    @FXML
    private Label labMouton;

    @FXML
    private Label labPeriode;

    @FXML
    private Slider slideLoup;

    @FXML
    private Slider slideMouton;

    @FXML
    private Pane panDeJeu;

    @FXML
    void ajouter(ActionEvent event) {
        RadioButton button = (RadioButton) this.rond.getSelectedToggle();
        for (int i = 0; i < Integer.valueOf(this.txtFieldAj.getText()); i++) {
            if (button.getText().equals("Loup")) {
                Acteur loup = new Loup(this.e);
                this.e.ajouter(loup);
                creerSprite(loup);
            }
            else {
                Acteur mouton = new Mouton(this.e);
                this.e.ajouter(mouton);
                creerSprite(mouton);
            }
        }
    }

    @FXML
    void unTour(ActionEvent event) {
        this.e.unTour();
        /*this.e.getActeursObservable().addListener(birth);
        /*if () {
            this.labPeriode.setText("Jour");
        }
        else {
            this.labPeriode.setText("Nuit");
        }
        for(int j=this.TableDeJeu.getChildren().size()-1; j>=0 ; j--){
            if(this.environnement.getActeur(this.TableDeJeu.getChildren().get(j).getId())==null){
                this.TableDeJeu.getChildren().remove(j);
            }
        }
            }
            else {
                if (panDeJeu.lookup("#" + e.getActeurs().get(i).getId()) != null) {
                    panDeJeu.getChildren().get(i).setTranslateX(this.e.getActeurs().get(i).getX());
                    panDeJeu.getChildren().get(i).setTranslateY(this.e.getActeurs().get(i).getY());
                } else {
                    creerSprite(e.getActeurs().get(i));
                }
            }
        }*/
    }

    @FXML
    void faireDesTours(ActionEvent event) {
        for (int i = 0; i < Integer.valueOf(this.txtFIeldLancer.getText()); i++) {
            unTour(event);
        }
    }

    public void creerSprite(Acteur a) {
        if (a instanceof Loup) {
            Circle r = new Circle(3);
            r.translateXProperty().bind(a.getXProperty());
            r.translateYProperty().bind(a.getYProperty());
            r.setFill(Color.RED);
            r.setId(a.getId());
            panDeJeu.getChildren().add(r);
        }
        else {
            Circle r = new Circle(2);
            r.translateXProperty().bind(a.getXProperty());
            r.translateYProperty().bind(a.getYProperty());
            r.setFill(Color.WHITE);
            r.setId(a.getId());
            panDeJeu.getChildren().add(r);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.e = new Environnement(300, 300);
        this.e.getNbToursProperty().addListener((observable, oldValue, newValue) -> this.labTours.setText(newValue.toString()));
        this.slideLoup.valueProperty().addListener((observable, oldValue, newValue) -> Loup.setReproduction(this.slideLoup.getValue()));
        this.slideMouton.valueProperty().addListener((observable, oldValue, newValue) -> Mouton.setReproduction(this.slideMouton.getValue()));

        this.e.getActeurs().addListener((InvalidationListener) c -> {
            while (c.) {
                /*if (c.wasRemoved()) {
                    for (int i = 0; i < this.e.getActeurs().size(); i++) {
                        panDeJeu.getChildren().remove(i);
                        e.getActeurs().remove(i);
                    }
                }*/
                if (c.wasAdded()==true) {
                    for (int i = 0; i < this.e.getActeurs().size(); i++) {
                        if (panDeJeu.lookup("#" + e.getActeurs().get(i).getId()) != null) {
                            creerSprite(e.getActeurs().get(i));
                        }
                    }
                }
            }
        });
    }
}
