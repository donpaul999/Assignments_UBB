#pragma once
#include "UI.h"// I DON'T TEST UI - I don't have to add every library
#include <cassert>
#include <fstream>
#include "DynamicVector.h"
class Tests
{
public:
	void runAllTests();
private:
	void runMovieTests();
	void runDynamicArrayTests();
	void runRepositoryTests();
	void runAdminServiceTests();
	void runUserServiceTests();

	void Movie_AnyMovie_MovieCreated();
    void setterGetterTitle_AnyMovie_CorrectTitle();
    void setterGetterGenre_AnyMovie_CorrectGenre();
    void setterGetterYearOfRelease_AnyMovie_CorrectYearOfRelease();
    void setterGetterNumberOfLikes_AnyMovie_CorrectNumberOfLikes();
    void setterGetterTrailer_AnyMovie_CorrectTrailer();
    void explode_AnyString_CorrectTokenizedString();
    void getOutputForm_AnyMovie_CorrectOutputForm();
    void operatorInput_AnyMovie_CorrectRead();
    void operatorOutput_AnyMovie_CorrectWrite();
    void operatorDifferent_AnyMovies_DifferentMovies();

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
	void writeMoviesToFile_AnyMovie_AddSucessful();
	void loadMoviesFromFile_AnyMovie_LoadSucessful();
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
    void explode_AnyString_TokenizedString();
    void changeRepositoryFileName_AnyFileName_FileNameChanged();

	void UserService_AnyUserService_UserServiceCreated();
	void userGetMovieList_AnyUserService_CorrectMovies();
	void userGetWatchList_AnyUserService_CorrectWatchList();
	void listMoviesByGenre_NoMovieWithGenre_ReturnsMinusOne();
    void listMoviesByGenre_ExistsMovieWithGenre_ReturnsOne();
    void addMovieToWatchList_ExistsMovieInWatchLists_ReturnsMinusOne();
    void addMovieToWatchList_MovieNotInWatchLists_ReturnsOne();
    void addMovieToWatchListByTitle_NoMovieInMovieLists_ReturnsMinusOne();
    void addMovieToWatchListByTitle_MovieInMovieLists_ReturnsOne();
    void getWatchListLength_AnyUserService_CorrectLength();
    void goToNextMovieByGenre_AnyUserService_CorrectPosition();
    void goToNextMovieByGenre_AnyUserService_FirstPosition();
};

