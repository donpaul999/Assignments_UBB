using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using ConsoleApplication1.Domain;
using ConsoleApplication1.Internal;

namespace ConsoleApplication1.Implementation
{
    public class AsyncTaskImplementation : TaskImplementation
    {
        private static List<string> hosts;

        protected override async void StartClient(string host, int id)
        {
            var ipHostInfo = Dns.GetHostEntry(host.Split('/')[0]);
            var ipAddress = ipHostInfo.AddressList[0];
            var remoteEndpoint = new IPEndPoint(ipAddress, Parser.PORT);

            var client = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp); // create client socket

            var requestSocket = new CustomSocket
            {
                sock = client,
                hostname = host.Split('/')[0],
                endpoint = host.Contains("/") ? host.Substring(host.IndexOf("/", StringComparison.Ordinal)) : "/",
                remoteEndPoint = remoteEndpoint,
                id = id
            }; // state object

            await Connect(requestSocket); // connect to remote server

            await Send(requestSocket,
                Parser.GetRequestString(requestSocket.hostname, requestSocket.endpoint)); // request data from the server

            await Receive(requestSocket); // receive server response

            Console.WriteLine("Connection {0}: Content length is:{1}", requestSocket.id, Parser.GetContentLen(requestSocket.responseContent.ToString()));

            // release the socket
            client.Shutdown(SocketShutdown.Both);
            client.Close();
        }

        private static async Task Connect(CustomSocket state)
        {
            state.sock.BeginConnect(state.remoteEndPoint, ConnectCallback, state);

            await Task.FromResult<object>(state.connectDone.WaitOne()); // block until signaled
        }

        private static async Task Send(CustomSocket state, string data)
        {
            var byteData = Encoding.ASCII.GetBytes(data);

            // send data
            state.sock.BeginSend(byteData, 0, byteData.Length, 0, SendCallback, state);

            await Task.FromResult<object>(state.sendDone.WaitOne());
        }

        private static async Task Receive(CustomSocket state)
        {
            // receive data
            state.sock.BeginReceive(state.buffer, 0, CustomSocket.BUFF_SIZE, 0, ReceiveCallback, state);

            await Task.FromResult<object>(state.receiveDone.WaitOne());
        }
    }
}