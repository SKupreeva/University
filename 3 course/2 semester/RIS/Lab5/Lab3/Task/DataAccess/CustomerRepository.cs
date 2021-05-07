using Lab3.DataAccessModels;
using Lab3.Task;
using System.Collections.Generic;
using System.Linq;

namespace Lab3.DataAccess
{
    public class CustomerRepository : ICustomerRepository
    {
        private readonly AppDbContext appDbContext;

        public CustomerRepository(AppDbContext appDBContext)
        {
            appDbContext = appDBContext;
        }

        public IEnumerable<CustomerEntity> GetAllCustomers => appDbContext.CustomerEntity;
        public CustomerEntity FindCustomerById(int customerId) => appDbContext.CustomerEntity.FirstOrDefault(f => f.id == customerId);
    }
}
