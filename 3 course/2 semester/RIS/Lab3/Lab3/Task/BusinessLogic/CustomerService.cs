using Lab3.BusinessLogicModels;
using Lab3.DataAccessModels;

namespace Lab3.BusinessLogic
{
    public class CustomerService : ICustomerService
    {
        private ICustomerRepository customerRepository;
        
        public CustomerService(ICustomerRepository repository)
        {
            customerRepository = repository;
        }

        public Customer FindCustomerById(int id)
        {
            CustomerEntity customerEntity = customerRepository.FindCustomerById(id);
            if (customerEntity != null)
            {
                Customer customer = new Customer(
                    customerEntity.id,
                    customerEntity.name,
                    customerEntity.surname,
                    customerEntity.firstAddress,
                    customerEntity.secondAddress,
                    customerEntity.faxNumber
                    );
                return customer;
            }
            else return null;
        }
    }
}
