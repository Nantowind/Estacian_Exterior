package Persistence;

import Entities.Clientes;
import Entities.Comentarios;

import java.util.ArrayList;
import java.util.Collection;

public final class ClientesDAO extends DAO{

    public void saveCliente(Clientes cliente)throws Exception{

        try{
            if (findNullValueInClienteForInsert(cliente)){
            throw new Exception("cliente null value fund");
            }
            String sqlQuery = "INSERT INTO clientes (nombre, calle, numero, codigo_postal, ciudad, pais, email)"+
                    "values (    '" + cliente.getNombre() +
                            "' , '" + cliente.getCalle() +
                            "' ,  " + cliente.getNumero() +
                            "  , '" + cliente.getCodigo_postal() +
                            "' , '" + cliente.getCiudad() +
                            "' , '" + cliente.getPais() +
                            "' , '" + cliente.getEmail() + "' ;";
            insertDeleteUpdate(sqlQuery);

        }catch (Exception e){
            e.getMessage();
            throw e;
        }
    }
    public void editCliente(Clientes cliente)throws Exception{
        try {
            if (findNullValueInClienteForEdit(cliente)){
                throw new Exception("null value fund");
            }
            String sqlQuery= "UPDATE clientes SET nombre = '" + cliente.getNombre() +
                                                    "', calle ='"+cliente.getCalle() +
                                                    "', numero = "+cliente.getNumero()+
                                                    ", codigo_postal ='"+cliente.getCodigo_postal()+
                                                    "', ciudad = '" +cliente.getCiudad()+
                                                    "', pais = '"+ cliente.getPais()+
                                                    "', email = '"+ cliente.getEmail()+
                               "' where id_cliente = " +cliente.getId_cliente()+";";

            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("cannot edit cliente");
            throw e;
        }

    }
    public void deleteCliente(Clientes cliente)throws Exception{
        try{
            if (cliente == null){
                throw new Exception("cannor delete Comentario - comentario is null ");

            }
            String sqlQuery = "DELETE FROM comentarios WHERE id_cliente = " + cliente.getId_cliente();
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("cannot delete cliente");
            e.getMessage();
            throw e;
        }
    }

    public Clientes searchClienteByID(int id) throws Exception{

        try{
            String sqlQuery = "SELECT * FROM clientes where id_cliente =" + id+" ;";
            queryDataBase(sqlQuery);
            Clientes cliente = null;

            while (resultSet.next()){
                cliente = new Clientes();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigo_postal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
            }

            disconnectDataBase();
            return cliente;

        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }


    public Collection<Clientes> searchAllClientes() throws Exception{

        try{
            String sqlQuery = "SELECT * FROM clientes;";
            queryDataBase(sqlQuery);
            Clientes cliente = null;

            Collection<Clientes> clientes =new ArrayList<>();
            while (resultSet.next()){
                cliente = new Clientes();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigo_postal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }

            disconnectDataBase();
            return clientes;

        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }

    //nested method
    private boolean findNullValueInClienteForInsert(Clientes cliente){
        boolean valueNull = false;
        if (cliente == null){
            valueNull = true;
            System.out.println("cliente 'object' cannot be null");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre 'value' cannot be null");
        }

        if (cliente.getCalle() == null || cliente.getCalle().trim().isEmpty()){
            valueNull = true;
            System.out.println("calle 'value' cannot be null");
        }

        if (cliente.getNumero() == null || cliente.getNumero() < 1){
            valueNull = true;
            System.out.println("numero 'value' cannot be null");
        }

        if (cliente.getCodigo_postal() == null || cliente.getCodigo_postal().trim().isEmpty()){
            valueNull = true;
            System.out.println("codigo_postal 'value' cannot be null");
        }

        if (cliente.getCiudad() == null || cliente.getCiudad().trim().isEmpty()){
            valueNull = true;
            System.out.println("ciudad 'value' cannot be null");
        }

        if (cliente.getPais() == null || cliente.getPais().trim().isEmpty()){
            valueNull = true;
            System.out.println("pais 'value' cannot be null");
        }

        // Checks for Email
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()){
            valueNull = true;
            System.out.println("Email 'value' cannot be null");
        }
        if (!cliente.getEmail().contains("@")){
            valueNull = true;
            System.out.println("email 'value'  must contain @  " );
        }
        if (!cliente.getEmail().toLowerCase().contains(".com")){
            valueNull = true;
            System.out.println("email 'value'  must contain '.com'  " );
        }
        if (cliente.getEmail().length() < 8 || cliente.getEmail().length() > 64 ){
            valueNull = true;
            System.out.println("email 'value' the number of characters in the email is not correct ");
        }

        //Warning this is different
        if (cliente.getId_cliente() != null ){
            valueNull = true;
            System.out.println("id_casa 'value' must be null");
        }

        return valueNull;

    }

    private boolean findNullValueInClienteForEdit(Clientes cliente){
        boolean valueNull = false;
        if (cliente == null){
            valueNull = true;
            System.out.println("cliente 'object' cannot be null");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre 'value' cannot be null");
        }

        if (cliente.getCalle() == null || cliente.getCalle().trim().isEmpty()){
            valueNull = true;
            System.out.println("calle 'value' cannot be null");
        }

        if (cliente.getNumero() == null || cliente.getNumero() < 1){
            valueNull = true;
            System.out.println("numero 'value' cannot be null");
        }

        if (cliente.getCodigo_postal() == null || cliente.getCodigo_postal().trim().isEmpty()){
            valueNull = true;
            System.out.println("codigo_postal 'value' cannot be null");
        }

        if (cliente.getCiudad() == null || cliente.getCiudad().trim().isEmpty()){
            valueNull = true;
            System.out.println("ciudad 'value' cannot be null");
        }

        if (cliente.getPais() == null || cliente.getPais().trim().isEmpty()){
            valueNull = true;
            System.out.println("pais 'value' cannot be null");
        }

        // Checks for Email
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()){
            valueNull = true;
            System.out.println("Email 'value' cannot be null");
        }
        if (!cliente.getEmail().contains("@")){
            valueNull = true;
            System.out.println("email 'value'  must contain @  " );
        }
        if (!cliente.getEmail().toLowerCase().contains(".com")){
            valueNull = true;
            System.out.println("email 'value'  must contain '.com'  " );
        }
        if (cliente.getEmail().length() < 8 || cliente.getEmail().length() > 64 ){
            valueNull = true;
            System.out.println("email 'value' the number of characters in the email is not correct ");
        }
        //Warning this is different
        if (cliente.getId_cliente() == null || cliente.getId_cliente() <1){
            valueNull = true;
            System.out.println("id_casa 'value' cannot be null");
        }

        return valueNull;

    }

}
