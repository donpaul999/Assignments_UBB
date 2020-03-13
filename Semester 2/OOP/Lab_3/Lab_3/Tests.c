#include "Tests.h"
#pragma once

void runTests()
{
	testMaterial();
	testDynamicallyVector();
	testService();
	testRepository();
}

void testMaterial()
{
	
	testCreateMaterial();
	testDestroyMaterial();
	testGetQuantity();
	testGetName();
	testGetID();
	testGetSupplier();
}

void testCreateMaterial()
{
	Material* materialUsed = createMaterial(5, "Shop", "Test", 123);
	assert(getID(materialUsed) == 5);
	assert(strcmp(getSupplier(materialUsed), "Shop") == 0);
	assert(strcmp(getName(materialUsed), "Test") == 0);
	assert(getQuantity(materialUsed) == 123);
	destroyMaterial(materialUsed);
}

void testDestroyMaterial()
{
	Material* materialUsed = createMaterial(5, "Shop", "Test", 123);
	destroyMaterial(materialUsed);
}

void testGetQuantity()
{
	Material* materialUsed = createMaterial(5, "Shop", "Test", 200);
	assert(getQuantity(materialUsed) == 200);
	destroyMaterial(materialUsed);

}

void testGetName()
{
	Material* materialUsed = createMaterial(5, "Shop", "Ana", 123);
	assert(strcmp(getName(materialUsed), "Ana") == 0);
	destroyMaterial(materialUsed);
}

void testGetID()
{
	Material* materialUsed = createMaterial(10, "Shop", "Test", 123);
	assert(getID(materialUsed) == 10);
	destroyMaterial(materialUsed);
}

void testGetSupplier()
{
	Material* materialUsed = createMaterial(5, "Shop", "Test", 123);
	assert(strcmp(getSupplier(materialUsed), "Shop") == 0);
	destroyMaterial(materialUsed);
}

void testDynamicallyVector()
{
	testCreateDynamicallyVector();
	testDestroyDynamicallyVector();
	testSizeOfVector();
	testResizeDynamicallyVector();
	testAlmostFullCapacityUsed();
	testAppendDynamicallyVector();
	testDeleteByPosition();
	testUpdateByPosition();
	testGetElementByPosition();
}

void testCreateDynamicallyVector()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	assert(vectorTest->length == 0);
	assert(vectorTest->capacity == 1);
	destroyDynamicallyVector(&vectorTest);
}

void testDestroyDynamicallyVector()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	destroyDynamicallyVector(&vectorTest);

}

void testSizeOfVector()
{

	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	assert(sizeOfVector(vectorTest) == 0);
	int valuesToAppend[] = { 2, 4, 16, 256 };
	for (int i = 0; i < 4; ++i) {
		appendDynamicallyVector(vectorTest, &valuesToAppend[i]);
		assert(sizeOfVector(vectorTest) == i + 1);
	}
	destroyDynamicallyVector(&vectorTest);

}

void testResizeDynamicallyVector()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(5);
	assert(vectorTest->length == 0);
	assert(vectorTest->capacity == 5);
	destroyDynamicallyVector(&vectorTest);

}

void testAlmostFullCapacityUsed()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	assert(vectorTest->length == 0);
	int valueToAppend = 100;
	assert(almostFullCapacityUsed(vectorTest) == 0);
	appendDynamicallyVector(vectorTest, &valueToAppend);
	assert(almostFullCapacityUsed(vectorTest) == 1);
	destroyDynamicallyVector(&vectorTest);

}

void testAppendDynamicallyVector()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	int valuesToAppend[] = { 2, 4, 16, 256 };
	for (int i = 0; i < 4; ++i) {
		appendDynamicallyVector(vectorTest, &valuesToAppend[i]);
		assert(vectorTest->length == i + 1);
	}
	destroyDynamicallyVector(&vectorTest);

}

void testDeleteByPosition()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	int valuesToAppend[] = { 2, 4, 16, 256 };
	for (int i = 0; i < 4; ++i) {
		appendDynamicallyVector(vectorTest, &valuesToAppend[i]);
	}
	deleteByPosition(vectorTest, 2);
	deleteByPosition(vectorTest, 2);
	assert(vectorTest->length == 2);
	destroyDynamicallyVector(&vectorTest);

}

void testUpdateByPosition()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	int valuesToAppend[] = { 2, 4, 16, 256 };
	for (int i = 0; i < 4; ++i) {
		appendDynamicallyVector(vectorTest, &valuesToAppend[i]);
	}
	int* valueToUpdateWith = 5;
	updateByPosition(vectorTest, 1, &valueToUpdateWith);
	int* valueAtPosition = (int*)getElementByPosition(vectorTest, 1);
	assert(*valueAtPosition == 5);
	destroyDynamicallyVector(&vectorTest);

}

