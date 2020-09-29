#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <fstream>
#include <sstream>
#include <windows.h>
#include <stdio.h>
using namespace std;

namespace My_Space
{
	template <class T>
	class Model
	{
		T n;
		static int k;
	public:
		static void show(T n)
		{
			k = n;
			cout << "	Кол-во = " << k << endl;
		}
		static void show(T n, int m, int v)
		{
			k = n;
			if (k > m)
			{
				if (k < v)
					cout << "	Значение статической переменной > " << m << ", но < " << v << "." << endl;
				else
					cout << "	Значение статической переменной > " << m << "." << endl;
			}
		}
		void operator++(int)
		{
			k++;
		}
	};
}
using namespace My_Space;

template<class T>
void quickSortR(T* a, long N)
{
	long i = 0, j = N - 1;
	T temp, p;
	p = a[N >> 1];
	do {
		while (a[i] < p) i++;
		while (a[j] > p) j--;

		if (i <= j) {
			temp = a[i]; a[i] = a[j]; a[j] = temp;
			i++; j--;
		}
	} while (i <= j);
	if (j > 0) quickSortR(a, j);
	if (N > i) quickSortR(a + i, N - i);
}

void shifr(char *s);
void deshifr(char *s);

int check_str(char str[]);
int check_year(int g);
int check_day(int d, int m);

void run_hf();
void run_cf();
void run_dr();
void run_person();
void run_order();

void show_us();
void add_us();
void del_us();
void user();
void admin();

void menu_del_n_p();

void menu_us();
void menu_ad();
void menu_1();
void menu_upr();
void menu_del();
void menu_mode();
void menu_read_ad();
void menu_read_us();

struct data
{
	int day;
	int month;
	int year;
};
struct time
{
	int hour;
	int min;
};
struct adres 
{
	char city[20];
	char street[25];
	int num_h;
};
int l, q = 0, mm = 0;

class Food
{
public:
	virtual void show(int k) = 0;
	virtual void get() = 0;
};

class Hot_Food : public Food
{
public:
	char name[20];
	char av[5];
    int price;
	void show(int k);
	void get();
	void order_hf();
	void sort_hf();
	void del_hf();
	void top_3();
};

class Cold_Food : public Food
{
public:
	char name[20];
	char av[5];
	int price;
	void show(int k);
	void get();
	void order_cf();
	void sort_p_cf();
	void poisk_cf();
	void filter_g_k();
	void del_cf();
};

class Drinks : public Food
{
public:
	char name[20];
	char av[5];
	int price;
	void show(int k);
	void get();
	void order_dr();
	void sort_dr();
	void poisk_dr();
	void del_dr();
};

class Person
{
public:
	long long tel_num;
	int ID_P;
	char fam[20];
	char name[15];
	char f_name[20];
	struct data db;
	void show_p(int k);
	void get_p();
	void del_id_p();
	void redact_ID();
	void redact_n();
};

class Order
{
public:
	int num_o;
	char status[10];
	struct adres ad;
	struct data dfo;
	struct time t;
	void show_o(int k);
	void get_o();
	friend void menu_make_o();
	void filter_st_o();
};

Food *f;
Hot_Food HF[255];
Cold_Food CF[255];
Drinks DR[255];
Person P[255];
Order O[255];

void main()
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	menu_1();
	system("pause");
}

void Cold_Food :: show(int k)
{
	run_cf();
	cout << "	Перечень холодных блюд:" << endl;
	cout << "_____________________________________________________" << endl;
	cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
	cout << "_____________________________________________________" << endl;
	for (int i = 0; i < k; i++)
	{
		printf("|%3d|%20s|%16d|%9s|\n", i + 1, CF[i].name, CF[i].price, CF[i].av);
		cout << "_____________________________________________________" << endl;
	}
	cout << endl;
}

