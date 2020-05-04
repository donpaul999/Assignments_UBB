//
// Created by Paul Colta on 04/05/2020.
//
#include <iostream>
#include "UI.h"
void UI::runApp(){
    std::string input;
    while(1){
        std::cin >> input;
        if(input == "exit")
            break;
        else
        if(input == "list")
            list();
        else
            if(input == "add")
                addUnit();
            if(input == "fileLocation")
                changeFile();
    }
};
void UI::addUnit()
{
    std::string console;
    getline(std::cin, console);
    std::vector<std::string> tokenized = explode(console, ", ");
    if(tokenized[1] == "Neonatal"){
        service.addNeonatalUnit(tokenized[0], stoi(tokenized[3]), stoi(tokenized[4]), stoi(tokenized[5]), stod(tokenized[6]));
    }
    else{
        service.addSurgery(tokenized[0], stoi(tokenized[2]), stoi(tokenized[3]));
}

}
void UI::list(){
    std::string console;
    getline(std::cin, console);
    std::vector<NeonatalUnit> listNeonatal;
    std::vector<Surgery> listSurgery;
    if(console.size() == 0) {
        listNeonatal = service.getAllNeonatalUnits();
        listSurgery = service.getAllSurgeries();
    }
    else{
        listNeonatal = service.getAllEfficientNeonatalUnits();
        listSurgery = service.getAllEfficientSurgery();
    }
    for(int i = 0; i < listNeonatal.size(); ++i){
        std::cout << listNeonatal[i].toString()<<'\n';
    }
    for(int i = 0; i < listSurgery.size(); ++i){
        std::cout << listSurgery[i].toString()<<'\n';
    }
};
void UI::changeFile(){
    std::string console;
    getline(std::cin, console);
    service.changeFile(console);
}

const std::vector<std::string>UI::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
{
    std::vector<std::string>trashValues = {};
    std::string partialStringObtained{ "" };
    std::vector<std::string> explodedString;

    for (auto iterator : stringToExplode)
    {
        if (iterator != separatorsUsed[0] && iterator != separatorsUsed[1])
            partialStringObtained += iterator;
        else
        if ((iterator == separatorsUsed[0] || iterator == separatorsUsed[1]) && partialStringObtained != "") {
            if (find(trashValues.begin(), trashValues.end(), partialStringObtained) == trashValues.end()) {
                explodedString.push_back(partialStringObtained);
            }
            partialStringObtained = "";
        }
    }
    if (partialStringObtained != "")
        explodedString.push_back(partialStringObtained);
    return explodedString;
}
UI::UI(bool isFileOrMem, Service newService){
    this->isFileOrMem = isFileOrMem;
    this->service = newService;
}