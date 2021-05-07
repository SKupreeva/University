using Lab3.Task.DataAccessModels;
using System.Collections.Generic;
using System.Linq;

namespace Lab3.Task.DataAccess
{
    public class OfficeRepository : IOfficeRepository
    {
        private readonly AppDbContext appDbContext;

        public OfficeRepository(AppDbContext appDbContext)
        {
            this.appDbContext = appDbContext;
        }

        public IEnumerable<OfficeEntity> GetAllOffices => appDbContext.OfficeEntity;
        public OfficeEntity FindOfficeById(int officeId) => appDbContext.OfficeEntity.FirstOrDefault(f => f.id == officeId);
    }
}
