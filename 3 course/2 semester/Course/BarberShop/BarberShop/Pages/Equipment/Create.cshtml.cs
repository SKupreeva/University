using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Equipment
{
    public class CreateModel : PageModel
    {
        private readonly BarberContext context;

        public CreateModel(BarberContext _context)
        {
            context = _context;
        }

        public IActionResult OnGet()
        {
            equipment = new EquipmentEntity
            {
                name = "Name",
                serviceLife = 3,
                operatingTime = 2,
                price = 23445,
                workplace = context.Workplaces.FirstOrDefault(t => t.id == 1)
            };
            ViewData["Workplaces"] = new SelectList(context.Workplaces, "id", "number");
            return Page();
        }

        [BindProperty]
        public EquipmentEntity equipment { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            if (context.Equipments.FirstOrDefault(t => t.name == equipment.name) == null)
            {
                equipment.workplace = context.Workplaces.FirstOrDefault(t => t.id == equipment.workplaceId);
                if (!ModelState.IsValid)
                {
                    return Page();
                }
                context.Equipments.Add(equipment);
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
