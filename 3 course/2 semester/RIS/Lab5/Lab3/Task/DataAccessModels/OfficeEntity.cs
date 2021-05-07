using Lab3.DataAccessModels;
using System.Collections.Generic;

namespace Lab3.Task.DataAccessModels
{
    public class OfficeEntity
    {
        public int id { get; set; }
        public string name { get; set; }

        public IEnumerable<CustomerEntity> Customers { get; set; }
    }
}
