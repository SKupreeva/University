using System;
using System.Collections.Generic;
using System.Linq;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Stylist
{
    public class DisplayModel : PageModel
    {
        private ISpecialityService specialityService;
        private IStylistService stylistService;

        public List<SpecialityEntity> specialities;
        public List<StylistEntity> stylists;

        public DisplayModel(ISpecialityService _specialityService, IStylistService _stylistService)
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

        public void OnPost(string username, string sortOrder, string speciality)
        {
            ViewData["UsernameSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Name desc" : "Name";
            ViewData["SpecSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Spec desc" : "Spec";

            if (!String.IsNullOrEmpty(username))
            {
                stylists = stylistService.GetAllStylists.Where(s => s.username == username).ToList();
            }
            else if (!String.IsNullOrEmpty(speciality))
            {
                var specialityId = specialityService.FindSpecialityByName(speciality).id;
                stylists = stylistService.GetAllStylists.Where(s => s.specialityid == specialityId).ToList();
            }
            else stylists = stylistService.GetAllStylists.ToList();
            switch (sortOrder)
            {
                case "Name desc":
                    stylists = stylists.OrderByDescending(s => s.username).ToList();
                    break;
                case "Spec":
                    stylists = stylists.OrderBy(s => s.speciality).ToList();
                    break;
                case "Spec desc":
                    stylists = stylists.OrderByDescending(s => s.speciality).ToList();
                    break;
                default:
                    stylists = stylists.OrderBy(s => s.username).ToList();
                    break;
            }
            foreach (var e in stylists)
            {
                specialities.Add(specialityService.FindSpecialityById(e.specialityid));
            }
        }
    }
}
