#include "TableModel.h"

//Send the data to the service
//Add in the model list if everything is ok
void TableModel::addInList(std::string name, std::string constellation, int RA, int Dec, int diameter)
{
	service.addStar(name, constellation, RA,  Dec,diameter);
	Star x(name, constellation, RA, Dec, diameter);
	starList.push_back(x);
	insertRows(starList.size(), 1);
}





bool TableModel::insertRows(int position, int rows, const QModelIndex& index)
{
	beginInsertRows(QModelIndex(), position, position + rows - 1);
	endInsertRows();
	return true;
}

bool TableModel::removeRows(int position, int rows, const QModelIndex& index)
{

	beginRemoveRows(QModelIndex(), position, position + rows - 1);
	endRemoveRows();
	return true;
}

int TableModel::rowCount(const QModelIndex& parent) const
{
	return starList.size();
	return 0;
}

int TableModel::columnCount(const QModelIndex& parent) const
{
	return 5;
}

QVariant TableModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int col = index.column();
	Star starUsed = starList[row];
	int likes = starUsed.getRA();
	int year = starUsed.getDec();
	int diam = starUsed.getDiameter();
	std::string likesString = std::to_string(likes);
	std::string yearString = std::to_string(year);
	std::string diamString = std::to_string(diam);
	if (role == Qt::DisplayRole)
	{
		switch (col)
		{
		case 0:
			return QString::fromStdString(starUsed.getName());
		case 1:
			return QString::fromStdString(starUsed.getConstellation());
		case 2:
			return QString::fromStdString(likesString);
		case 3:
			return QString::fromStdString(yearString);
		case 4:
			return QString::fromStdString(diamString);
		default:
			break;
		}
	}
	
	return QVariant();
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section) {
			case 0:
				return QString("Name");
			case 1:
				return QString("Constellation");
			case 2:
				return QString("RA");
			case 3:
				return QString("Dec");
			case 4:
				return QString("Diameter");
			default:
				break;
			}
		}
	}
	return QVariant();
}