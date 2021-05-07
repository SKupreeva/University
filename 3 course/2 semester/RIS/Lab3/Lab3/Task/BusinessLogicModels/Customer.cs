using System;

namespace Lab3.BusinessLogicModels
{
    public class Customer
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string firstAddress { get; set; }
        public string secondAddress { get; set; }
        public ulong faxNumber { get; set; }

        public Customer(int id, string name, string surname, string firstAddress, string secondAddress, ulong faxNumber)
        {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.firstAddress = firstAddress;
            this.secondAddress = secondAddress;
            this.faxNumber = faxNumber;
        }
    }
}
