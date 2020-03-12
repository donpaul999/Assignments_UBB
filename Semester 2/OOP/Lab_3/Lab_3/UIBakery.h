#pragma once
#include "Service.h"
#include <string.h>
typedef struct {
    Service* bakeryService;
}BakeryUI;

BakeryUI* createUI(Service* service);
void destroyUI(BakeryUI* bakeryUI);
void startAppUI(BakeryUI* bakeryUI);
void uiAddMaterial(BakeryUI* bakeryUI);
void uiUpdateMaterial(BakeryUI* bakeryUI);
void uiDeleteMaterial(BakeryUI* bakeryUI);
void uiListMaterials(BakeryUI* bakeryUI);
void uiUndo(BakeryUI* bakeryUI);
void uiRedo(BakeryUI* bakeryUI);
