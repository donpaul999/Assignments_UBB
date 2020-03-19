#include "Tests.h"
#include <iostream>
void Tests::runAllTests()
{	
	runMovieTests();
	runDynamicArrayTests();
	runRepositoryTests();
	runAdminServiceTests();
	runUserServiceTests();
}

void Tests::runMovieTests()
{
	Movie_AnyMovie_MovieCreated();
}

void Tests::runDynamicArrayTests()
{
	 DynamicVector_AnyVector_VectorCreated();
	 resizeElementsList_AnyVector_VectorResized();
	 searchElementInList_ElementInList_ReturnsTrue();
	 searchElementInList_ElementNotInList_ReturnsFalse();
	 append_ElementNotInList_ElementAppended();
	 append_AnyElement_ElementAppended();
	 remove_ElementInTheList_ElementRemoved();
	 remove_ElementNotInTheList_SizeRemainsTheSame();
	 update_ElementInTheList_ElementUpdated();
	 operatorPosition_ValidPosition_ElementReturned();
	 operatorPosition_InvalidPosition_ExceptionReturned();
	 size_AnyVector_CorrectSize();
	 operatorEqual_AnyVector_CorrectAssignment();
}

void Tests::runRepositoryTests()
{
	 Repository_AnyRepository_RepositoryCreated();
	 addMovie_MovieNotInTheList_ReturnsOne();
	 addMovie_MovieInTheList_ReturnsMinusOne();
	 deleteMovie_MovieInTheList_ReturnsOne();
	 deleteMovie_MovieNotInTheList_ReturnsMinusOne();
	 update_MovieInTheList_ReturnsOne();
	 update_MovieNotInTheList_ReturnsMinusOne();
	 getNumberOfMovies_AnyRepository_CorrectNumberOfMovies();
	 getMovieAtPosition_ValidPosition_ReturnsMovie();
	 getMovieAtPosition_InValidPosition_ReturnsException();
}

void Tests::runAdminServiceTests()
{
	 AdminService_AnyAdminService_AdminServiceCreated();
	 adminAddMovie_MovieNotInTheList_ReturnsOne();
	 adminAddMovie_MovieInTheList_ReturnsMinusOne();
	 adminDeleteMovie_MovieInTheList_ReturnsOne();
	 adminDeleteMovie_MovieNotInTheList_ReturnsMinusOne();
	 adminUpdate_MovieInTheList_ReturnsOne();
	 adminUpdate_MovieNotInTheList_ReturnsMinusOne();
	 adminGetMovieList_AnyAdminService_CorrectMovies();
}

void Tests::runUserServiceTests()
{
	UserService_AnyUserService_UserServiceCreated();
	userGetMovieList_AnyUserService_CorrectMovies();
}

void Tests::Movie_AnyMovie_MovieCreated()
{
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	assert(movieUsed.getTitle() == "Test");
	assert(movieUsed.getGenre() == "CategoryTest");
	assert(movieUsed.getYearOfRelease() == 123);
	assert(movieUsed.getNumberOfLikes() == 456);
	assert(movieUsed.getTrailer() == "TrailerTest");

}

void Tests::DynamicVector_AnyVector_VectorCreated()
{
	DynamicVector<int> vectorUsed{ 5 };
	assert(vectorUsed.capacity() == 5);

}

void Tests::resizeElementsList_AnyVector_VectorResized()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.resizeElementsList();
	assert(vectorUsed.capacity() == 4);
}

void Tests::searchElementInList_ElementInList_ReturnsTrue()
{
	DynamicVector<int> vectorUsed{ 2 };
	int firstElementToAdd = 1, secondElementToAdd = 5;
	vectorUsed.append(firstElementToAdd);
	vectorUsed.append(secondElementToAdd);
	assert(vectorUsed.searchElementInList(5) == 1);
}

void Tests::searchElementInList_ElementNotInList_ReturnsFalse()
{
	DynamicVector<int> vectorUsed{ 2 };
	int firstElementToAdd = 1, secondElementToAdd = 4;
	vectorUsed.append(firstElementToAdd);
	vectorUsed.append(secondElementToAdd);
	assert(vectorUsed.searchElementInList(4) == 1);
}

void Tests::append_ElementNotInList_ElementAppended()
{
	DynamicVector<int> vectorUsed{ 2 };
	int firstElementToAdd = 1, secondElementToAdd = 5;
	vectorUsed.append(firstElementToAdd);
	assert(vectorUsed.searchElementInList(5) == -1);
	vectorUsed.append(secondElementToAdd);	
	assert(vectorUsed.searchElementInList(5) == 1);
}

void Tests::append_AnyElement_ElementAppended()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	assert(vectorUsed.searchElementInList(1) == 0);
	vectorUsed.append(1);
	assert(vectorUsed.size() == 2);
}

