import Entities.Comentarios;
import Persistence.ComentariosDAO;

public class EstanciaExterior {
    public static void main(String[] args) {

        ComentariosDAO comentariosDAO = new ComentariosDAO();
        Comentarios comentarios = new Comentarios();
        comentarios.setId_comentario(5);
        try {
            System.out.println(comentariosDAO.findNullValueInComments(comentarios));
        }catch (Exception e){
            System.out.println("erro ");
        }

    }
}
