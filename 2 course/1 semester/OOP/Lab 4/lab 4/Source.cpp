#define _CRT_SECURE_NO_WARNINGS

#include <iostream>

using namespace std;

struct book
{
	int number;
	char name[256];
	int abon;
}; book b[5];

class Book
{
	int g;
public:
	Book()
	{
		for (int i = 0; i < 5; i++)
		{
			b[i].abon = 0;
			if (i < 3)
				b[i].number = 32456 + i;
			else
				b[i].number = 0;
		}
		strcpy(b[0].name, "Alice in the forest.");
		strcpy(b[1].name, "Walking dead.");
		strcpy(b[2].name, "Dark knight.");
	}
};

void show_st();

class Library
{
	int l;
public:
	int n_d = 0;
	void menu();
};

class Department :Library
{
	int n = 0;
public:
	int k = 0;
	char n_b[256];
	void menu()
	{
		cout << "____________________________________________" << endl;
		cout << "|  Детский  |  Взрослый  |  Краеведческий  |" << endl;
		cout << "____________________________________________" << endl;
		cout << "|     1     |      2     |        3        |" << endl;
		cout << "____________________________________________" << endl;
		cout << "	Выберите номер отдела библиотеки: ";
		while (!scanf_s("%d", &n_d) || (n_d < 1 || n_d > 3) || (n_d % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 3.\n";
		}
	}
	Department()
	{
		menu();
		switch (n_d)
		{
		case 1:
		{
			cout << "	Отдел детской литературы" << endl;
			cout << "    Выберите действие:" << endl;
			cout << "1. Добавить книгу." << endl;
			cout << "2. Удалить книгу." << endl;
			cout << "3. Взять книгу на абонимент." << endl;
			while (!scanf_s("%d", &n) || (n < 1 || n > 3) || (n % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3.\n";
			}
			switch (n)
			{
			case 1: add_b();
				break;
			case 2: del_b();
				break;
			case 3: b_abon();
				break;
			}
		}
		break;
		case 2:
		{
			cout << "	Отдел взрослой литературы" << endl;
			cout << "    Выберите действие:" << endl;
			cout << "1. Добавить книгу." << endl;
			cout << "2. Удалить книгу." << endl;
			cout << "3. Взять книгу на абонимент" << endl;
			while (!scanf_s("%d", &n) || (n < 1 || n > 3) || (n % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3.\n";
			}
			switch (n)
			{
			case 1: add_b();
				break;
			case 2: del_b();
				break;
			case 3: b_abon();
				break;
			}
		}
		break;
		case 3:
		{
			cout << "	Краеведческий отдел" << endl;
			cout << "    Выберите действие:" << endl;
			cout << "1. Добавить книгу." << endl;
			cout << "2. Удалить книгу." << endl;
			cout << "3. Взять книгу на абонимент" << endl;
			while (!scanf_s("%d", &n) || (n < 1 || n > 3) || (n % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3.\n";
			}
			switch (n)
			{
			case 1: add_b();
				break;
			case 2: del_b();
				break;
			case 3: b_abon();
				break;
			}
		}
		break;
		}
	}
	void add_b()
	{
		int f = 0;
		cout << "Введите номер книги: ";
		while (!scanf_s("%d", &k) || (k < 0 || k>100000000) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число.\n";
		}
		cout << "Введите название книги: ";
		rewind(stdin);
		gets_s(n_b);
		for (int i = 0; i < 5; i++)
		{
			if (b[i].number == 0)
			{
				b[i].number = k;
				strcpy(b[i].name, n_b);
				b[i].abon = 0;
				f++;
				break;
			}
		}
		if (f == 0)
		{
			cout << endl << "Ошибка. Книга не может быть добавлена." << endl << endl;
			show_st();
		}
		else
		{
		cout << endl << "	Операция прошла успешно." << endl << endl;
		show_st();
		}
	}
	void del_b()
	{
		int f = 0;
		cout << "Введите номер книги: ";
		while (!scanf_s("%d", &k) || (k < 0 || k>100000000) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число.\n";
		}
		for (int i = 0; i < 5; i++)
		{
			if (b[i].number == k)
			{
				for (int j = i; j < 5; j++)
				{
					b[j] = b[j + 1];
					if (j == 4)
					{
						b[j].number = 0;
						b[j].abon = 0;
						strcpy(b[j].name, "0");
					}
					f++;
				}
				break;
			}
		}
		if (f == 0)
		{
			cout << endl << "Ошибка. Книги с таким номером нет." << endl << endl;
			show_st();
		}
		else
		{
			cout << endl << "	Операция прошла успешно." << endl << endl;
			show_st();
		}

	}
	void b_abon()
	{
		int f = 0;
		cout << "Введите номер книги: ";
		cin >> k;
		for (int i = 0; i < 4; i++)
		{
			if (b[i].number == k)
			{
				b[i].abon = 1;
				f++;
				break;
			}
		}
		if (f == 0)
		{
			cout << endl << "Ошибка. Книги с таким номером нет." << endl << endl;
			show_st();
		}
		else
		{
			cout << endl << "	Операция прошла успешно." << endl << endl;
			show_st();
		}
	}
};

int main()
{
	setlocale (LC_ALL, "Rus");
	Book bk;
	Library l;
	Department d;
	system("pause");
}

void show_st()
{
	int k = 0;
	cout << "Желаете вывести результат?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "Выберите действие: " << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
	}
	switch (k)
	{
	case 1:
	{
		cout << "__________________________________________________________________________" << endl;
		cout << "|   Номер   |               Название             |  Выдача на абонимент* |" << endl;
		cout << "__________________________________________________________________________" << endl;
		for (int i = 0; i < 5; i++)
		{
			if (b[i].number != 0)
			{
				printf("|%11d|%36s|%23d|\n", b[i].number, b[i].name, b[i].abon);
				cout << "__________________________________________________________________________" << endl;
			}
		}
		cout << "* - в столбце <Выдача на абонимент> если значение равно 0, то книга НЕ была взята на абонемент." << endl;
		cout << "	Если значение равно 1, то книга была взята на абонемент." << endl << endl;
		int h = 0;
		cout << "   Желаете продолжить?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "Выберите действие: " << endl;
		while (!scanf_s("%d", &h) || (h < 1 || h > 2) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
		}
		switch (h)
		{
		case 1: Department();
			break;
		case 2: exit(1);
			break;
		}
	}
	break;
	case 2: 
	{
		int h = 0;
		cout << "   Желаете продолжить?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "Выберите действие: " << endl;
		while (!scanf_s("%d", &h) || (h < 1 || h > 2) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
		}
		switch (h)
		{
		case 1: Department();
			break;
		case 2: exit(1);
			break;
		}
	}
		break;
	}
}