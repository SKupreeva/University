using System;
using System.Collections.Generic;
using System.Linq;
using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace BarberShop.Pages.Equipment
{
    public class MainModel : PageModel
    {
        private IWorkplaceService workplaceService;
        private IEquipmentService equipmentService;

        public List<EquipmentEntity> equipments;
        public List<WorkplaceEntity> workplaces;

        public MainModel(IWorkplaceService _workplaceService, IEquipmentService _equipmentService)
        {
            workplaceService = _workplaceService;
            equipmentService = _equipmentService;

            equipments = new List<EquipmentEntity>();
            workplaces = new List<WorkplaceEntity>();
        }

        public void OnGet()
        {
            equipments = equipmentService.GetEquipmentList;
            foreach (var e in equipments)
            {
                workplaces.Add(workplaceService.FindWorkplaceById(e.workplaceId));
            }
        }

        public void OnPost(string name, string sortOrder, string number)
        {
            ViewData["NameSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Name desc" : "Name";
            ViewData["PriceSortParm"] = String.IsNullOrEmpty(sortOrder) ? "Spec desc" : "Spec";

            if (!String.IsNullOrEmpty(name))
            {
                equipments = equipmentService.GetAllEquipments.Where(s => s.name == name).ToList();
            }
            else if (!String.IsNullOrEmpty(number))
            {
                var workplaceId = workplaceService.FindWorkplaceByNumber(int.Parse(number)).id;
                equipments = equipmentService.GetAllEquipments.Where(s => s.workplaceId == workplaceId).ToList();
            }
            else equipments = equipmentService.GetAllEquipments.ToList();
            switch (sortOrder)
            {
                case "Name desc":
                    equipments = equipments.OrderByDescending(s => s.name).ToList();
                    break;
                case "Spec":
                    equipments = equipments.OrderBy(s => s.workplace.number).ToList();
                    break;
                case "Spec desc":
                    equipments = equipments.OrderByDescending(s => s.workplace.number).ToList();
                    break;
                default:
                    equipments = equipments.OrderBy(s => s.name).ToList();
                    break;
            }
            foreach (var e in equipments)
            {
                workplaces.Add(workplaceService.FindWorkplaceById(e.workplaceId));
            }
        }
    }
}
