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
	if (!fl) cout << "	�������� �� ������ ����������: " << endl;
	else cout << "	�������� ����� ���������� ����������: " << endl;
	if (prev) cout << "���������� �������� = " << prev->get() << endl;
	else cout << "���������� �������� = NULL" << endl;
	cout << "������� �������� = " << that->get() << endl;
}

template <class T2>
int Transaction<T2>::BeginTransaction()
{
	delete prev;
	prev = that;
	that = new T2(*prev);
	if (!that) return 0;
	string new_inf;
	cout << "\n������� ����� ��������: ";
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
	cout << "_____________________________________________" << endl << "	���������� �����:" << endl << "_____________________________________________" << endl << endl;
	k = 1;
	for (int i = 0; i < 10; i++)
		m[i] = rand() % 3 + 1;
	for (int i = 0; i < 10; i++, k++)
	{
		cout << "������ � " << k << "." << endl << "	�����:";
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
	char faculty[4] = "���";
	int gr_n; 
public:
	User();
	void show();
};

User::User()
{
	gr_n = 872308;
	strcpy_s(u.f, 8, "�������");
	strcpy_s(u.i, 7, "������");
	strcpy_s(u.o, 11, "����������");
}

void User::show()
{
	cout << "_____________________________________________" << endl << "	���������� � ������������:" << endl << "_____________________________________________" << endl << endl;
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
	strcpy_s(s.f, 9, "��������");
	strcpy_s(s.i, 5, "����");
	strcpy_s(s.o, 12, "�����������");
}

void Answer::show()
{
	cout << "_____________________________________________" << endl << "	���������� � ����������� �����:" << endl << "_____________________________________________" << endl << endl;
	cout << "	����: " << endl;
	cout << "		" << d.d << "." << d.m << "." << d.y << endl;
	cout << "	���: " << endl;
	cout << "		" << s.f << " " << s.i << " " << s.o << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class Question
{
	char theme[14] = "IT-����������";
	int v[10];
public:
	void show();
};

void Question::show()
{
	cout << "_____________________________________________" << endl << "	���������� � �������� �����:" << endl << "_____________________________________________" << endl << endl;
	cout << "	���������� �������� � ����� = 10." << endl;
	cout << "	���� �����: " << endl;
	cout << "		" << theme << "." << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

class Admin
{
	struct FIO a;
	char org[6] = "�����";
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
	strcpy_s(a.f, 6, "�����");
	strcpy_s(a.i, 6, "�����");
	strcpy_s(a.o, 11, "����������");
	cout << "_____________________________________________" << endl << "	���������� �� ��������������:" << endl << "_____________________________________________" << endl << endl;
	cout << "	���:" << endl;
	cout << "		" << a.f << " " << a.i << " " << a.o << endl;
	cout << "	�����������:" << endl;
	cout << "		" << org << endl;
	cout << "_____________________________________________" << endl << endl;
	menu();
}

void Admin::tr()
{
	Transaction<Admin> trans;
	trans->set_dol("��������� ����������");
	trans.Show(0);
	if (trans.BeginTransaction())
	{
		trans.Show(1);
	}
	cout << endl << "	1-�� ���������� ������ �������." << endl << endl;
	cout << endl;
	trans.DeleteTransaction();
	trans.Commit();
	cout << "	1-�� ���������� ���� ��������." << endl << endl;
	trans.Show(0);
	cout << endl;
	cout << "	2-�� ���������� ������." << endl << endl;
	if (trans.BeginTransaction()) {
		trans.Show(1);
		trans.Commit();
	}
	cout << endl << "	2-�� ���������� ������ �������." << endl << endl;
}

Test T;
User U;
Answer A;
Question Q;
Admin ad;

void menu()
{
	int y = 0;
	cout << "	������������!" << endl << endl;
	cout << "1. ���������� � ����������� �����." << endl;
	cout << "2. ���������� � ������������." << endl;
	cout << "3. ���������� � ����������� �����." << endl;
	cout << "4. ���������� � �������� �����." << endl;
	cout << "5. ���������� �� ��������������." << endl;
	cout << "6. ����������." << endl;
	cout << "7. �����." << endl;
	cout << "	�������� ��������:" << endl;
	while ((!scanf_s("%d", &y)) || ((y < 1) || (y > 7)) || (y % 1 != 0))
	{
		cout << "	������. ������� ����������� ����� �� 1 �� 7." << endl;
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
		Smart_pointer<Admin> ptr_admin(new Admin("����� 1"));
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