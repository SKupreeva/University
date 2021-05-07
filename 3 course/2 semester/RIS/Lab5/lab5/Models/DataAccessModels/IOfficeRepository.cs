using System.Collections.Generic;

namespace lab5.Models.DataAccessModels
{
    public interface IOfficeRepository
    {
        IEnumerable<OfficeEntity> GetAllOffices { get; }
        OfficeEntity FindOfficeById(int id);
        IEnumerable<WorkerEntity> GetOfficeWorkers(int id);
        void AddOffice(OfficeEntity office);
        void ModifyOffice(OfficeEntity office);
        void DeleteOffice(OfficeEntity office);
    }
}
