using System.Linq;
using System.Threading.Tasks;
using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace BarberShop.Pages.Stylist
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
        public StylistEntity stylist { get; set; }
        public string ErrorMessage { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id, bool? saveChangesError = false)
        {
            if (id == null)
            {
                return NotFound();
            }

            stylist = await context.Stylists
                .AsNoTracking()
                .FirstOrDefaultAsync(m => m.id == id);
            stylist.speciality = context.Specialities.AsNoTracking().FirstOrDefault(s => s.id == stylist.specialityid);

            if (stylist == null)
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

            var spec = await context.Stylists.FindAsync(id);

            if (spec == null)
            {
                return NotFound();
            }

            try
            {
                context.Stylists.Remove(spec);
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
