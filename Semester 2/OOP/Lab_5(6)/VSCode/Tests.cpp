#include "Tests.h"
#include <iostream>
void Tests::runAllTests()
{	
	runMovieTests();
	runDynamicArrayTests();
	//runRepositoryTests();
	runAdminServiceTests();
	runUserServiceTests();
    runFileRepositoryTests();
}

void Tests::runMovieTests()
{
	Movie_AnyMovie_MovieCreated();
	setterGetterTitle_AnyMovie_CorrectTitle();
    setterGetterGenre_AnyMovie_CorrectGenre();
    setterGetterYearOfRelease_AnyMovie_CorrectYearOfRelease();
    setterGetterNumberOfLikes_AnyMovie_CorrectNumberOfLikes();
    setterGetterTrailer_AnyMovie_CorrectTrailer();
    explode_AnyString_CorrectTokenizedString();
    getOutputForm_AnyMovie_CorrectOutputForm();
    operatorOutput_AnyMovie_CorrectWrite();
    operatorInput_AnyMovie_CorrectRead();
    operatorDifferent_AnyMovies_DifferentMovies();
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
	 writeMoviesToFile_AnyMovie_AddSucessful();
     loadMoviesFromFile_AnyMovie_LoadSucessful();
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
     explode_AnyString_TokenizedString();
     changeRepositoryFileName_AnyFileName_FileNameChanged();
}

void Tests::runUserServiceTests()
{
	UserService_AnyUserService_UserServiceCreated();
	userGetMovieList_AnyUserService_CorrectMovies();
    userGetWatchList_AnyUserService_CorrectWatchList();
    listMoviesByGenre_NoMovieWithGenre_ReturnsMinusOne();
    listMoviesByGenre_ExistsMovieWithGenre_ReturnsOne();
    addMovieToWatchList_ExistsMovieInWatchLists_ReturnsMinusOne();
    addMovieToWatchList_MovieNotInWatchLists_ReturnsOne();
    addMovieToWatchListByTitle_NoMovieInMovieLists_ReturnsMinusOne();
    addMovieToWatchListByTitle_MovieInMovieLists_ReturnsOne();
    getWatchListLength_AnyUserService_CorrectLength();
    goToNextMovieByGenre_AnyUserService_CorrectPosition();
    goToNextMovieByGenre_AnyUserService_FirstPosition();
}

void Tests::runFileRepositoryTests(){
    FileRepository_AnyRepository_FileRepositoryCreated();
    writeMoviesToFile_AnyMovie_AddSucessfulFileRepository();
    loadMoviesFromFile_AnyMovie_LoadSucessfulFileRepository();
    addMovie_MovieNotInTheList_ReturnsOneFileRepository();
    addMovie_MovieInTheList_ReturnsMinusOneFileRepository();
    deleteMovie_MovieInTheList_ReturnsOneFileRepository();
    deleteMovie_MovieNotInTheList_ReturnsMinusOneFileRepository();
    update_MovieInTheList_ReturnsOneFileRepository();
    update_MovieNotInTheList_ReturnsMinusOneFileRepository();
    getMovieAtPosition_ValidPosition_ReturnsMovieFileRepository();
    getMoviesByGenre_ValidGenre_ReturnsListOfMovies();
    getMoviesByGenre_InvalidGenre_ReturnsEmptyList();
    getNumberOfMovies_AnyRepository_CorrectNumberOfMoviesFileRepository();
    getMovieAtPosition_InValidPosition_ReturnsExceptionFileRepository();
}

void Tests::Movie_AnyMovie_MovieCreated()
{
	Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	assert(movieUsed.getTitle() == "Test");
	assert(movieUsed.getGenre() == "CategoryTest");
	assert(movieUsed.getYearOfRelease() == 2015);
	assert(movieUsed.getNumberOfLikes() == 456);
	assert(movieUsed.getTrailer() == "www.TrailerTest");

}

