#include <iostream>
#include <stdio.h>
#include <memory>
#include <string>
#include <Windows.h>

using namespace std;
void menu();

template <typename T>
class Smart_pointer {
	T *m_obj;
public:
	Smart_pointer(T *obj) : m_obj(obj) {}
	~Smart_pointer() {
		delete m_obj;
	}
	T* operator->() { return m_obj; }
	T& operator* () { return *m_obj; }
};

template <class T2>
class Transaction
{
	T2* that;
	T2* prev;
public:
	Transaction() : prev(NULL), that(new T2) {}
	Transaction(const Transaction & obj) : that(new T2(*(obj.that))), prev(NULL) {}
	~Transaction() { delete that;  delete prev; }
	Transaction& operator=(const Transaction & obj);
	void Show(int);
	int BeginTransaction();
	void Commit();
	void DeleteTransaction();
	T2* operator->();
};

template <class T2>
Transaction<T2>& Transaction<T2>::operator=(const Transaction<T2> & obj)
{
	if (this != &obj)
	{
		delete that;
		that = new T2(*(obj.that));
		return *this;
	}
}

template <class T2>
T2* Transaction<T2>::operator->()
{
	return that;
}

template <class T2>
void Transaction<T2>::Show(int fl)
{
	cout << endl;
	if (!fl) cout << "	Значения до начала транзакции: " << endl;
	else cout << "	Значения после выполнения транзакции: " << endl;
	if (prev) cout << "Предыдущее значение = " << prev->get() << endl;
	else cout << "Предыдущее значение = NULL" << endl;
	cout << "Текущее значение = " << that->get() << endl;
}

template <class T2>
int Transaction<T2>::BeginTransaction()
{
	delete prev;
	prev = that;
	that = new T2(*prev);
	if (!that) return 0;
	string new_inf;
	cout << "\nВведите новое значение: ";
	cin >> new_inf;
	that->set_dol(new_inf);
	return 1;
}

template <class T2>
void Transaction<T2>::Commit()
{
	delete prev;
	prev = NULL;
}

template <class T2>
void Transaction<T2>::DeleteTransaction()
{
	if (prev != NULL)
	{
		delete that;
		that = prev;
		prev = NULL;
	}
}

struct FIO
{
	char f[20];
	char i[20];
	char o[20];
};

struct Date
{
	int d;
	int m;
	int y;
};

class Test
{
	string name;
	string theme;
	int k;
	int m[10];
public:
	void show();
};

