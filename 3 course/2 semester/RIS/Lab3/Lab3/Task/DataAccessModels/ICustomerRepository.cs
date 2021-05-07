using System.Collections.Generic;

namespace Lab3.DataAccessModels
{
    public interface ICustomerRepository
    {
        IEnumerable<CustomerEntity> GetAllCustomers { get; }
        CustomerEntity FindCustomerById(int customerId);
    }
}
