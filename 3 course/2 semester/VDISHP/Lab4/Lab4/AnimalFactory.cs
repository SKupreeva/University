using System;
using System.Collections.Generic;

namespace Lab4
{
    abstract class PrintingInfo
    {
        public abstract void PrintInfo();
    }

    abstract class Movement: PrintingInfo
    {
        public sealed override void PrintInfo()
        {
            Info();
            Move();
        }
        private void Info() { Console.WriteLine("Было произведено движение..."); }
        public abstract void Move();
    }

    class Swim : Movement
    {
        public override void Move()
        {
            Console.WriteLine("Рыбка плавает!\n");
        }
    }

    class Walk : Movement
    {
        public override void Move()
        {
            Console.WriteLine("Котик ходит лапками!\n");
        }
    }

    abstract class Breathing: PrintingInfo
    {
        public sealed override void PrintInfo()
        {
            Info();
            Breath();
        }
        private void Info() { Console.WriteLine("Было произведено дыхание..."); }
        public abstract void Breath();
    }

    class Lungs : Breathing
    {
        public override void Breath()
        {
            Console.WriteLine("Котик дышит легкими!\n");
        }
    }

    class Gills : Breathing
    {
        public override void Breath()
        {
            Console.WriteLine("Рыбка дышит жабрами!\n");
        }
    }

    abstract class AnimalFactory
    {
        public abstract Movement CreateMovement();
        public abstract Breathing CreateBreathing();
    }

    class CatFactory : AnimalFactory
    {
        public override Breathing CreateBreathing()
        {
            return new Lungs();
        }

        public override Movement CreateMovement()
        {
            return new Walk();
        }
    }

    class FishFactory : AnimalFactory
    {
        public override Breathing CreateBreathing()
        {
            return new Gills();
        }

        public override Movement CreateMovement()
        {
            return new Swim();
        }
    }

    class Animal
    {
        private Movement movement;
        private Breathing breathing;

        public Animal(AnimalFactory factory)
        {
            movement = factory.CreateMovement();
            breathing = factory.CreateBreathing();
        }

        public void Move()
        {
            movement.PrintInfo();
        }

        public void Breath()
        {
            breathing.PrintInfo();
        }
    }
}
