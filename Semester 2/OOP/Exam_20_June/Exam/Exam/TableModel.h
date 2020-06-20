#pragma once
#include "Service.h"
#include "qabstractitemmodel.h"
class TableModel: public QAbstractTableModel
{
private:
	Service& service;
public:
	TableModel(Service& service) : service{ service } {};
	void addInList();
	bool insertRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	bool removeRows(int position, int rows, const QModelIndex& index = QModelIndex()) override;
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
};

