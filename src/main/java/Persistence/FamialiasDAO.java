package Persistence;

import Entities.Familias;

import java.util.ArrayList;
import java.util.Collection;

public final class FamialiasDAO extends DAO {

    public void saveFamilia(Familias familia)throws Exception{

        try {
            if (findNullValueInFamiliaForSave(familia)){
                throw new Exception("value null fund");
            }

            String sqlQuery = "INSERT INTO familia" +
                    "(nombre, edad_minima, edad_maxima, num_hijos, email, id_casa_familia)"+
                    "values ('"+familia.getNombre()+
                    "', "+familia.getEdad_minima()+
                    ", "+familia.getEdad_maxima()+
                    ", "+familia.getNum_hijos()+
                    ", '"+ familia.getEmail()+
                    "', "+ familia.getId_casa_familia()+");";

            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("not save familia");
            throw e;
        }


    }
    public void editFamilia(Familias familia)throws Exception{

        try {
            if (findNullValueInFamiliaForEdit(familia)){
                throw new Exception("value null fund");
            }

            String sqlQuery = "UPDATE  familias SET nombre = '"+familia.getNombre()+
                                                            "', edad_minima ="+ familia.getEdad_minima()+
                                                            ", edad_maxima=" + familia.getEdad_maxima()+
                                                            ", num_hijos="+ familia.getNum_hijos()+
                                                            ", email ='"+ familia.getEmail()+
                                                            "', id_casa_familia=" + familia.getId_casa_familia()+
                    "where id_familia ="+familia.getId_familia()+";";

            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("not edit familia");
            throw e;
        }

    }
    public void deleteFamilia(Familias familia)throws Exception{

        try {
            if (familia == null){
                throw new Exception("estancia is null");
            }
            String sqlQuery = "DELETE FROM estancia where id_familia= "+familia.getId_familia()+";";
            insertDeleteUpdate(sqlQuery);

        }catch (Exception e){
            System.out.println("not delete famlia");
            throw e;
        }

    }

    public Collection<Familias> searchAllFamilias()throws Exception{
        try {
            String sqlQuery= "SELECT * FROM familias";
            queryDataBase(sqlQuery);
            Familias familia = null;
            Collection<Familias> familias =new ArrayList<>();
            while (resultSet.next()){
                familia = new Familias();
                familia.setId_familia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdad_minima(resultSet.getInt("edad_minima"));
                familia.setEdad_maxima(resultSet.getInt("edad_maxima"));
                familia.setNum_hijos(resultSet.getInt("num_hijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setId_casa_familia(resultSet.getInt("id_casa_familia"));
                familias.add(familia);
            }

            disconnectDataBase();
            return familias;
        }catch (Exception e){
            disconnectDataBase();
            e.printStackTrace();
            System.out.println("error search familia");
            throw e;
        }


    }

    public Familias searchFamilia(int id)throws Exception{
        try {
            String sqlQuery= "SELECT * FROM famlias WHERE id_famlia ="+id;
            queryDataBase(sqlQuery);
            Familias familia = null;

            while (resultSet.next()){
                familia = new Familias();
                familia.setId_familia(resultSet.getInt("id_familia"));
                familia.setNombre(resultSet.getString("nombre"));
                familia.setEdad_minima(resultSet.getInt("edad_minima"));
                familia.setEdad_maxima(resultSet.getInt("edad_maxima"));
                familia.setNum_hijos(resultSet.getInt("num_hijos"));
                familia.setEmail(resultSet.getString("email"));
                familia.setId_casa_familia(resultSet.getInt("is_casa_familia"));
            }

            disconnectDataBase();
            return familia;
        }catch (Exception e){
            disconnectDataBase();
            System.out.println("error search familia");
            throw e;
        }


    }


    private boolean findNullValueInFamiliaForSave(Familias familia){
        boolean valueNull = false;
        if (familia == null){
            valueNull = true;
            System.out.println("familia 'object' cannot be null");
        }
        if (familia.getNombre() == null || familia.getNombre().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre 'value' cannot be null");
        }
        if (familia.getEdad_minima() == null || familia.getEdad_minima()< 1){
            valueNull = true;
            System.out.println("edad_minima 'value' cannot be null");
        }
        if (familia.getEdad_maxima() == null || familia.getEdad_maxima()< 1){
            valueNull = true;
            System.out.println("edad_maxima 'value' cannot be null");
        }
        if (familia.getNum_hijos() == null || familia.getNum_hijos()< 1){
            valueNull = true;
            System.out.println("num_hijos 'value' cannot be null");
        }
        if (familia.getId_casa_familia() == null || familia.getId_casa_familia()< 1){
            valueNull = true;
            System.out.println("id_casa_familia 'value' cannot be null");
        }

        // Checks for Email
        if (familia.getEmail() == null || familia.getEmail().trim().isEmpty()){
            valueNull = true;
            System.out.println("Email 'value' cannot be null");
        }
        if (!familia.getEmail().contains("@")){
            valueNull = true;
            System.out.println("email 'value'  must contain @  " );
        }
        if (!familia.getEmail().toLowerCase().contains(".com")){
            valueNull = true;
            System.out.println("email 'value'  must contain '.com'  " );
        }
        if (familia.getEmail().length() < 8 || familia.getEmail().length() > 64 ){
            valueNull = true;
            System.out.println("email 'value' the number of characters in the email is not correct ");
        }

        //Warning this is diferent
        if (familia.getId_familia() != null ){
            valueNull = true;
            System.out.println("id_familia 'value' must be null");
        }
        return valueNull;

    }

    private boolean findNullValueInFamiliaForEdit(Familias familia){
        boolean valueNull = false;
        if (familia == null){
            valueNull = true;
            System.out.println("familia 'object' cannot be null");
        }
        if (familia.getNombre() == null || familia.getNombre().trim().isEmpty()){
            valueNull = true;
            System.out.println("nombre 'value' cannot be null");
        }
        if (familia.getEdad_minima() == null || familia.getEdad_minima()< 1){
            valueNull = true;
            System.out.println("edad_minima 'value' cannot be null");
        }
        if (familia.getEdad_maxima() == null || familia.getEdad_maxima()< 1){
            valueNull = true;
            System.out.println("edad_maxima 'value' cannot be null");
        }
        if (familia.getNum_hijos() == null || familia.getNum_hijos()< 1){
            valueNull = true;
            System.out.println("num_hijos 'value' cannot be null");
        }
        if (familia.getId_casa_familia() == null || familia.getId_casa_familia()< 1){
            valueNull = true;
            System.out.println("id_casa_familia 'value' cannot be null");
        }

        // Checks for Email
        if (familia.getEmail() == null || familia.getEmail().trim().isEmpty()){
            valueNull = true;
            System.out.println("Email 'value' cannot be null");
        }
        if (!familia.getEmail().contains("@")){
            valueNull = true;
            System.out.println("email 'value'  must contain @  " );
        }
        if (!familia.getEmail().toLowerCase().contains(".com")){
            valueNull = true;
            System.out.println("email 'value'  must contain '.com'  " );
        }
        if (familia.getEmail().length() < 8 || familia.getEmail().length() > 64 ){
            valueNull = true;
            System.out.println("email 'value' the number of characters in the email is not correct ");
        }

        //Warning this is diferent
        if (familia.getId_familia() == null ){
            valueNull = true;
            System.out.println("Id_familia 'value' must be null");
        }
        return valueNull;

    }
}
