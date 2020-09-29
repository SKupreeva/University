#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <string.h>

using namespace std;

class City
{
protected:
	char name_c[30];
public:
	City()
	{
		strcpy(name_c, "Минск");
		pr_n();
	}
	void pr_n()
	{
		cout << "Город - " << name_c << endl;
	}
	virtual void print() = 0;
	virtual void show() = 0;
};

class Product
{
public:
	char Name_p[30];
	char sort[30];
	int kol;
	int p;
	Product() {}
	Product(int K, int P)
	{
		/*cout << "Введите название товара: ";
		fflush(stdin);
		gets_s(Name_p);
		cout << "Введите сорт товара: ";
		fflush(stdin);
		gets_s(sort);*/
		strcpy(Name_p, "Смеситель");
		strcpy(sort, "Все для дома");
		kol = K;
		p = P;
		Menu();
	}
	void pr_p()
	{
		cout << "Название товара - " << Name_p << endl;
		cout << "Сорт товара - " << sort << endl;
		cout << "Сумма товара = " << p << endl;
		cout << "Доступное количество товара = " << kol << endl;
	}
	void Menu()
	{
		int k = 0;
		pr_p();
		cout << endl << "	Желаете:" << endl;
		cout << "1. Купить товар." << endl;
		cout << "2. Продать товар." << endl;
		cout << "3. Вернуть товар." << endl;
		cout << "4. Выйти." << endl;
		cout << "	Выберите действие: ";
		while (!(scanf_s("%d", &k))|| (k < 1) || (k > 4) || (k % 1 != 0))
		{
			cout << endl << "Ошибка. Введите натуральное число от 1 до 4." << endl;
			fflush(stdin);
		}
		switch (k)
		{
		case 1: buy();
			break;
		case 2: sell();
			break;
		case 3: ret();
			break;
		case 4: exit(1);
			break;
		}
	}
	void buy() 
	{
		pr_p();
		int k = 0;
		cout << endl << "	Желаете купить товар?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие: ";
		while (!(scanf_s("%d", &k)) || (k < 1) || (k > 2) || (k % 1 != 0))
		{
			cout << endl << "Ошибка. Введите натуральное число от 1 до 2." << endl;
			fflush(stdin);
		}
		switch (k)
		{
		case 1: cout << "Товар был успешно куплен." << endl << endl;
			break;
		case 2: cout << "Товар не был куплен." << endl << endl;
			break;
		}
	}
	void sell()
	{
		pr_p();
		int k = 0;
		cout << endl << "	Желаете продать товар?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие: ";
		while (!(scanf_s("%d", &k)) || (k < 1) || (k > 2) || (k % 1 != 0))
		{
			cout << endl << "Ошибка. Введите натуральное число от 1 до 2." << endl;
			fflush(stdin);
		}
		switch (k)
		{
		case 1: cout << "Товар был успешно продан." << endl << endl;
			break;
		case 2: cout << "Товар не был продан." << endl << endl;
			break;
		}
	}
	void ret()
	{
		pr_p();
		int k = 0;
		cout << endl << "	Желаете вернуть товар?" << endl;
		cout << "1. Да." << endl;
		cout << "2. Нет." << endl;
		cout << "	Выберите действие: ";
		while (!(scanf_s("%d", &k)) || (k < 1) || (k > 2) || (k % 1 != 0))
		{
			cout << endl << "Ошибка. Введите натуральное число от 1 до 2." << endl;
			fflush(stdin);
		}
		switch (k)
		{
		case 1: cout << "Товар был успешно возвращен." << endl << endl;
			break;
		case 2: cout << "Товар не был возвращен." << endl << endl;
			break;
		}
	}
};

class Shop : public City, public Product
{
protected:
	char name_s[30];
	char type[30];
public:
	Shop()
	{
		get_s_n();
		get_s_t();
		cout << endl << endl;
		show();
	}
	void get_s_n()
	{
		strcpy(name_s, "Пчелка");
		/*cout << "Введите название магазина: ";
		fflush(stdin);
		gets_s(name_s);*/
	}
	void get_s_t()
	{
		strcpy(type, "Стройматериалы");
		/*cout << "Введите тип магазина: ";
		fflush(stdin);
		gets_s(type);*/
	}
	void show()
	{
		cout << "Название магазина - " << name_s << endl;
		cout << "Тип магазина - " << type << endl;
	}
	void print() {}
};

class Bank : public City
{
protected:
	int n;
	int sum;
public:
	Bank() {}
	Bank(int N, int SUM)
	{
		n = N;
		sum = SUM;
		cout << endl << endl;
		pr();
	}
	void pr()
	{
		cout << "Номер счета в банке - " << n << endl;
		cout << "Баланс на данном счету = " << sum << endl;
	}
	virtual void show() {};
	virtual void print() {};
};

class Costumer: public Shop, public Bank
{
protected:
	char fam[30];
	int sum_d;
	int sum_p;
public:
	Costumer(int SUM_D, int SUM_P)
	{
		/*cout << "Введите фамилию покупателя: ";
		fflush(stdin);
		gets_s(fam);*/
		strcpy(fam, "Печанский");
		sum_d = SUM_D;
		sum_p = SUM_P;
		cout << endl << endl;
		print();
	}
	void print()
	{
		cout << "Фамилия покупателя - " << fam << endl;
		cout << "Сумма денег покупателя = " << sum_d << endl;
		cout << "Сумма покупки = " << sum_p << endl;
	}
};

int main()
{
	setlocale(LC_ALL, "Russian");
	City *c[2];
	Product p(12334, 130);
	Shop S;
	Bank B(123435, 12000);
	Costumer C(13000, 456);
	c[0] = &S;
	c[1] = &B;
	for (int i = 0; i < 2; i++)
	{
		c[i]->print();
		c[i]->show();
	}
	cout << endl << endl;
	system("pause");
}