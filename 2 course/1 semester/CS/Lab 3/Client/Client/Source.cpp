#define _WINSOCK_DEPRECATED_NO_WARNINGS 

#include<stdio.h>
#include <iostream>
#include <WinSock2.h>
#include <string.h>

#pragma comment(lib,"Wsock32.lib")

using namespace std;

FILE *f_list;
struct teachers {
	char FIO[20];
	int exp;
	char deg[20];
	char sub[20];
};
struct teachers t;
struct teachers m[30];

void main()
{
	setlocale(0, "rus");
	SetConsoleCP(1251);
	SetConsoleOutputCP(1251);
	int end = 1;
	WORD wVersionRequested;
	WSADATA wsaData;
	int err;
	wVersionRequested = MAKEWORD(2, 2);
	err = WSAStartup(wVersionRequested, &wsaData);
	if (err != 0) { return; }
	while (true) {

		SOCKET s = socket(AF_INET, SOCK_STREAM, 0);
		sockaddr_in dest_addr;
		dest_addr.sin_family = AF_INET;
		dest_addr.sin_port = htons(1280);
		dest_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
		if (connect(s, (sockaddr *)&dest_addr, sizeof(dest_addr)) == -1) 
		{
			cout << "	Нет подключения к серверу!" << endl;
			return;
		}
		else
			cout << "	Клиент подключен к серверу!" << endl;
		connect(s, (sockaddr*)&dest_addr, sizeof(dest_addr));
		char buf[100], b[100];
		cout << "Введите название предмета: " << endl;
		cin >> buf;
		cin.get();
		send(s, buf, strlen(buf) + 1, 0);
		recv(s, buf, sizeof(buf), 0);
		cout << "Преподаватели, стаж которых больше 5 лет:" << buf << endl;
		closesocket(s);
	}
	WSACleanup();
	system("pause");
}