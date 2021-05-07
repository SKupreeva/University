using Lab3.BusinessLogicModels;
using Lab3.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace Lab3.Controllers
{
    public class HomeController : Controller
    {
        private ICustomerService customerService;

        public HomeController(ICustomerService _customerService)
        {
            customerService = _customerService;
        }

        public ActionResult Index(int customerId)
        {
            var homeList = new HomeViewModel
            {
                Customer = customerService.FindCustomerById(customerId)
            };

            return View(homeList);
        }
    }
}
