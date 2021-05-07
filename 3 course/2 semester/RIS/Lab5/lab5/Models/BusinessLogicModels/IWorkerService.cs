using lab5.Models.DataAccessModels;
using System.Collections.Generic;

namespace lab5.Models.BusinessLogicModels
{
    public interface IWorkerService
    {
        WorkerEntity FindWorkerById(int id);
        List<Worker> GetWorkerList { get; }
        void AddWorkerInDb(WorkerEntity worker);
        void ModifyWorkerInDb(WorkerEntity worker);
        void DeleteWorkerInDb(WorkerEntity worker);
    }
}
