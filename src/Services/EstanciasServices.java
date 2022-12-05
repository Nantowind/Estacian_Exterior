package Services;

import Entities.Casas;
import Entities.Clientes;
import Entities.Estancias;
import Persistence.CasasDAO;
import Persistence.ClientesDAO;
import Persistence.EstanciasDAO;

import java.util.Collection;

public class EstanciasServices {

    private EstanciasDAO DAO;

    public EstanciasServices() {
        this.DAO = new EstanciasDAO();
    }

    public void searchEstanciaForClient()throws Exception{
        try {
            ClientesDAO searchCliente = new ClientesDAO();
            CasasDAO searchCasa = new CasasDAO();
            Collection<Estancias> estancias = DAO.searchAllEstancia();

            for (Estancias estancia : estancias){

               Casas tempCasa = searchCasa.searchCasas(estancia.getId_casa());
               Clientes tempCliente = searchCliente.searchClienteByID(estancia.getId_cliente());

                System.out.println(tempCliente +"tipo_vivienda='"+ tempCasa.getTipo_vivienda()+"'");

            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }


    }



}
