#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

#define m 50

void main()
{
	setlocale(LC_ALL, "Rus");
	int n = 0, k = 0;
	int list[m];
	int next[m];
	for (int i = 0; i < m; i++)
	{
		list[i] = next[i] = 0;
	}
	printf("Введите количество элементов:\n");
	while (!scanf_s("%d", &n) || (n < 0 || n>10000))
	{
		rewind(stdin);
		printf("Ошибка. Введите число:\n");
	}
	if (n > m)
	{
		printf("Ошибка. Максимальное число элементов: 50.\n");
		rewind(stdin);
		printf("Введите количество элементов повторно:\n");
		while (!scanf_s("%d", &n) || (n < 0 || n>10000))
		{
			rewind(stdin);
			printf("Ошибка. Введите число:\n");
		}
	}
	if (n < 0)
	{
		printf("Ошибка. Введите положительное число:\n");
		rewind(stdin);
		printf("Введите количество элементов повторно:\n");
		while (!scanf_s("%d", &n) || (n < 0 || n>10000))
		{
			rewind(stdin);
			printf("Ошибка. Введите число:\n");
		}
	}
	printf("Введите элементы:\n");
	for (int i = 0; i < n; i++)
	{
		while (!scanf_s("%d", &list[i]) || (list[i] < 0 || list[i]>10000))
		{
			rewind(stdin);
			printf("Ошибка. Введите число:\n");
		}
		next[i] = i + 1;
		k++;
	}
	next[k] = -1;
	for (int i = 0, k = 0; i < n; i++)
	{
		if (list[i] % 2 == 0)
		{
			if (list[i - 1] % 2 != 0)
			{
				k=list[i];
				list[i] = list[i - 1];
				list[i - 1] = k;
				i++;
			}
			else
			{
				if (list[i + 1] % 2 != 0)
				{
					k=list[i];
					list[i] = list[i + 1];
					list[i + 1] = k;
					i++;
				}
			}
		}
	}
	printf("\nОтредактированный массив:\n");
	for (int i = 0; i < n; i++)
	{
		printf("%d  ", list[i]);
	}
	int a[m];
	printf("\nВведите порядок следования индексов:\n");
	for (int i = 0; i < n; i++)
	{
		while (!scanf_s("%d", &a[i]) || (a[i] < 0 || a[i]>10000))
		{
			rewind(stdin);
			printf("Ошибка. Введите число:\n");
		}
	}
	printf("Конечный вариант массива с учетом порядка следования:\n");
	for (int i = 0; i < n; i++)
	{
		for (int l = 0; l < n; l++)
		{
			if (a[i] == next[l])
				printf("%d  ", list[l]);
		}
	}
	printf("\n");
	system("pause");
}