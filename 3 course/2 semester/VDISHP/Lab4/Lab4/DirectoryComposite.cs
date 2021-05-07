using System;
using System.Collections.Generic;

namespace Lab4
{
    abstract class Component
    {
        protected string name;

        public Component(string name)
        {
            this.name = name;
        }

        public virtual void Add(Component component) { }

        public virtual void Remove(Component component) { }

        public virtual void Print()
        {
            Console.WriteLine(name);
        }
    }

    class DirectoryComposite : Component
    {
        private List<Component> components = new List<Component>();

        public DirectoryComposite(string name): base(name)
        {
        }

        public override void Add(Component component)
        {
            components.Add(component);
        }

        public override void Remove(Component component)
        {
            components.Remove(component);
        }

        public override void Print()
        {
            Console.WriteLine("Узел " + name);
            Console.WriteLine("Подузлы:");
            for (int i = 0; i < components.Count; i++)
            {
                components[i].Print();
            }
        }
    }

    class File : Component
    {
        public File(string name)
                : base(name)
        { }
    }
}
