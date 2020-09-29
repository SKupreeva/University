#include <iostream>

using namespace std;
int l = 0;

template <class T1>
class Text
{
	T1 a;
public:
	Text() { a = 0; }
	void get(int A) { a = A;}
	T1 get() { return a;}
};

template <class T2>
class Tran
{
	T2 *that;
	T2 *prev;
public:
	Tran() :prev(NULL), that(new T2) {}  
	Tran(const Tran & obj) :
		that(new T2(*(obj.that))), prev(NULL) {}
	~Tran() { delete that;  delete prev; }
	Tran& operator=(const Tran & obj);
	void Show(int);
	int BeginTrans(); 
	void Commit();   
	void DeleteTrans();
	T2* operator->();
};

template <class T2>
Tran<T2>& Tran<T2>::operator=(const Tran<T2> & obj)
{
	if (this != &obj) 
	{
		delete that;
		that = new T2(*(obj.that));
	}
	return *this;
}

template <class T2>
T2* Tran<T2>::operator->()
{
	return that;
}

template <class T2>
void Tran<T2>::Show(int fl) 
{
	cout << "Ваше число ";
	if (!fl) cout << "до операции " << endl;
	else cout << "после выполнения операции " << endl;
	if (prev) cout << "prev = " << prev->get() << endl;
	else cout << "prev = NULL" << endl;
	cout << "that = " << that->get() << endl;
}

template <class T2>
int Tran<T2>::BeginTrans() 
{
	delete prev;  
	prev = that;                
	that = new T2(*prev); 
	if (!that) return 0;        
	that->get(l);               
	return 1;  
}

template <class T2>
void Tran<T2>::Commit()
{
	delete prev;                 
	prev = NULL;                
}

template <class T2>
void Tran<T2>::DeleteTrans()
{
	if (prev != NULL)
	{
		delete that;
		that = prev;
		prev = NULL;
	}
}

int main()
{
	Tran<Text<int> > t;
	setlocale(LC_ALL, "Rus");
	int k = 0, h = 0;
	cout << "Введите число, которое хотите отредактировать: ";
	while (!(scanf_s("%d", &k)) || (k < 1 || k > 100000000) || (k % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число." << endl;
	}
	cout << "Введите число, на которое Вы хотите его заменить: ";
	while (!(scanf_s("%d", &l)) || (l < 1 || l > 100000000) || (l % 1 != 0))
	{
		rewind(stdin);
		cout << "Ошибка. Введите натуральное число." << endl;
	}
	t->get(k);
	t.Show(0);
	if (t.BeginTrans())
	{
		t.Show(1);
		t.Commit();
	}
	t.DeleteTrans();
	system("pause");
}