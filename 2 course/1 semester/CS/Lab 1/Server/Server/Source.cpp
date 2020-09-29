#pragma comment(lib, "Ws2_32.lib")

#include <winsock2.h>
#include <iostream>

using namespace std;

#pragma warning(disable: 4996)

int main()
{
	WSAData wsaData;
	WORD DDLVersion = MAKEWORD(2, 1);
	if (WSAStartup(DDLVersion, &wsaData) != 0)
	{
		cout << "Error 1." << endl;
		exit(1);
	}
	SOCKADDR_IN saddr;
	int size = sizeof(saddr);
	saddr.sin_addr.s_addr = inet_addr("127.0.0.1");
	saddr.sin_port = htons(1280);
	saddr.sin_family = AF_INET;
	SOCKET s = socket(AF_INET, SOCK_STREAM, NULL);
	bind(s, (SOCKADDR*)&saddr, sizeof(saddr));
	listen(s, SOMAXCONN);
	SOCKET NC;
	NC = accept(s, (SOCKADDR*)&saddr, &size);
	if (NC == 0)
		cout << "Error 2." << endl;
	else
	{
		cout << "Connection is established." << endl;
		char msg[256];
		char tr[] = "Point is from rectangle.";
		char fl[] = "Point is NOT from rectangle.";
		recv(NC, msg, sizeof(msg), NULL);
		int x = atoi(msg);
		cout << x << endl;
		recv(NC, msg, sizeof(msg), NULL);
		int y = atoi(msg);
		cout << y << endl;
		recv(NC, msg, sizeof(msg), NULL);
		int x1 = atoi(msg);
		cout << x1 << endl;
		recv(NC, msg, sizeof(msg), NULL);
		int y1 = atoi(msg);
		cout << y1 << endl;
		if ((x1 > 0) && (y1 > 0))
		{
			if ((x1 <= x) && (y1 <= y))
				strncpy(msg, tr, 25);
			else
				strncpy(msg, fl, 29);
		}
		else
			strncpy(msg, fl, 29);
		send(NC, msg, sizeof(msg), NULL);
	}
	system("pause");
}