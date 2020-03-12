#include "Command.h"
#pragma once

Command* createCommand(Operation* undo, Operation* redo)
{
    Command* command = (Command*)malloc(sizeof(Command));
    command->undo = undo;
    command->redo = redo;
    return command;
}

void destroyCommand(Command* command)
{
    destroyOperation(command->undo);
    destroyOperation(command->redo);
    free(command);
}

Operation* getUndoOperation(Command* command)
{
    return command->undo;
}

Operation* getRedoOperation(Command* command)
{
    return command->redo;
}
