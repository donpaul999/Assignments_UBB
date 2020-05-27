#include "MyListTableModel.h"
#include <iostream>
void MyListTableModel::addInWatchList(const std::string& title)
{
	service.addMovieToWatchListByTitle(title);
	insertRows(service.userGetWatchList().size(), 1);

}



void MyListTableModel::changeRepositoryFileName(std::string fileName, std::string extension)
{

	int firstLength = service.userGetWatchList().size();
	service.changeRepositoryFileName(fileName, extension);
	int secondLength = service.userGetWatchList().size();
	if (firstLength - secondLength > 0)
		removeRows(secondLength, firstLength - secondLength);
	else
		insertRows(firstLength, secondLength - firstLength);
}


bool MyListTableModel::insertRows(int position, int rows, const QModelIndex& index)
{
	beginInsertRows(QModelIndex(), position, position + rows - 1);
	endInsertRows();
	return true;
}

bool MyListTableModel::removeRows(int position, int rows, const QModelIndex& index)
{
	
	beginRemoveRows(QModelIndex(), position, position + rows - 1);
	endRemoveRows();
	return true;
}

int MyListTableModel::rowCount(const QModelIndex& parent) const
{
	return service.userGetWatchList().size();
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

QVariant MyListTableModel::headerData(int section, Qt::Orientation orientation, int role) const
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
