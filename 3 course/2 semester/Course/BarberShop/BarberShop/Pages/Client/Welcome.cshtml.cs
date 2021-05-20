using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Client
{
    public class WelcomeModel : PageModel
    {
        private IClientService clientService;
        private IStylistService stylistService;

        public ClientEntity client;
        public StylistEntity stylist;

        public WelcomeModel(IClientService _clientService, IStylistService _stylistService)
        {
            clientService = _clientService;
            stylistService = _stylistService;
        }

        public void OnGet(int id)
        {
            client = clientService.FindClientById(id);
            stylist = stylistService.FindStylistById(client.stylistId);
        }
    }
}
