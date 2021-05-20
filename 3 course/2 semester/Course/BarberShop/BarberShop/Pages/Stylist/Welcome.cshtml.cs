using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Stylist
{
    public class WelcomeModel : PageModel
    {
        private ISpecialityService specialityService;
        private IStylistService stylistService;

        public SpecialityEntity speciality;
        public StylistEntity stylist;

        public WelcomeModel(ISpecialityService _specialityService, IStylistService _stylistService)
        {
            specialityService = _specialityService;
            stylistService = _stylistService;
        }

        public void OnGet(int id)
        {
            stylist = stylistService.FindStylistById(id);
            speciality = specialityService.FindSpecialityById(stylist.specialityid);
        }
    }
}
