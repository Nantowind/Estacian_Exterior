package Estancia;

import Services.CasasServices;
import Services.ComentariosServices;
import Services.EstanciasServices;
import Services.FamiliasServices;

import java.util.Scanner;

public class UIMenu {

    public void Menu() throws Exception {
        Scanner sc = new Scanner(System.in);

        FamiliasServices familiaNueva = new FamiliasServices();
        CasasServices casaNueva = new CasasServices();
        EstanciasServices estanciaNueva = new EstanciasServices();
        ComentariosServices nuevoComentario = new ComentariosServices();
        int opcion = 0;
        try {

            do {
                System.out.println("1- search for family by number of children and ages");
                System.out.println("2- search available houses by period and country");
                System.out.println("3- search family by email");
                System.out.println("4- search for available houses as of a given date and a specific number of days");
                System.out.println("5- search for clients who at some point made a stay and house description");
                System.out.println("6- increase prices of stays");
                System.out.println("7- Obtain the number of houses that exist for each of the different countries");
                System.out.println("8- Search houses by country and comment");
                opcion = Integer.valueOf(sc.nextLine());
                switch (opcion){

                    case 1:
                        familiaNueva.listFamiliesByChildrenAndAge();
                        break;
                    case 2:
                        casaNueva.searchForDateAndCountry();
                        break;
                    case 3:
                        familiaNueva.listFamiliesByEmail();
                        break;
                    case 4:
                        casaNueva.searchDateAndRangeDays();
                        break;
                    case 5:
                        estanciaNueva.searchEstanciaForClient();
                        break;
                    case 6:
                        casaNueva.increasePricePerCountry();
                        break;
                    case 7:
                        casaNueva.countCasasforPais();
                        break;
                    case 8:
                        nuevoComentario.searchComentariosForContainAndCountry();
                        break;
                    default:
                        System.out.println("incorrect value");

                }
                System.out.println("");
            }while (opcion < 9 || opcion > 0);


        }catch (NumberFormatException a){
            System.out.println("incorrect number");
            Menu();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("errorcito");
            throw e;
        }

    }
}
