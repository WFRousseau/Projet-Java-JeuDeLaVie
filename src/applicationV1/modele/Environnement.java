package applicationV1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private int width,height;
    private ObservableList<Acteur> acteurs;
    private IntegerProperty nbToursProperty;

    public Environnement(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.nbToursProperty = new SimpleIntegerProperty(0);
        this.acteurs= FXCollections.observableArrayList();
    }

    public  int getNbTours(){
        return nbToursProperty.getValue();
    }

    public IntegerProperty getNbToursProperty() {
        return this.nbToursProperty;

    }

    public  void setNbTours(int n){
        nbToursProperty.setValue(n);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public Acteur getActeur(String id) {
        for(Acteur a:this.acteurs){
            if(a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }


    public void ajouter(Acteur a){
        acteurs.add(a);
    }

    public boolean dansTerrain(int x, int y){
        return (0 <= x && x<this.width && 0<=y && y< this.height);
    }

    public void unTour(){
        // cela ne peut etre un foreach a cause des naissances
        // modification de acteurs.
        //System.out.println("tour " + this.nbTours);
        for(int i=0;i<acteurs.size(); i++){
            Acteur a = acteurs.get(i);
            a.agit();
        }
        for(int i=acteurs.size()-1; i>=0;i--){
            Acteur a = acteurs.get(i);
            if(!a.estVivant()){
                System.out.println("mort de : " + a);
                this.getActeurs().remove(i);
            }
        }
        nbToursProperty.setValue(nbToursProperty.getValue()+1);
    }

    public void init(){
        for(int i=0; i<50;i++){
            this.ajouter(new Loup(this));
        }
        for(int i=0; i<100;i++){
            this.ajouter(new Mouton(this));
        }
    }


}
