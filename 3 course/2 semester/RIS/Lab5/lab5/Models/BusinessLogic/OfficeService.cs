using System.Collections.Generic;
using lab5.Models.BusinessLogicModels;
using lab5.Models.DataAccessModels;

namespace lab5.Models.BusinessLogic
{
    public class OfficeService: IOfficeService
    {
        private IOfficeRepository officeRepository;

        public OfficeService(IOfficeRepository repository)
        {
            officeRepository = repository;
        }

        public List<Office> GetOfficeList
        {
            get
            {
                var offices = new List<Office>();
                var officeEntityList = officeRepository.GetAllOffices;
                if (officeEntityList != null)
                {
                    foreach (var o in officeEntityList)
                    {
                        offices.Add(new Office(
                                o.id,
                                o.name,
                                o.adress,

                                o.workers
                                ));
                    }
                }
                return offices;
            }
        }

        public void AddOfficeInDb(OfficeEntity office)
        {
            officeRepository.AddOffice(office);
        }

        public void DeleteOfficeInDb(OfficeEntity office)
        {
            officeRepository.DeleteOffice(office);
        }

        public OfficeEntity FindOfficeById(int id)
        {
            OfficeEntity officeEntity = officeRepository.FindOfficeById(id);
            return officeEntity;
        }

        public IEnumerable<WorkerEntity> GetOfficeWorkers(int id)
        {
            var workers = officeRepository.GetOfficeWorkers(id);
            return workers;
        }

        public void ModifyOfficeInDb(OfficeEntity office)
        {
            officeRepository.ModifyOffice(office);
        }
    }
}
