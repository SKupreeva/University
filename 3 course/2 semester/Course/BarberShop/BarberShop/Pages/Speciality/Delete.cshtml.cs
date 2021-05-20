using System;
using System.Threading.Tasks;
using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace BarberShop.Pages.Speciality
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
        public SpecialityEntity speciality { get; set; }
        public string ErrorMessage { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id, bool? saveChangesError = false)
        {
            if (id == null)
            {
                return NotFound();
            }

            speciality = await context.Specialities
                .AsNoTracking()
                .FirstOrDefaultAsync(m => m.id == id);

            if (speciality == null)
            {
                return NotFound();
            }

            if (saveChangesError.GetValueOrDefault())
            {
                ErrorMessage = String.Format("Удаление № {ID} невозможно. Попробуйте снова!", id);
            }

            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var spec = await context.Specialities.FindAsync(id);

            if (spec == null)
            {
                return NotFound();
            }

            try
            {
                context.Specialities.Remove(spec);
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
