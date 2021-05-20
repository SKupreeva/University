using System;
using System.Collections.Generic;
using System.Linq;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Client
{
    public class DisplayModel : PageModel
    {
        private IClientService clientService;
        private IStylistService stylistService;

        public List<ClientEntity> clients;
        public List<StylistEntity> stylists;

        public DisplayModel(IClientService _clientService, IStylistService _stylistService)
        {
            clientService = _clientService;
            stylistService = _stylistService;

            clients = new List<ClientEntity>();
            stylists = new List<StylistEntity>();
        }

        public void OnGet()
        {
            clients = clientService.GetClientList;
            foreach (var e in clients)
            {
                stylists.Add(stylistService.FindStylistById(e.stylistId));
            }
        }

        public void OnPost(string login, string sortOrder, string stylist)
        {
            ViewData["LoginSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Name desc" : "Name";
            ViewData["LastNameSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Spec desc" : "Spec";

            if (!String.IsNullOrEmpty(login))
            {
                clients = clientService.GetAllClients.Where(s => s.username == login).ToList();
            }
            else if (!String.IsNullOrEmpty(stylist))
            {
                var stylistId = stylistService.FindStylistByUsername(stylist).id;
                clients = clientService.GetAllClients.Where(s => s.stylistId == stylistId).ToList();
            }
            else clients = clientService.GetAllClients.ToList();
            switch (sortOrder)
            {
                case "Name desc":
                    clients = clients.OrderByDescending(s => s.username).ToList();
                    break;
                case "Spec":
                    clients = clients.OrderBy(s => s.lastName).ToList();
                    break;
                case "Spec desc":
                    clients = clients.OrderByDescending(s => s.lastName).ToList();
                    break;
                default:
                    clients = clients.OrderBy(s => s.username).ToList();
                    break;
            }
            foreach (var e in clients)
            {
                stylists.Add(stylistService.FindStylistById(e.stylistId));
            }
        }
    }
}