void Tests::setterGetterTitle_AnyMovie_CorrectTitle(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    movieUsed.setTitle("Test2");
    assert(movieUsed.getTitle() == "Test2");
}
void Tests::setterGetterGenre_AnyMovie_CorrectGenre(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    movieUsed.setGenre("Test2");
    assert(movieUsed.getGenre() == "Test2");
}
void Tests::setterGetterYearOfRelease_AnyMovie_CorrectYearOfRelease(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    movieUsed.setYearOfRelease(2005);
    assert(movieUsed.getYearOfRelease() == 2005);
}
void Tests::setterGetterNumberOfLikes_AnyMovie_CorrectNumberOfLikes(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    movieUsed.setNumberOfLikes(2005);
    assert(movieUsed.getNumberOfLikes() == 2005);
}
void Tests::setterGetterTrailer_AnyMovie_CorrectTrailer(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    movieUsed.setTrailer("www.TrailerTest2");
    assert(movieUsed.getTrailer() == "www.TrailerTest2");
}

void Tests::explode_AnyString_CorrectTokenizedString(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    std::string stringUsed = "we,separate,string";
    std::vector<std::string> tokenizedString = movieUsed.explode(stringUsed, ",");
    assert(tokenizedString.size() == 3);
    assert(tokenizedString[0] == "we");
}

void Tests::getOutputForm_AnyMovie_CorrectOutputForm(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    std::string stringUsed = movieUsed.getOutputForm();
    assert(stringUsed == "Title: Test, Genre: CategoryTest, Year of Release: 2015, Likes: 456, Trailer: www.TrailerTest;");
}

void Tests::operatorInput_AnyMovie_CorrectRead() {
    std::ifstream fin("TestFileJops.txt");
    Movie movieUsed;
    fin >> movieUsed;
    assert(movieUsed.getTitle() == "Test");
    assert(movieUsed.getGenre() == "CategoryTest");
    assert(movieUsed.getYearOfRelease() == 2015);
    assert(movieUsed.getNumberOfLikes() == 456);
    assert(movieUsed.getTrailer() == "www.TrailerTest");
    fin.close();
}

void Tests::operatorOutput_AnyMovie_CorrectWrite(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    std::ofstream fout("TestFileJops.txt");
    fout << movieUsed;
    fout.close();
    std::ifstream fin("TestFileJops.txt");
    fin >> movieUsed;
    assert(movieUsed.getTitle() == "Test");
    assert(movieUsed.getGenre() == "CategoryTest");
    assert(movieUsed.getYearOfRelease() == 2015);
    assert(movieUsed.getNumberOfLikes() == 456);
    assert(movieUsed.getTrailer() == "www.TrailerTest");
    fin.close();
}

void Tests::operatorDifferent_AnyMovies_DifferentMovies(){
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    Movie secondMovieUsed = { "Test2", "CategoryTest", 2015, 456, "www.TrailerTest" };
    assert(movieUsed != secondMovieUsed);
}

void Tests::DynamicVector_AnyVector_VectorCreated()
{
	DynamicVector<int> vectorUsed{ 5 };
	assert(vectorUsed.capacity() == 5);

}

void Tests::resizeElementsList_AnyVector_VectorResized()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
    vectorUsed.append(2);
    vectorUsed.append(3);
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
	assert(vectorUsed.searchElementInList(5) == -1);
}

void Tests::append_ElementNotInList_ElementAppended()
{
	DynamicVector<int> vectorUsed{ 2 };
	int firstElementToAdd = 1, secondElementToAdd = 5;
	vectorUsed.append(firstElementToAdd);
	vectorUsed.append(secondElementToAdd);
	assert(vectorUsed.searchElementInList(5) == 1);
}

void Tests::append_AnyElement_ElementAppended()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	vectorUsed.append(1);
	assert(vectorUsed.size() == 2);
}

void Tests::remove_ElementInTheList_ElementRemoved()
{
	DynamicVector<int> vectorUsed{ 2 };
    vectorUsed.append(1);
    vectorUsed.append(2);
    vectorUsed.append(3);
	vectorUsed.remove(1);
	assert(vectorUsed.searchElementInList(1) == -1);

}

void Tests::remove_ElementNotInTheList_SizeRemainsTheSame()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
	vectorUsed.remove(2);
	assert(vectorUsed.size() == 1);
}

