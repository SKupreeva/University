#include "Header.h" 

int main()
{
	setlocale(LC_ALL, "Rus");
	int vibor = 0;
	while (vibor != 5)
	{
		printf("__�������� ��������:__\n");
		printf(" - (1)���� ����������.\n");
		printf(" - (2)�������� ����������.\n");
		printf(" - (3)�������� ������������ ����������.\n");
		printf(" - (4)���������� �� �����.\n");
		printf(" - (5)����� �� ���������.\n");
		printf("\n");
		scanf_s("%d", &vibor);
		switch (vibor)
		{
		case 1: enter();
			break;
		case 2: read();
			break;
		case 3: poisk();
			break;
		case 4: sort_sum();
			break;
		case 5: return 0;
			break;
		}
	}
	return 0;
}