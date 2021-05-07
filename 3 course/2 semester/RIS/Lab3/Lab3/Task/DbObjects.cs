using Lab3.DataAccessModels;
using System.Collections.Generic;
using System.Linq;

namespace Lab3.Task
{
    public class DbObjects
    {
        public static void Initial(AppDbContent content)
        {
            if (!content.CustomerEntity.Any())
                content.CustomerEntity.AddRange(CustomerEntities.Select(c => c.Value));

            content.SaveChanges();
        }

        private static Dictionary<string, CustomerEntity> customer;

        public static Dictionary<string, CustomerEntity> CustomerEntities
        {
            get
            {
                if (customer == null)
                {
                    var list = new CustomerEntity[]
                    {
                        new CustomerEntity
                        {
                            name = "William",
                            surname = "Gallagher",
                            firstAddress = "314 Robinson Lane",
                            secondAddress = "Englishtown, NJ 07726",
                            faxNumber = 15646584841
                        },
                        new CustomerEntity
                        {
                            name = "Nancy",
                            surname = "Taylor",
                            firstAddress = "Wilmington, DE 19805",
                            secondAddress = "5 S.Main Street",
                            faxNumber = 48478465456
                        },
                        new CustomerEntity
                        {
                            name = "Sandra",
                            surname = "Daniel",
                            firstAddress = "401 Magnetic Drive, Unit 2",
                            secondAddress = "12 rue Eugеne Hеnaff",
                            faxNumber = 49864645464
                        },
                        new CustomerEntity
                        {
                            name = "Ethelbert",
                            surname = "Terry",
                            firstAddress = "Toronto, Ontario, M3J 3H9",
                            secondAddress = "93000 Bobigny France",
                            faxNumber = 89749845455
                        },
                        new CustomerEntity
                        {
                            name = "Edward",
                            surname = "Ward",
                            firstAddress = "Trace Brooks Apt. 822",
                            secondAddress = "Murphy Canyon Suite 583",
                            faxNumber = 48949845846
                        },
                    };
                    customer = new Dictionary<string, CustomerEntity>();
                    foreach (CustomerEntity l in list)
                        customer.Add(l.name, l);
                }

                return customer;
            }
        }
    }
}
