using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;

//SqlCommand - ExecuteScalar, ExecuteReader, ExecuteNonQuery
//SqlDataAdapter - update row and eliminate row

namespace Lab_1
{
    class Program
    {
        static void Main(string[] args)
        {
            SqlConnection conn = new SqlConnection();
            conn.ConnectionString = "Data Source = PAULCOLTA060E\\SQLEXPRESS; Initial Catalog = it_company; " +
                " Integrated Security = SSPI";
            DataSet ds = new DataSet();
            SqlCommand cmd = new SqlCommand("SELECT * from Programmers", conn);
            SqlCommand countCmd = new SqlCommand("UPDATE Programmers SET phone = 123 WHERE phone is NULL", conn);
            SqlCommand readCmd = new SqlCommand("SELECT id, name, phone from Programmers WHERE id > 1", conn);

            conn.Open();

            string select = cmd.ExecuteScalar().ToString();
            string count = countCmd.ExecuteNonQuery().ToString();

            Console.WriteLine(select);
            Console.WriteLine(count);

            SqlDataReader reader = readCmd.ExecuteReader();
            while(reader.Read()) {
                Console.WriteLine("{0}, {1}, {2}", reader[0], reader[1], reader[2]);
            }
            reader.Close();


            conn.Close();

            Console.ReadLine();
        }
    }
}


/*
    SqlDataAdapter da = new SqlDataAdapter("SELECT * FROM Programmers", conn);
    SqlCommandBuilder cb = new SqlCommandBuilder(da);

    da.Fill(ds, "Programmers");

    foreach (DataRow dr in ds.Tables["Programmers"].Rows)
        Console.WriteLine("{0}, {1}", dr["id"], dr["name"]);

    DataRow drr = ds.Tables["Programmers"].NewRow();
    drr["name"] = "ccc";
    drr["email"] = "ccc";
    drr["birthday"] = "10-02-2020";
    ds.Tables["Programmers"].Rows.Add(drr);

    da.Update(ds, "Programmers");

    /*
    conn.Open();
    Console.WriteLine("Hello world!");
    conn.Close();
*/
