using BarberShop.Models.Repository;
using Microsoft.EntityFrameworkCore;

namespace BarberShop.Models
{
    public class BarberContext : DbContext
    {
        public BarberContext(DbContextOptions<BarberContext> options) : base(options) { Database.EnsureCreated(); }

        public DbSet<ClientEntity> Clients { get; set; }
        public DbSet<StylistEntity> Stylists { get; set; }
        public DbSet<SpecialityEntity> Specialities { get; set; }
        public DbSet<WorkplaceEntity> Workplaces { get; set; }
        public DbSet<EquipmentEntity> Equipments { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ClientEntity>().ToTable("Client");
            modelBuilder.Entity<StylistEntity>().ToTable("Stylist");
            modelBuilder.Entity<SpecialityEntity>().ToTable("Speciality");
            modelBuilder.Entity<WorkplaceEntity>().ToTable("Workplace");
            modelBuilder.Entity<EquipmentEntity>().ToTable("Equipment");
        }
    }
}
