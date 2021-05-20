using System;
using System.Linq;
using System.Threading.Tasks;
using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace BarberShop.Pages.Equipment
{
    public class DeleteModel : PageModel
    {
        private readonly BarberContext context;
        private readonly ILogger<DeleteModel> logger;

        public DeleteModel(BarberContext _context,
                           ILogger<DeleteModel> _logger)
        {
            context = _context;
            logger = _logger;
        }

        [BindProperty]
        public EquipmentEntity equipment { get; set; }
        public string ErrorMessage { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id, bool? saveChangesError = false)
        {
            if (id == null)
            {
                return NotFound();
            }

            equipment = await context.Equipments
                .AsNoTracking()
                .FirstOrDefaultAsync(m => m.id == id);
            equipment.workplace = context.Workplaces.AsNoTracking().FirstOrDefault(s => s.id == equipment.workplaceId);

            if (equipment == null)
            {
                return NotFound();
            }

            if (saveChangesError.GetValueOrDefault())
            {
                ErrorMessage = string.Format("Удаление № {ID} невозможно. Попробуйте снова!", id);
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var spec = await context.Equipments.FindAsync(id);

            if (spec == null)
            {
                return NotFound();
            }

            try
            {
                context.Equipments.Remove(spec);
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            catch (DbUpdateException ex)
            {
                logger.LogError(ex, ErrorMessage);

                return RedirectToAction("./Delete",
                                     new { id, saveChangesError = true });
            }
        }
    }
}
