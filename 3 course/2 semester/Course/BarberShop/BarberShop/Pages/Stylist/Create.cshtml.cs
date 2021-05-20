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
    public class CreateModel : PageModel
    {
        private readonly BarberContext context;
        private Shifrator shifrator;

        public CreateModel(BarberContext _context)
        {
            context = _context;
            shifrator = new Shifrator();
        }

        public IActionResult OnGet()
        {
            stylist = new StylistEntity
            {
                username = "Username",
                firstName = "First Name",
                lastName = "Last Name",
                speciality = context.Specialities.FirstOrDefault(t => t.id == 1),
                password = "1234"
            };
            ViewData["Specialities"] = new SelectList(context.Specialities, "id", "name");
            return Page();
        }

        [BindProperty]
        public StylistEntity stylist { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            if (context.Stylists.FirstOrDefault(t => t.username == stylist.username) == null)
            {
                stylist.password = shifrator.Shifr(stylist.password);
                stylist.speciality = context.Specialities.FirstOrDefault(t => t.id == stylist.specialityid);
                if (!ModelState.IsValid)
                {
                    return Page();
                }
                context.Stylists.Add(stylist);
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
