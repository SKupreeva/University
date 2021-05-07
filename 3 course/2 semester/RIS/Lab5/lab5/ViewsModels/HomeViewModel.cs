using lab5.Models.BusinessLogicModels;
using System.Collections.Generic;

namespace lab5.ViewsModels
{
    public class HomeViewModel
    {
        public List<Worker> workers { get; set; }
        public List<Office> offices { get; set; }

        public HomeViewModel(List<Worker> workers, List<Office> offices)
        {
            this.workers = workers;
            this.offices = offices;
        }
    }
}
