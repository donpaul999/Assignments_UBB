#include "MyListTableModel.h"

int MyListTableModel::rowCount(const QModelIndex& parent) const
{
	return service.getWatchListLength();
}

int MyListTableModel::columnCount(const QModelIndex& parent) const
{
	return 5;
}

QVariant MyListTableModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int col = index.column();
	Movie movieUsed = service.userGetWatchList()[row];
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
	return QVariant();
}
