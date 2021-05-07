using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Lab3.DataAccessModels
{
    public class CustomerEntity
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string firstAddress { get; set; }
        public string secondAddress { get; set; }
        public ulong faxNumber { get; set; }
    }
}
