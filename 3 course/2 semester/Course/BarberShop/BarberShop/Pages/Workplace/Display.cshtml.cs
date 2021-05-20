using System;
using System.Collections.Generic;
using System.Linq;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Workplace
{
    public class DisplayModel : PageModel
    {
        private IWorkplaceService workplaceService;
        private IStylistService stylistService;

        public List<WorkplaceEntity> workplaces;
        public List<StylistEntity> stylists;

        public DisplayModel(IWorkplaceService _workplaceService, IStylistService _stylistService)
        {
            workplaceService = _workplaceService;
            stylistService = _stylistService;

            workplaces = new List<WorkplaceEntity>();
            stylists = new List<StylistEntity>();
        }

        public void OnGet()
        {
            workplaces = workplaceService.GetWorkplaceList;
            foreach (var w in workplaces)
            {
                stylists.Add(stylistService.FindStylistById(w.stylistId));
            }
        }

        public void OnPost(string username, string sortOrder, string number)
        {
            ViewData["NumberSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Name desc" : "Name";
            ViewData["StylistSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Spec desc" : "Spec";

            if (!String.IsNullOrEmpty(username))
            {
                var stylistId = stylistService.FindStylistByUsername(username).id;
                workplaces = workplaceService.GetAllWorkplaces.Where(s => s.stylistId == stylistId).ToList();
            }
            else if (!String.IsNullOrEmpty(number))
            {
                workplaces = workplaceService.GetAllWorkplaces.Where(s => s.number.ToString() == number).ToList();
            }
            else workplaces = workplaceService.GetAllWorkplaces.ToList();
            switch (sortOrder)
            {
                case "Name desc":
                    workplaces = workplaces.OrderByDescending(s => s.number).ToList();
                    break;
                case "Spec":
                    workplaces = workplaces.OrderBy(s => s.stylist.username).ToList();
                    break;
                case "Spec desc":
                    workplaces = workplaces.OrderByDescending(s => s.stylist.username).ToList();
                    break;
                default:
                    workplaces = workplaces.OrderBy(s => s.number).ToList();
                    break;
            }
            foreach (var w in workplaces)
            {
                stylists.Add(stylistService.FindStylistById(w.stylistId));
            }
        }
    }
}
