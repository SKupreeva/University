#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	setlocale(LC_ALL, "Russian");
	int c = 0, d = 0, **m;
	cout << "������� ���������� ����� �������: ";
	while (!scanf_s("%d", &d) || (d < 1 || d > 100000000) || (d % 1 != 0))
	{
		rewind(stdin);
		cout << "������. ������� ����������� �����." << endl;
	}
	cout << "������� ���������� �������� �������: ";
	while (!scanf_s("%d", &c) || (c < 1 || c > 100000000) || (c % 1 != 0))
	{
		rewind(stdin);
		cout << "������. ������� ����������� �����." << endl;
	}
	m = new int*[c];
	for (int j = 0; j < c; j++)
		m[j] = new int[d];
	cout << "������� �������� ��������� �������: " << endl;
	for (int i = 0; i < d; i++)
	{
		for (int j = 0; j < c; j++)
		{
			cout << "������� ������� m[" << i << "][" << j << "] = ";
			while (!scanf_s("%d", &m[i][j]) || (m[i][j] < -100000000 || m[i][j] > 100000000) || (m[i][j] % 1 != 0))
			{
				rewind(stdin);
				cout << "������. ������� ����� �����." << endl;
			}
		}
	}
	cout << "���� �������� �������: " << endl;
	for (int i = 0; i < d; i++)
	{
		for (int j = 0; j < c; j++)
			cout << " " << m[i][j] << " ";
		cout << endl;
	}
	int h;
	int n[256];
	for (int i = 0; i < d; i++)
	{
		h = 0;
		for (int j = 0; j < c; j++)
		{
			if ((j % 2 == 0) && (m[i][j] > 0))
				h = h + m[i][j];
		}
		n[i] = h;
	}
	n[d] = 0;
	cout << "��������� ������� �������������� ����� �������: " << endl;
	for (int i = 0; i < d; i++)
		cout << " " << n[i] << " ";
	cout << endl;
	int r = 0, v = 0;
	for (int i = 1; i <= d - 1; i++)
	{
		int j = i;
		while (n[j] < n[j - 1] && j >= 1)
		{
			r = n[j];
			n[j] = n[j - 1];
			n[j - 1] = r;
			for (int k = 0; k < c; k++)
			{
				v = m[j][k];
				m[j][k] = m[j - 1][k];
				m[j - 1][k] = v;
			}
			j--;
		}
	}
	cout << "�������� ������� �������������� ����� �������: " << endl;
	for (int i = 0; i < d; i++)
		cout << " " << n[i] << " ";
	cout << endl;
	cout << "���� ����������������� �������: " << endl;
	for (int i = 0; i < d; i++)
	{
		for (int j = 0; j < c; j++)
			cout << " " << m[i][j] << " ";
		cout << endl;
	}
	delete (m);
	system("pause");
}