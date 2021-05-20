using System;
using System.Collections.Generic;
using System.Linq;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Speciality
{
    public class MainModel : PageModel
    {
        private ISpecialityService specialityService;

        public IEnumerable<SpecialityEntity> specialities;

        public MainModel(ISpecialityService _specialityService)
        {
            specialityService = _specialityService;
        }

        public void OnGet()
        {
            specialities = specialityService.GetAllSpecialities;
        }

        public void OnPost(string name, string sortOrder, string education)
        {
            ViewData["NameSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Name desc" : "Name";
            ViewData["ExperienceSortParam"] = String.IsNullOrEmpty(sortOrder) ? "Spec desc" : "Spec";

            if (!String.IsNullOrEmpty(name))
            {
                specialities = specialityService.GetAllSpecialities.Where(s => s.name == name).ToList();
            }else if (!String.IsNullOrEmpty(education))
            {
                specialities = specialityService.GetAllSpecialities.Where(s => s.education == education).ToList();
            }
            else specialities = specialityService.GetAllSpecialities.ToList();
            switch (sortOrder)
            {
                case "Name desc":
                    specialities = specialities.OrderByDescending(s => s.name).ToList();
                    break;
                case "Spec":
                    specialities = specialities.OrderBy(s => s.experience).ToList();
                    break;
                case "Spec desc":
                    specialities = specialities.OrderByDescending(s => s.experience).ToList();
                    break;
                default:
                    specialities = specialities.OrderBy(s => s.name).ToList();
                    break;
            }
        }
    }
}
