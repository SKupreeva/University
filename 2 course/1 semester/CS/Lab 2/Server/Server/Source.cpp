#include <winsock2.h>
#pragma comment(lib, "ws2_32.lib")
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <string>
#include <Windows.h>

using namespace std;

int main()
{
	setlocale(LC_ALL, "Rus");
	WORD wVersionRequested;
	WSADATA wsaData;
	wVersionRequested = MAKEWORD(2, 2);
	if (WSAStartup(wVersionRequested, &wsaData) != 0)
	{
		cout << "Error1." << endl;
		exit(1);
	}
	SOCKET s = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	SOCKADDR_IN addr;
	addr.sin_addr.s_addr = htonl(INADDR_ANY);
	addr.sin_family = AF_INET;
	addr.sin_port = htons(9999);
	int l = sizeof(addr);
	bind((SOCKET)s, (LPSOCKADDR)&addr, l);
	char b[200];
	char a[200];
	char f = '\0';
	int rv = recvfrom(s, b, strlen(b), 0, (sockaddr*)&addr, &l);
	cout << "Connected." << endl;
	for (int i = 0; b[i] != '\0'; i++)
	{
		cout << b[i];
	}
	cout << endl;
	if (strlen(b) > 7)
	{
		for (int i = 0; i < 7; i++)
		{
			a[i+1] = b[i];
		}
		a[0] = '{';
		a[8] = '}';
		a[9] = '\0';
		sendto(s, a, lstrlen(b), 0, (sockaddr*)&addr, l);
	}
	else
	{
		for (int i = 0; i < strlen(b); i++)
		{
			a[i] = b[i];
		}
		sendto(s, a, lstrlen(b), 0, (sockaddr*)&addr, l);
	}
	closesocket(s);
	WSACleanup();
	system("pause");
}