import controller, repository, ui

def main():
    repo = repository.Repository()
    contr = controller.Controller(repo)
    view = ui.UI(contr, repo)

    view.menu()

# run the main function only if this module is executed as the main script
# (if you import this as a module then nothing is executed)
if __name__=="__main__":
    # call the main function
    main()

