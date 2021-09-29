using System;
using System.Collections.Generic;
using System.Linq;

namespace Lab7
{
    internal class MainMenu
    {
        private int choice = 0;
        private List<string> regions = new List<string>();
        private List<Ad> ads = new List<Ad>();
        private Room room = new Room();

        private string xmlFileName = "Ads.xml";

        private delegate void Message();
        private Message message;

        internal MainMenu()
        {
            try
            {
                ads = room.FromXmlToList(xmlFileName);
                foreach(var ad in ads)
                {
                    if (!regions.Contains(ad.Region))
                        regions.Add(ad.Region);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error! {e.Message}");
            }
        }

        public void ShowMenu()
        {
            choice = 0;
            Console.WriteLine("\tMain menu");
            Console.WriteLine("1. Show room ads.");
            Console.WriteLine("2. Add new room ad.");
            Console.WriteLine("3. Find room.");
            Console.WriteLine("0. Exit.");
            Console.WriteLine("Please choose menu variant:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 0 || choice > 3)
            {
                Console.WriteLine("Error! Write number from 0 to 3.");
            }
            switch (choice)
            {
                case 0: Environment.Exit(0);
                    break;
                case 1: ShowRoomAds();
                    break;
                case 2: AddNewRoom();
                    break;
                case 3: FindRoom();
                    break;
            }
        }

        private void ShowRoomAds()
        {
            try
            {
                ShowRegionTable(regions);

                choice = 0;
                Console.WriteLine("Please choose region from table below and enter it's number or enter 0 to exit:");
                while (!int.TryParse(Console.ReadLine(), out choice) || choice < 0 || choice > regions.Count)
                {
                    Console.WriteLine($"Error! Write number from 0 to {regions.Count}.");
                }
                if (choice == 0) ShowMenu();
                else WorkWithAds();
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error! {e.Message}");
            }
        }

        private void AddNewRoom()
        {
            try
            {
                Console.WriteLine("\n\n\tAdding new room\n");

                ShowRegionTable(regions);
                string address;
                int price = 0, id = 1;
                choice = 0;
                Console.WriteLine("Please choose region from table below and enter it's number:");
                while (!int.TryParse(Console.ReadLine(), out choice) || choice < 1 || choice > regions.Count)
                {
                    Console.WriteLine($"Error! Write number from 1 to {regions.Count}.");
                }

                Console.WriteLine("\nPlease eneter room's address:");
                while (string.IsNullOrEmpty(address = Console.ReadLine()))
                {
                    Console.WriteLine("Error! Write address.");
                }

                Console.WriteLine("\nPlease enter room's price:");
                while (!int.TryParse(Console.ReadLine(), out price) || price < 0 || price > 10000000)
                {
                    Console.WriteLine($"Error! Write number from 0 to 10000000.");
                }

                for(int i = 0; i < ads.Count; i++)
                {
                    if (ads.FirstOrDefault(a => a.Id == id) != null)
                        id++;
                }

                ads.Add(new Ad() {
                    Id = id,
                    Region = regions[choice - 1],
                    Address = address,
                    WatchCount = 0,
                    Price = price
                });

                if(room.FromListToXml(ads, xmlFileName))
                    message = Success;
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error! {e.Message}");
                message = Fail;
            }
            finally
            {
                message();
                ShowMenu();
            }
        }

        private void FindRoom()
        {
            Console.WriteLine("\n\n\tSearching room\n");
            ShowRegionTableForSearching();
            
            choice = 0;
            Console.WriteLine("Please choose region from table below and enter it's number:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 1 || choice > regions.Count + 1)
            {
                Console.WriteLine($"Error! Write number from 1 to {regions.Count + 1}.");
            }

            int maxPrice = 0;
            if(choice > regions.Count)
            {
                Console.WriteLine($"You choose all regions. Please enter max price:");
                while (!int.TryParse(Console.ReadLine(), out maxPrice) || maxPrice < 0 || maxPrice > 10000000)
                {
                    Console.WriteLine($"Error! Write number from 1 to 10000000.");
                }
                WorkWithMaxPriceAll(maxPrice);
            }
            else
            {
                Console.WriteLine($"You choose {regions[choice - 1]} region. Please enter max price:");
                while (!int.TryParse(Console.ReadLine(), out maxPrice) || maxPrice < 0 || maxPrice > 10000000)
                {
                    Console.WriteLine($"Error! Write number from 1 to 10000000.");
                }

                WorkWithMaxPricePartial(maxPrice);
            }
            
        }

        private void WorkWithMaxPriceAll(int maxPrice)
        {
            var selectedAds = new List<Ad>();
            int number = 0;
            Console.WriteLine("_____________________________________");
            Console.WriteLine("| № |    Region    |     Address    |");
            Console.WriteLine("_____________________________________");
            foreach (var ad in ads)
            {
                if (ad.Price < maxPrice)
                {
                    number++;
                    Console.WriteLine($"|{number,3}|{ad.Region,14}|{ad.Address,16}|");
                    Console.WriteLine("_____________________________________");
                    selectedAds.Add(ad);
                }
            }
            Console.WriteLine("\n\n\n");

            choice = 0;
            Console.WriteLine("Please choose room ad number to read more info about room or enter 0 to exit:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 0 || choice > number)
            {
                Console.WriteLine($"Error! Write number from 0 to {number}.");
            }

            if (choice == 0) ShowMenu();
            else ShowRoomInfo(ads.IndexOf(selectedAds[choice - 1]));
        }

        private void WorkWithMaxPricePartial(int maxPrice)
        {
            var selectedAds = new List<Ad>();
            int number = 0;
            Console.WriteLine("_____________________________________");
            Console.WriteLine("| № |    Region    |     Address    |");
            Console.WriteLine("_____________________________________");
            foreach (var ad in ads)
            {
                if (ad.Price < maxPrice && ad.Region == regions[choice - 1])
                {
                    number++;
                    Console.WriteLine($"|{number,3}|{ad.Region,14}|{ad.Address,16}|");
                    Console.WriteLine("_____________________________________");
                    selectedAds.Add(ad);
                }
            }
            Console.WriteLine("\n\n\n");

            choice = 0;
            Console.WriteLine("Please choose room ad number to read more info about room or enter 0 to exit:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 0 || choice > number)
            {
                Console.WriteLine($"Error! Write number from 0 to {number}.");
            }

            if (choice == 0) ShowMenu();
            else ShowRoomInfo(ads.IndexOf(selectedAds[choice - 1]));
        }

        private void WorkWithAds()
        {
            List<Ad> thisRegionAds = new List<Ad>();
            int number = 0;
            Console.WriteLine("_____________________________________");
            Console.WriteLine("| № |    Region    |     Address    |");
            Console.WriteLine("_____________________________________");
            foreach (var ad in ads)
            {
                if (ad.Region == regions[choice - 1])
                {
                    number++;
                    Console.WriteLine($"|{number,3}|{ad.Region,14}|{ad.Address,16}|");
                    Console.WriteLine("_____________________________________");
                    thisRegionAds.Add(ad);
                }
            }
            Console.WriteLine("\n\n\n");

            choice = 0;
            Console.WriteLine("Please choose room ad number to read more info about room or enter 0 to exit:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 0 || choice > number)
            {
                Console.WriteLine($"Error! Write number from 0 to {number}.");
            }

            if (choice == 0) ShowMenu();
            else ShowRoomInfo(ads.IndexOf(thisRegionAds[choice - 1]));
        }

        private void ShowRegionTable(List<string> regions)
        {
            int number = 0;
            Console.WriteLine("____________________");
            Console.WriteLine("| № |    Region    |");
            Console.WriteLine("____________________");
            foreach (var region in regions)
            {
                number++;
                Console.WriteLine($"|{number,3}|{region,14}|");
                Console.WriteLine("____________________");
            }
            Console.WriteLine("\n\n");
        }

        private void ShowRegionTableForSearching()
        {
            int number = 0;
            Console.WriteLine("____________________");
            Console.WriteLine("| № |    Region    |");
            
            Console.WriteLine("____________________");
            foreach (var region in regions)
            {
                number++;
                Console.WriteLine($"|{number,3}|{region,14}|");
                Console.WriteLine("____________________");
            }
            Console.WriteLine($"|{number + 1,3}|           All|");
            Console.WriteLine("____________________");
            Console.WriteLine("\n\n");
        }
        
        private void ShowRoomInfo(int index)
        {
            ads[index].WatchCount++;
            Console.WriteLine("_____________________________________________________________");
            Console.WriteLine("| № |    Region    |     Address    |   WatchCount  | Price |");
            Console.WriteLine("_____________________________________________________________");
            Console.WriteLine($"|{1,3}|{ads[index].Region,14}|{ads[index].Address,16}|{ads[index].WatchCount,15}|{ads[index].Price,7}|");
            Console.WriteLine("_____________________________________________________________");
            Console.WriteLine("\n\n\n");

            if (room.FromListToXml(ads, xmlFileName))
                message = Success;
            else message = Fail;
            message();

            Console.WriteLine("Do you want to save info in text file?");
            choice = 0;
            Console.WriteLine("1. Yes.");
            Console.WriteLine("2. No.");
            Console.WriteLine("Please choose menu variant:");
            while (!int.TryParse(Console.ReadLine(), out choice) || choice < 1 || choice > 2)
            {
                Console.WriteLine("Error! Write number from 1 to 2.");
            }
            switch (choice)
            {
                case 1:
                    SaveRoomInTextFile(index);
                    break;
                case 2:
                    ShowMenu();
                    break;
            }
        }

        private void SaveRoomInTextFile(int index)
        {
            try
            {
                string filename;
                Console.WriteLine("Please write file name:");
                while (string.IsNullOrEmpty(filename = Console.ReadLine()))
                {
                    Console.WriteLine("Error! Write file name.");
                }
                room.WriteRoomInfoInFile(filename, ads[index]);
                message = Success;
            }
            catch (Exception e)
            {
                Console.WriteLine($"Error! {e.Message}");
                message = Fail;
            }
            finally
            {
                message();
                ShowMenu();
            }
        }

        private void Success()
        {
            Console.WriteLine("\n----------------------------------");
            Console.WriteLine("\n\tSuccess!\n");
            Console.WriteLine("----------------------------------\n");
        }

        private void Fail()
        {
            Console.WriteLine("\n----------------------------------");
            Console.WriteLine("\n\tFailed!\n");
            Console.WriteLine("----------------------------------\n");
        }
    }
}
