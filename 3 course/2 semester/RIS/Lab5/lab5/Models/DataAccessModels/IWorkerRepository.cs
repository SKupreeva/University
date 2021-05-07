using System.Collections.Generic;

namespace lab5.Models.DataAccessModels
{
    public interface IWorkerRepository
    {
        IEnumerable<WorkerEntity> GetAllWorkers { get; }
        WorkerEntity FindWorkerById(int id);
        void AddWorker(WorkerEntity worker);
        void ModifyWorker(WorkerEntity worker);
        void DeleteWorker(WorkerEntity worker);
    }
}
