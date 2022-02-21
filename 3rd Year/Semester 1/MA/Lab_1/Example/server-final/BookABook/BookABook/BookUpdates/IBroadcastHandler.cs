using System.Net.WebSockets;
using System.Threading.Tasks;

namespace BookABook.BookUpdates
{
    public interface IBroadcastHandler<T>
    {
        Task AddConnection(WebSocket webSocket);

        void Broadcast(T obj);
    }
}