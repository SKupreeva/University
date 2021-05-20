using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System.Threading.Tasks;

namespace BarberShop.Pages.Workplace
{
    public class CreateModel : PageModel
    {
        private readonly BarberContext context;

        public int maxNumber;

        public CreateModel(BarberContext _context)
        {
            context = _context;
            maxNumber = 0;
        }

        public IActionResult OnGet()
        {
            maxNumber = context.Workplaces.OrderByDescending(t => t.number).First().number + 1;
            workplace = new WorkplaceEntity
            {
                number = maxNumber,
                stylist = context.Stylists.FirstOrDefault(t => t.id == 1)
            };
            ViewData["Stylists"] = new SelectList(context.Stylists, "id", "username");
            return Page();
        }

        [BindProperty]
        public WorkplaceEntity workplace { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            if (context.Workplaces.FirstOrDefault(t => t.number == workplace.number) == null)
            {
                workplace.stylist = context.Stylists.FirstOrDefault(t => t.id == workplace.stylistId);
                if (!ModelState.IsValid)
                {
                    return Page();
                }
                context.Workplaces.Add(workplace);
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }
            else return Page();
        }
    }
}
