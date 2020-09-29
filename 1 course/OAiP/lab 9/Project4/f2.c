#include <stdio.h>
#include <conio.h>
#include <string.h>

typedef struct FILIALY
{
	char nazvanie[30];
	int god;
	int sum;
};
int n;
int itog;
struct FILIALY fil[100];

void enter()
{
	int i;
	printf("Введите количество филиалов.\n");
	scanf_s("%d", &n);
	for (i = 0; i < n; i++)
	{
		printf("Введите название филиала.\n");
		scanf_s("%s", &fil[i].nazvanie);
		printf("Введите год ремонта.\n");
		scanf_s("%d", &fil[i].god);
		printf("Введите стоимость ремонта.\n");
		scanf_s("%d", &fil[i].sum);
	}

}

void sort_sum()
{
	int i, z;
	struct FILIALY s;
	for (i = 0; i < n; i++)
	{
		if (fil[i].sum > fil[i + 1].sum)
		{
			int a = fil[i].sum;
			fil[i].sum = fil[i + 1].sum;
			fil[i + 1].sum = a;
			s = fil[i];
			fil[i] = fil[i + 1];
			fil[i + 1] = s;
		}
	}
	printf("Номер филиала   Название   Год ремонта   Стоимость\n");
	printf("___________________________________________________\n");
	for (z = 0; z < n; z++)
	{
		printf("%d    %s    %d    %d\n", i, fil[z].nazvanie, fil[z].god, fil[z].sum);
		itog = itog + fil[z].sum;
	}
	printf("___________________________________________________\n");
	printf("                         Итоговая стоимость: %d\n", itog);
}

void read()
{
	int i;
	printf("Номер филиала   Название   Год ремонта   Стоимость\n");
	printf("___________________________________________________\n");
	for (i = 0, itog=0; i < n; i++)
	{
		printf("%d    %s    %d    %d\n", i, fil[i].nazvanie, fil[i].god, fil[i].sum);
		itog = itog + fil[i].sum;
	}
	printf("___________________________________________________\n");
	printf("                         Итоговая стоимость: %d\n", itog);
}

void sort_nazv()
{
	int i, z;
	struct FILIALY nf;
	for (i = 0; i < n; i++)
	{
		if (strcmp(fil[i].sum, fil[i + 1].sum) > 0)
		{
			nf = fil[i];
			fil[i] = fil[i + 1];
			fil[i + 1] = nf;
		}
	}
	printf("Номер филиала   Название   Год ремонта   Стоимость\n");
	printf("___________________________________________________\n");
	for (z = 0; z < n; z++)
	{
		printf("%d    %s    %d    %d\n", i, fil[z].nazvanie, fil[z].god, fil[z].sum);
		itog = itog + fil[z].sum;
	}
	printf("___________________________________________________\n");
	printf("                         Итоговая стоимость: %d\n", itog);
}

void poisk()
{
	int i;
	char imia[30];
	printf("Введите название филиала.\n");
	scanf_s("%s", &imia);
	for (i = 0; i < n; i++)
	{
		if (imia == fil[i].nazvanie)
		{
			printf("Номер филиала   Название   Год ремонта   Стоимость\n");
			printf("___________________________________________________\n");
			printf("%d    %s    %d    %d\n", i, fil[i].nazvanie, fil[i].god, fil[i].sum);
			break;
		}
	}
}