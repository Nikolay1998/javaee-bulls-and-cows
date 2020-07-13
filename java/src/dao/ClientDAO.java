package dao;

import model.Client;

import java.util.List;

public interface ClientDAO {
    Client createClient(Client client);
    Client getClient(String login, String password);
    Client getClientByLogin(String login);
    void updateRating(Double newRating, Integer id);
    Client updateClient(Client client);
    List<Client> getClientsWithRating();
}
