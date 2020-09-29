#include <stdio.h> 
#include <conio.h> 
#include <string.h> 
#include <stdlib.h> 
#include <locale.h> 
#define buf 256 

typedef struct FILIALY
{
	char nazvanie[buf];
	int god;
	int sum;
};
int n;
static struct FILIALY *fil, t;

void enter()
{
	int i;
	printf("Введите количество филиалов.\n");
	scanf_s("%d", &n);
	printf("\n");
	fil = (struct FILIALY *)malloc(n * sizeof(struct FILIALY));
	for (i = 0; i < n; i++)
	{
		rewind(stdin);
		printf("Введите название филиала.\n");
		gets_s(fil[i].nazvanie, buf);
		printf("\n");
		printf("Введите год ремонта.\n");
		scanf_s("%d", &fil[i].god);
		printf("\n");
		printf("Введите стоимость ремонта.\n");
		scanf_s("%d", &fil[i].sum);
		printf("\n");
	}

}

void sort_sum()
{
	for (int j = n - 1; j >= 0; j--)
	{
		for (int i = 0; i < j; i++)
		{
			if (fil[i].sum > fil[i + 1].sum)
			{
				t = fil[i];
				fil[i] = fil[i + 1];
				fil[i + 1] = t;
			}
		}
	}
}

void read()
{
	printf("Номер филиала Название Год ремонта Стоимость\n");
	printf("___________________________________________________\n");
	int itog = 0;
	for (int i = 0; i < n; i++)
	{
		
		printf("%d %16s %8d %9d\n", i, fil[i].nazvanie, fil[i].god, fil[i].sum);
		printf("\n");
		itog = itog + fil[i].sum;
	}
	printf("___________________________________________________\n");
	printf("\n");
	printf(" Итоговая стоимость: %d\n", itog);
	printf("\n");
}


void poisk()
{
	int i, flag = 0;
	char imia[buf];
	printf("Введите название филиала.\n");
	printf("\n");
	rewind(stdin);
	gets_s(imia, buf);
	for (i = 0; i < n; i++)
	{
		if (strcmp(fil[i].nazvanie, imia) == 0)
		{
			flag = 1;
			printf("Номер филиала Название Год ремонта Стоимость\n");
			printf("___________________________________________________\n");
			printf("%6d %9s %4d %9d\n", i, fil[i].nazvanie, fil[i].god, fil[i].sum);
			printf("___________________________________________________\n");
			printf("\n");
		}
	}
	if (flag == 0) printf("Не найдено\n");
	printf("\n");
}