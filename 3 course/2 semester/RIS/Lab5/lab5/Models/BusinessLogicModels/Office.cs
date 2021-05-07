using lab5.Models.DataAccessModels;
using System.Collections.Generic;

namespace lab5.Models.BusinessLogicModels
{
    public class Office
    {
        public int id { get; set; }
        public string name { get; set; }
        public string adress { get; set; }

        public IEnumerable<WorkerEntity> workers { get; set; }

        public Office(int id, string name, string adress, IEnumerable<WorkerEntity> workers)
        {
            this.id = id;
            this.name = name;
            this.adress = adress;
            this.workers = workers;
        }
    }
}
