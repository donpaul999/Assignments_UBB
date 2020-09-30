#include "TableModel.h"

void TableModel::addInList()
{
	service.addObject();
	//insertRows(service.userGetWatchList().size(), 1);

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
	//return service.userGetWatchList().size();
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
	/*Movie movieUsed = service.userGetWatchList()[row];
	int likes = movieUsed.getNumberOfLikes();
	int year = movieUsed.getYearOfRelease();
	std::string likesString = std::to_string(likes);
	std::string yearString = std::to_string(year);
	if (role == Qt::DisplayRole)
	{
		switch (col)
		{
		case 0:
			return QString::fromStdString(movieUsed.getTitle());
		case 1:
			return QString::fromStdString(movieUsed.getGenre());
		case 2:
			return QString::fromStdString(yearString);
		case 3:
			return QString::fromStdString(likesString);
		case 4:
			return QString::fromStdString(movieUsed.getTrailer());
		default:
			break;
		}
	}
	*/
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
				return QString("Title");
			case 1:
				return QString("Genre");
			case 2:
				return QString("Year");
			case 3:
				return QString("Nb. of Likes");
			case 4:
				return QString("Trailer");
			default:
				break;
			}
		}
	}
	return QVariant();
}