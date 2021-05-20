using System.Collections.Generic;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Stylist
{
    public class SignInModel : PageModel
    {
        public IEnumerable<StylistEntity> stylists;
        private IStylistService stylistService;

        public SignInModel(IStylistService _stylistService)
        {
            stylistService = _stylistService;
        }

        public void OnGet()
        {
            stylists = stylistService.GetAllStylists;
        }

        public IActionResult OnPostSubmit(string username, string password)
        {
            if (stylistService.SignIn(username, password))
            {
                var stylist = stylistService.FindStylistByUsername(username);
                return RedirectToPage("/Stylist/Welcome", new { id = stylist.id });
            }
            else return RedirectToPage("/Error", new { message = "Логин или пароль введены неправильно! Попробуйте еще раз" });
        }
    }
}
