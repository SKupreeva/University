using System;
using System.Text;
namespace Lab4
{
    class Moustache { }

    class Tail { }

    class Paws { }

    class Color { public String FurColor { get; set; } }

    class Cat
    {
        public Moustache Moustache;
        public Tail Tail;
        public Paws Paws;
        public Color Color;

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("Состав котика:\n");
            if (Moustache != null)
                sb.Append("Усы\n");
            if (Tail != null)
                sb.Append("Хвост\n");
            if (Paws != null)
                sb.Append("Лапки\n");
            if (Color != null)
                sb.Append(Color.FurColor + " шерсть\n");
            return sb.ToString();
        }
    }

    abstract class CatBuilder
    {
        public Cat Cat { get; private set; }
        public void CreateCat()
        {
            Cat = new Cat();
        }
        public abstract void SetMoustache();
        public abstract void SetTail();
        public abstract void SetPaws();
        public abstract void SetColor();
    }

    class Nature
    {
        public Cat Create(CatBuilder catBuilder)
        {
            catBuilder.CreateCat();
            catBuilder.SetMoustache();
            catBuilder.SetTail();
            catBuilder.SetPaws();
            catBuilder.SetColor();
            return catBuilder.Cat;
        }
    }

    class BlackCatBuilder : CatBuilder
    {
        public override void SetColor()
        {
            Cat.Color = new Color { FurColor = "Черная"};
        }

        public override void SetMoustache()
        {
            Cat.Moustache = new Moustache();
        }

        public override void SetPaws()
        {
            Cat.Paws = new Paws();
        }

        public override void SetTail()
        {
            Cat.Tail = new Tail();
        }
    }
}
