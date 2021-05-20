using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;
using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class EquipmentService : IEquipmentService
    {
        private readonly BarberContext context;
        public SelectList equips;

        public EquipmentService(BarberContext appDbContext)
        {
            context = appDbContext;
            equips = new SelectList(context.Equipments, nameof(EquipmentEntity.id), nameof(EquipmentEntity.name));
        }

        public IEnumerable<EquipmentEntity> GetAllEquipments => context.Equipments;

        public List<EquipmentEntity> GetEquipmentList => context.Equipments.ToList();

        SelectList IEquipmentService.equips => throw new System.NotImplementedException();

        public bool AddEquipmentInBd(EquipmentEntity equipment)
        {
            if (FindEquipmentById(equipment.id) == null)
            {
                context.Equipments.Add(equipment);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool DeleteEquipmentFromBd(EquipmentEntity equipment)
        {
            if (FindEquipmentById(equipment.id) != null)
            {
                context.Equipments.Remove(equipment);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool EditEquipmentInBd(EquipmentEntity equipment)
        {
            if (FindEquipmentById(equipment.id) != null)
            {
                context.Equipments.Update(equipment);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public EquipmentEntity FindEquipmentById(int id) => context.Equipments.FirstOrDefault(i => i.id == id);

        public EquipmentEntity FindEquipmentByName(string name) => context.Equipments.FirstOrDefault(i => i.name == name);

        public bool IfEquipmentIsAlreadyExist(string name)
        {
            if (FindEquipmentByName(name) != null) return true;
            else return false;
        }
    }
}
