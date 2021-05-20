using BarberShop.Models.Repository;
using System.Collections.Generic;

namespace BarberShop.Models.BusinessLogicModels
{
    public interface IClientService
    {
        IEnumerable<ClientEntity> GetAllClients { get; }
        bool IfClientIsAlreadyExist(string login);
        ClientEntity FindClientById(int id);
        bool AddClientInDb(ClientEntity client);
        ClientEntity FindClientByUsername(string username);
        bool DeleteClientFromBd(ClientEntity client);
        bool EditClientInBd(ClientEntity client);
        bool SignIn(string username, string password);
        List<ClientEntity> GetClientList { get; }
    }
}
