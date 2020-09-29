#include<stdio.h>
#include<conio.h>
#include<locale.h>

int main()
{
	setlocale(LC_ALL, "Rus");
	int mas[100][100];
	int a = 0, a1, b = 0, b1, n = 0, m = 0, o = 0, o1 = 0, p = 0, p1 = 0, i=0, i1=0,
		i2=0, j=0, j1=0, j2=0, f = 0, k = 0, d = 0, q = 0, max = 0;
	printf("Введите число строк:\n");
	while (!scanf_s("%d", &n) || (n < 0 || n>99999999999999999))
	{
		rewind(stdin);
		printf("Ошибка. Введите число строк:\n");
	}
	printf("Введите число столбцов:\n");
	while (!scanf_s("%d", &m) || (m < 0 || m>99999999999999999))
	{
		rewind(stdin);
		printf("Ошибка. Введите число столбцов:\n");
	}
	printf("Введите число a:\n");
	while (!scanf_s("%d", &a) || (a < -9999999999999 || a>9999999999999))
	{
		rewind(stdin);
		printf("Ошибка. Введите число:\n");
	}
	printf("Введите число b:\n");
	while (!scanf_s("%d", &b) || (b < -99999999999999 || b>999999999999999))
	{
		rewind(stdin);
		printf("Ошибка. Введите число:\n");
	}
	printf("Введите массив:\n");
	for (i=0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			rewind(stdin);
			printf("Введите значение элемента:\n");
			printf("mas[%d][%d]= ", i, j);
			while (!scanf_s("%d", &mas[i][j]) || (mas[i][j] < -99999999999999 || mas[i][j] > 999999999999999))
			{
				rewind(stdin);
				printf("Ошибка. Введите число:\n");
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
		printf("Между а и b нет других элементов.\n");
	if (k == (n*m))
		printf("Элемента b нет.\n");
	if (f == n * m)
		printf("Элемента a нет.\n");
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
		printf("Положительный элемент - последний.\n");
	if (q == (n*m))
		printf("Нет положительных элементов.\n");
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
		printf("Элементы на промежутке равны.\n");
		printf("Номер максимального элемента: mas[%d][%d]. /n Максимальный элемент равен %d.\n", o1, p1, max);
	con: system("pause");
}