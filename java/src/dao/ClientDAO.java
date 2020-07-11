package dao;

import model.Client;

public interface ClientDAO {
    Client createClient(Client client);

    Client getClientByLogin(String login);
}
