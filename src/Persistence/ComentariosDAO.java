package Persistence;

import Entities.Clientes;
import Entities.Comentarios;

import java.util.ArrayList;
import java.util.Collection;

public final class ComentariosDAO extends DAO {

    public void saveComentario(Comentarios comentario) throws Exception{

        try {
            if (findNullValueInCommentsForSave(comentario) == true){
                throw new Exception("null value found");
            }
            String sqlQuery = "INSERT INTO comentarios (id_casa,comentario)"+
                    " values ("+comentario.getId_casa()+ ", '"+
                                comentario.getId_comentario()+"';" ;


            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            e.getMessage();
            System.out.println("i don't can save comment");
            throw e;
        }

    }
    public void editComentario(Comentarios comentario) throws Exception{

        try {
            if (findNullValueInCommentsForEdit(comentario)){
                throw new Exception("cannot edit comentario - comentario is null ");
            }
            String sqlQuery= "UPDATE comentarios SET id_casa =" + comentario.getId_casa() +
                                                  ", comentario = '" + comentario.getComentario()+
                             "' where id_comentario =" + comentario.getId_comentario() + ";";
            insertDeleteUpdate(sqlQuery);

        }catch (Exception e){
            e.getMessage();
            System.out.println("cannot edit comentario");
            throw e;
        }


    }
    public void deleteComentario(Comentarios comentario) throws Exception{

        try{
            if (comentario == null){
                throw new Exception("cannor delete Comentario - comentario is null ");

            }
            String sqlQuery = "DELETE FROM comentarios WHERE id_comentario = " + comentario.getId_comentario();
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("cannot delete Comentario");
            e.getMessage();
            throw e;
        }

    }

    public Comentarios searchComentariosById(int id) throws  Exception{
        try{
            String sqlQuery = "SELECT * FROM comentarios where id_comentario =" + id+" ;";
            queryDataBase(sqlQuery);
            Comentarios comentario = null;

            while (resultSet.next()){
                comentario = new Comentarios();
                comentario.setId_comentario( resultSet.getInt("id_comentario"));
                comentario.setId_casa(resultSet.getInt("id_casa"));
                comentario.setComentario(resultSet.getString("comentario"));
            }

            disconnectDataBase();
            return comentario;

        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }

    public Collection<Comentarios> searchAllComentarios() throws  Exception{
        try{
            String sqlQuery = "SELECT * FROM comentarios;";
            queryDataBase(sqlQuery);
            Comentarios comentario = null;

            Collection<Comentarios> comentarios =new ArrayList<>();
            while (resultSet.next()){
                comentario = new Comentarios();

                comentario.setId_comentario( resultSet.getInt("id_comentario"));
                comentario.setId_casa(resultSet.getInt("id_casa"));
                comentario.setComentario(resultSet.getString("comentario"));

                comentarios.add(comentario);
            }

            disconnectDataBase();
            return comentarios;

        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }

    //nested method
    //These two methods differ only because the comparison of the id_comentario value is different in each case.
    private boolean findNullValueInCommentsForSave(Comentarios comentarios){
        boolean valueNull = false;
        if (comentarios == null){
            valueNull = true;
            System.out.println("Comentarios 'object' cannot be null");
        }
        if (comentarios.getComentario() == null || comentarios.getComentario().trim().isEmpty()){
            valueNull = true;
            System.out.println("comentario 'value' cannot be null");
        }
        if (comentarios.getId_casa() == null || comentarios.getId_casa() < 1) {
            valueNull = true;
            System.out.println("id_casa 'value' cannot be null ");
        }
        //Warning this is different
        if (comentarios.getId_comentario() != null){
            System.out.println("id_commentario comment must be null");
        }
        return valueNull;


    }

    private boolean findNullValueInCommentsForEdit(Comentarios comentarios){
        boolean valueNull = false;
        if (comentarios == null){
            valueNull = true;
            System.out.println("Comentarios 'object' cannot be null");
        }
        if (comentarios.getComentario() == null || comentarios.getComentario().trim().isEmpty()){
            valueNull = true;
            System.out.println("comentario 'value' cannot be null");
        }
        if (comentarios.getId_casa() == null || comentarios.getId_casa() < 1) {
            valueNull = true;
            System.out.println("id_casa 'value' connor be null ");
        }
        //Warning this is different
        if (comentarios.getId_comentario() == null || comentarios.getId_comentario() <1){
            System.out.println("id_commentario 'value' cannot be null");
        }
        return valueNull;


    }
}
