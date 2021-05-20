using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace BarberShop.Models.BusinessLogicModels
{
    public interface IWorkplaceService
    {
        IEnumerable<WorkplaceEntity> GetAllWorkplaces { get; }
        WorkplaceEntity FindWorkplaceById(int id);
        WorkplaceEntity FindWorkplaceByNumber(int number);
        bool IfWorkplaceIsAlreadyExist(int number);
        bool AddWorkplaceInBd(WorkplaceEntity workplace);
        bool DeleteWorkplaceFromBd(WorkplaceEntity workplace);
        bool EditWorkplaceInBd(WorkplaceEntity workplace);
        List<WorkplaceEntity> GetWorkplaceList { get; }
        public SelectList workplaces { get; }
    }
}
