using Lab3.DataAccessModels;
using Lab3.Task.DataAccessModels;
using System.Collections.Generic;
using System.Linq;

namespace Lab3.Task
{
    public class DbObjects
    {
        public static void Initial(AppDbContext context)
        {
            if (!context.CustomerEntity.Any())
                context.CustomerEntity.AddRange(CustomerEntities.Select(c => c.Value));

            if (!context.OfficeEntity.Any())
              context.OfficeEntity.AddRange(OfficeEntities.Select(c => c.Value));

            context.OfficeEntity.UpdateRange();
            context.CustomerEntity.UpdateRange();
            //context.SaveChanges();
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
                            officeId = 1
                        },
                        new CustomerEntity
                        {
                            name = "Nancy",
                            surname = "Taylor",
                            firstAddress = "Wilmington, DE 19805",
                            secondAddress = "5 S.Main Street",
                            officeId = 1
                        },
                        new CustomerEntity
                        {
                            name = "Sandra",
                            surname = "Daniel",
                            firstAddress = "401 Magnetic Drive, Unit 2",
                            secondAddress = "12 rue Eugеne Hеnaff",
                            officeId = 2
                        },
                        new CustomerEntity
                        {
                            name = "Ethelbert",
                            surname = "Terry",
                            firstAddress = "Toronto, Ontario, M3J 3H9",
                            secondAddress = "93000 Bobigny France",
                            officeId = 2
                        },
                        new CustomerEntity
                        {
                            name = "Edward",
                            surname = "Ward",
                            firstAddress = "Trace Brooks Apt. 822",
                            secondAddress = "Murphy Canyon Suite 583",
                            officeId = 3
                        },
                    };
                    customer = new Dictionary<string, CustomerEntity>();
                    foreach (CustomerEntity l in list)
                        customer.Add(l.name, l);
                }

                return customer;
            }
        }

        private static Dictionary<string, OfficeEntity> office;
        public static Dictionary<string, OfficeEntity> OfficeEntities
        {
            get
            {
                if (office == null)
                {
                    var list = new OfficeEntity[]
                    {
                        new OfficeEntity
                        {
                            name = "Green Snake"
                        },
                        new OfficeEntity
                        {
                            name = "Gold Apple"
                        },
                        new OfficeEntity
                        {
                            name = "Black Star"
                        },
                        new OfficeEntity
                        {
                            name = "Blue Lagoon"
                        }
                    };
                    office = new Dictionary<string, OfficeEntity>();
                    foreach (OfficeEntity l in list)
                        office.Add(l.name, l);
                }

                return office;
            }
        }
    }
}
