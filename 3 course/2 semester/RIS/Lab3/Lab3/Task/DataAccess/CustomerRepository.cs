using Lab3.DataAccessModels;
using Lab3.Task;
using System.Collections.Generic;
using System.Linq;

namespace Lab3.DataAccess
{
    public class CustomerRepository : ICustomerRepository
    {
        private readonly AppDbContent appDbContent;

        public CustomerRepository(AppDbContent appDBContent)
        {
            appDbContent = appDBContent;
        }

        public IEnumerable<CustomerEntity> GetAllCustomers => appDbContent.CustomerEntity;
        public CustomerEntity FindCustomerById(int customerId) => appDbContent.CustomerEntity.FirstOrDefault(f => f.id == customerId);
    }
}