void testGetElementByPosition()
{
	DynamicallyVector* vectorTest = createDynamicallyVector(1);
	int valuesToAppend[] = { 2, 4, 16, 256 };
	for (int i = 0; i < 4; ++i) {
		appendDynamicallyVector(vectorTest, &valuesToAppend[i]);
	}
	int* valueAtPosition = (int*)getElementByPosition(vectorTest, 2);
	assert(*valueAtPosition == 16);
	destroyDynamicallyVector(&vectorTest);

}

void testService()
{
	testCreateService();
	testDestroyService();
	testRemoveMaterialService();
	testFindMaterialService();
	testUpdateMaterialService();
	testAddMaterialService();
	testReturnMaterialsWithNameService();
	testReturnMaterialsWithQuantityService();

}

void testCreateService()
{
	Repository* repoTest = createRepository(8);
	Service* serviceTest = createService(repoTest);
	int lengthOfList = sizeOfVector(serviceTest->repo->materialsList);
	assert(lengthOfList == 0);
	destroyService(serviceTest);
}

void testDestroyService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	destroyService(serviceTest);
}

void testRemoveMaterialService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	deleteByPosition(serviceTest->repo->materialsList, 0);
	int lengthOfList = sizeOfVector(serviceTest->repo->materialsList);
	assert(lengthOfList == 0);
	destroyService(serviceTest);
}

void testFindMaterialService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	int positionWhereFound = findMaterial(serviceTest->repo, 1);
	assert(positionWhereFound == 0);
	destroyService(serviceTest);
}

void testUpdateMaterialService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	updateMaterialService(serviceTest, 1, "Maria", "mere", 123, 0);
	Material* materialUsed = getElementByPosition(serviceTest->repo->materialsList, 0);
	assert(strcmp(materialUsed->supplier), "Maria" == 0);
	destroyService(serviceTest);
}

void testAddMaterialService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	int lengthOfList = sizeOfVector(serviceTest->repo->materialsList);
	assert(lengthOfList == 1);
	destroyService(serviceTest);
}

void testReturnMaterialsWithNameService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	addMaterialService(serviceTest, 2, "Maria", "mere", 123, 0);
	addMaterialService(serviceTest, 3, "Ioana", "pere", 123, 0);
	int *lengthOfFiltered;
	DynamicallyVector* vectorTest = returnMaterialsWithNameService(serviceTest, &lengthOfFiltered, "mere");
	assert(lengthOfFiltered == 2);
	destroyService(serviceTest);
}

void testReturnMaterialsWithQuantityService()
{
	Repository* repoTest = createRepository(10);
	Service* serviceTest = createService(repoTest);
	addMaterialService(serviceTest, 1, "Ana", "mere", 123, 0);
	addMaterialService(serviceTest, 2, "Maria", "mere", 123, 0);
	addMaterialService(serviceTest, 3, "Ioana", "pere", 200, 0);
	int *lengthOfFiltered;
	DynamicallyVector* vectorTest = returnMaterialsWithQuantityService(serviceTest, &lengthOfFiltered, 125);
	assert(lengthOfFiltered == 2);
	destroyService(serviceTest);
}

void testRepository()
{
	testCreateRepository();
	testDestroyMaterialsList();
	testDestroyHistoryList();
	testDestroyRepository();
	//testAddMaterial();
	//testUpdateMaterial();
	//testFindMaterial();
	//testRemoveMaterial();
	//testReturnMaterialsWithName();
	//testReturnMaterialsWithQuantity();

}

void testCreateRepository()
{
	Repository* repositoryTest = createRepository(10);
	assert(sizeOfVector(repositoryTest->materialsList) == 0);
	destroyRepository(repositoryTest);
}

void testDestroyMaterialsList()
{
	Repository* repositoryTest = createRepository(10);
	destroyMaterialsList(repositoryTest->materialsList);
	destroyRepository(repositoryTest);

}

void testDestroyHistoryList()
{
	Repository* repositoryTest = createRepository(10);
	destroyHistoryList(repositoryTest->historyList);
	destroyRepository(repositoryTest);

}

void testDestroyRepository()
{
	Repository* repositoryTest = createRepository(10);
	destroyRepository(repositoryTest);
}