void Tests::update_ElementInTheList_ElementUpdated()
{
	DynamicVector<int> vectorUsed{ 2 };
	vectorUsed.append(1);
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
	DynamicVector<int> secondVectorUsed{ 1 };
	secondVectorUsed = firstVectorUsed;
	assert(secondVectorUsed.searchElementInList(2) == 1);
}

void Tests::Repository_AnyRepository_RepositoryCreated()
{
	Repository repositoryUsed{"TestFileJops.txt"};
}

void Tests::writeMoviesToFile_AnyMovie_AddSucessful()
{
	Repository repositoryUsed{"TestFileJops.txt"};
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed.addMovie(movieUsed);
	repositoryUsed.writeMoviesToFile();
    repositoryUsed.loadMoviesFromFile();
    assert(repositoryUsed.getNumberOfMovies() == 1);
}

void Tests::loadMoviesFromFile_AnyMovie_LoadSucessful()
{
	Repository repositoryUsed{"TestFileJops.txt"};
	repositoryUsed.loadMoviesFromFile();
	assert(repositoryUsed.getNumberOfMovies() == 1);
}


void Tests::addMovie_MovieNotInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	assert(repositoryUsed->addMovie(movieUsed) == 1);
}

void Tests::addMovie_MovieInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	try {
        repositoryUsed->addMovie(movieUsed);
        assert(false);
    }
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::deleteMovie_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->deleteMovie(movieUsed) == 1);
}

void Tests::deleteMovie_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	try{
	    assert(repositoryUsed->deleteMovie(movieUsed) == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::update_MovieInTheList_ReturnsOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->updateMovie(movieUsed) == 1);
}

void Tests::update_MovieNotInTheList_ReturnsMinusOne()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	try{
	    assert(repositoryUsed->updateMovie(movieUsed) == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::getMovieAtPosition_ValidPosition_ReturnsMovie()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->getMovieAtPosition(0) == movieUsed);
}

void Tests::getMovieAtPosition_InValidPosition_ReturnsException()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	try {
		Movie movieUsed = repositoryUsed->getMovieAtPosition(-1);
	}
	catch (std::exception Exception) {
		assert(true);
	}
}

void Tests::getNumberOfMovies_AnyRepository_CorrectNumberOfMovies()
{
	Repository* repositoryUsed = new Repository();
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed->addMovie(movieUsed);
	assert(repositoryUsed->getNumberOfMovies() == 1);
}

void Tests::AdminService_AnyAdminService_AdminServiceCreated(){
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
    std::vector<Movie> movieList = {};
    repositoryUsed.writeMoviesToFile(movieList, "inputFile.in");
}

void Tests::adminAddMovie_MovieNotInTheList_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
    assert(adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest") == 1);
    adminServiceUsed.adminDeleteMovie("Test");
}

void Tests::adminAddMovie_MovieInTheList_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	try{
	    assert(adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest") == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
	adminServiceUsed.adminDeleteMovie("Test");
}

void Tests::explode_AnyString_TokenizedString()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
    std::string stringUsed = "we,separate,string";
    std::vector<std::string> tokenizedString = adminServiceUsed.explode(stringUsed, ',');
    assert(tokenizedString.size() == 3);
    assert(tokenizedString[0] == "we");
}

void Tests::changeRepositoryFileName_AnyFileName_FileNameChanged()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
    assert(adminServiceUsed.changeRepositoryFileName("newTextFile.txt") == 1);
}

void Tests::adminDeleteMovie_MovieInTheList_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	assert(adminServiceUsed.adminDeleteMovie("Test") == 1);
}

