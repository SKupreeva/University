using System.Collections.Generic;
namespace lab5.Models.DataAccessModels
{
    public class OfficeEntity
    {
        public int id { get; set; }
        public string name { get; set; }
        public string adress { get; set; }

        public IEnumerable<WorkerEntity> workers { get; set; }
    }
}
