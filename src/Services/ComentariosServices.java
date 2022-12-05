package Services;

import Entities.Casas;
import Entities.Clientes;
import Entities.Comentarios;
import Persistence.CasasDAO;
import Persistence.ComentariosDAO;

import java.util.Collection;
import java.util.Scanner;

public class ComentariosServices {

    private ComentariosDAO DAO;

    public ComentariosServices() {
        this.DAO = new ComentariosDAO();
    }

    public void searchComentariosForContainAndCountry() throws Exception {
        Scanner sc = new Scanner(System.in);
        CasasDAO searchCasa = new CasasDAO();
        try {
            Collection<Comentarios> comentarios = DAO.searchAllComentarios();
            System.out.print("insert part of the comment you want to search for: ");
            String comment = String.valueOf(sc.nextLine());
            System.out.print("Insert country: ");
            String country = String.valueOf(sc.nextLine());

            for (Comentarios comentario: comentarios){
                if (comentario.getComentario().contains(comment)){
                    Casas casa  = searchCasa.searchCasas(comentario.getId_casa());
                    if (casa.getPais().equalsIgnoreCase(country)){
                        System.out.println(casa);
                        System.out.println(comentario.getComentario());
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }


    }




}
