package Persistence;

import Entities.Casas;
import Entities.Comentarios;

import java.util.ArrayList;
import java.util.Collection;

public final class CasasDAO extends DAO{
    public void saveCasa(Casas casa)throws Exception{

        try {
            if (findNullValueInCasaForSave(casa) == true ){
                throw new Exception("null value found");
            }

            String sqlQuery = "INSERT INTO casas (calle, numero, codigo_postal, ciudad, pais, fecha_desde, fecha_hasta," +
                    "tiempo_minimo, tiempo_maximo, precio_habitacion, tipo_vivienda) " +
                    "values ('" + casa.getCalle()+
                            "',"+casa.getNumero()+
                            ", '"+casa.getCodigo_postal()+
                            "','"+casa.getCiudad()+
                            "','"+casa.getPais()+
                            "','"+casa.getFecha_desde()+
                            "','"+casa.getFecha_hasta()+
                            "',"+casa.getTiempo_minimo()+
                            ","+ casa.getTiempo_maximo()+
                            ","+casa.getPrecio_habitacion()+
                            ",'"+casa.getTipo_vivienda()+"');";
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            e.getMessage();
            System.out.println("i don't can save 'casa'");
            throw e;
        }


    }
    public void editCasa(Casas casa)throws Exception{

        try {
            if (findNullValueInCasaForEdit(casa)){
                throw new Exception("cannot edit casa - casa is null ");
            }
            String sqlQuery = "UPDATE casas SET calle = '" + casa.getCalle() +
                                            "', numero = "+casa.getNumero()+
                                            ", codigo_postal ='"+casa.getCodigo_postal()+
                                            "', ciudad = '" +casa.getCiudad()+
                                            "', pais = '"+ casa.getPais()+
                                            "', fecha_desde = '"+casa.getFecha_desde()+
                                            "', fecha_hasta = '"+casa.getFecha_hasta() +
                                            "', tiempo_minimo = "+casa.getTiempo_minimo()+
                                            ", tiempo_maximo = "+casa.getTiempo_maximo()+
                                            ",tipo_vivienda = '"+casa.getTipo_vivienda()+
                                 "' where id_casa = " +casa.getId_casa()+";";
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            e.getMessage();
            System.out.println("cannot edit casa");
            throw e;
        }

    }
    public void deleteCasa(Casas casa)throws Exception{

        try{
            if (casa == null){
            throw new Exception("cannot delete casa - casa is null ");

            }
            String sqlQuery = "DELETE FROM casas WHERE id_casa = " +casa.getId_casa()+"";
            insertDeleteUpdate(sqlQuery);
        }catch (Exception e){
            System.out.println("cannot delete casa");
            e.getMessage();
            throw e;
        }
    }
    public Casas searchCasas(int id)throws Exception{

        try {
            String sqlQuery = "SELECT * FROM casas where id_casa = "+id+";";
            queryDataBase(sqlQuery);
            Casas casa = null;
            while (resultSet.next()){
                casa = new Casas();
                casa.setId_casa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigo_postal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFecha_desde(resultSet.getDate("fecha_desde"));
                casa.setFecha_hasta(resultSet.getDate("fecha_hasta"));
                casa.setTiempo_minimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempo_maximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecio_habitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipo_vivienda(resultSet.getString("tipo_habitacion"));
            }
            disconnectDataBase();
            return casa;
        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }
    public Collection<Casas> searchAllCasas(int id)throws Exception{

        try {
            String sqlQuery = "SELECT * FROM casas ;";
            queryDataBase(sqlQuery);
            Casas casa = null;
            Collection<Casas> casas = new ArrayList<>();
            while (resultSet.next()){
                casa = new Casas();
                casa.setId_casa(resultSet.getInt("id_casa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigo_postal(resultSet.getString("codigo_postal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFecha_desde(resultSet.getDate("fecha_desde"));
                casa.setFecha_hasta(resultSet.getDate("fecha_hasta"));
                casa.setTiempo_minimo(resultSet.getInt("tiempo_minimo"));
                casa.setTiempo_maximo(resultSet.getInt("tiempo_maximo"));
                casa.setPrecio_habitacion(resultSet.getDouble("precio_habitacion"));
                casa.setTipo_vivienda(resultSet.getString("tipo_habitacion"));
                casas.add(casa);
            }
            disconnectDataBase();
            return casas;
        }catch (Exception e){
            disconnectDataBase();
            e.getMessage();
            e.printStackTrace();
            throw e;
        }

    }


    //nested method
    //These two methods differ only because the comparison of the id_casa value is different in each case.
    public boolean findNullValueInCasaForSave(Casas casa) throws Exception, NullPointerException{
        boolean valueNull = false;
        if (casa == null){
            valueNull = true;
            System.out.println("casa 'object' cannot be null");
        }

        if (casa.getCalle() == null  || casa.getCalle().trim().isEmpty()){
            valueNull = true;
            System.out.println("calle 'value' cannot be null");
        }

        if (casa.getNumero() == null || casa.getNumero() < 1){
            valueNull = true;
            System.out.println("numero 'value' cannot be null");
        }

        if (casa.getCodigo_postal() == null || casa.getCodigo_postal().trim().isEmpty()){
            valueNull = true;
            System.out.println("codigo_posta 'value' cannot be null");
        }

        if (casa.getCiudad() == null || casa.getCiudad().trim().isEmpty() ){
            valueNull = true;
            System.out.println("ciudad 'value' cannot be null");
        }

        if (casa.getPais() == null || casa.getPais().trim().isEmpty() ){
            valueNull = true;
            System.out.println("pais 'value' cannot be null");
        }
        if (casa.getFecha_desde() == null ){
            valueNull = true;
            System.out.println("fecha_desde 'value' cannot be null");
        }

        if (casa.getFecha_hasta() == null ){
            valueNull = true;
            System.out.println("fecha_hasta 'value' cannot be null");
        }

        if (casa.getTiempo_minimo() == null || casa.getTiempo_minimo() < 1 ){
            valueNull = true;
            System.out.println("tiempo_minimo 'value' cannot be null");
        }

        if (casa.getTiempo_maximo() == null || casa.getTiempo_maximo() < 1){
            valueNull = true;
            System.out.println("tiempo_maximo 'value' cannot be null");
        }

        if (casa.getPrecio_habitacion() == null || casa.getPrecio_habitacion() <1){
            valueNull = true;
            System.out.println("precio_habitacion 'value' cannot be null");
        }

        if (casa.getTipo_vivienda() == null || casa.getTipo_vivienda().trim().isEmpty()){
            valueNull = true;
            System.out.println("tipo_vivienda 'value' cannot be null");
        }

        //Warning this is different
        if (casa.getId_casa() != null ){
            valueNull = true;
            System.out.println("id_casa 'value' must be null");
        }

        return valueNull;


    }
    public boolean findNullValueInCasaForEdit(Casas casa) throws Exception, NullPointerException{
        boolean valueNull = false;
        if (casa == null){
            valueNull = true;
            System.out.println("casa 'object' cannot be null");
        }

        if (casa.getCalle() == null  || casa.getCalle().trim().isEmpty()){
            valueNull = true;
            System.out.println("calle 'value' cannot be null");
        }

        if (casa.getNumero() == null || casa.getNumero() < 1){
            valueNull = true;
            System.out.println("numero 'value' cannot be null");
        }

        if (casa.getCodigo_postal() == null || casa.getCodigo_postal().trim().isEmpty()){
            valueNull = true;
            System.out.println("codigo_posta 'value' cannot be null");
        }

        if (casa.getCiudad() == null || casa.getCiudad().trim().isEmpty() ){
            valueNull = true;
            System.out.println("ciudad 'value' cannot be null");
        }

        if (casa.getPais() == null || casa.getPais().trim().isEmpty() ){
            valueNull = true;
            System.out.println("pais 'value' cannot be null");
        }
        if (casa.getFecha_desde() == null ){
            valueNull = true;
            System.out.println("fecha_desde 'value' cannot be null");
        }

        if (casa.getFecha_hasta() == null ){
            valueNull = true;
            System.out.println("fecha_hasta 'value' cannot be null");
        }

        if (casa.getTiempo_minimo() == null || casa.getTiempo_minimo() < 1 ){
            valueNull = true;
            System.out.println("tiempo_minimo 'value' cannot be null");
        }

        if (casa.getTiempo_maximo() == null || casa.getTiempo_maximo() < 1){
            valueNull = true;
            System.out.println("tiempo_maximo 'value' cannot be null");
        }

        if (casa.getPrecio_habitacion() == null || casa.getPrecio_habitacion() <1){
            valueNull = true;
            System.out.println("precio_habitacion 'value' cannot be null");
        }

        if (casa.getTipo_vivienda() == null || casa.getTipo_vivienda().trim().isEmpty()){
            valueNull = true;
            System.out.println("tipo_vivienda 'value' cannot be null");
        }

        //Warning this is different
        if (casa.getId_casa() == null || casa.getId_casa() < 1){
            valueNull = true;
            System.out.println("id_casa 'value' cannot be null");
        }

        return valueNull;


    }
}
