#pragma once
#include "qabstractitemmodel.h"
#include "FileRepository.h"
class MyListTableModel : public QAbstractTableModel{

private:
	FileRepository* repo;
public:
	MyListTableModel(FileRepository* repo) : repo{ repo } {};
	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
};

