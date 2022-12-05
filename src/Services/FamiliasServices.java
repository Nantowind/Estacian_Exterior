package Services;

import Entities.Familias;
import Persistence.FamialiasDAO;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FamiliasServices {

    private FamialiasDAO DAO;

    public FamiliasServices() {
        this.DAO = new FamialiasDAO();
    }

    public void listFamiliesByChildrenAndAge() throws Exception {
        Scanner sc = new Scanner(System.in);
        int minAge;
        int maxAge;
        int numMinChildren;
        try{

            System.out.print("insert minimum age: ");
            minAge = Integer.valueOf(sc.nextLine());
            System.out.print("insert 'maximum' age: ");
            maxAge = Integer.valueOf(sc.nextLine());
            System.out.print("insert the minimum number of children: ");
            numMinChildren = Integer.valueOf(sc.nextLine());

            Collection<Familias> familias = DAO.searchAllFamilias();

            for (Familias familia : familias){
                if (familia.getEdad_minima() > minAge && familia.getEdad_maxima() < maxAge &&
                    familia.getNum_hijos() >= numMinChildren){
                    System.out.println(familia);
                }

            }

        }catch (ValueException | NumberFormatException e){
            System.out.println("value incorrect");
            listFamiliesByChildrenAndAge();
            throw e;
        }catch (Exception d){
            throw d;
        }

    }
    public void listFamiliesByEmail() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert email to search: ");
        String emailSearched = String.valueOf(sc.nextLine());
        int contador = 0;
        try {
            Collection<Familias> familias = DAO.searchAllFamilias();

            for (Familias familia: familias){
                if (familia.getEmail().contains(emailSearched)){
                    contador++;
                    System.out.println(familia.getEmail());
                }

            }
            if (contador == 0){
                System.out.println("No email found containing: "+ emailSearched );
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            throw e;
        }

    }
}
