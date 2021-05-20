using BarberShop.Models;
using BarberShop.Models.Repository;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System.Threading.Tasks;

namespace BarberShop.Pages.Speciality
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
            speciality = new SpecialityEntity
            {
                name="Стилист",
                education = "Высшее",
                experience = 2
            };
            return Page();
        }

        [BindProperty]
        public SpecialityEntity speciality { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            var emptySpeciality = new SpecialityEntity();

            if (await TryUpdateModelAsync<SpecialityEntity>(
                emptySpeciality,
                "speciality",
                s => s.name, s => s.education, s => s.experience))
            {
                context.Specialities.Add(emptySpeciality);
                await context.SaveChangesAsync();
                return RedirectToPage("./Main");
            }

            return Page();
        }
    }
}
