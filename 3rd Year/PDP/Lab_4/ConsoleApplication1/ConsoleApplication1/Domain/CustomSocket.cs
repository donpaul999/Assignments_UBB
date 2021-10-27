using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace ConsoleApplication1.Domain
{
    public class CustomSocket
    {
        public ManualResetEvent connectDone = new ManualResetEvent(false);
        public ManualResetEvent sendDone = new ManualResetEvent(false);
        public ManualResetEvent receiveDone = new ManualResetEvent(false);

        public Socket sock = null; // socket to connect to server

        public const int BUFF_SIZE = 1024; // 1024 bytes 

        public byte[] buffer = new byte[BUFF_SIZE]; 

        public StringBuilder responseContent = new StringBuilder();

        public int id; // keep a unique id for each custom socket
        public string hostname; 
        public string endpoint; 

        public IPEndPoint remoteEndPoint; // ip of website endpoint (address+port)
    }
}