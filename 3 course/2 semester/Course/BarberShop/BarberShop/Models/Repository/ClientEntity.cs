using System;

namespace BarberShop.Models.Repository
{
    public class ClientEntity
    {
        public int id { get; set; }
        public string username { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public string password { get; set; }
        public int stylistId { get; set; }

        public StylistEntity stylist { get; set; }
    }
}
