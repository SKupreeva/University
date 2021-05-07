using lab5.Models.DataAccessModels;
using System.Collections.Generic;
using System.Linq;

namespace lab5.Models.DataAccess
{
    public class WorkerRepository : IWorkerRepository
    {
        private readonly AppDbContext appDbContext;

        public WorkerRepository(AppDbContext appDbContext)
        {
            this.appDbContext = appDbContext;
        }

        public IEnumerable<WorkerEntity> GetAllWorkers => appDbContext.WorkerEntity;

        public void AddWorker(WorkerEntity worker)
        {
            appDbContext.WorkerEntity.Add(worker);
            appDbContext.SaveChanges();
        }

        public WorkerEntity FindWorkerById(int id) => appDbContext.WorkerEntity.FirstOrDefault(s => s.id == id);

        public void ModifyWorker(WorkerEntity worker)
        {
            appDbContext.WorkerEntity.Update(worker);
            appDbContext.SaveChanges();
        }

        public void DeleteWorker(WorkerEntity worker)
        {
            appDbContext.WorkerEntity.Remove(worker);
            appDbContext.SaveChanges();
        }
    }
}
