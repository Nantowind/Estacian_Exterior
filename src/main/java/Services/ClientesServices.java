package Services;

import Entities.Casas;
import Entities.Clientes;
import Entities.Estancias;
import Persistence.ClientesDAO;
import Persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ClientesServices {

    private ClientesDAO DAO;

    public ClientesServices() {
        this.DAO = new ClientesDAO();
    }



}
