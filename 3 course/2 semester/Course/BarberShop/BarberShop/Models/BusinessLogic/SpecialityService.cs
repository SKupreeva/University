using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;
using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class SpecialityService : ISpecialityService
    {
        public readonly BarberContext context;
        public SelectList specialities { get; set; }

        public SpecialityService(BarberContext appDbContext)
        {
            context = appDbContext;
            specialities = new SelectList(context.Specialities, nameof(SpecialityEntity.id), nameof(SpecialityEntity.name));
        }

        public IEnumerable<SpecialityEntity> GetAllSpecialities => context.Specialities;

        public List<SpecialityEntity> GetSpecialityList => context.Specialities.ToList();

        public bool AddSpecialityInBd(SpecialityEntity speciality)
        {
            if (FindSpecialityById(speciality.id) == null)
            {
                context.Specialities.Add(speciality);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool DeleteSpecialityFromBd(SpecialityEntity speciality)
        {
            if (FindSpecialityById(speciality.id) != null)
            {
                context.Specialities.Remove(speciality);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool EditSpecialityInBd(SpecialityEntity speciality)
        {
            if (FindSpecialityById(speciality.id) != null)
            {
                context.Specialities.Update(speciality);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public SpecialityEntity FindSpecialityById(int id) => context.Specialities.FirstOrDefault(i => i.id == id);

        public SpecialityEntity FindSpecialityByName(string name) => context.Specialities.FirstOrDefault(i => i.name == name);

        public bool IfSpecialityIsAlreadyExist(string name)
        {
            if (FindSpecialityByName(name) != null) return true;
            else return false;
        }
    }
}
