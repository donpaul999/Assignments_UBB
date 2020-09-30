//
// Created by Paul Colta on 04/05/2020.
//

#include "Repository.h"

void Repository::addNeonatalUnit(NeonatalUnit newUnit){
    listOfNeonatalUnits.push_back(newUnit);
    if(inFileOrMemory == 1)
        writeFile();
}


void Repository::addSurgery(Surgery newSurgery){
    listOfSurgeries.push_back(newSurgery);
    if(inFileOrMemory == 1)
        writeFile();
}

std::vector <NeonatalUnit> Repository::getAllNeonatalUnits(){
    return listOfNeonatalUnits;
}

std::vector <Surgery> Repository::getAllSurgeries(){
    return  listOfSurgeries;
}

std::vector <NeonatalUnit> Repository::getAllEfficientNeonatalUnits(){
    std::vector <NeonatalUnit> newList = {};
    for(int i = 0; i < listOfNeonatalUnits.size(); ++i){
        if(listOfNeonatalUnits[i].isEfficient())
            newList.push_back(listOfNeonatalUnits[i]);
    }
    return newList;
}
std::vector <Surgery>  Repository::getAllEfficientSurgery(){
    std::vector <Surgery> newList = {};
    for(int i = 0; i < listOfSurgeries.size(); ++i){
        if(listOfSurgeries[i].isEfficient())
            newList.push_back(listOfSurgeries[i]);
    }
    return newList;
}
void Repository::writeFile() {
    std::ofstream fout(fileName);
    for(int i = 0; i < listOfNeonatalUnits.size(); ++i) {
        fout << listOfNeonatalUnits[i].toString() << '\n';
    }
    for(int i = 0; i < listOfSurgeries.size(); ++i) {
        fout << listOfSurgeries[i].toString() << '\n';
    }
}