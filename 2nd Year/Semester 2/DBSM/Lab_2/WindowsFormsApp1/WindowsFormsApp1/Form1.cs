using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        SqlConnection connection;
        SqlDataAdapter daHoliday, daProgrammer;
        DataSet ds;
        SqlCommandBuilder cb;
        SqlCommand cmd;

        BindingSource bsHoliday, bsProgrammer;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            daHoliday.Update(ds, "Holidays");
        }
        private void button1_Click_1(object sender, EventArgs e)
        {
            connection = new SqlConnection(@"Data Source = PAULCOLTA060E\SQLEXPRESS; Initial Catalog = it_company; Integrated Security = SSPI");
            ds = new DataSet();
            daHoliday = new SqlDataAdapter("select * from Holidays", connection);
            daProgrammer = new SqlDataAdapter("select * from Programmers", connection);
            cb = new SqlCommandBuilder(daHoliday);

            daHoliday.Fill(ds, "Holidays");
            daProgrammer.Fill(ds, "Programmers");

            DataRelation dr = new DataRelation("Holidays_Programmers_id_fk",
                ds.Tables["Programmers"].Columns["id"], ds.Tables["Holidays"].Columns["programmer_id"]);
            ds.Relations.Add(dr);


            bsHoliday = new BindingSource();
            bsProgrammer = new BindingSource();

            bsProgrammer.DataSource = ds;
            bsProgrammer.DataMember = "Programmers";

            bsHoliday.DataSource = bsProgrammer;
            bsHoliday.DataMember = "Holidays_Programmers_id_fk";

            GridHoliday.DataSource = bsHoliday;
            GridProgrammer.DataSource = bsProgrammer;
        }

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        /*
        private void button3_Click(object sender, EventArgs e)
        {
            connection.Open();
            cmd = new SqlCommand("delete from Holidays where id=" + GridHoliday.SelectedRows[0].Cells[0].Value, connection);
            cmd.ExecuteNonQuery();
            connection.Close();
            daHoliday.Update(ds, "Holidays");
        }
        
        */
    }
}
