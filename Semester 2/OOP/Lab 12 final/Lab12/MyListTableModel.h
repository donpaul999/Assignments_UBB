#pragma once
#include "qabstractitemmodel.h"
#include "UserService.h"

class MyListTableModel : public QAbstractTableModel{

private:
	UserService service;
public:
	MyListTableModel(UserService service) : service{ service } {};
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
};

