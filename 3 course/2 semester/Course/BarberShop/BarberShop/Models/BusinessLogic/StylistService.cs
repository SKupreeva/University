using BarberShop.Models.BusinessLogicModels;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class StylistService : IStylistService
    {
        private readonly BarberContext context;
        public SelectList stylists;

        public StylistService(BarberContext appDbContext)
        {
            context = appDbContext;
            stylists = new SelectList(context.Stylists, nameof(StylistEntity.id), nameof(StylistEntity.username));
        }

        public IEnumerable<StylistEntity> GetAllStylists => context.Stylists;

        public List<StylistEntity> GetStylistList => context.Stylists.ToList();

        SelectList IStylistService.stylists => throw new System.NotImplementedException();

        public bool AddStylistInDb(StylistEntity stylist)
        {
            if (FindStylistById(stylist.id) == null)
            {
                context.Stylists.Add(stylist);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool DeleteStylistFromBd(StylistEntity stylist)
        {
            if (FindStylistById(stylist.id) != null)
            {
                context.Stylists.Remove(stylist);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public bool EditStylistInBd(StylistEntity stylist)
        {
            if (FindStylistById(stylist.id) != null)
            {
                context.Stylists.Update(stylist);
                context.SaveChanges();
                return true;
            }
            else return false;
        }

        public StylistEntity FindStylistById(int id) => context.Stylists.AsNoTracking().FirstOrDefault(s => s.id == id);

        public StylistEntity FindStylistByUsername(string username) => context.Stylists.AsNoTracking().FirstOrDefault(s => s.username == username);

        public bool IfStylistIsAlreadyExist(string login)
        {
            if (FindStylistByUsername(login) != null) return true;
            else return false;
        }

        public bool SignIn(string username, string password)
        {
            if (FindStylistByUsername(username) != null)
            {
                var stylist = context.Stylists.FirstOrDefault(s => s.username == username);
                if (stylist.password == password) return true;
                else return false;
            }
            else return false;
        }
    }
}
