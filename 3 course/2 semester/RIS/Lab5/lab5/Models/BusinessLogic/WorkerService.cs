using System.Collections.Generic;
using lab5.Models.BusinessLogicModels;
using lab5.Models.DataAccessModels;

namespace lab5.Models.BusinessLogic
{
    public class WorkerService : IWorkerService
    {
        private IWorkerRepository workerRepository;

        public WorkerService(IWorkerRepository repository)
        {
            workerRepository = repository;
        }

        public List<Worker> GetWorkerList {
            get {
                var workers = new List<Worker>();
                var workerEntityList = workerRepository.GetAllWorkers;
                if(workerEntityList != null)
                {
                    foreach (var w in workerEntityList)
                    {
                        workers.Add(new Worker(
                                w.id,
                                w.name,
                                w.surname,
                                w.position,

                                w.officeId,
                                w.office
                                ));
                    }
                }
                return workers;
            }
        }

        public void AddWorkerInDb(WorkerEntity worker)
        {
            workerRepository.AddWorker(worker);
        }

        public WorkerEntity FindWorkerById(int id)
        {
            WorkerEntity workerEntity = workerRepository.FindWorkerById(id);
            return workerEntity;
        }

        public void ModifyWorkerInDb(WorkerEntity worker)
        {
            workerRepository.ModifyWorker(worker);
        }

        public void DeleteWorkerInDb(WorkerEntity worker)
        {
            workerRepository.DeleteWorker(worker);
        }
    }
}
