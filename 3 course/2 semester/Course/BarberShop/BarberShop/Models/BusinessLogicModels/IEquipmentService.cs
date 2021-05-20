using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace BarberShop.Models.BusinessLogicModels
{
    public interface IEquipmentService
    {
        IEnumerable<EquipmentEntity> GetAllEquipments { get; }
        EquipmentEntity FindEquipmentById(int id);
        EquipmentEntity FindEquipmentByName(string name);
        bool IfEquipmentIsAlreadyExist(string name);
        bool AddEquipmentInBd(EquipmentEntity equipment);
        bool DeleteEquipmentFromBd(EquipmentEntity equipment);
        bool EditEquipmentInBd(EquipmentEntity equipment);
        List<EquipmentEntity> GetEquipmentList { get; }
        public SelectList equips { get; }
    }
}