void Test::show()
{
	cout << "_____________________________________________" << endl << "	Результаты теста:" << endl << "_____________________________________________" << endl << endl;
	k = 1;
	for (int i = 0; i < 10; i++)
		m[i] = rand() % 3 + 1;
	for (int i = 0; i < 10; i++, k++)
	{
		cout << "Вопрос № " << k << "." << endl << "	Ответ:";
		switch (m[i])
		{
		case 1:cout << "A";
			break;
		case 2:cout << "B";
			break;
		case 3:cout << "C";
			break;
		}
		cout << endl;
	}
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class User
{
	struct FIO u;
	char faculty[4] = "ИЭФ";
	int gr_n; 
public:
	User();
	void show();
};

User::User()
{
	gr_n = 872308;
	strcpy_s(u.f, 8, "Филенко");
	strcpy_s(u.i, 7, "Кирилл");
	strcpy_s(u.o, 11, "Викторович");
}

void User::show()
{
	cout << "_____________________________________________" << endl << "	Информация о пользователе:" << endl << "_____________________________________________" << endl << endl;
	cout << u.f << " " << u.i << " " << u.o << " - " << faculty << " - " << gr_n << "." << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class Answer
{
	struct Date d;
	struct FIO s;
public:
	Answer();
	void show();
};

Answer::Answer()
{
	d.d = 23;
	d.m = 06;
	d.y = 2018;
	strcpy_s(s.f, 9, "Степанюк");
	strcpy_s(s.i, 5, "Анна");
	strcpy_s(s.o, 12, "Арнольдовна");
}

void Answer::show()
{
	cout << "_____________________________________________" << endl << "	Информация о прохождении теста:" << endl << "_____________________________________________" << endl << endl;
	cout << "	Дата: " << endl;
	cout << "		" << d.d << "." << d.m << "." << d.y << endl;
	cout << "	ФИО: " << endl;
	cout << "		" << s.f << " " << s.i << " " << s.o << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class Question
{
	char theme[14] = "IT-технологии";
	int v[10];
public:
	void show();
};

void Question::show()
{
	cout << "_____________________________________________" << endl << "	Информация о вопросах теста:" << endl << "_____________________________________________" << endl << endl;
	cout << "	Количество вопросов в тесте = 10." << endl;
	cout << "	Тема теста: " << endl;
	cout << "		" << theme << "." << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class Admin
{
	struct FIO a;
	char org[6] = "БГУИР";
	string dol;
public:
	Admin() : dol("") {}
	Admin(string dol);
	string get();
	void show_dol();
	void set_dol(string dol);
	void tr();
	void show();
};

Admin::Admin(string dol)
{
	this->dol = dol;
}

string Admin::get()
{
	return dol;
}

void Admin::show_dol()
{
	cout << dol;
}

void Admin::set_dol(string dol)
{
	this->dol = dol;
}

void Admin::show()
{
	strcpy_s(a.f, 6, "Корох");
	strcpy_s(a.i, 6, "Мария");
	strcpy_s(a.o, 11, "Филипповна");
	cout << "_____________________________________________" << endl << "	Информация об администраторе:" << endl << "_____________________________________________" << endl << endl;
	cout << "	ФИО:" << endl;
	cout << "		" << a.f << " " << a.i << " " << a.o << endl;
	cout << "	Организация:" << endl;
	cout << "		" << org << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

void Admin::tr()
{
	Transaction<Admin> trans;
	trans->set_dol("Первичная информация");
	trans.Show(0);
	if (trans.BeginTransaction())
	{
		trans.Show(1);
	}
	cout << endl << "	1-ая транзакция прошла успешно." << endl << endl;
	cout << endl;
	trans.DeleteTransaction();
	trans.Commit();
	cout << "	1-ая транзакция была отменена." << endl << endl;
	trans.Show(0);
	cout << endl;
	cout << "	2-ая транзакция начата." << endl << endl;
	if (trans.BeginTransaction()) {
		trans.Show(1);
		trans.Commit();
	}
	cout << endl << "	2-ая транзакция прошла успешно." << endl << endl;
}

Test T;
User U;
Answer A;
Question Q;
Admin ad;

void menu()
{
	int y = 0;
	cout << "	Здравствуйте!" << endl << endl;
	cout << "1. Информация о результатах теста." << endl;
	cout << "2. Информация о пользователе." << endl;
	cout << "3. Информация о прохождении теста." << endl;
	cout << "4. Информация о вопросах теста." << endl;
	cout << "5. Информация об администраторе." << endl;
	cout << "6. Транзакция." << endl;
	cout << "7. Выход." << endl;
	cout << "	Выберите действие:" << endl;
	while ((!scanf_s("%d", &y)) || ((y < 1) || (y > 7)) || (y % 1 != 0))
	{
		cout << "	Ошибка. Введите натуральное число от 1 до 7." << endl;
		rewind(stdin);
	}
	system("cls");
	switch (y)
	{
	case 1: T.show();
		break;
	case 2: U.show();
		break;
	case 3: A.show();
		break;
	case 4: Q.show();
		break;
	case 5: ad.show();
		break;
	case 6: 
	{
		Smart_pointer<Admin> ptr_admin(new Admin("Админ 1"));
		ptr_admin->tr();
		menu();
	}
			break;
	case 7: exit(0);
		break;
	}
}

void main()
{
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	menu();
	system("pause");
}