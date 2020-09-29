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
	printf("������� ���������� ���������:\n");
	while (!scanf_s("%d", &n) || (n < 0 || n>10000))
	{
		rewind(stdin);
		printf("������. ������� �����:\n");
	}
	if (n > m)
	{
		printf("������. ������������ ����� ���������: 50.\n");
		rewind(stdin);
		printf("������� ���������� ��������� ��������:\n");
		while (!scanf_s("%d", &n) || (n < 0 || n>10000))
		{
			rewind(stdin);
			printf("������. ������� �����:\n");
		}
	}
	if (n < 0)
	{
		printf("������. ������� ������������� �����:\n");
		rewind(stdin);
		printf("������� ���������� ��������� ��������:\n");
		while (!scanf_s("%d", &n) || (n < 0 || n>10000))
		{
			rewind(stdin);
			printf("������. ������� �����:\n");
		}
	}
	printf("������� ��������:\n");
	for (int i = 0; i < n; i++)
	{
		while (!scanf_s("%d", &list[i]) || (list[i] < 0 || list[i]>10000))
		{
			rewind(stdin);
			printf("������. ������� �����:\n");
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
	printf("\n����������������� ������:\n");
	for (int i = 0; i < n; i++)
	{
		printf("%d  ", list[i]);
	}
	int a[m];
	printf("\n������� ������� ���������� ��������:\n");
	for (int i = 0; i < n; i++)
	{
		while (!scanf_s("%d", &a[i]) || (a[i] < 0 || a[i]>10000))
		{
			rewind(stdin);
			printf("������. ������� �����:\n");
		}
	}
	printf("�������� ������� ������� � ������ ������� ����������:\n");
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