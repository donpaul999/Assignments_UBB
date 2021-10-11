using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Threading.Tasks;

namespace BookABook.BookUpdates
{
    public class BroadcastHandler<T> : IBroadcastHandler<T>
    {
        private const int CONNECTION_TIMEOUT_MILLISECONDS = 5000;

        private readonly List<SocketConnectionHandler> socketConnections = new();

        public Task AddConnection(WebSocket webSocket)
        {
            var handler = new SocketConnectionHandler(webSocket);

            socketConnections.Add(handler);

            return handler.WaitUntilClosed();
        }

        public Task Broadcast(T obj)
        {
            return Task.WhenAll(socketConnections.Select(socket =>
                HandleBroadcastForSocket(obj, socket)));
        }

        private async Task HandleBroadcastForSocket(T obj, SocketConnectionHandler socket)
        {
            await socket.Send(obj);

            if (!await socket.CheckClientConnection(CONNECTION_TIMEOUT_MILLISECONDS))
                socketConnections.Remove(socket);
        }
    }
}