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
using System.Configuration;
using System.Collections.Specialized;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        String firstTable, secondTable, foreignKey, firstTablePK, secondTableFK;
        SqlConnection connection;
        SqlDataAdapter daSecondTable, daFirstTable;
        DataSet ds;
        SqlCommandBuilder cb;
        SqlCommand cmd;

        BindingSource bsSecondTable, bsFirstTable;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            firstTable = ConfigurationManager.AppSettings.Get("FirstTable");
            secondTable = ConfigurationManager.AppSettings.Get("SecondTable");
            foreignKey = ConfigurationManager.AppSettings.Get("ForeignKey");
            firstTablePK = ConfigurationManager.AppSettings.Get("FirstTablePK");
            secondTableFK = ConfigurationManager.AppSettings.Get("SecondTableFK");

            label1.Text = firstTable;
            label2.Text = secondTable;

            connection = new SqlConnection(@"Data Source = PAULCOLTA060E\SQLEXPRESS; Initial Catalog = it_company; Integrated Security = SSPI");
            ds = new DataSet();
            daSecondTable = new SqlDataAdapter("select * from " + secondTable, connection);
            daFirstTable = new SqlDataAdapter("select * from " + firstTable, connection);
            cb = new SqlCommandBuilder(daSecondTable);

            daSecondTable.Fill(ds, secondTable);
            daFirstTable.Fill(ds, firstTable);

            DataRelation dr = new DataRelation(foreignKey,
                ds.Tables[firstTable].Columns[firstTablePK], ds.Tables[secondTable].Columns[secondTableFK]);
            ds.Relations.Add(dr);


            bsSecondTable = new BindingSource();
            bsFirstTable = new BindingSource();

            bsFirstTable.DataSource = ds;
            bsFirstTable.DataMember = firstTable;

            bsSecondTable.DataSource = bsFirstTable;
            bsSecondTable.DataMember = foreignKey;

            GridHoliday.DataSource = bsSecondTable;
            GridProgrammer.DataSource = bsFirstTable;
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            daSecondTable.Update(ds, secondTable);
        }

        private void GridProgrammer_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            connection = new SqlConnection(@"Data Source = PAULCOLTA060E\SQLEXPRESS; Initial Catalog = it_company; Integrated Security = SSPI");
            ds = new DataSet();
            daSecondTable = new SqlDataAdapter("select * from " + secondTable, connection);
            daFirstTable = new SqlDataAdapter("select * from " + firstTable, connection);
            cb = new SqlCommandBuilder(daSecondTable);

            daSecondTable.Fill(ds, secondTable);
            daFirstTable.Fill(ds, firstTable);

            DataRelation dr = new DataRelation(foreignKey,
                ds.Tables[firstTable].Columns[firstTablePK], ds.Tables[secondTable].Columns[secondTableFK]);
            ds.Relations.Add(dr);


            bsSecondTable = new BindingSource();
            bsFirstTable = new BindingSource();

            bsFirstTable.DataSource = ds;
            bsFirstTable.DataMember = firstTable;

            bsSecondTable.DataSource = bsFirstTable;
            bsSecondTable.DataMember = foreignKey;

            GridHoliday.DataSource = bsSecondTable;
            GridProgrammer.DataSource = bsFirstTable;
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
