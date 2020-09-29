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
		cout << "Количество живых существ на планете Земля = " << c << " (1 триллион) особей." << endl;
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
		cout << "Количество млекопитающих на планете Земля = " << m << "(8.7 миллиона)" << endl;
		cout << "	(не включая Человека разумного(около 7 000 000 000 особей))." << endl;
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
		cout << "Количество действующих браконьеров на планете Земля(в 1976 г.) = " << f1 << " особей." << endl;
		cout << "Количество действующих браконьеров на планете Земля(в 1989 г.) = " << f2 << " особей." << endl;
		cout << "Количество действующих браконьеров на планете Земля(в 1999 г.) = " << f3 << " особей." << endl;
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
		cout << "Количество птиц на планете Земля = " << b << " (100 миллиардов) особей." << endl;
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
		cout << "Количество утконосов на планете Земля(в 1976 г.) = " << p1 << " особей." << endl;
		cout << "Количество утконосов на планете Земля(в 1989 г.) = " << p2 << " особей." << endl;
		cout << "Количество утконосов на планете Земля(в 1999 г.) = " << p3 << " особей." << endl;
		cout << endl << endl;
	}
};

int main()
{
	setlocale(LC_ALL, "Russian");
	cout << "Сводка влияния деятельности вида 'Человек Разумный'" << endl << "		на вид 'Утконос'" << endl << endl;
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
		cout << "	Что желаете сделать?" << endl;
		cout << "1. Вывести информацию о кол-ве живых существ." << endl;
		cout << "2. Вывести информацию о кол-ве млекопитающих." << endl;
		cout << "3. Вывести информацию о кол-ве птиц." << endl;
		cout << "4. Вывести сводку кол-ва браконьеров." << endl;
		cout << "5. Вывести сводку кол-ва утконосов." << endl;
		cout << "6. Вывести всю информацию." << endl;
		cout << "7. Выйти из программы." << endl;
		cout << "	Выберите действие: " << endl;
		while (!scanf_s("%d", &k) || (k < 1 || k > 7) || (k % 1 != 0))
		{
			rewind(stdin);
			cout << "Ошибка. Введите натуральное число от 1 до 7." << endl;
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