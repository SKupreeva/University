using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Workplace
{
    public class EditModel : PageModel
    {
        private readonly BarberContext context;
        public int maxNumber;

        public EditModel(BarberContext appDbContext)
        {
            context = appDbContext;
            maxNumber = 0;
        }

        [BindProperty]
        public WorkplaceEntity workplace { get; set; }

        public async Task<ActionResult> OnGetAsync(int id)
        {
            workplace = await context.Workplaces.FindAsync(id);

            maxNumber = context.Workplaces.OrderByDescending(t => t.number).First().number + 1;
            ViewData["Stylists"] = new SelectList(context.Stylists, "id", "username");

            if (workplace == null) return NotFound();
            return Page();
        }

        public async Task<ActionResult> OnPostAsync(int id)
        {
            var toUpdate = await context.Workplaces.FindAsync(id);
            toUpdate.stylist = context.Stylists.FirstOrDefault(t => t.id == toUpdate.stylistId);
            if (toUpdate == null) return NotFound();
            if (await TryUpdateModelAsync(
                toUpdate,
                "workplace",
                s => s.number, s => s.stylist, s => s.stylistId))
            {
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
