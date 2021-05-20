using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Equipment
{
    public class EditModel : PageModel
    {
        private readonly BarberContext context;

        public EditModel(BarberContext appDbContext)
        {
            context = appDbContext;
        }

        [BindProperty]
        public EquipmentEntity equipment { get; set; }

        public async Task<ActionResult> OnGetAsync(int id)
        {
            equipment = await context.Equipments.FindAsync(id);

            ViewData["Workplaces"] = new SelectList(context.Workplaces, "id", "number");

            if (equipment == null) return NotFound();
            return Page();
        }

        public async Task<ActionResult> OnPostAsync(int id)
        {
            var toUpdate = await context.Equipments.FindAsync(id);
            toUpdate.workplace = context.Workplaces.FirstOrDefault(t => t.id == toUpdate.workplaceId);
            if (toUpdate == null) return NotFound();
            if (await TryUpdateModelAsync(
                toUpdate,
                "equipment",
                s => s.name, s => s.serviceLife, s => s.operatingTime, s => s.price, s => s.workplaceId, s => s.workplace))
            {
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }

    }
}
