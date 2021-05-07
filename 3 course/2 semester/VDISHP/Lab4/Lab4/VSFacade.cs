using System;

namespace Lab4
{
    class TextEditor
    {
        public void CreateCode()
        {
            Console.WriteLine("Написание кода");
        }
        public void Save()
        {
            Console.WriteLine("Сохранение кода");
        }
    }

    class Compiller
    {
        public void Compile()
        {
            Console.WriteLine("Компиляция приложения");
        }
    }

    class CLR
    {
        public void Execute()
        {
            Console.WriteLine("Выполнение приложения");
        }
        public void Finish()
        {
            Console.WriteLine("Завершение работы приложения");
        }
    }

    class VSFacade
    {
        TextEditor textEditor;
        Compiller compiller;
        CLR clr;
        public VSFacade(TextEditor te, Compiller compil, CLR clr)
        {
            textEditor = te;
            compiller = compil;
            this.clr = clr;
        }
        public void Start()
        {
            textEditor.CreateCode();
            textEditor.Save();
            compiller.Compile();
            clr.Execute();
        }
        public void Stop()
        {
            clr.Finish();
        }
    }

    class User
    {
        public void CreateApplication(VSFacade facade)
        {
            Console.WriteLine("Ход создания приложения:");
            facade.Start();
            facade.Stop();
            Console.WriteLine("\n");
        }
    }
}
