using System;
using System.Net.WebSockets;
using System.Text.Json;
using System.Threading;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using JsonSerializer = System.Text.Json.JsonSerializer;

namespace BookABook.BookUpdates
{
    public class SocketConnectionHandler
    {
        public bool IsConnected { get; private set; }

        private byte[] buffer = new byte[0];
        private readonly WebSocket webSocket;
        private int? connectionTimeoutMilliseconds;

        public SocketConnectionHandler(WebSocket webSocket, int? connectionTimeoutMilliseconds = null)
        {
            IsConnected = true;
            this.webSocket = webSocket;
            this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
        }

        public async Task<bool> CheckClientConnection(int? timeoutMilliseconds = null)
        {
            var response = await Receive(timeoutMilliseconds);

            if (response?.CloseStatus != null)
                await Close(response);

            return IsConnected;
        }

        public async Task<WebSocketReceiveResult> Receive(int? timeoutMilliseconds = null)
        {
            var cancellationToken = GetCancellationToken(timeoutMilliseconds);

            try
            {
                return await webSocket.ReceiveAsync(new ArraySegment<byte>(buffer), cancellationToken);
            }
            catch (Exception)
            {
                await Close();

                return null;
            }
        }

        public async Task Send(object obj)
        {
            var parserSettings = new JsonSerializerOptions();
            parserSettings.PropertyNamingPolicy = JsonNamingPolicy.CamelCase;
            var encodedObject = new ArraySegment<byte>(JsonSerializer.SerializeToUtf8Bytes(obj, parserSettings));
            
            try
            {
                await webSocket.SendAsync(
                    encodedObject,
                    WebSocketMessageType.Text,
                    true,
                    CancellationToken.None);
            }
            catch (Exception)
            {
                await Close();
            }
        }

        public async Task Close(WebSocketReceiveResult receiveResult = null)
        {
            IsConnected = false;

            var closeStatus = receiveResult?.CloseStatus.Value ??
                WebSocketCloseStatus.NormalClosure;
            var closeDescription = receiveResult?.CloseStatusDescription ??
                WebSocketCloseStatus.NormalClosure.ToString();

            try
            {
                await webSocket.CloseAsync(closeStatus, closeDescription, CancellationToken.None);
            }
            catch (Exception)
            {
            }
        }

        public async Task WaitUntilClosed()
        {
            while (IsConnected)
                await Task.Delay(5000);
        }

        private CancellationToken GetCancellationToken(int? timeoutMilliseconds)
        {
            if (timeoutMilliseconds != null)
                return new CancellationTokenSource((int)timeoutMilliseconds).Token;

            if (connectionTimeoutMilliseconds != null)
                return new CancellationTokenSource((int)connectionTimeoutMilliseconds).Token;

            return CancellationToken.None;
        }
    }
}