#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <conio.h>
#include <malloc.h>

struct st1 {
	char *symb;
	int head;
	int size;
};

struct st2 {
	int *code;
	int head;
	int size;
};

int i = 0;

int Init(st1 *stack1, st2 *stack2, int n)
{
	stack1->symb = (char*)malloc(n);
	stack1->size = n;
	stack1->head = 0;
	stack2->code = (int*)malloc(sizeof(int)*n);
	stack2->size = n;
	stack2->head = 0;
	return n;
}

int Inp(st1 *stack1, st2 *stack2, char element)
{
	rewind(stdin);
	stack1->symb[i] = element;
	stack2->code[i] = (int)element;
	i++;
	return 1;
}

void Output(st1 *stack1, st2 *stack2, int n)
{
	printf("  ¬аши стеки: \n");
	for (int i = 0; i < n; i++)
	{
		printf("%c\t%d\n", stack1->symb[i], stack2->code[i]);
	}
}

int main()
{
	setlocale(LC_ALL, "");
	st1 a;
	st2 b;
	int n;
	char lem;
	printf("¬ведите максимальный размер стеков: ");
	scanf_s("%d", &n);
	Init(&a, &b, n);
	printf("¬ведите стек символов:\n");
	for (; i < n; )
	{
		rewind(stdin);
		scanf_s("%c%*c", &lem);
		Inp(&a, &b, lem);
	}
	Output(&a, &b, n);
	printf("\n");
	system("pause");
}