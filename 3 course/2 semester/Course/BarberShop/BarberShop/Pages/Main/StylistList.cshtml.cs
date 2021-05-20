using System.Collections.Generic;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Main
{
    public class StylistListModel : PageModel
    {
        private ISpecialityService specialityService;
        private IStylistService stylistService;

        public List<SpecialityEntity> specialities;
        public List<StylistEntity> stylists;

        public StylistListModel(ISpecialityService _specialityService, IStylistService _stylistService)
        {
            specialityService = _specialityService;
            stylistService = _stylistService;

            specialities = new List<SpecialityEntity>();
            stylists = new List<StylistEntity>();
        }

        public void OnGet()
        {
            stylists = stylistService.GetStylistList;
            foreach (var e in stylists)
            {
                specialities.Add(specialityService.FindSpecialityById(e.specialityid));
            }
        }
    }
}