void Tests::adminDeleteMovie_MovieNotInTheList_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	try{
	    assert(adminServiceUsed.adminDeleteMovie("Test") == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::adminUpdate_MovieInTheList_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	assert(adminServiceUsed.adminUpdateMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest") == 1);
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::adminUpdate_MovieNotInTheList_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	try{
	    assert(adminServiceUsed.adminUpdateMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest") == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::adminGetMovieList_AnyAdminService_CorrectMovies()
{
    FileRepository repositoryUsed{"inputFile.in"};
    AdminService adminServiceUsed{ repositoryUsed };
	adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	std::vector<Movie>listOfMovies = adminServiceUsed.adminGetMovieList();
	assert(listOfMovies.size() == 1);
    adminServiceUsed.adminDeleteMovie("Test");
}

void Tests::UserService_AnyUserService_UserServiceCreated()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
}

void Tests::userGetMovieList_AnyUserService_CorrectMovies()
{
    FileRepository repositoryUsed{"inputFile.in"};
	UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed{ repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	std::vector<Movie>listOfMovies = userServiceUsed.userGetMovieList();
	assert(listOfMovies.size() == 1);
    adminServiceUsed.adminDeleteMovie("Test");
}

void Tests::userGetWatchList_AnyUserService_CorrectWatchList()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
	AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	userServiceUsed.listMoviesByGenre("");
	userServiceUsed.addMovieToWatchList();
	std::vector<Movie>listOfMovies = userServiceUsed.userGetWatchList();
	assert(listOfMovies.size() == 1);
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::listMoviesByGenre_NoMovieWithGenre_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	try {
        userServiceUsed.listMoviesByGenre("Comedy");
        assert(false);
    }
    catch(std::exception& exception){
        assert(true);
    }
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::listMoviesByGenre_ExistsMovieWithGenre_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	assert(userServiceUsed.listMoviesByGenre("CategoryTest") == 1);
    adminServiceUsed.adminDeleteMovie("Test");


}

void Tests::addMovieToWatchList_ExistsMovieInWatchLists_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	userServiceUsed.listMoviesByGenre("CategoryTest");
	userServiceUsed.addMovieToWatchList();
	try{
	    assert(userServiceUsed.addMovieToWatchList() == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::addMovieToWatchList_MovieNotInWatchLists_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	userServiceUsed.listMoviesByGenre("CategoryTest");
	assert(userServiceUsed.addMovieToWatchList() == 1);
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::addMovieToWatchListByTitle_NoMovieInMovieLists_ReturnsMinusOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	try{
	    assert(userServiceUsed.addMovieToWatchListByTitle("Hangover") == -1);
	}
    catch(std::exception& exception){
        assert(true);
    }
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::addMovieToWatchListByTitle_MovieInMovieLists_ReturnsOne()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	assert(userServiceUsed.addMovieToWatchListByTitle("Test") == 1);
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::getWatchListLength_AnyUserService_CorrectLength()
{
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
	userServiceUsed.addMovieToWatchListByTitle("Test");
	assert(userServiceUsed.getWatchListLength() == 1);
    adminServiceUsed.adminDeleteMovie("Test");

}

void Tests::goToNextMovieByGenre_AnyUserService_CorrectPosition(){
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
    adminServiceUsed.adminAddMovie("Test2", "CategoryTest", 2015, 456, "www.TrailerTest");
    adminServiceUsed.adminAddMovie("Test3", "CategoryTest", 2015, 456, "www.TrailerTest");
    userServiceUsed.listMoviesByGenre("CategoryTest");
    userServiceUsed.goToNextMovieByGenre();
    Movie movieUsed = userServiceUsed.getCurrentMovie();
    assert(movieUsed.getTitle() == "Test2");
    adminServiceUsed.adminDeleteMovie("Test");
    adminServiceUsed.adminDeleteMovie("Test2");
    adminServiceUsed.adminDeleteMovie("Test3");

}

void Tests::goToNextMovieByGenre_AnyUserService_FirstPosition(){
    FileRepository repositoryUsed{"inputFile.in"};
    UserService userServiceUsed {repositoryUsed };
    AdminService adminServiceUsed { repositoryUsed };
    adminServiceUsed.adminAddMovie("Test", "CategoryTest", 2015, 456, "www.TrailerTest");
    adminServiceUsed.adminAddMovie("Test2", "CategoryTest", 2015, 456, "www.TrailerTest");
    adminServiceUsed.adminAddMovie("Test3", "CategoryTest", 2015, 456, "www.TrailerTest");
    userServiceUsed.listMoviesByGenre("CategoryTest");
    userServiceUsed.goToNextMovieByGenre();
    userServiceUsed.goToNextMovieByGenre();
    userServiceUsed.goToNextMovieByGenre();
    Movie movieUsed = userServiceUsed.getCurrentMovie();
    assert(movieUsed.getTitle() == "Test");
    adminServiceUsed.adminDeleteMovie("Test2");
    adminServiceUsed.adminDeleteMovie("Test");
    adminServiceUsed.adminDeleteMovie("Test3");
}


void Tests::FileRepository_AnyRepository_FileRepositoryCreated()
{
    FileRepository repositoryUsed{"inputFile.in"};
}

void Tests::writeMoviesToFile_AnyMovie_AddSucessfulFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    std::vector<Movie> movieList;
    movieList.push_back(movieUsed);
    repositoryUsed.writeMoviesToFile(movieList, "inputFile.in");
    assert(repositoryUsed.getNumberOfMovies() == 1);
}

void Tests::loadMoviesFromFile_AnyMovie_LoadSucessfulFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    std::vector<Movie> movieList = repositoryUsed.loadMoviesFromFile();
    assert(movieList.size() == 1);
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed.deleteMovie(movieUsed);
}


void Tests::addMovie_MovieNotInTheList_ReturnsOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.txt"};
    repositoryUsed.changeFileName("testFile.txt");
    Movie movieUsed = { "Test1", "CategoryTest", 2015, 456, "www.TrailerTest" };
    assert(repositoryUsed.addMovie(movieUsed) == 1);
	repositoryUsed.deleteMovie(movieUsed);
}

void Tests::addMovie_MovieInTheList_ReturnsMinusOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test2", "CategoryTest", 2015, 456, "www.TrailerTest" };
	repositoryUsed.addMovie(movieUsed);
    try{
        assert(repositoryUsed.addMovie(movieUsed) == -1);
    }
    catch(std::exception& exception){
        assert(true);
    }
	repositoryUsed.deleteMovie(movieUsed);
}

void Tests::deleteMovie_MovieInTheList_ReturnsOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test3", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    assert(repositoryUsed.deleteMovie(movieUsed) == 1);
}

void Tests::deleteMovie_MovieNotInTheList_ReturnsMinusOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test4", "CategoryTest", 2015, 456, "www.TrailerTest" };
    try{
        assert(repositoryUsed.deleteMovie(movieUsed) == -1);
    }
    catch(std::exception& exception){
        assert(true);
    }
}

