using Lab3.DataAccessModels;
using System.Collections.Generic;

namespace Lab3.Task.BusinessLogicModels
{
    public class Office
    {
        public int id { get; set; }
        public string name { get; set; }

        public IEnumerable<CustomerEntity> Customers { get; set; }

        public Office(int id, string name, IEnumerable<CustomerEntity> Customers)
        {
            this.id = id;
            this.name = name;

            this.Customers = Customers;
        }
    }
}
