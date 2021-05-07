using Microsoft.AspNetCore.Mvc;
using lab5.Models.BusinessLogicModels;
using lab5.ViewsModels;
using Microsoft.AspNetCore.Mvc.Rendering;
using lab5.Models.DataAccessModels;

namespace lab5.Controllers
{
    public class HomeController : Controller
    {
        private IWorkerService workerService;
        private IOfficeService officeService;

        public HomeController(IWorkerService _workerService, IOfficeService _officeService)
        {
            workerService = _workerService;
            officeService = _officeService;
        }

        /*public IActionResult Index()
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            return View(homeModel);
        }*/

        public ActionResult Index(string surname)
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            ViewBag.Surname = surname;
            return View(homeModel);
        }

        /*public ActionResult OfficeDetails()
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            return View(homeModel);
        }*/

        public ActionResult OfficeDetails(string name)
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            ViewBag.Name = name;
            return View(homeModel);
        }

        [HttpGet]
        public ActionResult Create()
        {
            SelectList offices = new SelectList(officeService.GetOfficeList, "id", "name");
            ViewBag.Offices = offices;
            return View();
        }

        [HttpPost]
        public ActionResult Create(WorkerEntity worker)
        {
            workerService.AddWorkerInDb(worker);
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult Edit(int id)
        {
            WorkerEntity worker = workerService.FindWorkerById(id);
            if (worker != null)
            {
                SelectList offices = new SelectList(officeService.GetOfficeList, "id", "name", worker.officeId);
                ViewBag.Offices = offices;
                return View(worker);
            }
            return RedirectToAction("Index");
        }

        [HttpPost]
        public ActionResult Edit(WorkerEntity worker)
        {
            workerService.ModifyWorkerInDb(worker);
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult Delete(int id)
        {
            WorkerEntity worker = workerService.FindWorkerById(id);
            if (worker != null)
            {
                var office = officeService.FindOfficeById((int)worker.officeId);
                ViewBag.Office = office.name;
                return View(worker);
            }
            return RedirectToAction("Index");
        }

        [HttpPost]
        public ActionResult Delete(WorkerEntity worker)
        {
            workerService.DeleteWorkerInDb(worker);
            return RedirectToAction("Index");
        }

        [HttpGet]
        public ActionResult CreateOffice()
        {
            return View();
        }

        [HttpPost]
        public ActionResult CreateOffice(OfficeEntity office)
        {
            officeService.AddOfficeInDb(office);
            return RedirectToAction("OfficeDetails");
        }

        [HttpGet]
        public ActionResult EditOffice(int id)
        {
            OfficeEntity office = officeService.FindOfficeById(id);
            if (office != null)
            {
                return View(office);
            }
            return RedirectToAction("OfficeDetails");
        }

        [HttpPost]
        public ActionResult EditOffice(OfficeEntity office)
        {
            officeService.ModifyOfficeInDb(office);
            return RedirectToAction("OfficeDetails");
        }

        [HttpGet]
        public ActionResult DeleteOffice(int id)
        {
            OfficeEntity office = officeService.FindOfficeById(id);
            if (office != null)
            {
                var workers = workerService.GetWorkerList;
                ViewBag.Workers = workers;
                return View(office);
            }
            return RedirectToAction("OfficeDetails");
        }

        [HttpPost]
        public ActionResult DeleteOffice(OfficeEntity office)
        {
            officeService.DeleteOfficeInDb(office);
            return RedirectToAction("OfficeDetails");
        }

        public ActionResult AllWorkers()
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            return View(homeModel);
        }

        public ActionResult AllOffices()
        {
            var homeModel = new HomeViewModel(workerService.GetWorkerList, officeService.GetOfficeList);
            return View(homeModel);
        }
    }
}
