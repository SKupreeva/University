using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Threading.Tasks;

namespace BarberShop.Pages.Speciality
{
    public class EditModel : PageModel
    {
        private readonly BarberContext context;

        public EditModel(BarberContext appDbContext)
        {
            context = appDbContext;
        }

        [BindProperty]
        public SpecialityEntity speciality { get; set; }

        public async Task<ActionResult> OnGetAsync(int id)
        {
            speciality = await context.Specialities.FindAsync(id);
            if (speciality == null) return NotFound();
            return Page();
        }

        public async Task<ActionResult> OnPostAsync(int id)
        {
            var toUpdate = await context.Specialities.FindAsync(id);
            if (toUpdate == null) return NotFound();
            if (await TryUpdateModelAsync(
                toUpdate,
                "speciality",
                s => s.name, s => s.education, s => s.experience))
            {
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            } 
            else return Page();
        }
    }
}
