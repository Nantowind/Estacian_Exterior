package Services;

import Entities.Casas;
import Persistence.CasasDAO;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.*;

public class CasasServices {

    private CasasDAO DAO;

    public CasasServices() {
        this.DAO = new CasasDAO();
    }

    public void searchForDateAndCountry() throws Exception {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("insert fecha_desde");
            Date dateMin =makeFecha_desde(sc);
            System.out.println("insert fecha_hasta");
            Date dateMax =makeFecha_hasta(sc);
            System.out.print("Insert pais");
            String pais= String.valueOf(sc.nextLine());

            Collection<Casas> casas = DAO.searchAllCasas();

            for (Casas casa : casas){
                if ( casa.getFecha_desde().after(dateMin) && casa.getFecha_hasta().before(dateMax)
                        && casa.getPais().equalsIgnoreCase(pais)){
                    System.out.println(casa);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Insert valid character");
            searchForDateAndCountry();
        }


    }
    public void searchDateAndRangeDays() throws Exception{
        ArrayList<Date>  dates = makeFecha_desdeAddDays();
        Date dateMin = dates.get(0);
        Date  dateMax = dates.get(1);

        try{
            Collection<Casas> casas = DAO.searchAllCasas();

            for (Casas casa : casas){
                if ( casa.getFecha_desde().after(dateMin) && casa.getFecha_hasta().before(dateMax)){
                    System.out.println(casa);
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Insert valid character");
            searchDateAndRangeDays();
        }



    }

    public void increasePricePerCountry() throws  Exception{
        Scanner sc = new Scanner(System.in);
        try {
            CasasDAO editCasa = new CasasDAO();
            System.out.print("insert how much percentage you want to increase: ");
            Double incresePrice = Double.valueOf(sc.nextLine());
            System.out.print("Insert the country where you want to apply the increase: ");
            String countryIncrese = String.valueOf(sc.nextLine());
            incresePrice /= 100;
            Collection<Casas> casas = DAO.searchAllCasas();
            for (Casas casa : casas){

                if (casa.getPais().equalsIgnoreCase(countryIncrese)){

//                  I get the price, I increase it in percentage And then I assign
//                  that value to the same object, to later add it
                    Double precioFinal = casa.getPrecio_habitacion() + (casa.getPrecio_habitacion() * incresePrice );
                    casa.setPrecio_habitacion(precioFinal);

                    editCasa.editCasa(casa);
                }

            }
            for (Casas casa : casas){
                System.out.println(casa);
            }


        }catch (NumberFormatException e){
            System.out.println("invalid number");
            increasePricePerCountry();
            throw e;
        }



    }

    public void countCasasforPais()throws Exception{

        ArrayList<Casas> casas = (ArrayList<Casas>) DAO.searchAllCasas();
        ArrayList<String> paises = new ArrayList<>();
        int position =0;
        int count =0;

        for (Casas casa : casas){
        paises.add(casa.getPais());
        }
        Collections.sort(paises);
        String aux= paises.get(0);
        for (int i = 0; i < paises.size(); i++) {
            if(aux.equalsIgnoreCase(paises.get(i)) ){
                count++;

                if (i == paises.size()-1){
                    System.out.println(count + ","+" "+paises.get(i));
                }

            } else {
                System.out.println(count + ","+" "+paises.get(i));
                count=1;
                aux= paises.get(i);
            }
        }


    }

    //method needs
    private Date makeFecha_desde(Scanner sc)throws Exception{
        int day;
        int month;
        int year;

        do {
            System.out.print("day: ");
            day = Integer.valueOf(sc.nextLine());
        } while (day < 1 || day >33 );

        do {
            System.out.print("month: ");
            month = Integer.valueOf(sc.nextLine());
        }while (month < 1 || month > 12 );

        do {
            System.out.print("Year: ");
            year = Integer.valueOf(sc.nextLine());
         }while (year < 1990 || year > 2030 );
        Date dateMin = new java.sql.Date(year-1900,month-1,day-1);
        System.out.println("");
        return dateMin;
    }
    private Date makeFecha_hasta(Scanner sc)throws Exception{
        int day;
        int month;
        int year;

        do {
            System.out.print("day: ");
            day = Integer.valueOf(sc.nextLine());
        }while (day < 1 || day >33 );

        do {
            System.out.print("month: ");
            month = Integer.valueOf(sc.nextLine());
        }while (month < 1 || month > 12 );

        do {
            System.out.print("Year: ");
            year = Integer.valueOf(sc.nextLine());
        }while (year < 1990 || year > 2030 );
        Date dateMin = new java.sql.Date(year-1900,month-1,day+1);
        System.out.println("");
        return dateMin;
    }
    private ArrayList<Date> makeFecha_desdeAddDays()throws Exception{

        Scanner sc = new Scanner(System.in);
        int day;
        int month;
        int year;
        int extraDays;
        ArrayList<Date> dates = new ArrayList<>();
        do {
            System.out.print("day: ");
            day = Integer.valueOf(sc.nextLine());
        }while (day < 1 || day >33 );

        do {
            System.out.print("month: ");
            month = Integer.valueOf(sc.nextLine());
        }while (month < 1 || month > 12 );

        do {
            System.out.print("Year: ");
            year = Integer.valueOf(sc.nextLine());
        }while (year < 1990 || year > 2030 );

        do {
            System.out.print("Insert range of days: ");
            extraDays = Integer.valueOf(sc.nextLine());
        }while (extraDays < 1 || extraDays > 365 );

        Date dateMin = new java.sql.Date(year-1900,month-1,day-1);
        Date dateMax = new java.sql.Date(year-1900,month-1,day+extraDays);
        dates.add(dateMin);
        dates.add(dateMax);

        System.out.println("");
        return dates;
    }

}
