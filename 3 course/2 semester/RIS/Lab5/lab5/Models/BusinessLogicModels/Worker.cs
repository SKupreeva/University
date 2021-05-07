using lab5.Models.DataAccessModels;

namespace lab5.Models.BusinessLogicModels
{
    public class Worker
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string position { get; set; }

        public int? officeId { get; set; }
        public OfficeEntity office { get; set; }

        public Worker(int id, string name, string surname, string position, int? officeId, OfficeEntity office)
        {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.position = position;

            this.officeId = officeId;
            this.office = office;
        }
    }
}
