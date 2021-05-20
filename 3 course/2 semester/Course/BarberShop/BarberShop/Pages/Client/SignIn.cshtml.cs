using System.Collections.Generic;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Client
{
    public class SignInModel : PageModel
    {
        public IEnumerable<ClientEntity> clients;
        private IClientService clientService;

        public SignInModel(IClientService _clientService)
        {
            clientService = _clientService;
        }

        public void OnGet()
        {
            clients = clientService.GetAllClients;
        }

        public IActionResult OnPostSubmit(string username, string password)
        {
            if (clientService.SignIn(username, password))
            {
                var client = clientService.FindClientByUsername(username);
                return RedirectToPage("/Client/Welcome", new { id = client.id});
            }  
            else return RedirectToPage("/Error", new { message = "Логин или пароль введены неправильно! Попробуйте еще раз" });
        }
    }
}
