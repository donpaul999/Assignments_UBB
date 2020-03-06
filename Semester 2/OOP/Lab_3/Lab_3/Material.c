#include "Material.h"
#include <string.h>
#include <assert.h>
Material createMaterial(int id, char supplier[], char name[], double quantity)
{
	Material object;
	object.id = id;
	strcpy(object.supplier, supplier);
	strcpy(object.name, name);
	object.quantity = quantity;
	return object;
}

double getQuantity(Material p)
{
	return p.quantity;
}

char* getName(Material* p)
{
	return p->name;
}

int getID(Material p)
{
	return p.id;
}

char* getSupplier(Material* p)
{
	return p->supplier;
}

void testMaterial()
{
	Material p;
	p = createMaterial(1,"grain", "test", 123254.2);
	assert(getQuantity(p) == 123254.2);
	assert(!strcmp(getName(&p), "grain"));
	assert(!strcmp(getSupplier(&p), "test"));
}