void Cold_Food :: get()
{
	system("cls");
	ofstream out;
	out.open("Cold_Food.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << endl << "	Введите название холодного блюда: ";
		cin >> name;
		int f = check_str(name);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> name;
			f = check_str(name);
		}
		out << name;
		cout << endl << "	Введите цену на данный продукт(в у. е.): ";
		while (!scanf_s("%d", &price) || (price < 0 || price > 100000) || (price % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 0 до 100000." << endl;
		}
		out << " " << price;
		cout << endl;
		int h = 0;
		cout << endl << "	Выберите наличие товара: " << endl;
		cout << "1. Есть." << endl;
		cout << "2. Нет." << endl;
		while (!scanf_s("%d", &h) || (h < 1 || h > 2) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		switch (h)
		{
		case 1: strcpy(av, "Есть");
			break;
		case 2: strcpy(av, "Нет");
			break;
		}
		out << " " << av << endl;
		cout << endl << "	Холодное блюдо было успешно добавлено." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Hot_Food :: show(int k)
{
	run_hf();
	cout << "	Перечень горячих блюд:" << endl;
	cout << "_____________________________________________________" << endl;
	cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
	cout << "_____________________________________________________" << endl;
	for (int i = 0; i < k; i++)
	{
		printf("|%3d|%20s|%16d|%9s|\n", i + 1, HF[i].name, HF[i].price, HF[i].av);
		cout << "_____________________________________________________" << endl;
	}
	cout << endl;
}

void Hot_Food :: get()
{
	system("cls");
	ofstream out;
	out.open("Hot_Food.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << endl << "	Введите название горячего блюда: ";
		cin >> name;
		int f = check_str(name);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> name;
			f = check_str(name);
		}
		out << name;
		cout << endl << "	Введите цену на данный продукт(в у. е.): ";
		while (!scanf_s("%d", &price) || (price < 0 || price > 100000) || (price % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 0 до 100000." << endl;
		}
		out << " " << price;
		int h = 0;
		cout << endl << "	Выберите наличие товара: " << endl;
		cout << "1. Есть." << endl;
		cout << "2. Нет." << endl;
		while (!scanf_s("%d", &h) || (h < 1 || h > 2) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		switch (h)
		{
		case 1: strcpy(av, "Есть");
			break;
		case 2: strcpy(av, "Нет");
			break;
		}
		out << " " << av << endl;
		cout << endl << "	Горячее блюдо было успешно добавлено." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Drinks :: show(int k)
{
	run_dr();
	cout << "	Перечень напитков:" << endl;
	cout << "_____________________________________________________" << endl;
	cout << "| № |  Название напитка  |  Цена(в у. е.) | Наличие |" << endl;
	cout << "_____________________________________________________" << endl;
	for (int i = 0; i < k; i++)
	{
		printf("|%3d|%20s|%16d|%9s|\n", i + 1, DR[i].name, DR[i].price, DR[i].av);
		cout << "_____________________________________________________" << endl;
	}
	cout << endl;
}

void Drinks :: get()
{
	system("cls");
	ofstream out;
	out.open("Drinks.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << endl << "	Введите название напитка: ";
		cin >> name;
		int f = check_str(name);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> name;
			f = check_str(name);
		}
		out << name;
		cout << endl << "	Введите цену на данный продукт(в у. е.): ";
		while (!scanf_s("%d", &price) || (price < 0 || price > 100000) || (price % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 0 до 100000." << endl;
		}
		out << " " << price;
		int h = 0;
		cout << endl << "	Выберите наличие товара: " << endl;
		cout << "1. Есть." << endl;
		cout << "2. Нет." << endl;
		while (!scanf_s("%d", &h) || (h < 1 || h > 2) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		switch (h)
		{
		case 1: strcpy(av, "Есть");
			break;
		case 2: strcpy(av, "Нет");
			break;
		}
		out << " " << av << endl;
		cout << endl << "	Напиток был успешно добавлено." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Person::show_p(int k)
{
	run_person();
	cout << "	Информация о клиентах:" << endl;
	cout << "____________________________________________________________________________________________________________" << endl;
	cout << "| № |    ID клиента   |      Фамилия      |      Имя     |    Отчество    | Номер телефона | Дата рождения |" << endl;
	cout << "____________________________________________________________________________________________________________" << endl;
	for (int i = 0; i < k; i++)
	{
		printf("|%3d|%17d|%19s|%14s|%16s|%16lld| %4d.%2d.%2d  |\n",
			i + 1, P[i].ID_P, P[i].fam, P[i].name, P[i].f_name, P[i].tel_num, P[i].db.day, P[i].db.month,  P[i].db.year);
		cout << "____________________________________________________________________________________________________________" << endl;
	}
	cout << endl;
}

void Person :: get_p()
{
	system("cls");
	ofstream out;
	out.open("Person.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << endl << "	Введите идентификационный номер: ";
		while (!scanf_s("%d", &ID_P) || (ID_P < 1 || ID_P > 100000000) || (ID_P % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		out << ID_P;
		cout << endl << "	Введите фамилию: ";
		cin >> fam;
		int f = 0;
		f = check_str(fam);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> fam;
			f = check_str(fam);
		}
		out << " " << fam;
		cout << endl << "	Введите имя: ";
		cin >> name;
		f = check_str(name);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> name;
			f = check_str(name);
		}
		out << " " << name;
		cout << endl << "	Введите отчество: ";
		cin >> f_name;
		f = check_str(f_name);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> f_name;
			f = check_str(f_name);
		}
		out << " " << f_name;
		cout << endl << "	Введите номер телефона: ";
		while (!scanf_s("%lld", &tel_num) || (tel_num < 375000000000 || tel_num > 376000000000) || (tel_num % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. число от 375 000 000 000 до 375 999 999 999." << endl;
		}
		out << " " << tel_num;
		cout << "	Введите дату рождения:" << endl;
		cout << "   Введите год:" << endl;
		while (!scanf_s("%d", &db.year) || (db.year < 1900 || db.year > 2005) || (db.year % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1900 до 2005." << endl;
		}
		out << " " << db.year;
		check_year(db.year);
		cout << "   Введите месяц:" << endl;
		while (!scanf_s("%d", &db.month) || (db.month < 1 || db.month > 12) || (db.month % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 12." << endl;
		}
		out << " " << db.month;
		check_day(db.day, db.month);
		db.day = q;
		out << " " << db.day << endl;
		cout << endl << "	Информация о клиенте была успешно добавлена." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Order :: show_o(int k)
{
	run_order();
	cout << "	Информация о заказах:" << endl;
	cout << "__________________________________________________________________________________________________" << endl;
	cout << "| № | Номер заказа |  Статус заказа  |      Адрес доставки      |   Время заказа   | Дата заказа |" << endl;
	cout << "__________________________________________________________________________________________________" << endl;
	for (int i = 0; i < k; i++)
	{
		printf("|%3d|%14d|%17s|%10s %13s %2d|       %2d.%2d      | %4d.%2d.%2d |\n",
			i + 1, O[i].num_o, O[i].status, O[i].ad.city, O[i].ad.street, O[i].ad.num_h, O[i].t.hour, O[i].t.min, O[i].dfo.day, O[i].dfo.month, O[i].dfo.year);
		cout << "__________________________________________________________________________________________________" << endl;

	}
	cout << endl;
}

void Order :: get_o()
{
	system("cls");
	ofstream out;
	out.open("Order.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << endl << "	Номер заказа: ";
		while (!scanf_s("%d", &num_o) || (num_o < 1 || num_o > 100000000) || (num_o % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		out << num_o;
		int h = 0;
		cout << endl;
		cout << "1. Готовится." << endl;
		cout << "2. Готов." << endl;
		cout << "3. Доставка." << endl;
		cout << "4. Доставлен." << endl;
		cout << "5. Отменен." << endl;
		cout << endl << "	Выберите статус заказа: ";
		while (!scanf_s("%d", &h) || (h < 1 || h > 5) || (h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число." << endl;
		}
		switch (h)
		{
		case 1: strcpy(status, "Готовится");
			break;
		case 2: strcpy(status, "Готов");
			break;
		case 3: strcpy(status, "Доставка");
			break;
		case 4: strcpy(status, "Доставлен");
			break;
		case 5: strcpy(status, "Отменен");
			break;
		}
		out << " " << status;
		cout << endl << "	Адрес доставки: ";
		cout << "   Введите город: ";
		cin >> ad.city;
		int f = check_str(ad.city);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> ad.city;
			f = check_str(ad.city);
		}
		out << " " << ad.city;
		cout << "   Введите название улицы: ";
		cin >> ad.street;
		f = check_str(ad.street);
		while (f == 0)
		{
			printf("Ошибка. Введите только буквы:\n");
			rewind(stdin);
			cin >> ad.street;
			f = check_str(ad.street);
		}
		out << " " << ad.street;
		cout << "   Введите номер дома(до 99): ";
		while (!scanf_s("%d", &ad.num_h) || (ad.num_h < 1 || ad.num_h > 99) || (ad.num_h % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 99." << endl;
		}
		out << " " << ad.num_h;
		cout << endl << "	Введите время заказа: ";
		cout << "   Введите часы(в 24-часовом формате): ";
		while (!scanf_s("%d", &t.hour) || (t.hour < 0 || t.hour > 23) || (t.hour % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 0 до 23." << endl;
		}
		out << " " << t.hour;
		cout << "   Введите минуты: ";
		while (!scanf_s("%d", &t.min) || (t.min < 0 || t.min > 59) || (t.min % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 0 до 59." << endl;
		}
		out << " " << t.min;
		cout << "	Введите дату заказа:" << endl;
		cout << "   Введите год:" << endl;
		while (!scanf_s("%d", &dfo.year) || (dfo.year < 2015 || dfo.year > 2019) || (dfo.year % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 2015 до 2019." << endl;
		}
		out << " " << dfo.year;
		check_year(dfo.year);
		cout << "   Введите месяц:" << endl;
		while (!scanf_s("%d", &dfo.month) || (dfo.month < 1 || dfo.month > 12) || (dfo.month % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 12." << endl;
		}
		out << " " << dfo.month;
		check_day(dfo.day, dfo.month);
		dfo.day = q;
		out << " " << dfo.day << endl;
		cout << endl << "	Информация о заказе была успешно добавлена." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void menu_make_o()
{
	system("cls");
	int k = 0;
	cout << "	Продукт из какого раздела хотите заказать?" << endl;
	cout << "1. Горячие блюда." << endl;
	cout << "2. Холодные блюда." << endl;
	cout << "3. Напитки." << endl;
	cout << "4. Выход." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 4) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 4." << endl;
	}
	switch (k)
	{
	case 1: HF->order_hf();
		break;
	case 2: CF->order_cf();
		break;
	case 3: DR->order_dr();
		break;
	case 4: menu_us();
		break;
	}
}

void Hot_Food :: order_hf()
{
	int m = 0, x = 0, k = 0, n = 0;
	run_hf();
	ifstream fp("Hot_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	HF->show(x);
	cout << "	Выберите номер товара: ";
	while (!scanf_s("%d", &m) || (m < 1 || m > x) || (m % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до " << x << "." << endl;
	}
	cout << "	Вы выбрали товар " << HF[m - 1].name << "." << endl;
	cout << "	Введите количество товара: " << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 10) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 10." << endl;
	}
	cout << "	Итого: " << (k*HF[m-1].price) << endl;
	int i = x++, f = 0;
	O[i].num_o = 1 + rand() % 100000;
	for (int j = 0; j < x; j++)
	{
		if (O[j].num_o == O[i].num_o)
			O[i].num_o++;
	}
	strcpy(O[i].status, "Обработка");
	cout << endl << "	Адрес доставки: " << endl;
	cout << "   Введите город: ";
	cin >> O[i].ad.city;
	f = check_str(O[i].ad.city);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.city;
		f = check_str(O[i].ad.city);
	}
	cout << "   Введите название улицы: ";
	cin >> O[i].ad.street;
	f = check_str(O[i].ad.street);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.street;
		f = check_str(O[i].ad.street);
	}
	cout << "   Введите номер дома(до 99): ";
	while (!scanf_s("%d", &O[i].ad.num_h) || (O[i].ad.num_h < 1 || O[i].ad.num_h > 99) || (O[i].ad.num_h % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 99." << endl;
	}
	system("cls");
	cout << endl << "	Введите время заказа: " << endl;
	cout << "   Введите часы(в 24-часовом формате): ";
	while (!scanf_s("%d", &O[i].t.hour) || (O[i].t.hour < 0 || O[i].t.hour > 23) || (O[i].t.hour % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 23." << endl;
	}
	cout << "   Введите минуты: ";
	while (!scanf_s("%d", &O[i].t.min) || (O[i].t.min < 0 || O[i].t.min > 59) || (O[i].t.min % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 59." << endl;
	}
	system("cls");
	cout << "	Введите дату заказа:" << endl;
	cout << "   Введите год:" << endl;
	while (!scanf_s("%d", &O[i].dfo.year) || (O[i].dfo.year < 2015 || O[i].dfo.year > 2019) || (O[i].dfo.year % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 2015 до 2019." << endl;
	}
	check_year(O[i].dfo.year);
	cout << "   Введите месяц:" << endl;
	while (!scanf_s("%d", &O[i].dfo.month) || (O[i].dfo.month < 1 || P[i].db.month > 12) || (O[i].dfo.month % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 12." << endl;
	}
	check_day(O[i].dfo.day, O[i].dfo.month);
	O[i].dfo.day = q;
	system("cls");
	cout << "  Желаете вывести отчет по заказу?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &n) || (n < 1 || n > 2) || (n % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	system("cls");
	switch (n)
	{
	case 1: {
		cout << "______________________________________________________________________________________________________" << endl;
		cout << "| Номер заказа |  Статус заказа  |      Адрес доставки      |   Время заказа   | Дата заказа | Итого |" << endl;
		cout << "______________________________________________________________________________________________________" << endl;
		printf("|%14d|%17s|%10s %13s %2d|       %2d.%2d      | %4d.%2d.%2d |%7d|\n",
			O[i].num_o, O[i].status, O[i].ad.city, O[i].ad.street, O[i].ad.num_h, O[i].t.hour, O[i].t.min, O[i].dfo.day, O[i].dfo.month, O[i].dfo.year, k*HF[m - 1].price);
		cout << "______________________________________________________________________________________________" << endl;
		cout << endl;
		int p = 0;
		cout << "  Желаете сохранить заказ?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &p) || (p < 1 || p > 2) || (p % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
		}
		switch (p)
		{
		case 1:
		{
			ofstream out;
			out.open("Order.txt", std::ios_base::app);
			if (out.is_open())
			{
				out << O[i].num_o << " " << O[i].status << " " << O[i].ad.city << " " << O[i].ad.street << " " << O[i].ad.num_h << " " << O[i].t.hour << " " << O[i].t.min << " " << O[i].dfo.year << " " << O[i].dfo.month << " " << O[i].dfo.day;
			}
			else
				cout << "	Не удалось открыть файл." << endl;
			out.close();
			menu_us();
		}
			break;
		case 2: menu_us();
			break;
		}
	}
		break;
	case 2: menu_us();
		break;
	}
}

void Cold_Food :: order_cf()
{
	int m = 0, x = 0, k = 0, n = 0;
	run_cf();
	ifstream fp("Cold_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	CF->show(x);
	cout << "	Выберите номер товара: ";
	while (!scanf_s("%d", &m) || (m < 1 || m > x) || (m % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до " << x << "." << endl;
	}
	cout << "	Вы выбрали товар " << CF[m - 1].name << "." << endl;
	cout << "	Введите количество товара: " << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 10) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 10." << endl;
	}
	cout << "	Итого: " << (k*CF[m-1].price) << endl;
	int i = x++, f = 0;
	O[i].num_o = 1 + rand() % 100000;
	for (int j = 0; j < x; j++)
	{
		if (O[j].num_o == O[i].num_o)
			O[i].num_o++;
	}
	strcpy(O[i].status, "Обработка");
	cout << endl << "	Адрес доставки: " << endl;
	cout << "   Введите город: ";
	cin >> O[i].ad.city;
	f = check_str(O[i].ad.city);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.city;
		f = check_str(O[i].ad.city);
	}
	cout << "   Введите название улицы: ";
	cin >> O[i].ad.street;
	f = check_str(O[i].ad.street);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.street;
		f = check_str(O[i].ad.street);
	}
	cout << "   Введите номер дома(до 99): ";
	while (!scanf_s("%d", &O[i].ad.num_h) || (O[i].ad.num_h < 1 || O[i].ad.num_h > 99) || (O[i].ad.num_h % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 99." << endl;
	}
	system("cls");
	cout << endl << "	Введите время заказа: " << endl;
	cout << "   Введите часы(в 24-часовом формате): ";
	while (!scanf_s("%d", &O[i].t.hour) || (O[i].t.hour < 0 || O[i].t.hour > 23) || (O[i].t.hour % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 23." << endl;
	}
	cout << "   Введите минуты: ";
	while (!scanf_s("%d", &O[i].t.min) || (O[i].t.min < 0 || O[i].t.min > 59) || (O[i].t.min % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 59." << endl;
	}
	system("cls");
	cout << "	Введите дату заказа:" << endl;
	cout << "   Введите год:" << endl;
	while (!scanf_s("%d", &O[i].dfo.year) || (O[i].dfo.year < 2015 || O[i].dfo.year > 2019) || (O[i].dfo.year % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 2015 до 2019." << endl;
	}
	check_year(O[i].dfo.year);
	cout << "   Введите месяц:" << endl;
	while (!scanf_s("%d", &O[i].dfo.month) || (O[i].dfo.month < 1 || P[i].db.month > 12) || (O[i].dfo.month % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 12." << endl;
	}
	check_day(O[i].dfo.day, O[i].dfo.month);
	O[i].dfo.day = q;
	system("cls");
	cout << "  Желаете вывести отчет по заказу?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &n) || (n < 1 || n > 2) || (n % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	switch (n)
	{
	case 1: {
		cout << "____________________________________________________________________________________________________________" << endl;
		cout << "| Номер заказа |  Статус заказа  |        Адрес доставки     |   Время заказа   | Дата заказа |   Итого   |" << endl;
		cout << "____________________________________________________________________________________________________________" << endl;
		printf("|%14d|%17s|%10s %13s %2d|       %2d.%2d      |  %2d.%2d.%4d |%11d|\n",
			O[i].num_o, O[i].status, O[i].ad.city, O[i].ad.street, O[i].ad.num_h, O[i].t.hour, O[i].t.min, O[i].dfo.day, O[i].dfo.month, O[i].dfo.year, k*CF[m - 1].price);
		cout << "____________________________________________________________________________________________________________" << endl;
		cout << endl;
		int p = 0;
		cout << "  Желаете сохранить заказ?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &p) || (p < 1 || p > 2) || (p % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
		}
		switch (p)
		{
		case 1:
		{
			ofstream out;
			out.open("Order.txt", std::ios_base::app);
			if (out.is_open())
			{
				out << O[i].num_o << " " << O[i].status << " " << O[i].ad.city << " " << O[i].ad.street << " " << O[i].ad.num_h << " " << O[i].t.hour << " " << O[i].t.min << " " << O[i].dfo.year << " " << O[i].dfo.month << " " << O[i].dfo.day;
			}
			else
				cout << "	Не удалось открыть файл." << endl;
			out.close();
			menu_us();
		}
			break;
		case 2: menu_us();
			break;
		}
	}
		break;
	case 2: menu_us();
		break;
	}
}

void Drinks :: order_dr()
{
	int m = 0, x = 0, k = 0, n = 0;
	run_dr();
	ifstream fp("Drinks.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	DR->show(x);
	cout << "	Выберите номер товара: ";
	while (!scanf_s("%d", &m) || (m < 1 || m > x) || (m % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до " << x << "." << endl;
	}
	cout << "	Вы выбрали товар " << DR[m - 1].name << "." << endl;
	cout << "	Введите количество товара: " << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 10) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 10." << endl;
	}
	cout << "	Итого: " << (k*DR[m - 1].price) << endl;
	int i = x++, f = 0;
	O[i].num_o = 1 + rand() % 100000;
	for (int j = 0; j < x; j++)
	{
		if (O[j].num_o == O[i].num_o)
			O[i].num_o++;
	}
	strcpy(O[i].status, "Обработка");
	cout << endl << "	Адрес доставки: " << endl;
	cout << "   Введите город: ";
	cin >> O[i].ad.city;
	f = check_str(O[i].ad.city);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.city;
		f = check_str(O[i].ad.city);
	}
	cout << "   Введите название улицы: ";
	cin >> O[i].ad.street;
	f = check_str(O[i].ad.street);
	while (f == 0)
	{
		printf("Ошибка. Введите только буквы:\n");
		rewind(stdin);
		cin >> O[i].ad.street;
		f = check_str(O[i].ad.street);
	}
	cout << "   Введите номер дома(до 99): ";
	while (!scanf_s("%d", &O[i].ad.num_h) || (O[i].ad.num_h < 1 || O[i].ad.num_h > 99) || (O[i].ad.num_h % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 99." << endl;
	}
	system("cls");
	cout << endl << "	Введите время заказа: " << endl;
	cout << "   Введите часы(в 24-часовом формате): ";
	while (!scanf_s("%d", &O[i].t.hour) || (O[i].t.hour < 0 || O[i].t.hour > 23) || (O[i].t.hour % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 23." << endl;
	}
	cout << "   Введите минуты: ";
	while (!scanf_s("%d", &O[i].t.min) || (O[i].t.min < 0 || O[i].t.min > 59) || (O[i].t.min % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 59." << endl;
	}
	system("cls");
	cout << "	Введите дату заказа:" << endl;
	cout << "   Введите год:" << endl;
	while (!scanf_s("%d", &O[i].dfo.year) || (O[i].dfo.year < 2015 || O[i].dfo.year > 2019) || (O[i].dfo.year % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 2015 до 2019." << endl;
	}
	check_year(O[i].dfo.year);
	cout << "   Введите месяц:" << endl;
	while (!scanf_s("%d", &O[i].dfo.month) || (O[i].dfo.month < 1 || P[i].db.month > 12) || (O[i].dfo.month % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 12." << endl;
	}
	check_day(O[i].dfo.day, O[i].dfo.month);
	O[i].dfo.day = q;
	system("cls");
	cout << "  Желаете вывести отчет по заказу?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &n) || (n < 1 || n > 2) || (n % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	switch (n)
	{
	case 1: {
		cout << "__________________________________________________________________________________________________________" << endl;
		cout << "| Номер заказа |  Статус заказа  |      Адрес доставки      |   Время заказа   | Дата заказа |   Итого   |" << endl;
		cout << "__________________________________________________________________________________________________________" << endl;
		printf("|%14d|%17s|%10s %13s %2d|       %2d.%2d      | %4d.%2d.%2d | %7d|\n",
			O[i].num_o, O[i].status, O[i].ad.city, O[i].ad.street, O[i].ad.num_h, O[i].t.hour, O[i].t.min, O[i].dfo.day, O[i].dfo.month, O[i].dfo.year, k*DR[m - 1].price);
		cout << "______________________________________________________________________________________________" << endl;
		cout << endl;
		int p = 0;
		cout << "  Желаете сохранить заказ?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &p) || (p < 1 || p > 2) || (p % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
		}
		switch (p)
		{
		case 1:
		{
			ofstream out;
			out.open("Order.txt", std::ios_base::app);
			if (out.is_open())
			{
				out << O[i].num_o << " " << O[i].status << " " << O[i].ad.city << " " << O[i].ad.street << " " << O[i].ad.num_h << " " << O[i].t.hour << " " << O[i].t.min << " " << O[i].dfo.year << " " << O[i].dfo.month << " " << O[i].dfo.day;
			}
			else
				cout << "	Не удалось открыть файл." << endl;
			out.close();
			menu_us();
		}
		break;
		case 2: menu_us();
			break;
		}
	}
			break;
	case 2: menu_us();
		break;
	}
}

void menu_1()
{
	system("cls");
	int k = 0;
	while (k != 3)
	{
		int k = 0, f = 0;
		cout << "\n   Здравствуйте." << endl;
		cout << "1. Вход от имени администратора." << endl;
		cout << "2. Вход от имени пользователя." << endl;
		cout << "3. Выход из программы." << endl;
		cout << "	Выберете действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
		{
			cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
			rewind(stdin);
		}
		switch (k)
		{
		case 1: admin();
			break;
		case 2: user();
			break;
		case 3: exit(0);
		}
	}
}

void admin()
{
	std::ofstream out;
	char an[] = "admin";
	char ap[] = "zxcv";
	out.open("Admin.txt");
	if (out.is_open())
	{
		shifr(an);
		shifr(ap);
		out << an << endl << ap;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	int f = 0, k = 0, b = 0;
	string un;
	string pas;
	ifstream fout("Admin.txt");
	char a[255];
	getline(fout, un);
	getline(fout, pas);
	char *un1 = new char[un.length() + 1];
	strcpy(un1, un.c_str());
	deshifr(un1);
	char *pas1 = new char[pas.length() + 1];
	strcpy(pas1, pas.c_str());
	deshifr(pas1);
	while (f == 0)
	{
		cout << "Введите логин: ";
		cin >> a;
		cout << "Введите пароль: ";
		char pass[256];
		int d;
		for (d = 0; (pass[d] = _getch()) != 13;)
		{
			if (pass[d] == '\b' && d != 0)
			{
				printf("%s", "\b \b");
				d--;
			}
			else if (pass[d] != '\b')
			{
				printf("%c", '*');
				d++;
			}
		}
		if (strcmp(un1, a) == 0)
		{
			system("cls");
			cout << "\n   Добро пожаловать." << endl;
			f = 1;
			menu_ad();
		}
		if (f == 0)
		{
			cout << endl << "	Неправильное имя или пароль.\n";
			cout << "Желаете попробовать еще раз?\n";
			cout << "1. Да.\n";
			cout << "2. Нет.\n";
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
			}
			if (k == 2)
			{
				f = 1;
				menu_1();
			}
		}
	}
	fout.close();
	system("cls");
}

void user()
{
	int f = 0, k = 0, b = 0;
	string o;
	string un;
	string pas;
	char a[255];
	char p[255];	
	ifstream fout("User.txt");
	getline(fout, o);
	getline(fout, un);
	getline(fout, pas);
	char *un1 = new char[un.length() + 1];
	strcpy(un1, un.c_str());
	char *pas1 = new char[pas.length() + 1];
	strcpy(pas1, pas.c_str());
	deshifr(un1);
	deshifr(pas1);
	char pass[255];
	int d;
	while (f == 0)
	{
		cout << "Введите логин: ";
		cin >> a;
		cout << "Введите пароль: ";
		for (d = 0; (pass[d] = _getch()) != 13;)
		{
			if (pass[d] == '\b' && d != 0)
			{
				printf("%s", "\b \b");
				d--;
			}
			else if (pass[d] != '\b')
			{
				printf("%c", '*');
				d++;
			}
		}
		if (strcmp(un1, a) == 0)
		{
			system("cls");
			cout << "\n   Добро пожаловать." << endl;
			f = 1;
			menu_us();
		}
		if (f == 0)
		{
			cout << "	Неправильное имя или пароль.\n";
			cout << "Желаете попробовать еще раз?\n";
			cout << "1. Да.\n";
			cout << "2. Нет.\n";
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
			}
			if (k == 1)
				f = 0;
			if (k == 2)
			{
				f = 1;
				menu_1();
			}
		}
	}
	fout.close();
	system("cls");
	if (f == 1)
		cout << "\n   Добро пожаловать." << endl;
}

void menu_upr()
{
	system("cls");
	int k = 0;
	while (k != 4)
	{
		cout << endl << "1. Добавить пользователя." << endl;
		cout << "2. Удалить пользователя." << endl;
		cout << "3. Просмотреть всех пользователей." << endl;
		cout << "4. Выход." << endl;
		cout << "\nВыберете действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 4) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 4." << endl;
		}
		switch (k)
		{
		case 1: add_us();
			break;
		case 2: del_us();
			break;
		case 3: show_us();
			break;
		case 4:;
			break;
		}
	}
}

void show_us()
{
	system("cls");
	int x = 0;
	ifstream f("User.txt");
	while (true)
	{
		string v;
		getline(f, v);
		if (!f.eof())
			x++;
		else
			break;
	}
	f.close();
	string s;
	std::ifstream out;
	out.open("User.txt");
	if (out.is_open())
	{
		cout << "_____________\n";
		cout << "| № | Логин |\n";
		cout << "_____________\n";
		int k = 1;
		int c = 0;
		while (getline(out, s))
		{
			if ((++c % 2) != 0)
			{
				char *s1 = new char[s.length() + 1];
				strcpy(s1, s.c_str());
				deshifr(s1);
				printf("| %d |%7s|\n", k, s1);
				cout << "_____________\n";
				k++;
			}
		}
		cout << "\n";
		out.close();
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	menu_ad();
}

void add_us()
{
	system("cls");
	int k = 0;
	char s[255];
	char sp[255];
	std::ofstream out;
	out.open("User.txt", std::ios_base::app);
	if (out.is_open())
	{
		cout << "\nВведите логин пользователя: ";
		cin >> s;
		shifr(s);
		out << s << endl;
		cout << "Введите пароль: ";
		cin >> sp;
		shifr(sp);
		out << sp << endl;
		cout << "\nПользователь успешно добавлен.\n" << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	system("cls");
	menu_ad();
}

void del_us()
{
	system("cls");
	int x = 0;
	ifstream f("User.txt");
	while (true)
	{
		string v;
		getline(f, v);
		if (!f.eof())
			x++;
		else
			break;
	}
	f.close();
	string s;
	std::ifstream fout;
	fout.open("User.txt");
	if (fout.is_open())
	{
		cout << "_____________\n";
		cout << "| № | Логин |\n";
		cout << "_____________\n";
		int k = 1;
		int c = 0;
		while (getline(fout, s))
		{
			if ((++c % 2) != 0)
			{
				char *s1 = new char[s.length() + 1];
				strcpy(s1, s.c_str());
				deshifr(s1);
				printf("| %d |%7s|\n", k, s1);
				cout << "_____________\n";
				k++;
			}
		}
		cout << "\n";
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	fout.close();
	int k = 0;
	std::ifstream out;
	out.open("User.txt");
	if (out.is_open())
	{
		cout << "\nВведите номер пользователя, которого хотите удалить:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 10) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1.\n";
		}
		k = k * 2;
		ofstream fout;
		fout.open("User1.txt");
		if (fout.is_open())
		{
			int m = 0;
			std::string line;
			while (getline(out, line))
			{
				m++;
				if (m != k)
				{
					if (m != (k + 1))
						fout << line << endl;
				}
			}
		}
		fout.close();
		cout << "\nПользователь успешно удален." << endl;
		out.close();
	}
	else
		cout << "Ошибка. Не удалось открыть файл." << endl;
	remove("User.txt");
	rename("User1.txt", "User.txt");
	menu_ad();
}

void menu_ad()
{
	int k = 0;
	cout << "1. Ввод данных." << endl;
	cout << "2. Вывод данных на экран." << endl;
	cout << "3. Удаление данных." << endl;
	cout << "4. Изменить данные." << endl;
	cout << "5. Сортировка." << endl;
	cout << "6. Поиск данных." << endl;
	cout << "7. Фильтрация." << endl;
	cout << "8. Управление пользователями." << endl;
	cout << "9. Выход." << endl;
	cout << "\nВыберете действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 9) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 9." << endl;
	}
	switch (k)
	{
	case 1: {
		int k = 0;
		while (k != 6)
		{
			cout << endl << "1. Добавить новое Горячее блюдо." << endl;
			cout << "2. Добавить новое Холодное блюдо." << endl;
			cout << "3. Добавить новый Напиток." << endl;
			cout << "4. Добавить нового Клиента." << endl;
			cout << "5. Добавить новый Заказ." << endl;
			cout << "6. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 6) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 6." << endl;
			}
			switch (k)
			{
			case 1: 
			{
				ifstream fp1("Hot_Food.txt");
				int x = 0;
				while (true)
				{
					string v;
					getline(fp1, v);
					if (!fp1.eof())
						x++;
					else
						break;
				}
				fp1.close();
				int i = x++;
				HF[i].get();
			}
				break;
			case 2: 
			{
				ifstream fp1("Cold_Food.txt");
				int x = 0;
				while (true)
				{
					string v;
					getline(fp1, v);
					if (!fp1.eof())
						x++;
					else
						break;
				}
				fp1.close();
				int i = x++;
				CF[i].get();
			}
				break;
			case 3: 
			{
				ifstream fp1("Drinks.txt");
				int x = 0;
				while (true)
				{
					string v;
					getline(fp1, v);
					if (!fp1.eof())
						x++;
					else
						break;
				}
				fp1.close();
				int i = x++;
				DR[i].get();
			}
				break;
			case 4:
			{
				ifstream fp1("Person.txt");
				int x = 0;
				while (true)
				{
					string v;
					getline(fp1, v);
					if (!fp1.eof())
						x++;
					else
						break;
				}
				fp1.close();
				int i = x++;
				P[i].get_p();
			}
				break;
			case 5: 
			{
				ifstream fp1("Order.txt");
				int x = 0;
				while (true)
				{
					string v;
					getline(fp1, v);
					if (!fp1.eof())
						x++;
					else
						break;
				}
				fp1.close();
				int i = x++;
				O[i].get_o();
			}
				break;
			case 6: menu_ad();
				break;
			}
		}
	}
		break;
	case 2: menu_read_ad();
		break;
	case 3: menu_del();
		break;
	case 4: menu_mode();
		break;
	case 5: {
		int k = 0;
		while (k != 4)
		{
			cout << endl << "1. Сортировка по названию Горячего блюда." << endl;
			cout << "2. Сортировка по названию Напитка." << endl;
			cout << "3. Сортировка по цене Холодных блюд." << endl;
			cout << "4. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 4) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 4." << endl;
			}
			switch (k)
			{
			case 1: HF->sort_hf();
				break;
			case 2: DR->sort_dr();
				break;
			case 3: CF->sort_p_cf();
				break;
			case 4: menu_ad();
				break;
			}
		}
	}
			break;
	case 6: {
		int k = 0;
		while (k != 3)
		{
			cout << endl << "1. Поиск по Холодным блюдам." << endl;
			cout << "2. Поиск по Напиткам." << endl;
			cout << "3. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
			}
			switch (k)
			{
			case 1: CF->poisk_cf();
				break;
			case 2: DR->poisk_dr();
				break;
			case 3: menu_ad();
				break;
			}
		}
	}
			break;
	case 7: {
		int k = 0;
		while (k != 4)
		{
			cout << endl << "1. Фильтр по статусу заказа и названию города." << endl;
			cout << "2. Фильтр по наличию товара и цене." << endl;
			cout << "3. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
			}
			switch (k)
			{
			case 1: O->filter_st_o();
				break;
			case 2: CF->filter_g_k();
				break;
			case 3: menu_ad();
				break;
			}
		}
	}
			break;
	case 8: menu_upr();
		break;
	case 9: menu_1();
		break;
	}
}

void menu_us()
{
	int k = 0;
	cout << "1. Вывод данных на экран." << endl;
	cout << "2. Сортировка данных." << endl;
	cout << "3. Поиск данных." << endl;
	cout << "4. Фильтрация данных." << endl;
	cout << "5. Сделать заказ." << endl;
	cout << "6. Топ 3 самых дорогих товара." << endl;
	cout << "7. Выход." << endl;
	cout << "	Выберете действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 7) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 6." << endl;
	}
	system("cls");
	switch (k)
	{
	case 1: menu_read_us();
			break;
	case 2: {
		int k = 0;
		cout << "1. Сортировка по названию Горячего блюда." << endl;
		cout << "2. Сортировка по названию Напитка." << endl;
		cout << "3. Сортировка по цене Холодного блюда." << endl;
		cout << "4. Выход." << endl;
		cout << "	Выберете действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 4) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 4." << endl;
		}
		switch (k)
		{
		case 1: HF->sort_hf();
			break;
		case 2: DR->sort_dr();
			break;
		case 3: CF->sort_p_cf();
			break;
		case 4: menu_us();
			break;
		}
	}
			break;
	case 3: {
		int k = 0;
		while (k != 3)
		{
			cout << "1. Поиск по Холодным блюдам." << endl;
			cout << "2. Поиск по Напиткам." << endl;
			cout << "3. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
			}
			switch (k)
			{
			case 1: CF->poisk_cf();
				break;
			case 2: DR->poisk_dr();
				break;
			case 3: menu_us();
				break;
			}
		}
	}
			break;
	case 4: {
		int k = 0;
		while (k != 4)
		{
			cout << "1. Фильтр по статусу заказа и названию города." << endl;
			cout << "2. Фильтр по наличию товара и цене." << endl;
			cout << "3. Выход." << endl;
			cout << "	Выберете действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
			}
			switch (k)
			{
			case 1: O->filter_st_o();
				break;
			case 2: CF->filter_g_k();
				break;
			case 3: menu_ad();
				break;
			}
		}
	}
			break;
	case 5: menu_make_o();
		break;
	case 6: {mm = 0; HF->top_3(); }
		break;
	case 7: menu_1();
		break;
	}
}

void menu_read_us()
{
	system("cls");
	int k = 0;
	while (k != 5)
	{
		int i = 0;
		cout << "	Как хотите отобразить данные?" << endl;
		cout << "1. Вывести только таблицу Горячих блюд." << endl;
		cout << "2. Вывести только таблицу Холодных блюд." << endl;
		cout << "3. Вывести только таблицу Напитков." << endl;
		cout << "4. Вывести все таблицы." << endl;
		cout << "5. Выход." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 5) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 5." << endl;
		}
		system("cls");
		switch (k)
		{
		case 1:
		{
			int x = 0;
			ifstream fp("Hot_Food.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				HF->show(x++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
		}
			break;
		case 2:
		{
			int x = 0;
			ifstream fp("Cold_Food.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				CF->show(x++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
		}
			break;
		case 3:
		{
			int x = 0;
			ifstream fp("Drinks.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				DR->show(x++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
		}
			break;
		case 4: 
		{
			int x = 0, c = 0, d = 0;
			ifstream fp("Hot_Food.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				HF->show(x++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
			ifstream cp("Cold_Food.txt");
			while (true)
			{
				string v;
				getline(cp, v);
				if (!cp.eof())
					c++;
				else
					break;
			}
			cp.close();
			if (c != 0)
				CF->show(c++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
			ifstream dp("Drinks.txt");
			while (true)
			{
				string v;
				getline(dp, v);
				if (!dp.eof())
					d++;
				else
					break;
			}
			dp.close();
			if (d != 0)
				DR->show(d++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_us();
			}
		}
				break;
		case 5: menu_us();
			break;
		}
	}
}

void menu_read_ad()
{
	system("cls");
	int k = 0;
	while (k != 5)
	{
		int i = 0;
		cout << "	Как хотите отобразить данные?" << endl;
		cout << "1. Вывести только таблицу Продуктов." << endl;
		cout << "2. Вывести только таблицу Клиентов." << endl;
		cout << "3. Вывести только таблицу Заказов." << endl;
		cout << "4. Вывести все таблицы." << endl;
		cout << "5. Выход." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 5) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 5." << endl;
		}
		system("cls");
		switch (k)
		{
		case 1: 
		{
			int k = 0;
			while (k != 5)
			{
				int i = 0;
				cout << "	Как хотите отобразить данные?" << endl;
				cout << "1. Вывести только таблицу Горячих блюд." << endl;
				cout << "2. Вывести только таблицу Холодных блюд." << endl;
				cout << "3. Вывести только таблицу Напитков." << endl;
				cout << "4. Вывести все таблицы." << endl;
				cout << "5. Выход." << endl;
				cout << "	Выберите действие:" << endl;
				while (!scanf_s("%d", &k) || (k < 1 || k > 5) || (k % 1 != 0))
				{
					rewind(stdin);
					cout << "Ошибка. Введите натуральное число от 1 до 5." << endl;
				}
				system("cls");
				switch (k)
				{
				case 1:
				{
					int x = 0;
					ifstream fp("Hot_Food.txt");
					while (true)
					{
						string v;
						getline(fp, v);
						if (!fp.eof())
							x++;
						else
							break;
					}
					fp.close();
					if (x != 0)
						HF->show(x++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
				}
				break;
				case 2:
				{
					int x = 0;
					ifstream fp("Cold_Food.txt");
					while (true)
					{
						string v;
						getline(fp, v);
						if (!fp.eof())
							x++;
						else
							break;
					}
					fp.close();
					if (x != 0)
						CF->show(x++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
				}
				break;
				case 3:
				{
					int x = 0;
					ifstream fp("Drinks.txt");
					while (true)
					{
						string v;
						getline(fp, v);
						if (!fp.eof())
							x++;
						else
							break;
					}
					fp.close();
					if (x != 0)
						DR->show(x++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
				}
				break;
				case 4:
				{
					int x = 0, c = 0, d = 0;
					ifstream fp("Hot_Food.txt");
					while (true)
					{
						string v;
						getline(fp, v);
						if (!fp.eof())
							x++;
						else
							break;
					}
					fp.close();
					if (x != 0)
						HF->show(x++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
					ifstream cp("Cold_Food.txt");
					while (true)
					{
						string v;
						getline(cp, v);
						if (!cp.eof())
							c++;
						else
							break;
					}
					cp.close();
					if (c != 0)
						CF->show(c++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
					ifstream dp("Drinks.txt");
					while (true)
					{
						string v;
						getline(dp, v);
						if (!dp.eof())
							d++;
						else
							break;
					}
					dp.close();
					if (d != 0)
						DR->show(d++);
					else
					{
						cout << "	Товаров в этом разделе не найдено." << endl;
						menu_ad();
					}
				}
				break;
				}
			}
		}
			break;
		case 2: 
		{
			int x = 0;
			ifstream fp("Person.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				P->show_p(x++);
			else
			{
				cout << "	Пользователи не найдены." << endl;
				menu_ad();
			}
		}
			break;
		case 3:
		{
			int x = 0;
			ifstream fp("Order.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				O->show_o(x++);
			else
			{
				cout << "	Заказы не найдены." << endl;
				menu_ad();
			}
		}
			break;
		case 4: 
		{
			int x = 0, c = 0, d = 0, p = 0, o = 0;
			ifstream fp("Hot_Food.txt");
			while (true)
			{
				string v;
				getline(fp, v);
				if (!fp.eof())
					x++;
				else
					break;
			}
			fp.close();
			if (x != 0)
				HF->show(x++);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_ad();
			}
			ifstream cp("Cold_Food.txt");
			while (true)
			{
				string v;
				getline(cp, v);
				if (!cp.eof())
					c++;
				else
					break;
			}
			cp.close();
			if (c != 0)
				CF->show(c);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_ad();
			}
			ifstream dp("Drinks.txt");
			while (true)
			{
				string v;
				getline(dp, v);
				if (!dp.eof())
					d++;
				else
					break;
			}
			dp.close();
			if (d != 0)
				DR->show(d);
			else
			{
				cout << "	Товаров в этом разделе не найдено." << endl;
				menu_ad();
			}
			ifstream pp("Person.txt");
			while (true)
			{
				string v;
				getline(pp, v);
				if (!pp.eof())
					p++;
				else
					break;
			}
			pp.close();
			if (p != 0)
				P->show_p(p);
			else
			{
				cout << "	Пользователи не найдены." << endl;
				menu_ad();
			}
			ifstream op("Order.txt");
			while (true)
			{
				string v;
				getline(op, v);
				if (!op.eof())
					o++;
				else
					break;
			}
			op.close();
			if (o != 0)
				O->show_o(o);
			else
			{
				cout << "	Заказы не найдены." << endl;
				menu_ad();
			}
		}
				break;
		case 5: menu_ad();
			break;
		}
	}
}

void menu_del()
{
	system("cls");
	int k = 0;
	while (k != 3)
	{
		cout << "	По какому критерию хотите удалить данные?" << endl;
		cout << "1. По ID клиента." << endl;
		cout << "2. По названию продукта." << endl;
		cout << "3. Выход." << endl;
		cout << "	Выберете действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 3) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
		}
		switch (k)
		{
		case 1: P->del_id_p();
			break;
		case 2: menu_del_n_p();
			break;
		case 3: menu_ad();
			break;
		}
	}
}

void menu_mode()
{
	system("cls");
	int k = 0;
	while (k != 3)
	{
		cout << "	Какую информацию Вы хотите изменить?" << endl;
		cout << "1. ID клиента." << endl;
		cout << "2. Фамилию клиента." << endl;
		cout << "3. Выход." << endl;
		cout << "	Выберите действие:" << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k>3) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 3." << endl;
		}
		switch (k)
		{
		case 1: P->redact_ID();
			break;
		case 2: P->redact_n();
			break;
		case 3: menu_ad();
			break;
		}
	}
}

void Hot_Food :: sort_hf() 
{
	system("cls");
	run_hf();
	int k = 0, x = 0;
	ifstream fp("Hot_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	Hot_Food tmp;
	int flag = 1;
	while (flag != 0)
	{
		flag = 0;
		for (int i = 0; i < x - 1; i++)
			if (strcmp(HF[i].name, HF[i+1].name) > 0)
			{
				tmp = HF[i];
				HF[i] = HF[i + 1];
				HF[i + 1] = tmp;
				flag++;
			}
	}
	cout << "	Информация была успешно отсортирована." << endl;
	cout << "   Хотите вывести полученный результат?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
	}
	if (k == 1)
	{
		cout << "	Перечень горячих блюд:" << endl;
		cout << "_____________________________________________________" << endl;
		cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
		cout << "_____________________________________________________" << endl;
		for (int m = 0; m < x; m++)
		{
			printf("|%3d|%20s|%16f|%9s|\n", m + 1, HF[m].name, HF[m].price, HF[m].av);
			cout << "_____________________________________________________" << endl;
		}
		cout << endl;
	}
	printf("\n	Желаете сохранить изменения в файле?.\n");
	printf("1. Да.\n");
	printf("2. Нет.\n");
	printf("	Выберите действие:\n");
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	switch (k)
	{
	case 1: {
		ofstream out;
		out.open("Hot_Food.txt");
		for (int i = 0; i < x; i++)
			out << HF[i].name << " " << HF[i].price << " " << HF[i].av << endl;
		out.close();
		cout << "	Информация была успешно сохранена." << endl;
	}
			break;
	case 2:;
		break;
	}
}

void Drinks :: sort_dr()
{
	system("cls");
	run_dr();
	int k = 0, x = 0;
	ifstream fp("Drinks.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	Drinks tmp;
	int flag = 1;
	while (flag != 0)
	{
		flag = 0;
		for (int i = 0; i < x - 1; i++)
			if (strcmp(DR[i].name, DR[i + 1].name) > 0)
			{
				tmp = DR[i];
				DR[i] = DR[i + 1];
				DR[i + 1] = tmp;
				flag++;
			}
	}
	cout << "	Информация была успешно отсортирована." << endl;
	cout << "   Хотите вывести полученный результат?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
	}
	if (k == 1)
	{
		cout << "	Перечень напитков:" << endl;
		cout << "_____________________________________________________" << endl;
		cout << "| № |  Название напитка  |  Цена(в у. е.) | Наличие |" << endl;
		cout << "_____________________________________________________" << endl;;
		for (int m = 0; m < x; m++)
		{
			printf("|%3d|%20s|%16f|%9s|\n", m + 1, DR[m].name, DR[m].price, DR[m].av);
			cout << "_____________________________________________________" << endl;
		}
		cout << endl;
	}
	printf("\n	Желаете сохранить изменения в файле?.\n");
	printf("1. Да.\n");
	printf("2. Нет.\n");
	printf("	Выберите действие:\n");
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	switch (k)
	{
	case 1: {
		ofstream out;
		out.open("Drinks.txt");
		for (int i = 0; i < x; i++)
			out << DR[i].name << " " << DR[i].price << " " << DR[i].av << endl;
		out.close();
		cout << "	Информация была успешно сохранена." << endl;
	}
			break;
	case 2:;
		break;
	}
}

void Cold_Food :: sort_p_cf()
{
	system("cls");
	run_cf();
	int k = 0, x = 0;
	Cold_Food l;
	ifstream fp("Cold_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	for (int j = 0; j < x; j++)
	{
		for (int i = x - 1; i > j; i--)
		{

			if (CF[i - 1].price > CF[i].price)
			{
				l = CF[i - 1];
				CF[i - 1] = CF[i];
				CF[i] = l;
			}
		}
	}
	cout << "	Информация была успешно отсортирована." << endl;
	cout << "   Хотите вывести полученный результат?" << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
	}
	if (k == 1)
	{
		cout << "	Перечень холодных блюд:" << endl;
		cout << "_____________________________________________________" << endl;
		cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
		cout << "_____________________________________________________" << endl;
		for (int m = 0; m < x - 1; m++)
		{
			printf("|%3d|%20s|%16d|%9s|\n", m + 1, CF[m].name, CF[m].price, CF[m].av);
			cout << "_____________________________________________________" << endl;
		}
		cout << endl;
	}
	cout << "\n	Желаете сохранить изменения в файле?." << endl;
	cout << "1. Да." << endl;
	cout << "2. Нет." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 2." << endl;
	}
	switch (k)
	{
	case 1: {
		ofstream out;
		out.open("Cold_Food.txt");
		for (int i = 0; i < x; i++)
			out << CF[i].name << " " << CF[i].price << " " << CF[i].av << endl;
		out.close();
		cout << "	Информация была успешно сохранена." << endl;
	}
			break;
	case 2:;
		break;
	}
}

void Cold_Food :: poisk_cf()
{
	system("cls");
	run_cf();
	int x = 0, flag = 0, f = 0, m = 0, k = 0;
	ifstream fp("Cold_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	char imia[255];
	while (f == 0)
	{
		cout << "	Введите название холодного блюда: ";
		cin >> imia;
		cout << endl;
		for (int i = 0; i < x; i++)
		{
			if (strcmp(CF[i].name, imia) == 0)
			{
				flag = 1;
				m = i;
			}
		}
		if (flag == 1)
		{
			cout << "_____________________________________________________" << endl;
			cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
			cout << "_____________________________________________________" << endl;
			printf("|  1|%20s|%16d|%9s|\n", CF[m].name, CF[m].price, CF[m].av);
			cout << "_____________________________________________________" << endl << endl;
			f = 1;
		}
		else
		{
			cout << "	Не найдено. Желаете попробовать еще раз?" << endl;
			cout << "1. Да." << endl;
			cout << "2. Нет." << endl;
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
			}
			if (k == 2)
				f = 1;
		}
	}
}

void Drinks :: poisk_dr() 
{
	system("cls");
	run_dr();
	int x = 0, flag = 0, f = 0, m = 0, k = 0;
	ifstream fp("Drinks.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	char imia[255];
	while (f == 0)
	{
		cout << "	Введите название напитка: ";
		cin >> imia;
		cout << endl;
		for (int i = 0; i < x; i++)
		{
			if (strcmp(CF[i].name, imia) == 0)
			{
				flag = 1;
				m = i;
			}
		}
		if (flag == 1)
		{
			cout << "_____________________________________________________" << endl;
			cout << "| № |  Название напитка  |  Цена(в у. е.) | Наличие |" << endl;
			cout << "_____________________________________________________" << endl;
			printf("|  1|%20s|%16d|%9s|\n", DR[m].name, DR[m].price, DR[m].av);
			cout << "_____________________________________________________" << endl << endl;
			f = 1;
		}
		else
		{
			cout << "	Не найдено. Желаете попробовать еще раз?" << endl;
			cout << "1. Да." << endl;
			cout << "2. Нет." << endl;
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
			}
			if (k == 2)
				f = 1;
		}
	}
}

void Order :: filter_st_o()
{
	system("cls");
	run_order();
	int i, k = 0, flag1 = 0, flag2 = 0, f = 0, x = 0;
	ifstream fp("Order.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	char d[255];
	char b[255];
	while (f == 0)
	{
		cout << "	Введите статус заказа: ";
		cin >> d;
		cout << "	Введите название города: ";
		cin >> b;
		cout << endl;

		for (i = 0; i < x; i++)
		{
			if (strcmp(O[i].status, d) == 0)
				flag1++;
			if (strcmp(O[i].ad.city, d) == 0)
				flag2++;
		}
		if ((flag1 != 0) && (flag2 != 0))
		{
			cout << "______________________________________________________________________________________________" << endl;
			cout << "| Номер заказа |  Статус заказа  |      Адрес доставки      |   Время заказа   | Дата заказа |" << endl;
			cout << "______________________________________________________________________________________________" << endl;
			for (int i = 0; i < x; i++)
			{
				if (strcmp(O[i].status, d) == 0)
				{
					if (strcmp(O[i].ad.city, d) == 0)
					{
						printf("|%14d|%17s|%19s|%10s %13s %2d|       %2d.%2d       |  %2d.%2d.%4d |\n",
							O[i].num_o, O[i].status, O[i].ad.city, O[i].ad.street, O[i].ad.num_h, O[i].t.hour, O[i].t.min, O[i].dfo.day, O[i].dfo.month, O[i].dfo.year);
						cout << "______________________________________________________________________________________________" << endl;
					}
				}
			}
			f++;
		}
		if (flag1 == 0 || flag2 == 0)
		{
			cout << "	Совпадений не найдено. Желаете попробовать еще раз?" << endl;
			cout << "1. Да." << endl;
			cout << "2. Нет." << endl;
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
			}
			if (k == 2)
				f = 1;
		}
	}
}

void Cold_Food :: filter_g_k()
{
	system("cls");
	run_cf();
	run_hf();
	run_dr();
	int i, k = 0, flag1 = 0, flag2 = 0, f = 0, x = 0, h = 0;
	ifstream fp("Cold_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	ifstream op("Hot_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			k++;
		else
			break;
	}
	fp.close();
	ifstream dp("Drinks.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			h++;
		else
			break;
	}
	fp.close();
	char d[255];
	int e = 0;
	while (f == 0)
	{
		cout << "	Введите наличие товара: ";
		cin >> d;
		cout << "	Введите цену товара: ";
		cin >> e;
		cout << endl;
		for (i = 0; i < x; i++)
		{
			if (strcmp(CF[i].av, d) == 0)
				flag1++;
			if (CF[i].price == e)
				flag2++;
		}
		if ((flag1 != 0) && (flag2 != 0))
		{
			cout << "	Перечень холодных блюд:" << endl;
			cout << "_____________________________________________________" << endl;
			cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
			cout << "_____________________________________________________" << endl;
			for (int i = 0; i < x; i++)
			{
				if (strcmp(CF[i].av, d) == 0)
				{
					if (CF[i].price == e)
					{
						printf("|%3d|%20s|%16f|%9s|\n", i + 1, CF[i].name, CF[i].price, CF[i].av);
						cout << "_____________________________________________________" << endl;
					}
				}

			}
			cout << endl;
			f++;
		}
		for (flag1 = 0, flag2 = 0, i = 0; i < x; i++)
		{
			if (strcmp(HF[i].av, d) == 0)
				flag1++;
			if (HF[i].price == e)
				flag2++;
		}
		if ((flag1 != 0) && (flag2 != 0))
		{
			cout << "	Перечень горячих блюд:" << endl;
			cout << "_____________________________________________________" << endl;
			cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
			cout << "_____________________________________________________" << endl;
			for (int i = 0; i < k; i++)
			{
				if (strcmp(HF[i].av, d) == 0)
				{
					if (HF[i].price == e)
					{
						printf("|%3d|%20s|%16f|%9s|\n", i + 1, HF[i].name, HF[i].price, HF[i].av);
						cout << "_____________________________________________________" << endl;
					}
				}

			}
			cout << endl;
			f++;
		}
		for (flag1 = 0, flag2 = 0, i = 0; i < x; i++)
		{
			if (strcmp(DR[i].av, d) == 0)
				flag1++;
			if (DR[i].price == e)
				flag2++;
		}
		if ((flag1 != 0) && (flag2 != 0))
		{
			cout << "	Перечень напитков:" << endl;
			cout << "_____________________________________________________" << endl;
			cout << "| № |   Название блюда   |  Цена(в у. е.) | Наличие |" << endl;
			cout << "_____________________________________________________" << endl;
			for (int i = 0; i < h; i++)
			{
				if (strcmp(DR[i].av, d) == 0)
				{
					if (DR[i].price == e)
					{
						printf("|%3d|%20s|%16f|%9s|\n", i + 1, DR[i].name, DR[i].price, DR[i].av);
						cout << "_____________________________________________________" << endl;
					}
				}

			}
			cout << endl;
			f++;
		}
		if (flag1 == 0 || flag2 == 0)
		{
			cout << "	Совпадений не найдено. Желаете попробовать еще раз?" << endl;
			cout << "1. Да." << endl;
			cout << "2. Нет." << endl;
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2)." << endl;
			}
			if (k == 2)
				f = 1;
		}
	}
}

void Person :: del_id_p()
{
	system("cls");
	run_person();
	ifstream fp("Person.txt");
	int x = 0;
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	std::ifstream out;
	out.open("Person.txt");
	if (out.is_open())
	{
		int id = 0, m = 255, f = 0, k = 0;
		while (f == 0)
		{
			cout << "	Введите ID клиента: ";
			cin >> id;
			cout << endl;
			for (int i = 0; i < x; i++)
			{
				if (P[i].ID_P == id)
				{
					m = i;
					i = x;
				}
			}
			out.close();
			if (m == 255)
			{
				cout << "	Не найдено. Желаете попробовать еще раз?\n";
				cout << "1. Да.\n";
				cout << "2. Нет.\n";
				cout << "	Выберите действие:" << endl;
				while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
				{
					rewind(stdin);
					cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
				}
				if (k == 2)
					f = 1;
			}
			else
			{
				ofstream out;
				out.open("uchet.txt");
				for (int i = 0; i < x; i++)
				{
					if (i != m)
						out << P[i].ID_P << " " << P[i].fam << " " << P[i].name << " " << P[i].f_name << " " << P[i].tel_num << " " << P[i].db.year << " " << P[i].db.month << " " << P[i].db.day << endl;
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl;
				f = 1;
			}
		}
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void menu_del_n_p()
{
	system("cls");
	int k = 0;
	cout << "	Продукт из какого раздела хотите удалить?" << endl;
	cout << "1. Горячие блюда." << endl;
	cout << "2. Холодные блюда." << endl;
	cout << "3. Напитки." << endl;
	cout << "4. Выход." << endl;
	cout << "	Выберите действие:" << endl;
	while (!scanf_s("%d", &k) || (k < 1 || k > 4) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 1 до 4." << endl;
	}
	system("cls");
	switch (k)
	{
	case 1: HF->del_hf();
		break;
	case 2: CF->del_cf();
		break;
	case 3: DR->del_dr();
		break;
	case 4: menu_ad();
		break;
	}
}

void Hot_Food :: del_hf()
{
	system("cls");
	run_hf();
	ifstream fp("Hot_Food.txt");
	int x = 0;
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	std::ifstream out;
	out.open("Hot_Food.txt");
	if (out.is_open())
	{
		char imia[255];
		int m = 255, f = 0, k = 0;
		while (f == 0)
		{
			cout << "	Введите название: ";
			cin >> imia;
			cout << endl;
			for (int i = 0; i < x; i++)
			{
				if (strcmp(HF[i].name, imia) == 0)
				{
					m = i;
					i = x;
				}
			}
			if (m == 255)
			{
				cout << "	Не найдено. Желаете попробовать еще раз?\n";
				cout << "1. Да.\n";
				cout << "2. Нет.\n";
				cout << "	Выберите действие:" << endl;
				while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
				{
					rewind(stdin);
					cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
				}
				if (k == 2)
					f = 1;
			}
			else
			{
				ofstream out;
				out.open("Hot_Food.txt");
				for (int i = 0; i < x; i++)
				{
					if (i != m)
						out << HF[i].name << " " << HF[i].price << " " << HF[i].av << endl;
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl;
				f = 1;
			}
		}
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Cold_Food :: del_cf()
{
	system("cls");
	run_cf();
	ifstream fp("Cold_Food.txt");
	int x = 0;
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	std::ifstream out;
	out.open("Cold_Food.txt");
	if (out.is_open())
	{
		char imia[255];
		int m = 255, f = 0, k = 0;
		while (f == 0)
		{
			cout << "	Введите название: ";
			cin >> imia;
			cout << endl;
			for (int i = 0; i < x; i++)
			{
				if (strcmp(CF[i].name, imia) == 0)
				{
					m = i;
					i = x;
				}
			}
			if (m == 255)
			{
				cout << "	Не найдено. Желаете попробовать еще раз?\n";
				cout << "1. Да.\n";
				cout << "2. Нет.\n";
				cout << "	Выберите действие:" << endl;
				while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
				{
					rewind(stdin);
					cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
				}
				if (k == 2)
					f = 1;
			}
			else
			{
				ofstream out;
				out.open("Cold_Food.txt");
				for (int i = 0; i < x; i++)
				{
					if (i != m)
						out << CF[i].name << " " << CF[i].price << " " << CF[i].av << endl;
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl;
				f = 1;
			}
		}
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Drinks :: del_dr()
{
	system("cls");
	run_dr();
	ifstream fp("Drinks.txt");
	int x = 0;
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	std::ifstream out;
	out.open("Drinks.txt");
	if (out.is_open())
	{
		char imia[255];
		int m = 255, f = 0, k = 0;
		while (f == 0)
		{
			cout << "	Введите название: ";
			cin >> imia;
			cout << endl;
			for (int i = 0; i < x; i++)
			{
				if (strcmp(DR[i].name, imia) == 0)
				{
					m = i;
					i = x;
				}
			}
			if (m == 255)
			{
				cout << "	Не найдено. Желаете попробовать еще раз?\n";
				cout << "1. Да.\n";
				cout << "2. Нет.\n";
				cout << "	Выберите действие:" << endl;
				while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
				{
					rewind(stdin);
					cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
				}
				if (k == 1)
					f = 0;
				if (k == 2)
					f = 1;
			}
			else
			{
				ofstream out;
				out.open("Drinks.txt");
				for (int i = 0; i < x; i++)
				{
					if (i != m)
						out << DR[i].name << " " << DR[i].price << " " << DR[i].av << endl;
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl;
				f = 1;
			}
		}
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_ad();
}

void Person :: redact_ID()
{
	system("cls");
	run_person();
	int c = 0, b = 0, flag = 0, f = 0, k = 0, x = 0;
	ifstream fp("Person.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	cout << "	Введите номер ID, который хотите заменить: ";
	cin >> b;
	cout << endl;
	while (f == 0)
	{
		for (int i = 0; i < x; i++)
		{
			if (P[i].ID_P == b)
			{
				cout << "	На какую информацию хотите заменить?\n";
				cin >> c;
				ofstream out;
				out.open("Person.txt");
				P[i].ID_P = c;
				for (int i = 0; i < x; i++)
					out << P[i].ID_P << " " << P[i].fam << " " << P[i].name << " " << P[i].f_name << " " << P[i].tel_num << " " << P[i].db.year << " " << P[i].db.month << " " << P[i].db.day << endl;
				out.close();
				flag = 1;
				f = 1;
			}
		}
		if (flag == 0)
		{
			cout << "	Не найдено. Желаете попробовать еще раз?\n";
			cout << "1. Да.\n";
			cout << "2. Нет.\n";
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
			}
			if (k == 2)
				f = 1;
		}
	}
	if (flag == 1)
	{
		k = 0;
		cout << "\n	Информация была успешно заменена.\n";
		cout << "	Желаете просмотреть результат?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие: " << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
		}
		if (k == 1)
			P->show_p(x);
	}
	menu_ad();
}

void Person :: redact_n()
{
	system("cls");
	run_person();
	ifstream fp("Person.txt");
	int x = 0;
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	char c[255], b[255];
	int  flag = 0, f = 0, k = 0;
	cout << "Введите фамилию, которую хотите заменить: ";
	cin >> b;
	cout << endl;
	while (f == 0)
	{
		for (int m = 0; m < x; m++)
		{
			if (strcmp(P[m].fam, b) == 0)
			{
				cout << "На какую информацию хотите заменить?\n";
				cin >> c;
				ofstream out;
				out.open("Person.txt");
				strcpy(P[m].fam, c);
				for (int i = 0; i < x; i++)
					out << P[i].ID_P << " " << P[i].fam << " " << P[i].name << " " << P[i].f_name << " " << P[i].tel_num << " " << P[i].db.year << " " << P[i].db.month << " " << P[i].db.day << endl;
				out.close();
				flag = 1;
				f = 1;
			}
		}
		if (flag == 0)
		{
			cout << "	Не найдено. Желаете попробовать еще раз?\n";
			cout << "1. Да.\n";
			cout << "2. Нет.\n";
			cout << "	Выберите действие:" << endl;
			while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
			{
				rewind(stdin);
				cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
			}
			if (k == 2)
				f = 1;
		}
	}
	if (flag == 1)
	{
		k = 0;
		cout << "\n	Информация была успешно заменена.\n";
		cout << "	Желаете просмотреть результат?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие: " << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k>2) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число(1 либо 2).\n";
		}
		if (k == 1)
			P->show_p(x);
	}
	menu_ad();
}

void Hot_Food::top_3()
{
	system("cls");
	run_cf();
	run_hf();
	run_dr();
	int x = 0, k = 0, h = 0;
	ifstream fp("Cold_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			x++;
		else
			break;
	}
	fp.close();
	Cold_Food lmp;
	for (int j = 0; j < x; j++)
	{
		for (int i = x - 1; i > j; i--)
		{
			if (CF[i - 1].price > CF[i].price)
			{
				lmp = CF[i - 1];
				CF[i - 1] = CF[i];
				CF[i] = lmp;
			}
		}
	}
	ifstream op("Hot_Food.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			k++;
		else
			break;
	}
	fp.close();
	Hot_Food tmp;
	for (int j = 0; j < x; j++)
	{
		for (int i = x - 1; i > j; i--)
		{
			if (HF[i - 1].price > HF[i].price)
			{
				tmp = HF[i - 1];
				HF[i - 1] = HF[i];
				HF[i] = tmp;
			}
		}
	}
	ifstream dp("Drinks.txt");
	while (true)
	{
		string v;
		getline(fp, v);
		if (!fp.eof())
			h++;
		else
			break;
	}
	fp.close();
	Drinks dmp;
	for (int j = 0; j < x; j++)
	{
		for (int i = x - 1; i > j; i--)
		{
			if (DR[i - 1].price > DR[i].price)
			{
				dmp = DR[i - 1];
				DR[i - 1] = DR[i];
				DR[i] = dmp;
			}
		}
	}
	struct top
	{
		int a;
		char name[255];
	}; 
	struct top smp;
	struct top t[9];
	for (int i = 0; i < 3; i++)
	{
		strcpy(t[i].name, HF[i].name);
		t[i].a = HF[i].price;
	}
	for (int i = 3, j = 0; i < 6; i++, j++)
	{
		strcpy(t[i].name, CF[j].name);
		t[i].a = CF[j].price;
	}
	for (int i = 6, j = 0; i < 9; i++, j++)
	{
		strcpy(t[i].name, DR[j].name);
		t[i].a = DR[j].price;
	}
	for (int j = 0; j < 9; j++)
	{
		for (int i = 8; i > j; i--)
		{
			if (t[i-1].a < t[i].a)
			{
				smp = t[i - 1];
				t[i - 1] = t[i];
				t[i] = smp;
			}
		}
	}
	cout << "	Топ 3 самых дорогих товарa:" << endl;
	cout << "________________________________" << endl;
	cout << "| № |  Цена  | Название товара |" << endl;
	cout << "________________________________" << endl;
	for (int i = 0; i < 3; i++)
	{
		printf("|%3d|%8d|%17s|\n", i + 1, t[i].a, t[i].name);
		cout << "________________________________" << endl;
	}
	cout << endl << endl;
	if (mm == 0)
		menu_us();
	else
		menu_ad();
}

int check_str(char str[])
{
	int i, f = 1;
	for (i = 0; i < strlen(str); i++)
	{
		if (((str[i] < -64 || (str[i] > -1 && str[i] < 65)) || (str[i] > 90 && str[i] < 97)) || (str[i] > 122))
		{
			f = 0;
			break;
		}
	}
	return f;
}

void shifr(char *s)
{
	for (int i = 0; i < strlen(s); i++)
	{
		s[i] = s[i] + 2;
	}
}

void deshifr(char *s)
{
	for (int i = 0; i < strlen(s); i++)
	{
		s[i] = s[i] - 2;
	}
}

int check_year(int g)
{
	if (g % 4 == 0)
		l = 1;
	else
		l = 0;
	return(g);
}

int check_day(int d, int m)
{
	printf("   Введите день: \n");
	switch (m)
	{
	case 1: while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
			break;
	case 2: if (l == 0)
	{
		while (!scanf_s("%d", &d) || (d < 1 || d > 28) || (d % 1 != 0))
		{
			rewind(stdin);
			printf("В этом месяце должно быть только 28 дней.\nВведите день еще раз:\n");
		}
	}
			else
	{
		while (!scanf_s("%d", &d) || (d < 1 || d > 29) || (d % 1 != 0))
		{
			rewind(stdin);
			printf("В этом месяце должно быть только 29 дней, т. к. этот год - високосный.\nВведите день еще раз:\n");
		}
	};
			break;
	case 3:while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
		   break;
	case 4:while (!scanf_s("%d", &d) || (d < 1 || d > 30) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должно быть только 30 дней.\nВведите день еще раз:\n");
	};
		   break;
	case 5: while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
			break;
	case 6: while (!scanf_s("%d", &d) || (d < 1 || d > 30) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должно быть только 30 дней.\nВведите день еще раз:\n");
	};
			break;
	case 7: while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
			break;
	case 8:while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
		   break;
	case 9: while (!scanf_s("%d", d) || (d < 1 || d > 30) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должно быть только 30 дней.\nВведите день еще раз:\n");
	};
			break;
	case 10:while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
			break;
	case 11: while (!scanf_s("%d", &d) || (d < 1 || d > 30) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должно быть только 30 дней.\nВведите день еще раз:\n");
	};
			 break;
	case 12: while (!scanf_s("%d", &d) || (d < 1 || d > 31) || (d % 1 != 0))
	{
		rewind(stdin);
		printf("В этом месяце должен быть только 31 день.\nВведите день еще раз:\n");
	};
			 break;
	}
	q = d;
	return d;
}

void run_hf()
{
	FILE *f;
	f = fopen("Hot_food.txt", "r");
	int m = 0;
	while (!feof(f))
	{
		fscanf(f, "%s", &HF[m].name);
		fscanf(f, "%d", &HF[m].price);
		fscanf(f, "%s", &HF[m].av);
		m++;
	}
	fclose(f);
}

void run_cf()
{
	FILE *f;
	f = fopen("Cold_Food.txt", "r");
	int m = 0;
	while (!feof(f))
	{
		fscanf(f, "%s", &CF[m].name);
		fscanf(f, "%d", &CF[m].price);		
		fscanf(f, "%s", &CF[m].av);
		m++;
	}
	fclose(f);
}

void run_dr()
{
	FILE *f;
	f = fopen("Drinks.txt", "r");
	int m = 0;
	while (!feof(f))
	{
		fscanf(f, "%s", &DR[m].name);
		fscanf(f, "%d", &DR[m].price);
		fscanf(f, "%s", &DR[m].av);
		m++;
	}
	fclose(f);
}

void run_person()
{
	FILE *f;
	f = fopen("Person.txt", "r");
	int m = 0;
	while (!feof(f))
	{
		fscanf(f, "%d", &P[m].ID_P);
		fscanf(f, "%s", &P[m].fam);
		fscanf(f, "%s", &P[m].name);
		fscanf(f, "%s", &P[m].f_name);
		fscanf(f, "%lld", &P[m].tel_num);
		fscanf(f, "%d", &P[m].db.year);
		fscanf(f, "%d", &P[m].db.month);
		fscanf(f, "%d", &P[m].db.day);
		m++;
	}
	fclose(f);
}

void run_order()
{
	FILE *f;
	f = fopen("Order.txt", "r");
	int m = 0;
	while (!feof(f))
	{
		fscanf(f, "%d", &O[m].num_o);
		fscanf(f, "%s", &O[m].status);
		fscanf(f, "%s", &O[m].ad.city);
		fscanf(f, "%s", &O[m].ad.street);
		fscanf(f, "%d", &O[m].ad.num_h);
		fscanf(f, "%d", &O[m].t.hour);
		fscanf(f, "%d", &O[m].t.min);
		fscanf(f, "%d", &O[m].dfo.year);
		fscanf(f, "%d", &O[m].dfo.month);
		fscanf(f, "%d", &O[m].dfo.day);
		m++;
	}
	fclose(f);
}