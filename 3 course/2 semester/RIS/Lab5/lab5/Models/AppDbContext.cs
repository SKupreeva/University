using lab5.Models.DataAccessModels;
using Microsoft.EntityFrameworkCore;

namespace lab5.Models
{
    public class AppDbContext: DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

        public DbSet<WorkerEntity> WorkerEntity { get; set; }
        public DbSet<OfficeEntity> OfficeEntity { get; set; }
    }
}
