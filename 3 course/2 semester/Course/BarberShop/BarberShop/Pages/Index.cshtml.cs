using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Collections.Generic;

namespace BarberShop.Pages
{
    public class IndexModel : PageModel
    {
        public IEnumerable<ClientEntity> Clients;
        private IClientService clientService;

        public IndexModel(IClientService _clientService)
        {
            clientService = _clientService;
        }

        public void OnGet()
        {
            Clients = clientService.GetAllClients;
        }
    }
}
