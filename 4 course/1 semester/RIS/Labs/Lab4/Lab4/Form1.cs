using System;
using System.Windows.Forms;

namespace Lab4
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            salesTableAdapter.Fill(this.warehouseDataSet.Sales);

        }

        private void radGridView1_Click(object sender, EventArgs e)
        {

        }

        private void saveBtn_Click(object sender, EventArgs e)
        {
            warehouseDataSet.Sales.AcceptChanges();
        }

        private void cancelBtn_Click(object sender, EventArgs e)
        {
            warehouseDataSet.Sales.RejectChanges();
        }
    }
}
