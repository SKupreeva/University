using Lab3.DataAccessModels;
using Lab3.Task.DataAccessModels;
using Microsoft.EntityFrameworkCore;

namespace Lab3.Task
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        public DbSet<CustomerEntity> CustomerEntity { get; set; }
        public DbSet<OfficeEntity> OfficeEntity { get; set; }
    }
}