#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string>
#pragma comment(linker, "/SUBSYSTEM:windows /ENTRY:mainCRTStartup")


void HideConsole()
{
    ::ShowWindow(::GetConsoleWindow(), SW_HIDE);
}

int main(int argc, char* argv[])
{
    WinExec(("java -jar dstguimodcreator.jar"), SW_HIDE);
}