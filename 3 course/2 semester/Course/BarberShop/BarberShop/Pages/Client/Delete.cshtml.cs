using System.Linq;
using System.Threading.Tasks;
using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;

namespace BarberShop.Pages.Client
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
        public ClientEntity client { get; set; }
        public string ErrorMessage { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id, bool? saveChangesError = false)
        {
            if (id == null)
            {
                return NotFound();
            }

            client = await context.Clients
                .AsNoTracking()
                .FirstOrDefaultAsync(m => m.id == id);
            client.stylist = context.Stylists.AsNoTracking().FirstOrDefault(s => s.id == client.stylistId);

            if (client == null)
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

            var spec = await context.Clients.FindAsync(id);

            if (spec == null)
            {
                return NotFound();
            }

            try
            {
                context.Clients.Remove(spec);
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
