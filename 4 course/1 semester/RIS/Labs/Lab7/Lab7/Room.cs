using System;

namespace Lab7
{
    internal partial class Room : RoomBase
    {
        protected int Id { get; set; }
        protected string Region { get; set; }
        protected string Address { get; set; }
    }

    internal partial class Room : RoomBase
    {
        override protected bool TryFindIfAdsElementExist(string param)
        {
            try
            {
                if (Region == param)
                    return true;
                else return false;
            }
            catch(Exception e)
            {
                Console.WriteLine($"Error: {e.Message}");
                return false;
            }
        }
    }
}
