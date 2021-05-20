using BarberShop.Models;
using BarberShop.Models.BusinessLogic;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Client
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
        public ClientEntity client { get; set; }

        public async Task<ActionResult> OnGetAsync(int id)
        {
            client = await context.Clients.FindAsync(id);
            client.password = shifrator.Deshifr(client.password);

            ViewData["Stylists"] = new SelectList(context.Stylists, "id", "username");

            if (client == null) return NotFound();
            return Page();
        }

        public async Task<ActionResult> OnPostAsync(int id)
        {
            var toUpdate = await context.Clients.FindAsync(id);
            toUpdate.stylist = context.Stylists.FirstOrDefault(t => t.id == toUpdate.stylistId);
            toUpdate.password = shifrator.Shifr(toUpdate.password);
            if (toUpdate == null) return NotFound();
            if (await TryUpdateModelAsync(
                toUpdate,
                "client",
                s => s.username, s => s.firstName, s => s.lastName, s => s.stylist, s => s.stylistId, s => s.password))
            {
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
