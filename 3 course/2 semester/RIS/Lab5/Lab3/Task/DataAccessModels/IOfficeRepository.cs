using System.Collections.Generic;

namespace Lab3.Task.DataAccessModels
{
    public interface IOfficeRepository
    {
        IEnumerable<OfficeEntity> GetAllOffices { get; }
        OfficeEntity FindOfficeById(int officeId);
    }
}
