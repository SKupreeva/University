using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Models.Repository
{
    public class EquipmentEntity
    {
        public int id { get; set; }
        public string name { get; set; }
        public int serviceLife { get; set; }
        public int operatingTime { get; set; }
        public int price { get; set; }
        public int workplaceId { get; set; }

        public WorkplaceEntity workplace { get; set; }
    }
}
