using Lab3.DataAccessModels;
using Microsoft.EntityFrameworkCore;

namespace Lab3.Task
{
    public class AppDbContent : DbContext
    {
        public AppDbContent(DbContextOptions<AppDbContent> options) : base(options) { }

        public DbSet<CustomerEntity> CustomerEntity { get; set; }
    }
}