void Tests::update_MovieInTheList_ReturnsOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    assert(repositoryUsed.updateMovie(movieUsed) == 1);
	repositoryUsed.deleteMovie(movieUsed);
}

void Tests::update_MovieNotInTheList_ReturnsMinusOneFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test5", "CategoryTest", 2015, 456, "www.TrailerTest" };
    try{
        assert(repositoryUsed.updateMovie(movieUsed) == -1);
    }
    catch(std::exception& exception){
        assert(true);
    }

}

void Tests::getMovieAtPosition_ValidPosition_ReturnsMovieFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test6", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    assert(repositoryUsed.getMovieAtPosition(0) == movieUsed);
    repositoryUsed.deleteMovie(movieUsed);
}

void Tests::getMovieAtPosition_InValidPosition_ReturnsExceptionFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test7", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    try {
        Movie movieUsed = repositoryUsed.getMovieAtPosition(-1);
    }
    catch (std::exception Exception) {
        assert(true);
    }
}

void Tests::getNumberOfMovies_AnyRepository_CorrectNumberOfMoviesFileRepository()
{
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test8", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    assert(repositoryUsed.getNumberOfMovies() == 1);
    repositoryUsed.deleteMovie(movieUsed);
}
void Tests::getMoviesByGenre_ValidGenre_ReturnsListOfMovies(){
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    std::vector<Movie> movieList = repositoryUsed.getMoviesByGenre("CategoryTest");
    assert(movieList.size() == 1);
    repositoryUsed.deleteMovie(movieUsed);

}
void Tests::getMoviesByGenre_InvalidGenre_ReturnsEmptyList(){
    FileRepository repositoryUsed{"inputFile.in"};
    Movie movieUsed = { "Test", "CategoryTest", 2015, 456, "www.TrailerTest" };
    repositoryUsed.addMovie(movieUsed);
    std::vector<Movie> movieList = repositoryUsed.getMoviesByGenre("Test");
    assert(movieList.size() == 0);
    repositoryUsed.deleteMovie(movieUsed);
}