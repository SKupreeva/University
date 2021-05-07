using lab5.Models.DataAccessModels;
using System.Collections.Generic;

namespace lab5.Models.BusinessLogicModels
{
    public interface IOfficeService
    {
        OfficeEntity FindOfficeById(int id);
        List<Office> GetOfficeList { get; }
        IEnumerable<WorkerEntity> GetOfficeWorkers(int id);
        void AddOfficeInDb(OfficeEntity office);
        void ModifyOfficeInDb(OfficeEntity office);
        void DeleteOfficeInDb(OfficeEntity office);
    }
}
