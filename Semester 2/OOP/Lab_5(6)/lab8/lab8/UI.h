#pragma once
#include "AdminService.h"
#include "UserService.h"
#include <fstream>
#include <string>
class UI
{
private:
    AdminService& adminService;
    UserService& userService;
public:
    UI(AdminService& adminService, UserService& userService);
    void runAdmin();
    void runUser();
    void runApp();
    void uiAdminAdd();
    void uiAdminDelete();
    void uiAdminUpdate();
    void uiAdminChangeFile();
    void uiAdminList();
    void uiUserAdd();
    void uiUserWatchList();
    void uiUserChangeFile();
    void uiUserNext();
    void uiUserSave();
    void uiUserList();
    void uiAdminUndo();
    void uiAdminRedo();
};

