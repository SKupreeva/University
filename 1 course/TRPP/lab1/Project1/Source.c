#include<stdio.h>
#include<conio.h>
#include<locale.h>

int main()
{
	setlocale(LC_ALL, "Rus");
	int mas[100][100];
	int a = 0, a1, b = 0, b1, n = 0, m = 0, o = 0, o1 = 0, p = 0, p1 = 0, i=0, i1=0,
		i2=0, j=0, j1=0, j2=0, f = 0, k = 0, d = 0, q = 0, max = 0;
	printf("������� ����� �����:\n");
	while (!scanf_s("%d", &n) || (n < 0 || n>99999999999999999))
	{
		rewind(stdin);
		printf("������. ������� ����� �����:\n");
	}
	printf("������� ����� ��������:\n");
	while (!scanf_s("%d", &m) || (m < 0 || m>99999999999999999))
	{
		rewind(stdin);
		printf("������. ������� ����� ��������:\n");
	}
	printf("������� ����� a:\n");
	while (!scanf_s("%d", &a) || (a < -9999999999999 || a>9999999999999))
	{
		rewind(stdin);
		printf("������. ������� �����:\n");
	}
	printf("������� ����� b:\n");
	while (!scanf_s("%d", &b) || (b < -99999999999999 || b>999999999999999))
	{
		rewind(stdin);
		printf("������. ������� �����:\n");
	}
	printf("������� ������:\n");
	for (i=0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			rewind(stdin);
			printf("������� �������� ��������:\n");
			printf("mas[%d][%d]= ", i, j);
			while (!scanf_s("%d", &mas[i][j]) || (mas[i][j] < -99999999999999 || mas[i][j] > 999999999999999))
			{
				rewind(stdin);
				printf("������. ������� �����:\n");
			}
		}
	}
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			if (mas[i][j] == a)
			{
				i1 = i;
				j1 = j;
			}
			else
			{
				f++;
				if (mas[i][j] == b)
				{
					i2 = i;
					j2 = j;
					break;
				}
				else
					k++;
			}
		}
	}
	if (i1 == i2 && j1 + 1 == j2)
		printf("����� � � b ��� ������ ���������.\n");
	if (k == (n*m))
		printf("�������� b ���.\n");
	if (f == n * m)
		printf("�������� a ���.\n");
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			if (mas[i][j] >= 0)
			{
				if (mas[i][j] > mas[i1][j1] && mas[i][j] < mas[i2][j2])
				{
					o = i;
					p = j;
					i = n;
					j = m;
				}
			}
		}
	}
	if (o == n && p == m)
		printf("������������� ������� - ���������.\n");
	if (q == (n*m))
		printf("��� ������������� ���������.\n");
	for (i = o; i < n; i++)
	{
		for (j = p+1; j < m; j++)
		{
			if (mas[i][j] >= max)
			{
				max = mas[i][j];
				o1 = i;
				p1 = j;
			}
			else
				q++;
			if (mas[i][j] == max)
				d++;
		}
	}
	if (d == m - p + (n - o++)*m)
		printf("�������� �� ���������� �����.\n");
		printf("����� ������������� ��������: mas[%d][%d]. /n ������������ ������� ����� %d.\n", o1, p1, max);
	con: system("pause");
}