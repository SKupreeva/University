using System.Linq;
using System.Threading.Tasks;
using BarberShop.Models;
using BarberShop.Models.BusinessLogic;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace BarberShop.Pages.Client
{
    public class SignUpModel : PageModel
    {
        private readonly BarberContext context;
        private Shifrator shifrator;

        public SignUpModel(BarberContext _context)
        {
            context = _context;
            shifrator = new Shifrator();
        }

        public IActionResult OnGet()
        {
            client = new ClientEntity
            {
                username = "Username",
                firstName = "First Name",
                lastName = "Last Name",
                stylist = context.Stylists.FirstOrDefault(t => t.id == 1),
                password = "1234"
            };
            ViewData["Stylists"] = new SelectList(context.Stylists, "id", "username");
            return Page();
        }

        [BindProperty]
        public ClientEntity client { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            if (context.Clients.FirstOrDefault(t => t.username == client.username) == null)
            {
                client.password = shifrator.Shifr(client.password);
                client.stylist = context.Stylists.FirstOrDefault(t => t.id == client.stylistId);
                if (!ModelState.IsValid)
                {
                    return Page();
                }
                context.Clients.Add(client);
                await context.SaveChangesAsync();
                return RedirectToPage("/Client/Welcome", new { id = client.id }) ;
            }
            else return RedirectToPage("/Index");
        }
    }
}
