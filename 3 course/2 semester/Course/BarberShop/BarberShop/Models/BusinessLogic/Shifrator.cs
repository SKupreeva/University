using System.Linq;

namespace BarberShop.Models.BusinessLogic
{
    public class Shifrator
    {
        public string Shifr(string password)
        {
            var array = string.Empty;
            foreach(var p in password.ToArray())
            {
                array += (char)(p + 3);
            }
            return array.ToString();
        }

        public string Deshifr(string password)
        {
            var array = string.Empty;
            foreach (var p in password.ToArray())
            {
                array += (char)(p - 3);
            }
            return array.ToString();
        }
    }
}
