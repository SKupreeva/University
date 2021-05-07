using lab5.Models.DataAccessModels;
using System.Collections.Generic;
using System.Linq;

namespace lab5.Models.DataAccess
{
    public class OfficeRepository : IOfficeRepository
    {
        private readonly AppDbContext appDbContext;

        public OfficeRepository(AppDbContext appDbContext)
        {
            this.appDbContext = appDbContext;
        }

        public IEnumerable<OfficeEntity> GetAllOffices => appDbContext.OfficeEntity;

        public void AddOffice(OfficeEntity office)
        {
            appDbContext.OfficeEntity.Add(office);
            appDbContext.SaveChanges();
        }

        public void DeleteOffice(OfficeEntity office)
        {
            appDbContext.OfficeEntity.Remove(office);
        }

        public OfficeEntity FindOfficeById(int id) => appDbContext.OfficeEntity.FirstOrDefault(n => n.id == id);
        public IEnumerable<WorkerEntity> GetOfficeWorkers(int id) => appDbContext.WorkerEntity.Where(n => n.officeId == id);

        public void ModifyOffice(OfficeEntity office)
        {
            appDbContext.OfficeEntity.Update(office);
            appDbContext.SaveChanges();
        }
    }
}
