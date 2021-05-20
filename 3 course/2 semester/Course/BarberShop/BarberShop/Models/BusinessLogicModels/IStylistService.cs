using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace BarberShop.Models.BusinessLogicModels
{
    public interface IStylistService
    {
        IEnumerable<StylistEntity> GetAllStylists { get; }
        bool IfStylistIsAlreadyExist(string login);
        StylistEntity FindStylistById(int id);
        StylistEntity FindStylistByUsername(string username);
        bool AddStylistInDb(StylistEntity stylist);
        bool DeleteStylistFromBd(StylistEntity stylist);
        bool EditStylistInBd(StylistEntity stylist);
        bool SignIn(string username, string password);
        List<StylistEntity> GetStylistList { get; }
        public SelectList stylists { get; }
    }
}
