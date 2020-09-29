#include <winsock2.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <string>
#include <WS2tcpip.h>
#include <Windows.h>

#pragma comment(lib, "ws2_32.lib")

using namespace std;

int main()
{
	setlocale(LC_ALL, "Rus");
	WORD wVersionRequested;
	WSADATA wsaData;
	wVersionRequested = MAKEWORD(2, 2);
	if (WSAStartup(wVersionRequested, &wsaData) != 0) {
		cout << "Error1." << endl;
		exit(1);
	}
	SOCKET s = socket(AF_INET, SOCK_DGRAM, 0);
	SOCKADDR_IN addr;
	inet_pton(AF_INET, "127.0.0.1", &(addr.sin_addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(9999);
	int l = sizeof(addr);
	int k = 0;
	char b[200];
	char buf[256];
	cout  << "Введите строку: ";
	gets_s(b);
	cout << "Длина строки = " << strlen(b) << endl;
	sendto(s, b, strlen(b), 0, (sockaddr*)&addr, l);
	int rv = recvfrom(s, b, lstrlen(b), 0, (sockaddr*)&addr, &l);	
	cout << "Новая строка: ";
	for (int i = 0; b[i] != '\0'; i++)
	{	
		cout << b[i];
	}
	cout << endl;
	closesocket(s);
	WSACleanup();
	system("pause");
}