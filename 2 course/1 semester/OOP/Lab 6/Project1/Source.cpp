#include <iostream>
#include <string.h>
#include <iomanip>

using namespace std;

class creature
{
protected:
	long long c;
public:
	creature() {}
	creature(long long C)
	{
		c = C;
	}
	void show()
	{
		cout << endl << endl;
		cout << "���������� ����� ������� �� ������� ����� = " << c << " (1 ��������) ������." << endl;
		cout << endl << endl;
	}
};

class mammals : creature
{
protected:
	int m;
public:
	mammals() {}
	mammals(int M)
	{
		m = M;
	}
	void show()
	{
		cout << endl << endl;
		cout << "���������� ������������� �� ������� ����� = " << m << "(8.7 ��������)" << endl;
		cout << "	(�� ������� �������� ���������(����� 7 000 000 000 ������))." << endl;
		cout << endl << endl;
	}
};

class poacher
{
protected:
	int f1, f2, f3;
public:
	poacher() {}
	poacher(int F1, int F2, int F3)
	{
		f1 = F1;
		f2 = F2;
		f3 = F3;
	}
	void show()
	{
		cout << endl << endl;
		cout << "���������� ����������� ����������� �� ������� �����(� 1976 �.) = " << f1 << " ������." << endl;
		cout << "���������� ����������� ����������� �� ������� �����(� 1989 �.) = " << f2 << " ������." << endl;
		cout << "���������� ����������� ����������� �� ������� �����(� 1999 �.) = " << f3 << " ������." << endl;
		cout << endl << endl;
	}
};

class birds :public creature
{
protected:
	long long b;
public:
	birds() {}
	birds(long long B)
	{
		b = B;
	}
	void show()
	{
		cout << endl << endl;
		cout << "���������� ���� �� ������� ����� = " << b << " (100 ����������) ������." << endl;
		cout << endl << endl;
	}
};

class platypas: public poacher, public mammals, birds
{
protected:
	int p1;
	int p2;
	int p3;
public:
	platypas(int P1, int P2, int P3)
	{
		p1 = P1;
		p2 = P2;
		p3 = P3;
	}
	void show()
	{
		cout << endl << endl;
		cout << "���������� ��������� �� ������� �����(� 1976 �.) = " << p1 << " ������." << endl;
		cout << "���������� ��������� �� ������� �����(� 1989 �.) = " << p2 << " ������." << endl;
		cout << "���������� ��������� �� ������� �����(� 1999 �.) = " << p3 << " ������." << endl;
		cout << endl << endl;
	}
};

int main()
{
	setlocale(LC_ALL, "Russian");
	cout << "������ ������� ������������ ���� '������� ��������'" << endl << "		�� ��� '�������'" << endl << endl;
	creature cr(1000000000000);
	creature *cr_t = &cr;
	mammals ml(8700000);
	mammals *ml_t = &ml;
	poacher pc(23000, 37000, 18000);
	poacher *pc_t = &pc;
	birds b(100000000000);
	birds *b_t = &b;
	platypas pl(560000, 240000, 198000);
	platypas *pl_t = &pl;
	int k = 0, f = 0;
	while (f != 1)
	{
		cout << "	��� ������� �������?" << endl;
		cout << "1. ������� ���������� � ���-�� ����� �������." << endl;
		cout << "2. ������� ���������� � ���-�� �������������." << endl;
		cout << "3. ������� ���������� � ���-�� ����." << endl;
		cout << "4. ������� ������ ���-�� �����������." << endl;
		cout << "5. ������� ������ ���-�� ���������." << endl;
		cout << "6. ������� ��� ����������." << endl;
		cout << "7. ����� �� ���������." << endl;
		cout << "	�������� ��������: " << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 7) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "������. ������� ����������� ����� �� 1 �� 7." << endl;
		}
		switch (k)
		{
		case 1: cr_t->show();
			break;
		case 2: ml_t->show();
			break;
		case 3: b_t->show();
			break;
		case 4: pc_t->show();
			break;
		case 5: pl_t->show();
			break;
		case 6:
		{
			cr_t->show();
			ml_t->show();
			b_t->show();
			pc_t->show();
			pl_t->show();
		}
		break;
		case 7: f = 1;
			break;
		}
	}
	cout << endl << endl;
	system("pause");
}