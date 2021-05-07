using Lab3.Task.DataAccessModels;

namespace Lab3.BusinessLogicModels
{
    public class Customer
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string firstAddress { get; set; }
        public string secondAddress { get; set; }

        public int officeId { get; set; }
        public OfficeEntity office { get; set; }

        public Customer(int id, string name, string surname, string firstAddress, string secondAddress, int officeId, OfficeEntity office)
        {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.firstAddress = firstAddress;
            this.secondAddress = secondAddress;

            this.officeId = officeId;
            this.office = office;
        }
    }
}
