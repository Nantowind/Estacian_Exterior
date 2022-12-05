import Entities.Comentarios;
import Estancia.UIMenu;
import Persistence.ComentariosDAO;
import Services.*;

import java.util.Scanner;

public class EstanciaExterior {
    public static void main(String[] args) {
        UIMenu menu = new UIMenu();
        try {
            menu.Menu();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }



    }
}
