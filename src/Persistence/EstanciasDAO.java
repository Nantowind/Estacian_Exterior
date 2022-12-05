package Persistence;

import Entities.Estancias;

import java.util.ArrayList;
import java.util.Collection;

public final class EstanciasDAO extends DAO {


    public void saveEstancia(Estancias estancia)throws Exception{

        try {
            if (findNullValueInEstanciaForSave(estancia)){
                throw new Exception("null value found");
            }

            String sqlQuery = "INSERT INTO estancias (id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta)"+
                            "values ("+estancia.getId_cliente()+
                                ", " +estancia.getId_casa()+
                                ", '"+estancia.getNombre_huesped()+
                                "', '"+estancia.getFecha_desde()+
                                "', '"+estancia.getFecha_hasta()+"';";
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("estancia not save");
            throw  e;
        }

    }
    public void editEstancia(Estancias estancia)throws Exception{
        try {
            if (findNullValueInEstanciaForEdit(estancia)){
                throw new Exception("value null fund");
            }
            String sqlQuery = "UPDATE  estancias SET id_cliente ="+estancia.getId_cliente()+
                                                    ", id_casa ="+estancia.getId_casa()+
                                                    ",  nombre_huesped ='"+estancia.getNombre_huesped()+
                                                    "', fecha_desde ='"+ estancia.getFecha_desde()+
                                                    "', fecha_hasta ='"+ estancia.getFecha_hasta()+"' "+
                    "where id_estancia ="+ estancia.getId_estancia()+";";
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("not edit estancia");
            throw e;
        }
    }
    public void deleteEstancia(Estancias estancia)throws Exception{

        try {
            if (estancia == null){
                throw new Exception("estancia is null");
            }
            String sqlQuery = "DELETE FROM estancias WHERE id_estancia ="+estancia.getId_estancia()+";";
            insertDeleteUpdate(sqlQuery);

        }catch (Exception e){
            System.out.println("not delete estancia");
            throw e;
        }


    }

    public Estancias searchEstancia(int id)throws Exception{

        try{
            String sqlQuery = "SELECT * FROM estancia where id_estancia ="+id;
            queryDataBase(sqlQuery);
            Estancias estancia = null;
            while (resultSet.next()){
                estancia = new Estancias();
                estancia.setId_estancia(resultSet.getInt("id_estancia"));
                estancia.setId_cliente(resultSet.getInt("id_cliente"));
                estancia.setId_casa(resultSet.getInt("id_casa"));
                estancia.setNombre_huesped(resultSet.getString("mombre_huesped"));
                estancia.setFecha_desde(resultSet.getDate("fecha_desde"));
                estancia.setFecha_hasta(resultSet.getDate("fecha_hasta"));
            }
            disconnectDataBase();
            return estancia;
        }catch (Exception e){
            disconnectDataBase();
            System.out.println("no search estancia");
            throw e;
        }

    }
    public Collection<Estancias> searchAllEstancia()throws Exception{

        try{
            String sqlQuery = "SELECT * FROM estancia ";
            queryDataBase(sqlQuery);
            Estancias estancia = null;
            Collection<Estancias> estancias = new ArrayList<>();
            while (resultSet.next()){
                estancia = new Estancias();
                estancia.setId_estancia(resultSet.getInt("id_estancia"));
                estancia.setId_cliente(resultSet.getInt("id_cliente"));
                estancia.setId_casa(resultSet.getInt("id_casa"));
                estancia.setNombre_huesped(resultSet.getString("mombre_huesped"));
                estancia.setFecha_desde(resultSet.getDate("fecha_desde"));
                estancia.setFecha_hasta(resultSet.getDate("fecha_hasta"));
                estancias.add(estancia);
            }
            disconnectDataBase();
            return estancias;
        }catch (Exception e){
            System.out.println("no search estancia");
            throw e;
        }

    }


    //nested method
    //These two methods differ only because the comparison of the id_estancia value is different in each case.
    public boolean findNullValueInEstanciaForSave(Estancias estancia){
        boolean valueNull = false;
        if (estancia == null){
            valueNull = true;
            System.out.println("cliente 'object' cannot be null");
        }


        if (estancia.getId_cliente() == null || estancia.getId_cliente() < 1){
            valueNull = true;
            System.out.println("getId_cliente 'value' cannot be null");
        }
        if (estancia.getId_casa() == null || estancia.getId_casa() < 1){
            valueNull = true;
            System.out.println("getId_cliente 'value' cannot be null");
        }
        if (estancia.getNombre_huesped() == null || estancia.getNombre_huesped().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre_huesped 'value' cannot be null");
        }
        if (estancia.getFecha_desde() == null ){
            valueNull = true;
            System.out.println("fecha_desde 'value' cannot be null");
        }

        if (estancia.getFecha_hasta() == null ){
            valueNull = true;
            System.out.println("fecha_hasta 'value' cannot be null");
        }


        //Warning this is different
        if (estancia.getId_estancia() != null ){
            valueNull = true;
            System.out.println("id_casa 'value' must be null");
        }

        return valueNull;

    }

    public boolean findNullValueInEstanciaForEdit(Estancias estancia){
        boolean valueNull = false;
        if (estancia == null){
            valueNull = true;
            System.out.println("cliente 'object' cannot be null");
        }


        if (estancia.getId_cliente() == null || estancia.getId_cliente() < 1){
            valueNull = true;
            System.out.println("getId_cliente 'value' cannot be null");
        }
        if (estancia.getId_casa() == null || estancia.getId_casa() < 1){
            valueNull = true;
            System.out.println("getId_cliente 'value' cannot be null");
        }
        if (estancia.getNombre_huesped() == null || estancia.getNombre_huesped().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre_huesped 'value' cannot be null");
        }
        if (estancia.getFecha_desde() == null ){
            valueNull = true;
            System.out.println("fecha_desde 'value' cannot be null");
        }

        if (estancia.getFecha_hasta() == null ){
            valueNull = true;
            System.out.println("fecha_hasta 'value' cannot be null");
        }


        //Warning this is different
        if (estancia.getId_estancia() == null ){
            valueNull = true;
            System.out.println("id_casa 'value' must be null");
        }

        return valueNull;

    }
}
