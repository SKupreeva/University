using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace BarberShop.Models.BusinessLogicModels
{
    public interface ISpecialityService
    {
        IEnumerable<SpecialityEntity> GetAllSpecialities { get; }
        SpecialityEntity FindSpecialityById(int id);
        SpecialityEntity FindSpecialityByName(string name);
        bool IfSpecialityIsAlreadyExist(string name);
        bool AddSpecialityInBd(SpecialityEntity speciality);
        bool DeleteSpecialityFromBd(SpecialityEntity speciality);
        bool EditSpecialityInBd(SpecialityEntity speciality);
        List<SpecialityEntity> GetSpecialityList { get; }
        public SelectList specialities { get; }
    }
}
