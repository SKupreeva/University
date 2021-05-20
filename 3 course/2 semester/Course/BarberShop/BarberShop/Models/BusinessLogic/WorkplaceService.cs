using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;
using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class WorkplaceService : IWorkplaceService
    {
        private readonly BarberContext context;
        public SelectList workplaces { get; set; }

        public WorkplaceService(BarberContext appDbContext)
        {
            context = appDbContext;
            workplaces = new SelectList(context.Workplaces, nameof(WorkplaceEntity.id), nameof(WorkplaceEntity.number));
        }

        public IEnumerable<WorkplaceEntity> GetAllWorkplaces => context.Workplaces;

        public List<WorkplaceEntity> GetWorkplaceList => context.Workplaces.ToList();

        public bool AddWorkplaceInBd(WorkplaceEntity workplace)
        {
            if (FindWorkplaceById(workplace.id) != null)
            {
                context.Workplaces.Add(workplace);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool DeleteWorkplaceFromBd(WorkplaceEntity workplace)
        {
            if (FindWorkplaceById(workplace.id) != null)
            {
                context.Workplaces.Remove(workplace);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool EditWorkplaceInBd(WorkplaceEntity workplace)
        {
            if (FindWorkplaceById(workplace.id) != null)
            {
                context.Workplaces.Update(workplace);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public WorkplaceEntity FindWorkplaceById(int id) => context.Workplaces.FirstOrDefault(i => i.id == id);

        public WorkplaceEntity FindWorkplaceByNumber(int number) => context.Workplaces.FirstOrDefault(i => i.number == number);

        public bool IfWorkplaceIsAlreadyExist(int number)
        {
            if (FindWorkplaceByNumber(number) != null) return true;
            else return false;
        }
    }
}
