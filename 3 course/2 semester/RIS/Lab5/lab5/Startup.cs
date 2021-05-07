using lab5.Models;
using lab5.Models.BusinessLogic;
using lab5.Models.BusinessLogicModels;
using lab5.Models.DataAccess;
using lab5.Models.DataAccessModels;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace lab5
{
    public class Startup
    {
        private IConfigurationRoot _confString;

        public Startup(IHostingEnvironment iHost)
        {
            _confString = new ConfigurationBuilder().SetBasePath(iHost.ContentRootPath).AddJsonFile("appsettings.json").Build();
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<AppDbContext>(options => options.UseSqlServer(_confString.GetConnectionString("DefaultConnection")));

            services.AddTransient<IWorkerService, WorkerService>();
            services.AddTransient<IWorkerRepository, WorkerRepository>();

            services.AddTransient<IOfficeService, OfficeService>();
            services.AddTransient<IOfficeRepository, OfficeRepository>();

            services.AddMvc();
        }
        
        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            app.UseDeveloperExceptionPage();
            app.UseStatusCodePages();
            app.UseStaticFiles();
            app.UseMvcWithDefaultRoute();

            using (var scope = app.ApplicationServices.CreateScope())
            {
                AppDbContext context = scope.ServiceProvider.GetRequiredService<AppDbContext>();
            }
        }
    }
}