void Tests::remove_ElementInTheList_ElementRemoved()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	assert(vectorUsed.searchElementInList(1) == 0);
	vectorUsed.remove(1);
	assert(vectorUsed.searchElementInList(1) == -1);

}

void Tests::remove_ElementNotInTheList_SizeRemainsTheSame()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	assert(vectorUsed.searchElementInList(1) == 0);
	vectorUsed.remove(2);
	assert(vectorUsed.size() == 1);
}

void Tests::update_ElementInTheList_ElementUpdated()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	assert(vectorUsed.searchElementInList(1) == 0);
	vectorUsed.update(1, 2);
	assert(vectorUsed.searchElementInList(1) == -1);
	assert(vectorUsed.searchElementInList(2) == 0);
}

void Tests::operatorPosition_ValidPosition_ElementReturned()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(5);
	assert(vectorUsed[0] == 5);
}

void Tests::operatorPosition_InvalidPosition_ExceptionReturned()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(5);
	try {
		int x = vectorUsed[1];
		assert(false);
	}
	catch(std::exception Exception){
		assert(true);
	}
}

void Tests::size_AnyVector_CorrectSize()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	vectorUsed.append(2);
	assert(vectorUsed.size() == 2);
}

void Tests::operatorEqual_AnyVector_CorrectAssignment()
{
	DynamicVector<int> firstVectorUsed{ 2 };
	firstVectorUsed.append(1);
	firstVectorUsed.append(2);
	DynamicVector<int> secondVectorUsed{ 2 };
	secondVectorUsed = firstVectorUsed;
	assert(secondVectorUsed.searchElementInList(2) == 1);
	assert(secondVectorUsed.searchElementInList(1) == 0);
}

void Tests::Repository_AnyRepository_RepositoryCreated()
{
	Repository* repositoryUsed = new Repository();
}

void Tests::addMovie_MovieNotInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	assert(repositoryUsed->addMovie(movieUsed) == 1);
}

void Tests::addMovie_MovieInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->addMovie(movieUsed) == -1);
}

void Tests::deleteMovie_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->deleteMovie(movieUsed) == 1);
}

void Tests::deleteMovie_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	assert(repositoryUsed->deleteMovie(movieUsed) == -1);
}

void Tests::update_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->updateMovie(movieUsed) == 1);
}

void Tests::update_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	assert(repositoryUsed->updateMovie(movieUsed) == -1);
}

void Tests::getMovieAtPosition_ValidPosition_ReturnsMovie()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->getMovieAtPosition(0) == movieUsed);
}

void Tests::getMovieAtPosition_InValidPosition_ReturnsException()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	try {
		Movie movieUsed = repositoryUsed->getMovieAtPosition(-1);
		assert(false);
	}
	catch (std::exception Exception) {
		assert(true);
	}
}

void Tests::getNumberOfMovies_AnyRepository_CorrectNumberOfMovies()
{
	Repository* repositoryUsed = new Repository();
	Movie movieUsed = { "Test", "CategoryTest", 123, 456, "TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->getNumberOfMovies() == 1);
}

void Tests::AdminService_AnyAdminService_AdminServiceCreated()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
}

void Tests::adminAddMovie_MovieNotInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = { *repositoryUsed };
	assert(adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest") == 1);
}

void Tests::adminAddMovie_MovieInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest");
	assert(adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest") == -1);
}

void Tests::adminDeleteMovie_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest");
	assert(adminServiceUsed.adminDeleteMovie("Test") == 1);
}

void Tests::adminDeleteMovie_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	assert(adminServiceUsed.adminDeleteMovie("Test") == -1);
}

void Tests::adminUpdate_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest");
	assert(adminServiceUsed.adminUpdateMovie("Test", "CategoryTest", 123, 456, "TrailerTest") == 1);
}

void Tests::adminUpdate_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	assert(adminServiceUsed.adminUpdateMovie("Test", "CategoryTest", 123, 456, "TrailerTest") == -1);
}

void Tests::adminGetMovieList_AnyAdminService_CorrectMovies()
{
	Repository* repositoryUsed = new Repository();
	AdminService adminServiceUsed = {*repositoryUsed};
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest");
	std::vector<Movie>listOfMovies = adminServiceUsed.adminGetMovieList();
	assert(listOfMovies.size() == 1);
}

void Tests::UserService_AnyUserService_UserServiceCreated()
{
	Repository* repositoryUsed = new Repository();
	UserService userServiceUsed = {*repositoryUsed};
}

void Tests::userGetMovieList_AnyUserService_CorrectMovies()
{
	Repository* repositoryUsed = new Repository();
	UserService userServiceUsed = { *repositoryUsed };
	AdminService adminServiceUsed = { *repositoryUsed };
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 123, 456, "TrailerTest");
	std::vector<Movie>listOfMovies = userServiceUsed.userGetMovieList();
	assert(listOfMovies.size() == 1);
}
