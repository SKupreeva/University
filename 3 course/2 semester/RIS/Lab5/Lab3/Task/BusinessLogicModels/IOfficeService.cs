using System;
using System.Collections.Generic;

namespace Lab3.Task.BusinessLogicModels
{
    public interface IOfficeService
    {
        Office FindOfficeById(int id);
    }
}
