using System.Collections.Generic;

namespace BarberShop.Models.Repository
{
    public class WorkplaceEntity
    {
        public int id { get; set; }
        public int number { get; set; }
        public int stylistId { get; set; }
        public StylistEntity stylist { get; set; }
    }
}
