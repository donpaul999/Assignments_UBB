#pragma once
#include "Service.h"
#include <assert.h>

void runTests();

void testMaterial();
void testCreateMaterial();
void testDestroyMaterial();
void testGetQuantity();
void testGetName();
void testGetID();
void testGetSupplier();


void testDynamicallyVector();
void testCreateDynamicallyVector();
void testDestroyDynamicallyVector();
void testSizeOfVector();
void testResizeDynamicallyVector();
void testAlmostFullCapacityUsed();
void testAppendDynamicallyVector();
void testDeleteByPosition();
void testUpdateByPosition();
void testGetElementByPosition();




void testService();
void testCreateService();
void testDestroyService();
void testRemoveMaterialService();
void testFindMaterialService();
void testUpdateMaterialService();
void testAddMaterialService();
void testReturnMaterialsWithNameService();
void testReturnMaterialsWithQuantityService();


void testRepository();
void testCreateRepository();
void testDestroyMaterialsList();
void testDestroyHistoryList();
void testDestroyRepository();
void testAddMaterial();
void testUpdateMaterial();
void testFindMaterial();
void testRemoveMaterial();
void testReturnMaterialsWithName();
void testReturnMaterialsWithQuantity();
void testUndo();
void testRedo();
