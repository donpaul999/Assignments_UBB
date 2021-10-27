using System.Linq;
using ConsoleApplication1.Implementation;

namespace ConsoleApplication1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            var hosts = new string[] { "www.cs.ubbcluj.ro/~rlupsa/edu/pdp/", "www.cs.ubbcluj.ro/~ilazar/ma/", "www.cs.ubbcluj.ro/~forest", "www.cs.ubbcluj.ro/~bradut" }.ToList();
            var task = new AsyncTaskImplementation();
            task.run(hosts);
        }
    }
}