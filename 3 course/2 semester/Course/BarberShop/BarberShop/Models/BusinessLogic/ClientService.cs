using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using System.Collections.Generic;
using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class ClientService: IClientService
    {
        private readonly BarberContext context;

        public ClientService(BarberContext appDbContext)
        {
            context = appDbContext;
        }

        public IEnumerable<ClientEntity> GetAllClients => context.Clients;

        public List<ClientEntity> GetClientList => context.Clients.ToList();

        public bool AddClientInDb(ClientEntity client)
        {
            if (FindClientById(client.id) == null)
            {
                context.Clients.Add(client);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool DeleteClientFromBd(ClientEntity client)
        {
            if (FindClientById(client.id) != null)
            {
                context.Clients.Remove(client);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool EditClientInBd(ClientEntity client)
        {
            if (FindClientById(client.id) != null)
            {
                context.Clients.Update(client);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public ClientEntity FindClientById(int id) => context.Clients.FirstOrDefault(i => i.id == id);

        public ClientEntity FindClientByUsername(string username) => context.Clients.FirstOrDefault(n => n.username == username);

        public bool IfClientIsAlreadyExist(string login)
        {
            if (FindClientByUsername(login) != null) return true;
            else return false;
        }

        public bool SignIn(string username, string password)
        {
            if (FindClientByUsername(username) != null)
            {
                var client = context.Clients.FirstOrDefault(s => s.username == username);
                if (client.password == password) return true;
                else return false;
            }
            else return false;
        }
    }
}
