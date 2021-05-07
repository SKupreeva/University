using Lab3.Task.DataAccessModels;

namespace Lab3.DataAccessModels
{
    public class CustomerEntity
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string firstAddress { get; set; }
        public string secondAddress { get; set; }

        public int officeId { get; set; }
        public OfficeEntity office { get; set; }
    }
}
