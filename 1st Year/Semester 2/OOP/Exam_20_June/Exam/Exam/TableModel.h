#pragma once
#include "Service.h"
#include "qabstractitemmodel.h"
class TableModel: public QAbstractTableModel
{
private:
	Service& service;
	std::vector<Star> starList;
public:
	TableModel(Service& service) : service{ service } {};
	void addInList(std::string name, std::string constellation, int RA, int Dec, int diameter);
	bool insertRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	bool removeRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
	void setStarList(std::vector<Star> list) {
		int firstLength = starList.size();
		starList = list;
		layoutChanged();
		 };
	void searchByName(std::string input) {
		std::vector<Star> stars = service.searchForStar(input);
		setStarList(stars);
	}
};

