#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <windows.h>
#include <list>
#include <map>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstring>

using namespace std;

void menu();
void menu_map();
void menu_list();

class Document
{
	char name[256];
	int s_n;
	char edition[256];
	map <string, int> document;
	map <string, int>::iterator mapPtr;
	list <string> lst;
	list <string>::iterator listPtr;
public:
	Document();
	void run_doc();
	void run_ed();
	void add_map_pair();
	void del_map_pair();
	void red_map_pair();
	void read_map();
	void search_map();
	void sort_map();
	void add_list();
	void del_list();
	void red_list();
	void read_list();
	void search_list();
	void sort_list();
};

Document D[255];
Document E[255];

Document::Document()
{
	D->run_doc();
	E->run_ed();
	int x = 0;
	ifstream fp("Document.txt");
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
	ifstream f("Edition.txt");
	int k = 0;
	while (true)
	{
		string v;
		getline(f, v);
		if (!f.eof())
			k++;
		else
			break;
	}
	f.close();
	for (int i = 0; i < x; i++)
		document.insert(pair<string, int>(D[i].name, D[i].s_n));
	for (int i = 0; i < k; i++)
		lst.push_back(E[i].edition);
}

void Document::run_doc()
{
	FILE *f;
	f = fopen("Document.txt", "r");
	int x = 0;
	while (!feof(f))
	{
		fscanf(f, "%s", &D[x].name);
		fscanf(f, "%d", &D[x].s_n);
		x++;
	}
	fclose(f);
}

void Document::run_ed()
{
	FILE *f;
	f = fopen("Edition.txt", "r");
	int x = 0;
	while (!feof(f))
	{
		fscanf(f, "%s", &E[x].edition);
		x++;
	}
	fclose(f);
}

