using Lab3.BusinessLogic;
using Lab3.BusinessLogicModels;
using Lab3.DataAccess;
using Lab3.DataAccessModels;
using Lab3.Task;
using Lab3.Task.BusinessLogic;
using Lab3.Task.BusinessLogicModels;
using Lab3.Task.DataAccess;
using Lab3.Task.DataAccessModels;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace Lab3
{
    public class Startup
    {
        private IConfigurationRoot _confString;

        public Startup(IHostingEnvironment iHost)
        {
            _confString = new ConfigurationBuilder().SetBasePath(iHost.ContentRootPath).AddJsonFile("db_settings.json").Build();
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<AppDbContext>(options => options.UseSqlServer(_confString.GetConnectionString("DefaultConnection")));
            services.AddTransient<ICustomerService, CustomerService>();
            services.AddTransient<ICustomerRepository, CustomerRepository>();

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
                DbObjects.Initial(context);
            }
        }
    }
}
