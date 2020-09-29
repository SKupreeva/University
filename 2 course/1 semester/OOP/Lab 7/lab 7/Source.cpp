#define _CRT_SECURE_NO_WARNINGS

#include <iomanip>
#include <stdio.h>
#include <string.h>
#include <iostream>

using namespace std;

template <class T>
class Max
{
	T *ms;
	int x, y;
public:
	Max() {}
	void max(int n, int m);
	void max(char* a, char*b);
};

template <class T>
void Max<T>:: max(int n, int m)
{
	cout << endl << "	������� ��������� ������� max." << endl;
	if (n > m)
		cout << "�����: � ������ �." << endl << "	x = " << n << endl;
	else
	{
		if (n == m)
			cout << "�����: � ����� �." << endl << "	x = " << n << endl <<  "	y = " << m << endl;
		else
			cout << "�����: y ������ x." << endl << "	y = " << m << endl;
	}
}

template <class T>
class Average
{
	T * a;
	int x, y;
public:
	Average() {}
	void av(int n, int m);
};

template <class T>
void Average<T>:: av(int n, int m)
{
	int k = (n + m) / 2;
	cout << endl << "�������� �������� x � y = " << k << endl << endl;
}

template <class T>
void Max<T>::max(char* a, char* b)
{
	int n = 0, m = 0;
	char x[256], y[256];
	strcpy(x, a);
	strcpy(y, b);
	n = strlen(x);
	m = strlen(y);
	cout << endl << "	������� ������������������ ������� max." << endl;
	if (n > m)
		cout << "�����: ������ � ������� ������ �." << endl << "	x = " << x << endl;
	else
	{
		if (n == m)
			cout << "�����: ������ � � � ������ ������." << endl << "	x = " << x << endl << "	y = " << y << endl;
		else
			cout << "�����: ������ � ������� ������ x." << endl << "	y = " << y << endl;
	}
}

int main()
{
	setlocale(LC_ALL, "Russian");
	int n = 0, m = 0;
	cout << "������� �������� �: ";
	while (!(scanf_s("%d", &n)) || (n > 999999999) || (n < -999999999) || (n % 1 != 0))
	{
		cout << "������. ���������� ������ ��� ���: " << endl;
		fflush(stdin);
	}
	cout << "������� �������� �: ";
	while (!(scanf_s("%d", &m)) || (m > 999999999) || (m < -999999999) || (m%1 != 0))
	{
		cout << "������. ���������� ������ ��� ���: " << endl;
		fflush(stdin);
	}
	char a[256];
	char b[256];
	cout << "������� ������ �: ";
	rewind(stdin);
	gets_s(a);
	cout << "������� ������ �: ";
	rewind(stdin);
	gets_s(b);
	Average <double> K;
	Max <int> I;
	Max <char> C;
	I.max(n, m);
	C.max(a, b);
	K.av(n, m);
	system("pause");
}