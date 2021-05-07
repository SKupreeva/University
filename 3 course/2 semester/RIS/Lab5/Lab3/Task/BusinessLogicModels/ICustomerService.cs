using Lab3.DataAccessModels;
using System.Collections.Generic;

namespace Lab3.BusinessLogicModels
{
    public interface ICustomerService
    {
        Customer FindCustomerById(int id);
    }
}
