#pragma once
#include "Operation.h"
typedef struct {
    Operation* redo;
    Operation* undo;
}Command;

Command* createCommand(Operation* undo, Operation* redo);
void destroyCommand(Command* command);
Operation* getUndoOperation(Command* command);
Operation* getRedoOperation(Command* command);
