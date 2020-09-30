#pragma once

#include <string>
class Car
{
private:
	std::string manufacturer, model,color;
	int yearOfFabriq;
public:
	Car(std::string manufacturer="", std::string model="", int year=0, std::string color="") {
		this->manufacturer = manufacturer;
		this->model = model;
		this->yearOfFabriq = year;
		this->color = color;
	}
	std::string getManufacturer() { return this->manufacturer; };
	std::string getColor() { return this->color; };
	std::string getOutputForm() { std::string output = getManufacturer() + " | " + getColor(); return output; };
	bool operator==(const Car& carToCheck) const { return manufacturer == carToCheck.manufacturer && model == carToCheck.model && color == carToCheck.color && yearOfFabriq == carToCheck.yearOfFabriq; };
};

