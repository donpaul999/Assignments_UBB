#pragma once
#include <cassert>
#include "UI.h" // I DON'T TEST UI - I don't have to add every library
class Tests
{
public:
	void runAllTests();
	void runMovieTests();
	void runDynamicArrayTests();
	void runRepositoryTests();
	void runAdminServiceTests();
	void runUserServiceTests();

	void Movie_AnyMovie_MovieCreated();
	//Setters & Getters = trivial tests 

	void DynamicVector_AnyVector_VectorCreated();
	void resizeElementsList_AnyVector_VectorResized();
	void searchElementInList_ElementInList_ReturnsTrue();
	void searchElementInList_ElementNotInList_ReturnsFalse();
	void append_ElementNotInList_ElementAppended();
	void append_AnyElement_ElementAppended();
	void remove_ElementInTheList_ElementRemoved();
	void remove_ElementNotInTheList_SizeRemainsTheSame();
	void update_ElementInTheList_ElementUpdated();
	void operatorPosition_ValidPosition_ElementReturned();
	void operatorPosition_InvalidPosition_ExceptionReturned();
	void size_AnyVector_CorrectSize();
	void operatorEqual_AnyVector_CorrectAssignment();

	void Repository_AnyRepository_RepositoryCreated();
	void addMovie_MovieNotInTheList_ReturnsOne();
	void addMovie_MovieInTheList_ReturnsMinusOne();
	void deleteMovie_MovieInTheList_ReturnsOne();
	void deleteMovie_MovieNotInTheList_ReturnsMinusOne();
	void update_MovieInTheList_ReturnsOne();
	void update_MovieNotInTheList_ReturnsMinusOne();
	void getMovieAtPosition_ValidPosition_ReturnsMovie();
	void getMovieAtPosition_InValidPosition_ReturnsException();
	void getNumberOfMovies_AnyRepository_CorrectNumberOfMovies();
	
	void AdminService_AnyAdminService_AdminServiceCreated();
	void adminAddMovie_MovieNotInTheList_ReturnsOne();
	void adminAddMovie_MovieInTheList_ReturnsMinusOne();
	void adminDeleteMovie_MovieInTheList_ReturnsOne();
	void adminDeleteMovie_MovieNotInTheList_ReturnsMinusOne();
	void adminUpdate_MovieInTheList_ReturnsOne();
	void adminUpdate_MovieNotInTheList_ReturnsMinusOne();
	void adminGetMovieList_AnyAdminService_CorrectMovies();

	void UserService_AnyUserService_UserServiceCreated();
	void userGetMovieList_AnyUserService_CorrectMovies();

};

