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
	SOCKET conn = socket(AF_INET, SOCK_STREAM, NULL);
	if (connect(conn, (SOCKADDR*)&saddr, sizeof(saddr)) != 0)
	{
		cout << "Error 3." << endl;
		return 1;
	}
	cout << "Client is connected." << endl;
	char msg[256];
	cout << "	For rectangle:" << endl << "Enter x: ";
	cin >> msg;
	send(conn, msg, sizeof(msg), NULL);
	cout << "Enter y: ";
	cin >> msg;
	send(conn, msg, sizeof(msg), NULL);
	cout << "	For point:" << endl << "Enter x: ";
	cin >> msg;
	send(conn, msg, sizeof(msg), NULL);
	cout << "Enter y: ";
	cin >> msg;
	send(conn, msg, sizeof(msg), NULL);
	recv(conn, msg, sizeof(msg), NULL);
	cout << endl << "	Result:" << endl << msg << endl;
	system("pause");
}