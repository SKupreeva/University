using System;
using System.Collections.Generic;

namespace Lab4
{
    interface IVisitor
    {
        void VisitAdminAcc(Admin acc);
        void VisitClientAcc(Client acc);
    }
    
    class RusVisitor : IVisitor
    {
        public void VisitAdminAcc(Admin acc)
        {
            string result = $"Имя администратора - {acc.Name.ToUpper()}, уровень доступа = {acc.AccessLevel}, длина пароля = {acc.Password.Length}.";
            Console.WriteLine(result);
        }

        public void VisitClientAcc(Client acc)
        {
            string result = $"Имя клиента - {acc.Name.ToUpper()}, длина пароля = {acc.Password.Length}.\n";
            Console.WriteLine(result);
        }
    }
    
    class EngVisitor : IVisitor
    {
        public void VisitAdminAcc(Admin acc)
        {
            string result = $"Admin name - {acc.Name.ToUpper()}, access level = {acc.AccessLevel}, password length = {acc.Password.Length}.";
            Console.WriteLine(result);
        }

        public void VisitClientAcc(Client acc)
        {
            string result = $"Client name - {acc.Name.ToUpper()}, password length = {acc.Password.Length}.\n";
            Console.WriteLine(result);
        }
    }

    class Registration
    {
        List<IAccount> accounts = new List<IAccount>();
        public void Add(IAccount acc)
        {
            accounts.Add(acc);
        }
        public void Remove(IAccount acc)
        {
            accounts.Remove(acc);
        }
        public void Accept(IVisitor visitor)
        {
            foreach (IAccount acc in accounts)
                acc.Accept(visitor);
        }
    }

    interface IAccount
    {
        void Accept(IVisitor visitor);
    }

    class Admin : IAccount
    {
        public string Name { get; set; }
        public int AccessLevel { get; set; }
        public string Password { get; set; }

        public void Accept(IVisitor visitor)
        {
            visitor.VisitAdminAcc(this);
        }
    }

    class Client : IAccount
    {
        public string Name { get; set; }
        public string Password { get; set; }

        public void Accept(IVisitor visitor)
        {
            visitor.VisitClientAcc(this);
        }
    }
}
