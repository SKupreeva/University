using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Models.Repository
{
    public class StylistEntity
    {
        public int id { get; set; }
        public string username { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string password { get; set; }
        public int specialityid { get; set; }

        public SpecialityEntity speciality { get; set; }
    }
}
