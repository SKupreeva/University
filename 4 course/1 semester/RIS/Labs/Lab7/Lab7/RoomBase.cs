using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Xml.Linq;

namespace Lab7
{
    internal class RoomBase
    {
        protected string adsElement = "ads";
        protected string adElement = "ad";
        protected string idElement = "id";
        protected string regionElement = "region";
        protected string addressElement = "address";
        protected string watchCountElement = "watchCount";
        protected string priceElement = "price";

        private string projectPath = "D:/универ/University/4 course/1 semester/RIS/Labs/Lab7/Lab7/";

        internal List<Ad> FromXmlToList(string filename)
        {
            try
            {
                var filePath = projectPath + filename;
                XDocument xdoc = XDocument.Load(filePath);
                var items = from xe in xdoc.Element(adsElement).Elements(adElement)
                            select new Ad
                            {
                                Id = int.Parse(xe.Element(idElement).Value),
                                Region = xe.Element(regionElement).Value,
                                Address = xe.Element(addressElement).Value,
                                WatchCount = int.Parse(xe.Element(watchCountElement).Value),
                                Price = int.Parse(xe.Element(priceElement).Value)
                            };
                return items.ToList();
            }
            catch(Exception e)
            {
                Console.WriteLine($"Error: {e.Message}");
                return null;
            }
        }

        internal bool FromListToXml(List<Ad> ads, string filename)
        {
            try
            {
                var filePath = projectPath + filename;
                XDocument xdoc = XDocument.Load(filePath);
                XElement root = xdoc.Element(adsElement);

                foreach(var ad in ads)
                {
                    var flag = 0;
                    foreach (XElement xe in root.Elements(adElement).ToList())
                    {
                        if (ad.Id == int.Parse(xe.Element(idElement).Value))
                        {
                            flag++;
                            xe.Element(regionElement).Value = ad.Region;
                            xe.Element(addressElement).Value = ad.Address;
                            xe.Element(watchCountElement).Value = ad.WatchCount.ToString();
                            xe.Element(priceElement).Value = ad.Price.ToString();
                            break;
                        }
                    }
                    if (flag == 0)
                    {
                        var xe = new XElement(adElement);
                        xe.Add(
                                new XElement(idElement, ad.Id),
                                new XElement(regionElement, ad.Region),
                                new XElement(addressElement, ad.Address),
                                new XElement(watchCountElement, ad.WatchCount),
                                new XElement(priceElement, ad.Price)
                            );
                        root.Add(xe);
                    }
                }
                xdoc.Save(filePath);
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error: {e.Message}");
                return false;
            }
        }

        internal bool WriteRoomInfoInFile(string filename, Ad room)
        {
            try
            {
                string filepath = projectPath + filename + ".txt";

                string roomFile = "Room id: " + room.Id + " Region: " + room.Region + " Address: " + room.Address + " Price: " + room.Price;
                using (StreamWriter writer = new StreamWriter(filepath))
                {
                    writer.WriteLine(roomFile);
                }

                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error: {e.Message}");
                return false;
            }
        }

        protected virtual bool TryFindIfAdsElementExist(string param)
        {
            try
            {
                if (adsElement == param)
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
