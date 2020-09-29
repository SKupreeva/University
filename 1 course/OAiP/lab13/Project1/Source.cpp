#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <conio.h>
#include <locale.h>

struct sp {
	char symb;
	sp *next;
};

int i = 0, k = 0, n = 0;

void vvod(sp **begin, sp **end, int size)
{
	char t ='0';
	while (size > i)
	{
		rewind(stdin);
		sp *a = (sp*)malloc(sizeof(sp));
		scanf_s("%c", &a->symb);
		i++;
		a->next = NULL;
		if (a->symb == t)
		{
			n = 1;
			printf("Вы ввели повторяющийся символ.\n");
			return;
		}
		if (*begin == NULL)
		{
			*begin = *end = a;
		}
		else
		{
			(*end)->next = a;
			*end = a;
		}
		t = a->symb;
	}
	printf("Вы ввели максимальное количество элементов очереди.\n");
	rewind(stdin);
}

void vivod(sp *begin)
{
	sp *a = begin;
	while (a != NULL)
	{
		printf("%c ", a->symb);
		a = a->next;
	}
}

int main()
{
	setlocale(LC_ALL, "");
	sp *begin = NULL, *end;
	int s;
	printf("Введите максимальный размер очереди: ");
	scanf_s("%d", &s);
	printf("Введите элементы очереди:\n");
	rewind(stdin);
	vvod(&begin, &end, s);
	if (n == 1)
	{
		while (begin != NULL)
		{
			k++;
			begin = begin->next;
		}
		printf("Размер очереди: %d.\n", k);
	}
	else
	{
		printf("Ваша очередь: ");
		vivod(begin);
		printf("\n");
	}
	system("pause");
}