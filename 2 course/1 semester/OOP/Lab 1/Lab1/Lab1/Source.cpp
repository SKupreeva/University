#include <iostream>
#include <iomanip>
#include <cstdio>

using namespace std;

int main()
{
	setlocale(LC_ALL, "Russian");
	char s[256];
	int k = 0;
	cout << "Введите строку: ";
	gets_s(s);
	int n = strlen(s);
	if (n == 0)
		cout << "Ошибка. Введите хотя бы 1 символ." << endl;
	else
	{
		char e[256];
		int h = n;
		for (int i = 0; i < n; i++, h--)
		{
			e[i] = s[h-1];
			if ((int)s[i] == 32)
				k++;
		}
		e[n] = 0;
		int m = -1;
		int l = 0;
		for (int i = 0; i < n; i++)
		{
			if ((int)e[i] == 32)
			{
				h = i;
				for (int j = m+1; j < i; j++, h--)
					s[j] = e[h - 1];
				m = i;
				s[i] = ' ';
				l++;
			}
			if ((k - l) == 0)
			{
				h = n;
				for (int j = m + 1; j < n; j++, h--)
					s[j] = e[h - 1];
				break;
			}
		}
		if (k == 0)
			cout << "У Вас в строке только 1 лексема." << endl;
		else
			cout << "Ваша строка: " << s << endl;
		cout << "Ваша строка по символам (символ, 16-ричная с-ма, 8-ричная с-ма): " << endl;
		for (int i = 0; i < strlen(s); i++)
			cout <<  setw(20) << setfill('*') << "	" << s[i] << " | " << hex << (int)s[i] << " | " << oct << (int)s[i] << endl;
	}
	system("pause");
}