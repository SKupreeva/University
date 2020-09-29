#include <iostream>
#define buf 256

using namespace std;

class String
{
	char str[buf];
	int n;
	int m;
public:
	String()
	{
		cout << "Введите строку: ";
		gets_s(str);
		n = strlen(str);
		if (n == 0)
			cout << "Ошибка. Введите хотя бы 1 символ." << endl;
		else
		{
			cout << "Длина вашей строки = " << n;
			cout << endl;
			m = (int)str[n - 1];
			cout << "Двоичный код последнего символа строки = " << m << endl << endl;
		}
	}
	
	String(const String &string)
	{
		cout << "	Результаты операций ++ (префиксной и постфиксной):" << endl;
	}
	void operator ++ ();
	void operator ++ (int);
	void show();
};

void String::operator++()
{
	n++;
}

void String::operator++(int)
{
	m++;
}

void String::show()
{
	cout << endl << "Отредактированное число (длина строки) = " << n << endl;
	cout << "Отредактированное число (код последнего элемента строки) = " << m << endl;
}

int main()
{
	setlocale(LC_ALL, "rus");
	String S;
	String dCopy(S);
	++S;
	S.show();
	S++;
	S.show();
	system("pause");
}