void Document::add_map_pair()
{
	ofstream out;
	out.open("Document.txt", std::ios_base::app);
	if (out.is_open())
	{
		string name;
		cout << "	Введите название документа: ";
		cin >> name;
		out << name << " ";
		int x = 0;
		ifstream fp("Document.txt");
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
		int m = x++;
		cout << "	Cерийный номер документа - " << m << endl;
		out << m << endl;
		document.insert(pair<string, int>(name, m));
		cout << endl << "	Документ был успешно добавлен!" << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_map();
}

void Document::red_map_pair()
{
	D->run_doc();
	ifstream fp("Document.txt");
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
	char c[255];
	string b;
	cout << "Введите название документа, который хотите заменить: ";
	cin >> b;
	cout << endl;
	for (int m = 0; m < x; m++)
	{
		mapPtr = document.find(b);
		if (mapPtr != document.end())
		{
			cout << "	Документ найден." << endl << "Серийный номер = " << mapPtr->second << endl;
			cout << "На какую информацию хотите заменить?\n";
			cin >> c;
			strcpy(D[m].name, c);
			document.clear();
			ofstream out;
			out.open("Document.txt");
			for (int i = 0; i < x; i++)
			{
				out << D[i].name << " " << D[i].s_n << endl;
				document.insert(pair<string, int>(D[i].name, D[i].s_n));
			}
			out.close();
			cout << "\n	Информация была успешно заменена." << endl << endl;
			break;
		}
		else
			cout << "	Ошибка. Не найдено." << endl << endl;
	}
	menu_map();
}

void Document::del_map_pair()
{
	D->run_doc();
	ifstream fp("Document.txt");
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
	out.open("Document.txt");
	if (out.is_open())
	{
		char imia[255];
		int m = 0;
		cout << "	Введите название документа, который хотите удалить: ";
		cin >> imia;
		mapPtr = document.find(imia);
		cout << endl;
		for (int j = 0; j < x; j++)
		{
			if (mapPtr != document.end())
			{
				m = j;
				j = x;
				ofstream out;
				out.open("Document.txt");
				document.clear();
				for (int i = 0; i < x; i++)
				{
					if (i != m)
					{
						out << D[i].name << " " << D[i].s_n << endl;
						document.insert(pair<string, int>(D[i].name, D[i].s_n));
					}
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl;
			}
			else
				cout << "	Ошибка. Не найдено." << endl << endl;
		}
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_map();
}

void Document::read_map()
{
	D->run_doc();
	int x = 0;
	ifstream fp("Document.txt");
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
	{
		cout << "	Таблица печатных изданий" << endl;
		cout << "_______________________________________________________________" << endl;
		cout << "|            Название документа              | Серийный номер |" << endl;
		cout << "_______________________________________________________________" << endl;
		mapPtr = document.begin();
		for (; mapPtr != document.end(); ++mapPtr)
		{
			cout << "|" << setw(44) << mapPtr->first << "|" << setw(16) << mapPtr->second << "|" << endl;
			cout << "_______________________________________________________________" << endl;
		}
		cout << endl;
	}
	else
		cout << "	Ошибка. В файле нет данных." << endl << endl;
	menu_map();
}

void Document::search_map()
{
	D->run_doc();
	ifstream fp("Document.txt");
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
	string b;
	cout << "Введите название документа, который хотите найти: ";
	cin >> b;
	cout << endl;
	mapPtr = document.find(b);
	if (mapPtr != document.end())
	{
		cout << "	Документ найден." << endl;
		cout << "_______________________________________________________________" << endl;
		cout << "|            Название документа              | Серийный номер |" << endl;
		cout << "_______________________________________________________________" << endl;
		cout << "|" << setw(44) << mapPtr->first << "|" << setw(16) << mapPtr->second << "|" << endl;
		cout << "_______________________________________________________________" << endl << endl;
	}
	else
		cout << "	Ошибка. Не найдено." << endl << endl;
	menu_map();
}

void Document::sort_map()
{
	D->run_doc();
	int k = 0, x = 0;
	Document d;
	ifstream fp("Document.txt");
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
			if (D[i - 1].s_n > D[i].s_n)
			{
				d = D[i - 1];
				D[i - 1] = D[i];
				D[i] = d;
			}
		}
	}
	cout << "	Информация была успешно отсортирована." << endl;
	cout << "	Таблица печатных изданий" << endl;
	cout << "_______________________________________________________________" << endl;
	cout << "|            Название документа              | Серийный номер |" << endl;
	cout << "_______________________________________________________________" << endl;
	for (int m = 0; m < x ; m++)
	{
		cout << "|" << setw(44) << D[m].name << "|" << setw(16) << D[m].s_n << "|" << endl;
		cout << "_______________________________________________________________" << endl;
	}
	cout << endl;
	menu_map();
}

void Document:: add_list()
{
	ofstream out;
	out.open("Edition.txt", std::ios_base::app);
	if (out.is_open())
	{
		char name[256];
		cout << "	Введите название издательства: ";
		cin >> name;
		int x = 0, f = 0;
		ifstream fp("Edition.txt");
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
		for (int i = 0; i < x; i++)
		{
			if (strcmp(E[i].edition, name) == 0)
				f = 1;
		}
		if (f == 0)
		{
			out << name << endl;
			lst.push_back(name);
			cout << endl << "	Издание было успешно добавлено!" << endl << endl;
		}
		else
			cout << "	Ошибка. Такое издательство уже есть в списке." << endl << endl;
	}
	else
		cout << "Ошибка. Не удалось открыть файл.\n";
	out.close();
	menu_list();
}

void Document::del_list()
{
	E->run_ed();
	ifstream fp("Edition.txt");
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
	out.open("Edition.txt");
	if (out.is_open())
	{
		char imia[255];
		int m = 999;
		cout << "	Введите название издательсьва, которое хотите удалить: ";
		cin >> imia;
		cout << endl;
		for (int j = 0; j < x; j++)
		{
			if (strcmp(E[j].edition, imia) == 0)
			{
				m = j;
				j = x;
				ofstream out;
				out.open("Edition.txt");
				lst.remove(imia);
				for (int i = 0; i < x; i++)
				{
					if (i != m)
						out << E[i].edition << endl;
				}
				out.close();
				cout << "	Информация была успешно удалена." << endl << endl;
			}
		}
		if (m == 999)
			cout << "	Ошибка. Не найдено." << endl << endl;
	}
	else
		cout << "	Не удалось открыть файл.\n";
	out.close();
	menu_list();
}

void Document::red_list()
{
	E->run_ed();
	ifstream fp("Edition.txt");
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
	char c[255];
	char b[255];
	int f = 0;
	cout << "	Введите название издания, которое хотите заменить: ";
	cin >> b;
	cout << endl;
	for (int m = 0; m < x; m++)
	{
		if (strcmp(E[m].edition, b) == 0)
		{
			cout << "	Издание найдено." << endl;
			cout << "На какую информацию хотите заменить?\n";
			cin >> c;
			strcpy(E[m].edition, c);
			lst.clear();
			ofstream out;
			out.open("Edition.txt");
			for (int i = 0; i < x; i++)
			{
				out << E[i].edition << endl;
				lst.push_back(E[i].edition);
			}
			out.close();
			cout << "\n	Информация была успешно заменена." << endl << endl;
			f = 1;
		}
	}
	if (f == 0)
		cout << "	Ошибка. Не найдено." << endl << endl;
	menu_list();
}

void Document:: read_list()
{
	E->run_ed();
	int x = 0;
	ifstream fp("Edition.txt");
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
	{
		cout << "	Список издательств" << endl;
		cout << "________________________________________________" << endl;
		cout << "|            Название издательства             |" << endl;
		cout << "________________________________________________" << endl;
		for (listPtr = lst.begin(); listPtr != lst.end(); listPtr++)
		{
			cout << "|" << setw(46) <<  *listPtr << "|" << endl;
			cout << "________________________________________________" << endl;
		}
		cout << endl;
	}
	else
		cout << "	Ошибка. В файле нет данных." << endl << endl;
	menu_list();
}

void Document::search_list()
{
	E->run_ed();
	ifstream fp("Edition.txt");
	int x = 0, m = 0, flag = 0;
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
	char b[255];
	cout << "Введите название издательства, которое хотите найти: ";
	cin >> b;
	cout << endl;
	for (int i = 0; i < x; i++)
	{
		if (strcmp(E[i].edition, b) == 0)
		{
			flag = 1;
			m = i;
			break;
		}
	}
	if (flag == 1)
	{
		cout << "	Издательство найдено." << endl;
		cout << "________________________________________________" << endl;
		cout << "|            Название издательства             |" << endl;
		cout << "________________________________________________" << endl;
		cout << "|" << setw(46) << E[m].edition << "|" << endl;
		cout << "________________________________________________" << endl << endl;
	}
	else
		cout << "	Ошибка. Не найдено." << endl << endl;
	menu_list();
}

void Document::sort_list()
{
	int k = 0;
	lst.sort();
	cout << "	Информация была успешно отсортирована." << endl;
	cout << "	Список издательств" << endl;
	cout << "________________________________________________" << endl;
	cout << "|            Название издательства             |" << endl;
	cout << "________________________________________________" << endl;
	for (listPtr = lst.begin(); listPtr != lst.end(); listPtr++)
	{
		cout << "|" << setw(46) << *listPtr << "|" << endl;
		cout << "________________________________________________" << endl;
	}
	cout << endl;
	menu_list();
}

void menu_map()
{
	int k = 0;
	cout << "	Вы в меню контейнера map." << endl << endl;
	cout << "1. Добавить данные." << endl;
	cout << "2. Удалить данные." << endl;
	cout << "3. Редактировать данные." << endl;
	cout << "4. Вывод на экран." << endl;
	cout << "5. Поиск." << endl;
	cout << "6. Сортировка." << endl;
	cout << "0. Выход." << endl;
	cout << "	Выберите действие: " << endl;
	while (!scanf_s("%d", &k) || (k < 0 || k > 6) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 6.\n";
	}
	system("cls");
	switch (k)
	{
	case 1:D->add_map_pair();
		break;
	case 2:D->del_map_pair();
		break;
	case 3:D->red_map_pair();
		break;
	case 4:D->read_map();
		break;
	case 5:D->search_map();
		break;
	case 6:D->sort_map();
		break;
	case 0: menu();
		break;
	}
}

void menu_list()
{
	int k = 0;
	cout << "	Вы в меню контейнера list." << endl << endl;
	cout << "1. Добавить данные." << endl;
	cout << "2. Удалить данные." << endl;
	cout << "3. Редактировать данные." << endl;
	cout << "4. Вывод на экран." << endl;
	cout << "5. Поиск." << endl;
	cout << "6. Сортировка." << endl;
	cout << "0. Выход." << endl;
	cout << "	Выберите действие: " << endl;
	while (!scanf_s("%d", &k) || (k < 0 || k > 6) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 6.\n";
	}
	system("cls");
	switch (k)
	{
	case 1:E->add_list();
		break;
	case 2:E->del_list();
		break;
	case 3:E->red_list();
		break;
	case 4:E->read_list();
		break;
	case 5:E->search_list();
		break;
	case 6:E->sort_list();
		break;
	case 0: menu();
		break;
	}
}

void menu()
{
	int k = 0;
	cout << "	Вы в главном меню." << endl << endl;
	cout << "1. Перейти в меню контейнера map." << endl;
	cout << "2. Перейти в меню контейнера list." << endl;
	cout << "0. Выход из программы." << endl;
	cout << "	Выберите действие: " << endl;
	while (!scanf_s("%d", &k) || (k < 0 || k > 2) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число от 0 до 2.\n";
	}
	system("cls");
	switch (k)
	{
	case 1: menu_map();
		break;
	case 2: menu_list();
		break;
	case 0: exit(0);
		break;
	}
}

void main()
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	cout << "	Здравствуйте!" << endl;
	menu();
	system("pause");
}