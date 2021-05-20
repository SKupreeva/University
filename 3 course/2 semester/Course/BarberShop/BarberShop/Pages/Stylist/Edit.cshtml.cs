using BarberShop.Models;
using BarberShop.Models.BusinessLogic;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Stylist
{
    public class EditModel : PageModel
    {
        private readonly BarberContext context;
        private Shifrator shifrator;

        public EditModel(BarberContext appDbContext)
        {
            context = appDbContext;
            shifrator = new Shifrator();
        }

        [BindProperty]
        public StylistEntity stylist { get; set; }

        public async Task<ActionResult> OnGetAsync(int id)
        {
            stylist = await context.Stylists.FindAsync(id);
            stylist.password = shifrator.Deshifr(stylist.password);

            ViewData["Specialities"] = new SelectList(context.Specialities, "id", "name");

            if (stylist == null) return NotFound();
            return Page();
        }

        public async Task<ActionResult> OnPostAsync(int id)
        {
            var toUpdate = await context.Stylists.FindAsync(id);
            toUpdate.speciality = context.Specialities.FirstOrDefault(t => t.id == toUpdate.specialityid);
            toUpdate.password = shifrator.Shifr(toUpdate.password);
            if (toUpdate == null) return NotFound();
            if (await TryUpdateModelAsync(
                toUpdate,
                "stylist",
                s => s.username, s => s.firstName, s => s.lastName, s => s.speciality, s => s.specialityid, s => s.password))
            {
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
