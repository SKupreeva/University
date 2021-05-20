using BarberShop.Models;
using BarberShop.Models.BusinessLogic;
using BarberShop.Models.BusinessLogicModels;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace BarberShop
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {
            string connection = Configuration.GetConnectionString("DefaultConnection");
            services.AddDbContext<BarberContext>(options => options.UseSqlServer(connection));

            services.AddTransient<IClientService, ClientService>();
            services.AddTransient<IStylistService, StylistService>();
            services.AddTransient<IEquipmentService, EquipmentService>();
            services.AddTransient<ISpecialityService, SpecialityService>();
            services.AddTransient<IWorkplaceService, WorkplaceService>();

            services.AddRazorPages();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Error");
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapRazorPages();
            });

            using (var scope = app.ApplicationServices.CreateScope())
            {
                BarberContext context = scope.ServiceProvider.GetRequiredService<BarberContext>();
                //DbInitializing.Initialize(context);
            }
        }
    }
}
