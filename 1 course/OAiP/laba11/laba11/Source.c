#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h> 
#include<string.h> 
#include<stdlib.h> 
#include<locale.h>

#define n 10 
#define buf 256


struct vedomost
{
	char FIO[buf];
	int number;
	int dsd;
	int ocenka;
	int group;
};

void main()
{
	char name[20][100];
	int num[2];
	setlocale(LC_ALL, "Rus");
	struct vedomost *gr1, *gr2;
	gr1 = (struct vedomost*)malloc(n * sizeof(struct vedomost));
	gr2 = (struct vedomost*)malloc(n * sizeof(struct vedomost));
	int a[11];
	for (int i = 0; i < 2; i++)
	{
		printf("Введите номер %d группы:\n", i + 1);
		scanf("%d", &num[i]);
	}
	strcpy(name[0], "Крылов Л. А");
	strcpy(name[1], "Лыньков И. И");
	strcpy(name[2], "Ященко В. В.");
	strcpy(name[3], "Лысик О. И.");
	strcpy(name[4], "Сыч С. Г.");
	strcpy(name[5], "Совушкина А. И.");
	strcpy(name[6], "Филипович В. Г.");
	strcpy(name[7], "Сухарев К. Т.");
	strcpy(name[8], "Компель Р. Л.");
	strcpy(name[9], "Выр А. Р.");
	strcpy(name[10], "Савушкин В. И.");
	strcpy(name[11], "Пагузо И. И.");
	strcpy(name[12], "Нимбер В. В.");
	strcpy(name[13], "Сомка О. И.");
	strcpy(name[14], "Лызунов С. Г.");
	strcpy(name[15], "Чепчиль А. И.");
	strcpy(name[16], "Расун В. Г.");
	strcpy(name[17], "Марель К. Т.");
	strcpy(name[18], "Кровян Р. Л.");
	strcpy(name[19], "Сырко Р. Л.");
	for (int i = 0, m = 0; i < n, m < n; i++, m++)
	{
		gr1[m].number = 34527801 + i;
		gr2[m].number = 34527901 + i;
	}
	int groupfor;
	printf("Список всех студентов:\n");
	for (int i = 0; i < 20; i++)
	{
		printf("%d   %s\n", i, name[i]);
	}
	for (int i = 0; i < 20; i++)
	{
		int k = 0;
		printf("О каком студенте хотите ввести информацию?\n");
		scanf("%d", &k);
		printf("Введите информацию о студенте %s:\n", name[k]);
		printf("Введите группу студента : ");
		scanf("%d", &groupfor);
		if (num[0] == groupfor)
		{
			printf("Студент %s группы %d:\n", name[i], num[0]);
			strcpy(gr1[i].FIO, name[i]);
			printf("Введите дату сдачи экзамена:\n");
			scanf("%d", &gr1[i].dsd);
			printf("Введите оценку студента:\n");
			scanf("%d", &gr1[i].ocenka);
		}
		if (num[1] == groupfor)
		{
			printf("Студент %s группы %d:\n", name[i], num[1]);
			strcpy(gr2[i].FIO, name[i]);
			printf("Введите дату сдачи экзамена:\n");
			scanf("%d", &gr2[i].dsd);
			printf("Введите оценку студента:\n");
			scanf("%d", &gr2[i].ocenka);
		}
	}
	for (int i = 0; i < 11; i++)
	{
		a[i] = 0;
		for (int j = 0; j < n; j++)
		{
			if (gr1[j].ocenka == i)
			{
				a[i]++;
			}
			else if (gr2[j].ocenka == i)
			{
				a[i]++;
			}
		}
	}
	printf("\n Количество оценок:\n");
	printf("------------------\n");
	printf("|Оценка|Количество|\n");
	printf("------------------\n");
	for (int i = 0; i < 11; i++)
	{
		if (a[i] != 0)
		{
			printf("| %d | %d |\n", i, a[i]);
			printf("------------------\n");
		}
	}
	free(gr1);
	free(gr2);
	system("pause");
}