using System;
using System.Collections.Generic;

namespace lab5.Models.DataAccessModels
{
    public class WorkerEntity
    {
        public int id { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string position { get; set; }

        public int? officeId { get; set; }
        public OfficeEntity office { get; set; }
    }
}
