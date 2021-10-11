import React, { useCallback, useEffect, useReducer } from 'react';
import PropTypes from 'prop-types';
import { getLogger } from '../core';
import { BookProps } from './BookProps';
import { createBook, getBooks, newWebSocket, updateBook } from './bookApi';

const log = getLogger('BookProvider');

type SaveBookFn = (book: BookProps) => Promise<any>;

export interface BookState {
  books?: BookProps[],
  fetching: boolean,
  fetchingError?: Error | null,
  saving: boolean,
  savingError?: Error | null,
  saveBook?: SaveBookFn,
}

interface ActionProps {
  type: string,
  payload?: any,
}

const initialState: BookState = {
  fetching: false,
  saving: false,
};

const FETCH_ITEMS_STARTED = 'FETCH_ITEMS_STARTED';
const FETCH_ITEMS_SUCCEEDED = 'FETCH_ITEMS_SUCCEEDED';
const FETCH_ITEMS_FAILED = 'FETCH_ITEMS_FAILED';
const SAVE_ITEM_STARTED = 'SAVE_ITEM_STARTED';
const SAVE_ITEM_SUCCEEDED = 'SAVE_ITEM_SUCCEEDED';
const SAVE_ITEM_FAILED = 'SAVE_ITEM_FAILED';

const reducer: (state: BookState, action: ActionProps) => BookState =
  (state, { type, payload }) => {
    switch (type) {
      case FETCH_ITEMS_STARTED:
        return { ...state, fetching: true, fetchingError: null };
      case FETCH_ITEMS_SUCCEEDED:
        return { ...state, books: payload.books, fetching: false };
      case FETCH_ITEMS_FAILED:
        return { ...state, fetchingError: payload.error, fetching: false };
      case SAVE_ITEM_STARTED:
        return { ...state, savingError: null, saving: true };
      case SAVE_ITEM_SUCCEEDED:
        const books = [...(state.books || [])];
        log(books);
        const item = payload.item;
        const index = books.findIndex(it => it.id === item.id);
        if (index === -1) {
          books.splice(0, 0, item);
        } else {
          books[index] = item;
        }
        return { ...state, books, saving: false };
      case SAVE_ITEM_FAILED:
        return { ...state, savingError: payload.error, saving: false };
      default:
        return state;
    }
  };

export const ItemContext = React.createContext<BookState>(initialState);

interface ItemProviderProps {
  children: PropTypes.ReactNodeLike,
}

export const BookProvider: React.FC<ItemProviderProps> = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);
  const { books, fetching, fetchingError, saving, savingError } = state;
  useEffect(getItemsEffect, []);
  useEffect(wsEffect, []);
  const saveBook = useCallback<SaveBookFn>(saveBookCallback, []);
  const value = { books, fetching, fetchingError, saving, savingError, saveBook };
  log('returns');
  return (
    <ItemContext.Provider value={value}>
      {children}
    </ItemContext.Provider>
  );

  function getItemsEffect() {
    let canceled = false;
    fetchItems();
    return () => {
      canceled = true;
    }

    async function fetchItems() {
      try {
        log('fetchItems started');
        dispatch({ type: FETCH_ITEMS_STARTED });
        const books = await getBooks();
        //log(books);
        log('fetchItems succeeded');
        if (!canceled) {
          dispatch({ type: FETCH_ITEMS_SUCCEEDED, payload: { books } });
        }
      } catch (error) {
        log('fetchItems failed');
        dispatch({ type: FETCH_ITEMS_FAILED, payload: { error } });
      }
    }
  }

  async function saveBookCallback(item: BookProps) {
    try {
      log('saveBook started');
      dispatch({ type: SAVE_ITEM_STARTED });
      const savedItem = await (item.id ? updateBook(item) : createBook(item));
      log('saveBook succeeded');
      dispatch({ type: SAVE_ITEM_SUCCEEDED, payload: { item: savedItem } });
    } catch (error) {
      log('saveBook failed');
      dispatch({ type: SAVE_ITEM_FAILED, payload: { error } });
    }
  }

  function wsEffect() {
    let canceled = false;
    log('wsEffect - connecting');
    const closeWebSocket = newWebSocket(message => {
      if (canceled) {
        return;
      }
      const { event, payload: { book }} = message;
      log(`ws message, item ${event}`);
      if (event === 'created' || event === 'updated') {
        dispatch({ type: SAVE_ITEM_SUCCEEDED, payload: { book } });
      }
    });
    return () => {
      log('wsEffect - disconnecting');
      canceled = true;
      closeWebSocket();
    }
  }
};
