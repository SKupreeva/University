#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <locale.h>
#include <math.h>

#define ap 0.001

double x1 = 0, x2 = 0, dx = 0, dsl = 0, dsr = 0, dsm = 0, dst = 0;
double pl = 0, pr = 0, pm = 0, pt = 0;

double func(double x)
{
	double f;
	f = x * cos(pow(x, 2));
	return f;
}

double leftT(double x1, double dx)
{
	dx =( x2 - x1)/ap;
	dsl = func(x1);
	for (int i = 0; i <= dx-1; i++)
	{
		x1 = x1 + i * ap;
		dsl = dsl + func(x1);
	}
	dsl = dsl * ap;
	printf("\ndsl = %lf\n\n", dsl);
	return dsl;
}

double rightT(double x2, double dx)
{
	dx = (x2 - x1)/ap;
	dsr = func(x2);
	for (int i = 0; i <= dx-1; i++)
	{
		x2 = x2 - i * ap;
		dsr = dsr + func(x2);
	}
	dsr = dsr * ap;
	printf("\ndsr = %lf\n\n", dsr);
	return dsr;
}

double midT(double x1, double x2, double dx)
{
	dx = (x2 - x1)/ap;
	dsm = func((x2+x1)/2);
	for (int i = 0; i <= dx-1; i++)
	{
		x1 = x1 + i * ap;
		dsm = dsm + func(x1);
	}
	dsm = dsm * ap;
	printf("\ndsm = %lf\n\n", dsm);
	return dsm;
}

double Trap(double x1, double x2, double dx)
{
	dx = (x2 - x1)/ap;
	dst = (func(x1) + func(x1)) / 2;
	for (int i = 0; i <= dx; i++)
	{
		x1 = x1 + i * ap;
		dst = dst + func(x1);
	}
	dst = dst * ap;
	printf("\ndst = %lf\n\n", dst);
	return dst;
}

double sr()
{
	double s = 0;
	s = (dsl + dsr + dsm + dst) / 4;
	pl = s - dsl;
	pr = s - dsr;
	pm = s - dsm;
	pt = s - dst;
	return (pl, pr, pm, pt);
}

void read1()
{
	system("cls");
	printf("______________________________________________________\n");
	printf("|    Метод вычисления    |  Результат  | Погрешность |\n");
	printf("______________________________________________________\n");
	printf("|  Левого треугольника   |%13lf|%13f|\n", dsl, pl);
	printf("______________________________________________________\n");
	printf("|  Правого треугольника  |%13lf|%13f|\n", dsr, pr);
	printf("______________________________________________________\n");
	printf("|Серединного треугольника|%13lf|%13f|\n", dsm, pm);
	printf("______________________________________________________\n");
	printf("|        Трапеции        |%13lf|%13f|\n", dst, pt);
	printf("______________________________________________________\n\n");
}

void fInp()
{
	FILE *f;
	f = fopen("1.txt", "w");
	if (f == NULL)
		printf("Ошибка. Не удалось открыть файл.\n");
	else
	{
		fprintf(f, "______________________________________________________\n");
		fprintf(f, "|    Метод вычисления    |  Результат  | Погрешность |\n");
		fprintf(f, "______________________________________________________\n");
		fprintf(f, "|  Левого треугольника   |%13lf|%13f|\n", dsl, pl);
		fprintf(f, "______________________________________________________\n");
		fprintf(f, "|  Правого треугольника  |%13lf|%13f|\n", dsr, pr);
		fprintf(f, "______________________________________________________\n");
		fprintf(f, "|Серединного треугольника|%13lf|%13f|\n", dsm, pm);
		fprintf(f, "______________________________________________________\n");
		fprintf(f, "|        Трапеции        |%13lf|%13f|\n", dst, pt);
		fprintf(f, "______________________________________________________\n");
		printf("\n\nТаблица успешно записана в файл.\n\n");
	}
	fclose(f);
}

int main()
{
	setlocale(LC_ALL, "Rus");
	int k = 0;
	while (k != 9)
	{
		printf("1. Ввод исходных данных.\n");
		printf("2. Расчет интеграла методом левого треугольника.\n");
		printf("3. Расчет интеграла методом правого треугольника.\n");
		printf("4. Расчет интеграла методом серединного треугольника.\n");
		printf("5. Расчет интеграла методом трапеции.\n");
		printf("6. Вывод всех результатов в виде таблицы.\n");
		printf("7. Запись таблицы в файл.\n");
		printf("8. Выход из программы.\n");
		printf("Выберите действие: ");
		while (!scanf_s("%d", &k) || (k < 1 || k>9) || (k % 1 != 0))
		{
			rewind(stdin);
			printf("Ошибка. Введите натуральное число от 1 до 9.\n");
		}
		switch (k)
		{
		case 1:
		{
			printf("\n Введите интервал (значения х1 и х2):\n");
			printf("\nВведите х1: ");
			scanf_s("%lf", &x1);
			printf("\nВведите х2: ");
			scanf_s("%lf", &x2);
			printf("\n");
		}
			break;
		case 2:leftT(x1, dx);
			break;
		case 3:rightT(x2, dx);
			break;
		case 4:midT(x1, x2, dx);
			break;
		case 5:Trap(x1, x2, dx);
			break;
		case 6:sr();
			read1();
			break;
		case 7:fInp();
			break;
		case 8:exit(0);
			break;
		}
	}
	system("pause");
}