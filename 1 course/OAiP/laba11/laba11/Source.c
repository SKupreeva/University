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
		printf("������� ����� %d ������:\n", i + 1);
		scanf("%d", &num[i]);
	}
	strcpy(name[0], "������ �. �");
	strcpy(name[1], "������� �. �");
	strcpy(name[2], "������ �. �.");
	strcpy(name[3], "����� �. �.");
	strcpy(name[4], "��� �. �.");
	strcpy(name[5], "��������� �. �.");
	strcpy(name[6], "��������� �. �.");
	strcpy(name[7], "������� �. �.");
	strcpy(name[8], "������� �. �.");
	strcpy(name[9], "��� �. �.");
	strcpy(name[10], "�������� �. �.");
	strcpy(name[11], "������ �. �.");
	strcpy(name[12], "������ �. �.");
	strcpy(name[13], "����� �. �.");
	strcpy(name[14], "������� �. �.");
	strcpy(name[15], "������� �. �.");
	strcpy(name[16], "����� �. �.");
	strcpy(name[17], "������ �. �.");
	strcpy(name[18], "������ �. �.");
	strcpy(name[19], "����� �. �.");
	for (int i = 0, m = 0; i < n, m < n; i++, m++)
	{
		gr1[m].number = 34527801 + i;
		gr2[m].number = 34527901 + i;
	}
	int groupfor;
	printf("������ ���� ���������:\n");
	for (int i = 0; i < 20; i++)
	{
		printf("%d   %s\n", i, name[i]);
	}
	for (int i = 0; i < 20; i++)
	{
		int k = 0;
		printf("� ����� �������� ������ ������ ����������?\n");
		scanf("%d", &k);
		printf("������� ���������� � �������� %s:\n", name[k]);
		printf("������� ������ �������� : ");
		scanf("%d", &groupfor);
		if (num[0] == groupfor)
		{
			printf("������� %s ������ %d:\n", name[i], num[0]);
			strcpy(gr1[i].FIO, name[i]);
			printf("������� ���� ����� ��������:\n");
			scanf("%d", &gr1[i].dsd);
			printf("������� ������ ��������:\n");
			scanf("%d", &gr1[i].ocenka);
		}
		if (num[1] == groupfor)
		{
			printf("������� %s ������ %d:\n", name[i], num[1]);
			strcpy(gr2[i].FIO, name[i]);
			printf("������� ���� ����� ��������:\n");
			scanf("%d", &gr2[i].dsd);
			printf("������� ������ ��������:\n");
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
	printf("\n ���������� ������:\n");
	printf("------------------\n");
	printf("|������|����������|\n");
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