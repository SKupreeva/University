using System;

namespace Lab4
{
    class Program
    {
        static void Main(string[] args)
        {
            VisitorFunction();
            CompositeFunction();
            FacadeFunction();
            BuilderFunction();
            FabrikaFunction();

            Console.ReadLine();
        }

        static void VisitorFunction()
        {
            Console.WriteLine("\tПосетитель:");

            var reg = new Registration();
            reg.Add(new Admin { Name = "Барбара Уоллес", Password = "82184931", AccessLevel = 8 });
            reg.Add(new Client { Name = "Федор Тряпичкин", Password = "3424131445" });
            Console.WriteLine("\tRusVisitor:");
            reg.Accept(new RusVisitor());
            Console.WriteLine("\tEngVisitor:");
            reg.Accept(new EngVisitor());
        }

        static void CompositeFunction()
        {
            Console.WriteLine("\tКомпоновщик:");

            Component fileSystem = new DirectoryComposite("Файловая система");
            Component diskC = new DirectoryComposite("Диск С");
            Component pngFile = new File("12345.png");
            Component docxFile = new File("Document.docx");
            diskC.Add(pngFile);
            diskC.Add(docxFile);
            fileSystem.Add(diskC);
            fileSystem.Print();
            Console.WriteLine();

            diskC.Remove(pngFile);
            Component docsFolder = new DirectoryComposite("Мои Документы");
            Component txtFile = new File("readme.txt");
            Component csFile = new File("Program.cs");
            docsFolder.Add(txtFile);
            docsFolder.Add(csFile);
            diskC.Add(docsFolder);

            fileSystem.Print();
            Console.WriteLine("\n");
        }

        static void FacadeFunction()
        {
            Console.WriteLine("\tФасад:");

            TextEditor textEditor = new TextEditor();
            Compiller compiller = new Compiller();
            CLR clr = new CLR();

            VSFacade ide = new VSFacade(textEditor, compiller, clr);

            User user = new User();
            user.CreateApplication(ide);
        }

        static void BuilderFunction()
        {
            Console.WriteLine("\tСтроитель:");

            Nature nature = new Nature();
            CatBuilder builder = new BlackCatBuilder();

            Cat blackCat = nature.Create(builder);
            Console.WriteLine(blackCat.ToString());

        }

        static void FabrikaFunction()
        {
            Console.WriteLine("\tФабрика + Шаблонный метод:");

            Animal cat = new Animal(new CatFactory());
            cat.Breath();
            cat.Move();

            Animal fish = new Animal(new FishFactory());
            fish.Breath();
            fish.Move();
        }
    }
}
