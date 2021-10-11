import axios from 'axios';
import { getLogger } from '../core';
import { BookProps } from './BookProps';

const log = getLogger('itemApi');

const baseUrl = 'localhost:5000';
const bookUrl = `http://${baseUrl}/books`;

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
    'Content-Type': 'application/json',
    "Access-Control-Allow-Origin" : "*",
    "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
  }
};

export const getBooks: () => Promise<BookProps[]> = () => {
  return withLogs(axios.get(bookUrl, config), 'getItems');
}

export const createBook: (item: BookProps) => Promise<BookProps[]> = item => {
  return withLogs(axios.post(bookUrl, item, config), 'createItem');
}

export const updateBook: (item: BookProps) => Promise<BookProps[]> = item => {
  return withLogs(axios.put(`${bookUrl}/${item.id}`, item, config), 'updateItem');
}

interface MessageData {
  event: string;
  payload: {
    book: BookProps;
  };
}

export const newWebSocket = (onMessage: (data: MessageData) => void) => {
  const ws = new WebSocket(`ws://${baseUrl}`)
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
    onMessage(JSON.parse(messageEvent.data));
  };
  return () => {
    ws.close();
  }
}
