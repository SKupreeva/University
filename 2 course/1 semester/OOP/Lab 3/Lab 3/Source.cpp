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
		cout << "������� ������: ";
		gets_s(str);
		n = strlen(str);
		if (n == 0)
			cout << "������. ������� ���� �� 1 ������." << endl;
		else
		{
			cout << "����� ����� ������ = " << n;
			cout << endl;
			m = (int)str[n - 1];
			cout << "�������� ��� ���������� ������� ������ = " << m << endl << endl;
		}
	}
	
	String(const String &string)
	{
		cout << "	���������� �������� ++ (���������� � �����������):" << endl;
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
	cout << endl << "����������������� ����� (����� ������) = " << n << endl;
	cout << "����������������� ����� (��� ���������� �������� ������) = " << m << endl;
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