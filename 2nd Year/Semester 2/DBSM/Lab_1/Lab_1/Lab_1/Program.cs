using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
namespace Lab_1
{
    class Program
    {
        static void Main(string[] args)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = "Data Source = PAULCOLTA060E\\SQLEXPRESS; Initial Catalog = it_company; " +
                " Integrated Security = SSPI";
        }
    }
}
