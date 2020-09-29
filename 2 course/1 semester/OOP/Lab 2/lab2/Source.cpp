#include <iostream>
#include <iomanip>

using namespace std;

class Mas
{
	static int n;
	static int min;
	static int max;
	static int **m;
	public:
		Mas(int, int **);
		void create_mas();
		void print_mas() const;
		static int func1();
		static int func2();
		~Mas();
};
int Mas::n;
int Mas::min;
int Mas::max;
int ** Mas::m;

int main()
{
	setlocale(LC_ALL, "Russian");
	int n, min = -1000, max = 0;
	int **m;
	cout << "Введите размер квадратной матрицы: ";
	cin >> n;
	m = new int*[n];
	for (int j = 0; j < n; j++)
		m[j] = new int[n];
	Mas M(n, m);
	M.create_mas();
	M.print_mas();
	M.func1();
	M.func2();
	M.~Mas();
	system("pause");
}

Mas::Mas(int N, int **M)
{
	n = N;
	m = M;
}

void Mas::print_mas() const
{
	cout << "		Ваш массив: " << endl;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			cout << "	" << setw(n) << m[i][j] << " ";
		cout << endl;
	}
}

int Mas::func1()
{
	min = 20;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (i > j)
			{
				if (m[i][j] < min)
					min = m[i][j];
			}
		}
	}
	cout << "Минимальный элемент ниже главной диагонали = " << min << endl;
	return(min);
}

int Mas::func2()
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (i < j)
			{
				if (m[i][j] > max)
					max = m[i][j];
			}
		}
	}
	cout << "Максимальный элемент выше главной диагонали = " << max << endl;
	return(max);
}

void Mas::create_mas()
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
			m[i][j] = rand() % 50 + 0;
	}
}

Mas::~Mas()
{
		delete m;
}