#pragma once
#include "qabstractitemmodel.h"
#include "UserService.h"

class MyListTableModel : public QAbstractTableModel{

private:
	UserService& service;
public:
	MyListTableModel(UserService& service) : service{ service } {};
	void addInWatchList(const std::string& title);
	bool insertRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	bool removeRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
	void changeRepositoryFileName(std::string fileName, std::string extension);
};

