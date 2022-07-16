package modele;

import java.util.ArrayList;
import java.util.List;

public class Session {
    public static Voiture currentVoiture;
    public static List<Voiture> listVoiture=new ArrayList<>();//implementation de tableau redimensionnable de l'interface List

    public static List<Voiture> getListVoiture() {
        return listVoiture;
    }

    public static void setListVoiture(List<Voiture> listVoiture) {
        Session.listVoiture = listVoiture;
    }

    public static Voiture getCurrentVoiture() {
        return currentVoiture;
    }

    public static void setCurrentVoiture(Voiture currentVoiture) {
        Session.currentVoiture = currentVoiture;
    }

}
