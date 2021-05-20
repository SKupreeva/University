using BarberShop.Models.Repository;
using System.Linq;

namespace BarberShop.Models
{
    public class DbInitializing
    {
        public static void Initialize(BarberContext context)
        {
            context.Database.EnsureCreated();

            if (context.Clients.Any())
            {
                return;
            }
            var specialities = new SpecialityEntity[]
            {
                new SpecialityEntity{name="Colorist", education="Higher", experience=2},
                new SpecialityEntity{name="Barber", education="Collage", experience=1},
                new SpecialityEntity{name="Stylist", education="Higher", experience=3}
            };
            foreach (SpecialityEntity s in specialities)
            {
                context.Specialities.Add(s);
            }
            context.SaveChanges();

            var stylists = new StylistEntity[]
            {
                new StylistEntity{username = "stylist1", firstName = "Alex", lastName = "King",
                    speciality = specialities[1], password = "stylist"},
                new StylistEntity{username = "stylist2", firstName = "Nick", lastName = "Killer",
                    speciality = specialities[1], password = "stylist"}
            };
            foreach (StylistEntity s in stylists)
            {
                context.Stylists.Add(s);
            }
            context.SaveChanges();

            var workplayces = new WorkplaceEntity[]
            {
                new WorkplaceEntity{number=1, stylist = stylists[1]},
                new WorkplaceEntity{number=2, stylist = stylists[1]},
                new WorkplaceEntity{number=3, stylist = stylists[1]}
            };
            foreach (WorkplaceEntity w in workplayces)
            {
                context.Workplaces.Add(w);
            }
            context.SaveChanges();

            var equips = new EquipmentEntity[]
            {
                new EquipmentEntity{name="Hairdryer 'Mon Paris'", serviceLife = 3, operatingTime = 2, price = 12000, workplace = workplayces[1]},
                new EquipmentEntity{name="Curling iron", serviceLife = 3, operatingTime = 4, price = 21540, workplace = workplayces[1]},
                new EquipmentEntity{name="Hair Dye 'Palette'", serviceLife = 2, operatingTime = 1, price = 152108, workplace = workplayces[2]}
            };
            foreach (EquipmentEntity e in equips)
            {
                context.Equipments.Add(e);
            }
            context.SaveChanges();

            var clients = new ClientEntity[]
            {
                new ClientEntity{username = "client1", firstName = "Ash", lastName = "Maddock", stylist = stylists[0], password = "client"},
                new ClientEntity{username = "client2", firstName = "Clarck", lastName = "Nayton", stylist = stylists[1], password = "client"},
                new ClientEntity{username = "client3", firstName = "Diana", lastName = "Mennie", stylist = stylists[0], password = "client"}
            };
            foreach (ClientEntity c in clients)
            {
                context.Clients.Add(c);
            }
            context.SaveChanges();
        }
    }
}
