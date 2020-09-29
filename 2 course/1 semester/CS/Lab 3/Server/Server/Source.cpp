#pragma comment(lib, "Ws2_32.lib")
//#define _WINSOCK_DEPRECATED_NO_WARNINGS 
#define _CRT_SECURE_NO_WARNINGS
#include <winsock2.h>
#include <iostream>
#include <cstring>
#include <stdio.h>

using namespace std;

struct teachers 
{
	char FIO[50];
	int exp;
	char deg[20];
	char sub[20];
};
struct teachers teacher;
struct teachers mas[30];

HANDLE CreateThread
(
	LPSECURITY_ATTRIBUTES lpThreadAttributes,
	DWORD dwStackSize,
	LPTHREAD_START_ROUTINE lpStartAddress,
	LPVOID lpParameter,
	DWORD dwCreationFlags,
	LPWORD lpThreadId
);

DWORD WINAPI ThreadFunc(LPVOID client_socket)
{
	SOCKET *s2 = ((SOCKET *)client_socket);
	char buf[100], buf1[100], s;
	while (recv(*s2, buf, sizeof(buf), 0) != 0)
	{
		int m = 0;
		buf1[0] = '\0';
		for (int i = 1; i <= 5; i++) 
		{
			if (strcmp(buf, mas[i].sub) == 0)
			{
				m++;
				if (mas[i].exp >= 5)
				{
					strcat_s(buf1, " ");
					strcat_s(buf1, mas[i].FIO);
				}
			}
		}
		if (m == 0)
			strcat_s(buf1, "\nПреподавателей не найдено.\n");
		else
			strcat_s(buf1, "\n");
		send(*s2, buf1, sizeof(buf1), 0);
	}
	closesocket(*s2);
	return 0;
}

int numcl = 0;

void print()
{
	if (numcl)
		cout << "Client connected - " << numcl << endl;
	else
		cout << "No clients connected!" << endl;
}

void main()
{
	strcpy_s(mas[1].FIO, "Фролова Н. А.");
	mas[1].exp = 6;
	strcpy_s(mas[1].deg, "Доцент");
	strcpy_s(mas[1].sub, "Math");

	strcpy_s(mas[2].FIO, "Савченко А. П.");
	mas[2].exp = 4;
	strcpy_s(mas[2].deg, "Магистр");
	strcpy_s(mas[2].sub, "English");

	strcpy_s(mas[3].FIO, "Кунцевич Р. Е.");
	mas[3].exp = 2;
	strcpy_s(mas[3].deg, "Магистр");
	strcpy_s(mas[3].sub, "Phisics");

	strcpy_s(mas[4].FIO, "Петров А. Н.");
	mas[4].exp = 15;
	strcpy_s(mas[4].deg, "Доцент");
	strcpy_s(mas[4].sub, "Math");

	strcpy_s(mas[5].FIO, "Аркушев Р. В.");
	mas[5].exp = 2;
	strcpy_s(mas[5].deg, "Магистр");
	strcpy_s(mas[5].sub, "Math");
	WORD wVersionRequested;
	WSADATA wsaData;
	int err;
	wVersionRequested = MAKEWORD(2, 2);
	err = WSAStartup(wVersionRequested, &wsaData);
	if (err != 0)
		return; 
	//создаем сокет
	SOCKET s = socket(AF_INET, SOCK_STREAM, 0);

	sockaddr_in local_addr;
	local_addr.sin_family = AF_INET;
	local_addr.sin_port = htons(1280);
	local_addr.sin_addr.s_addr = 0;
	//связываем сокет с адресом
	bind(s, (struct sockaddr*)&local_addr, sizeof(local_addr));

	//помещаем сокет в состояние прослушивания
	int c = listen(s, 5);
	cout << "Server is ready!" << endl;
	cout << endl;
	SOCKET client_socket;
	sockaddr_in client_addr;
	int client_addr_size = sizeof(client_addr);
	while (client_socket = accept(s, (sockaddr*)&client_addr, &client_addr_size))
	{
		numcl++;
		print();
		DWORD thID;
		CreateThread(NULL, NULL, ThreadFunc, &client_socket, NULL, &thID);
	}
}