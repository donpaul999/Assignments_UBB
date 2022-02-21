import axios from 'axios';
import { getLogger } from '../core';
import { BookProps } from './BookProps';

const log = getLogger('itemApi');

const baseUrl = 'localhost:5000';
const bookUrl = `https://${baseUrl}/api/Book`;

interface ResponseProps<T> {
  data: T;
}

function withLogs<T>(promise: Promise<ResponseProps<T>>, fnName: string): Promise<T> {
  log(`${fnName} - started`);
  return promise
    .then(res => {
      log(`${fnName} - succeeded`);
      return Promise.resolve(res.data);
    })
    .catch(err => {
      log(`${fnName} - failed`);
      return Promise.reject(err);
    });
}

const config = {
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin" : "*"
  }
};

export const getBooks: () => Promise<BookProps[]> = () => {
  return withLogs(axios.get(bookUrl, config), 'getBooks');
}

export const getBooksCount: (start:number, count: number) => Promise<BookProps[]> = (start, count) => {
  return withLogs(axios.get(`${bookUrl}/count/${start}/${count}`, config), 'getBooks');
}

export const createBook: (item: BookProps) => Promise<BookProps[]> = item => {
  return withLogs(axios.post(bookUrl, item, config), 'createBook');
}

export const updateBook: (item: BookProps) => Promise<BookProps[]> = item => {
  return withLogs(axios.put(`${bookUrl}/${item.id}`, item, config), 'updateBook');
}

export const removeBook: (item: BookProps) => Promise<BookProps[]> = item => {
  return withLogs(axios.delete(`${bookUrl}/${item.id}`, config), 'deleteBook');
}

interface MessageData {
  event: string;
  payload: {
    book: BookProps;
  };
}

export const newWebSocket = (onMessage: (data: MessageData) => void) => {
  const ws = new WebSocket(`wss://${baseUrl}/api/Book/updates`)
  ws.onopen = () => {
    log('web socket onopen');
  };
  ws.onclose = () => {
    log('web socket onclose');
  };
  ws.onerror = error => {
    log('web socket onerror', error);
  };
  ws.onmessage = messageEvent => {
    log('web socket onmessage');
    ws.send("");
    onMessage(JSON.parse(messageEvent.data));
  };
  return () => {
    ws.close();
  }
}
