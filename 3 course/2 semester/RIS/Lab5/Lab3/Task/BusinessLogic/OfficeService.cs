using Lab3.Task.BusinessLogicModels;
using Lab3.Task.DataAccessModels;

namespace Lab3.Task.BusinessLogic
{
    public class OfficeService : IOfficeService
    {
        private IOfficeRepository officeRepository;

        public OfficeService(IOfficeRepository repository)
        {
            officeRepository = repository;
        }

        public Office FindOfficeById(int id)
        {
            OfficeEntity officeEntity = officeRepository.FindOfficeById(id);
            if (officeEntity != null)
            {
                Office office = new Office(
                    officeEntity.id,
                    officeEntity.name,

                    officeEntity.Customers
                    );
                return office;
            }
            else return null;
        }
    }
}
