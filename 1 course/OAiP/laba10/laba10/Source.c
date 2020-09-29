#include<stdio.h>
#include<math.h>
#include<locale.h>
#define PI 3.14

struct pryamougolny
{
	float x;
	float y;
};
struct pryamougolny dekart;

struct polarny
{
	float r;
	float A;
};
struct polarny polar;

void PolarToDekart(float r, float A)
{
	A = A * PI / 180;
	dekart.x = r * cos(A);
	dekart.y = r * sin(A);
	printf("Координаты в прямоугольной системе:\n       x         y\n\n  %f     %f\n\n", dekart.x, dekart.y);
	return dekart;
}

#define func PolarToDekart(r, A)

void main()
{
	setlocale(LC_ALL, "Rus");
	float r, A;
	printf("Введите значение r:\n");
	scanf_s("%f", &r);
	printf("Введите значение угла A(в градусах):\n");
	scanf_s("%f", &A);
	func;
	system("pause");
}