void testAddMaterial()
{
	Repository* repositoryTest = createRepository(10);
	Material* materialUsed = createMaterial(1, "Ana", "mere", 123);
	int isFunctionSuccesful;
	isFunctionSuccesful = addMaterial(repositoryTest, materialUsed, 0);
	assert(sizeOfVector(repositoryTest->materialsList) == 1 && isFunctionSuccesful);
	destroyMaterial(materialUsed);
	destroyRepository(repositoryTest);
}

void testUpdateMaterial()
{
	Repository* repositoryTest = createRepository(10);
	Material* firstMaterialUsed = createMaterial(1, "Ana", "mere", 123);
	addMaterial(repositoryTest, firstMaterialUsed, 0);
	Material* secondMaterialUsed = createMaterial(1, "Ioana", "pere", 123);
	updateMaterial(repositoryTest, secondMaterialUsed, 0);
	Material* elementReturned = getElementByPosition(repositoryTest->materialsList, 0);
	assert(strcmp(elementReturned->name, "pere") == 0);
	destroyRepository(repositoryTest);
	destroyMaterial(firstMaterialUsed);
	destroyMaterial(secondMaterialUsed);

}

void testFindMaterial()
{
	Repository* repositoryTest = createRepository(10);
	Material* materialUsed = createMaterial(1, "Ana", "mere", 123);
	addMaterial(repositoryTest, materialUsed, 0);
	int firstPositionFound = findMaterial(repositoryTest, 1);
	assert(firstPositionFound == 0);
	int secondPositionFound = findMaterial(repositoryTest, 2);
	assert(secondPositionFound == -1);
	destroyRepository(repositoryTest);
	destroyMaterial(materialUsed);
}

void testRemoveMaterial()
{
	Repository* repositoryTest = createRepository(10);
	Material* firstMaterialUsed = createMaterial(1, "Ana", "mere", 123);
	addMaterial(repositoryTest, firstMaterialUsed, 0);
	Material* secondMaterialUsed = createMaterial(2, "Ioana", "pere", 123);
	addMaterial(repositoryTest, secondMaterialUsed, 0);
	removeMaterial(repositoryTest, 1, 0);
	assert(sizeOfVector(repositoryTest->materialsList) == 1);
	destroyRepository(repositoryTest);
	destroyMaterial(firstMaterialUsed);
	destroyMaterial(secondMaterialUsed);

}

void testReturnMaterialsWithName()
{
	Repository* repoTest = createRepository(10);
	Material* firstMaterialUsed = createMaterial(1, "Ana", "mere", 123);
	Material* secondMaterialUsed = createMaterial(2, "Maria", "mere", 123);
	Material* thirdMaterialUsed = createMaterial(3, "Ioana", "pere", 123);
	addMaterial(repoTest,firstMaterialUsed, 0);
	addMaterial(repoTest, secondMaterialUsed, 0);
	addMaterial(repoTest, thirdMaterialUsed, 0);
	int* lengthOfFiltered = 0;
	DynamicallyVector* vectorTest = returnMaterialsWithName(repoTest, &lengthOfFiltered, "mere");
	Material* fourthMaterialUsed = getElementByPosition(vectorTest, 1);
	assert(strcmp(fourthMaterialUsed->name, "Ana") == 0);	destroyRepository(repoTest);
	destroyMaterial(firstMaterialUsed);
	destroyMaterial(secondMaterialUsed);
	destroyMaterial(thirdMaterialUsed);
	destroyMaterial(fourthMaterialUsed);

}

void testReturnMaterialsWithQuantity()
{
	Repository* repoTest = createRepository(10);
	Material* firstMaterialUsed = createMaterial(1, "Ana", "mere", 123);
	Material* secondMaterialUsed = createMaterial(2, "Maria", "mere", 124);
	Material* thirdMaterialUsed = createMaterial(3, "Ioana", "pere", 523);
	addMaterial(repoTest, firstMaterialUsed, 0);
	addMaterial(repoTest, secondMaterialUsed, 0);
	addMaterial(repoTest, thirdMaterialUsed, 0);
	int* lengthOfFiltered = 0;
	DynamicallyVector* vectorTest = returnMaterialsWithQuantity(repoTest, &lengthOfFiltered, 125);
	Material* fourthMaterialUsed = getElementByPosition(vectorTest, 1);
	assert(strcmp(fourthMaterialUsed->name, "Ana") == 0);
	destroyRepository(repoTest);
	destroyMaterial(firstMaterialUsed);
	destroyMaterial(secondMaterialUsed);
	destroyMaterial(thirdMaterialUsed);
	destroyMaterial(fourthMaterialUsed);
}

